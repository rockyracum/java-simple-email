package io.github.dto;

import java.util.ArrayList;
import java.util.List;

import io.github.entity.EmailData;

public class EmailBodyDTO {

    private List<EmailData> to = new ArrayList<>();
    private EmailData sender;
    private String subject;
    private String textContent;

    public List<EmailData> getTo() {
        return to;
    }

    public void setTo(List<EmailData> to) {
        this.to = to;
    }

    public EmailData getSender() {
        return sender;
    }

    public void setSender(EmailData sender) {
        this.sender = sender;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

}
