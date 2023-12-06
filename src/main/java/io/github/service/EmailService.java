package io.github.service;

import io.github.dto.EmailBodyDTO;
import io.github.dto.EmailDTO;

public interface EmailService {

    public boolean verifyReCaptchaChallenge(String reCaptchaChallenge) throws Exception;

    public boolean sendEmail(EmailBodyDTO emailBody) throws Exception;

    public EmailBodyDTO emailBodyBuilder(EmailDTO email);

}
