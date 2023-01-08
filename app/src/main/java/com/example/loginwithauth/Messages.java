package com.example.loginwithauth;



public class Messages {
    public String subject, message, senderName, senderNumber, senderEmail, status, key,
            category, response, time, image1, image2, image3;


    public Messages(){

    }

    public Messages(String subText, String msgMain, String sender, String senderNum, String email, String status, String key,
                    String category, String response, String time, String image1, String image2, String image3 ) {
        this.subject = subText;
        this.message = msgMain;
        this.senderNumber = senderNum;
        this.senderName = sender;
        this.senderEmail = email;
        this.status = status;
        this.key = key;
        this.category = category;
        this.response = response;
        this.time = time;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;

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

    public String getKey() {
        return this.key;
    }

    public String getCategory() {
        return this.category;
    }

    public String getResponse() {
        return this.response;
    }

    public String getTime() {
        return this.time;
    }

    public String getImage1() {
        return image1;
    }

    public String getImage2() {
        return image2;
    }

    public String getImage3() {
        return image3;
    }

}
