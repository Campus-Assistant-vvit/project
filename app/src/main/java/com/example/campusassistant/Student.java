package com.example.campusassistant;

public class Student {

    private String name;
    private String rollNumber;
    private String company;
    private String aPackage;
    private String branch;
    private String year;

    public Student(String name, String rollNumber, String company, String aPackage, String branch, String year) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.company = company;
        this.aPackage = aPackage;
        this.branch = branch;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public String getCompany() {
        return company;
    }

    public String getPackage() {
        return aPackage;
    }

    public String getBranch() {
        return branch;
    }

    public String getYear() {
        return year;
    }
}
