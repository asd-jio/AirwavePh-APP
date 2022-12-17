package com.example.loginwithauth;



public class Messages {
    public String subject, message, senderName, senderNumber, senderEmail, status, key, category, response;


    public Messages(){

    }
    public Messages(String subText, String msgMain, String sender, String senderNum, String email, String status, String key, String category, String response) {
        this.subject = subText;
        this.message = msgMain;
        this.senderNumber = senderNum;
        this.senderName = sender;
        this.senderEmail = email;
        this.status = status;
        this.key = key;
        this.category = category;
        this.response = response;


    }

    public String getSubject() {
        return this.subject;
    }

    public String getMessage() {
        return this.message;
    }

    public String getSenderName() {
        return this.senderName;
    }

    public String getSenderNumber() {
        return this.senderNumber;
    }

    public String getSenderEmail() {
        return this.senderEmail;
    }

    public String getStatus() {
        return this.status;
    }
}
