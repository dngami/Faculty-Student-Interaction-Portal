package com.iitg.interaction.facultystudentinteractionportal;

import android.net.Uri;

import java.util.ArrayList;

public class UserInfo {

    public static Uri profilepicurl;
    public static  boolean logined=false;
    public static  boolean verified;
    public static  String fullname;
    public static  String username;
    public static  String usertype;
    public static  String rollnumber;
    public static  String email;
    public static  String occupation;
    public static  String department;
    public static  String year;
    public static ArrayList<String> courses;


    private static final UserInfo ourInstance = new UserInfo();

    public static UserInfo getInstance() {
        return ourInstance;
    }

    private UserInfo() {
    }

    public static void fillUserInfo(String username,String fullname, String usertype, String rollnumber, String email, String occupation, String department, String year , ArrayList<String> courses) {
        UserInfo.logined = true;
        UserInfo.username = username;
        UserInfo.fullname = fullname;
        UserInfo.usertype = usertype;
        UserInfo.rollnumber = rollnumber;
        UserInfo.email = email;
        UserInfo.occupation = occupation;
        UserInfo.department = department;
        UserInfo.year = year;

        UserInfo.courses = courses ;
    }


    public static void logout()
    {
        logined=false;
        verified=false;
        fullname=null;
        username=null;
        usertype=null;
        rollnumber=null;
        email=null;
        occupation=null;
        department=null;
        year=null;
        courses=null;
    }

}