package com.example.asad.volontaireos;

public class Infodata {
String infoID;
String infoName;
String infoDescription;


    public Infodata(String infoID, String infoName,String Description) {
        this.infoID = infoID;
        this.infoName = infoName;
        this.infoDescription = Description;

    }

    public String getInfoID() {
        return infoID;
    }

    public String getInfoName() {
        return infoName;
    }


    public String getInfoDescription() {
        return infoDescription;
    }
}
