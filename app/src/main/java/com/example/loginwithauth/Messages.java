package com.example.loginwithauth;



public class Messages {
    public String message, senderName, senderNumber, subject;


    public Messages(){

    }
    public Messages(String subText, String msgMain, String senderNum, String sender) {
        this.subject = subText;
        this.message = msgMain;
        this.senderNumber = senderNum;
        this.senderName = sender;
    }

    public String getSubject() {
        return this.subject;
    }

    public String getMessage() {
        return this.message;
    }

    public String getSenderInfo() {
        return this.senderName;
    }

    public String getSenderNumber() {
        return this.senderNumber;
    }
}
