package io.github.service.impl;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import io.github.dto.EmailBodyDTO;
import io.github.dto.EmailDTO;
import io.github.dto.SiteVerifyDTO;
import io.github.entity.EmailData;
import io.github.service.EmailService;
import io.github.util.ServiceUtils;

public class EmailServiceImpl implements EmailService {

    final String RECAPTCHA_PRIVATE_KEY = ServiceUtils.getSystemEnvOrProperty("RECAPTCHA_PRIVATE_KEY");
    final String SENDINBLUE_API_KEY = ServiceUtils.getSystemEnvOrProperty("SENDINBLUE_API_KEY");
    final String EMAIL_SENDER = ServiceUtils.getSystemEnvOrProperty("EMAIL_SENDER");
    final String EMAIL_SUBJECT = ServiceUtils.getSystemEnvOrProperty("EMAIL_SUBJECT");

    public boolean verifyReCaptchaChallenge(String reCaptchaChallenge) throws Exception {
        String url = "https://www.google.com/recaptcha/api/siteverify?secret=" + RECAPTCHA_PRIVATE_KEY + "&response="
                + reCaptchaChallenge;
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        String responseSuccess = new Gson().fromJson(response.body(), SiteVerifyDTO.class).getSuccess();

        return responseSuccess == "true";
    }

    public boolean sendEmail(EmailBodyDTO emailBody) throws Exception {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://api.sendinblue.com/v3/smtp/email"))
                .header("accept", "application/json")
                .headers("Content-Type", "application/json")
                .headers("api-key", SENDINBLUE_API_KEY)
                .POST(HttpRequest.BodyPublishers.ofString(new Gson().toJson(emailBody)))
                .build();
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        return response.statusCode() == 201;
    }

    public EmailBodyDTO emailBodyBuilder(EmailDTO email) {
        EmailData emailRecipient = new EmailData(EMAIL_SENDER, EMAIL_SENDER);
        EmailData emailSender = new EmailData(EMAIL_SENDER, EMAIL_SENDER);
        EmailBodyDTO emailBody = new EmailBodyDTO();

        List<EmailData> toList = new ArrayList<>();
        toList.add(emailRecipient);

        emailBody.setTo(toList);
        emailBody.setSender(emailSender);
        emailBody.setSubject(EMAIL_SUBJECT);
        emailBody.setTextContent(
                "Name: " + email.getName()
                        + "\nE-mail: " + email.getEmail()
                        + "\nSend-At: " + Instant.now()
                        + "\nMessage: " + email.getMessage());

        return emailBody;
    }

}
