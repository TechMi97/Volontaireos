package com.example.asad.volontaireos;

public class Infodata {
String infoID;
String infoTitle;
String infoDescription;

    public Infodata() {}

    public Infodata(String infoID, String infoTitle,String Description) {
        this.infoID = infoID;
        this.infoTitle = infoTitle;
        this.infoDescription = Description;

    }

    public String getInfoID() {
        return infoID;
    }

    public String getInfoTitle() {
        return infoTitle;
    }


    public String getInfoDescription() {
        return infoDescription;
    }


}
