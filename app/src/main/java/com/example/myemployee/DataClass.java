package com.example.myemployee;

public class DataClass {

    private String dataName;
    private String dataAge;
    private String dataCity;
    private String dataEducation;
    private String dataSkill;
    private String dataType;
    private String dataSalary;
    private String dataImage;

    private String dataEmail;

    private String dataPhone;

    private String dataCV;



    public String getDataName() {
        return dataName;
    }

    public String getDataAge() {
        return dataAge;
    }

    public String getDataCity() {
        return dataCity;
    }

    public String getDataEducation() {
        return dataEducation;
    }

    public String getDataSkill() {
        return dataSkill;
    }

    public String getDataType() {
        return dataType;
    }

    public String getDataSalary() {
        return dataSalary;
    }

    public String getDataImage() {
        return dataImage;
    }
    public String getDataEmail() {
        return dataEmail;
    }
    public String getDataCV() {
        return dataCV;
    }
    public String getDataPhone() {
        return dataPhone;
    }

    public DataClass(String dataName, String dataAge, String dataCity, String dataEducation, String dataSkill, String dataType, String dataSalary, String dataImage ,String dataEmail , String dataCV , String dataPhone) {
        this.dataName = dataName;
        this.dataAge = dataAge;
        this.dataCity = dataCity;
        this.dataEducation = dataEducation;
        this.dataSkill = dataSkill;
        this.dataType = dataType;
        this.dataSalary = dataSalary;
        this.dataImage = dataImage;
        this.dataEmail = dataEmail;
        this.dataCV = dataCV;
        this.dataPhone = dataPhone;

    }

    public DataClass(){

    }
}
