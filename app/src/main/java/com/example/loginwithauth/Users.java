package com.example.loginwithauth;

public class Users {

    public String  fname, email, Cnum, Anum, password, cpassword, Hnum, street, barangay, town, province, plan;

    public Users(){

    }

    public Users(String fname, String email, String cnum, String anum, String password, String cpassword, String hnum, String street, String barangay, String town, String province, String plan){
        this.fname = fname;
        this.email = email;
        this.street = street;
        this.Cnum = cnum;
        this.Anum = anum;
        this.password = password;
        this.cpassword = cpassword;
        this.Hnum = hnum;
        this.barangay = barangay;
        this.town = town;
        this.province = province;
        this.plan = plan;


    }


}
