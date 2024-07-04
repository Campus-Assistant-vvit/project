package com.example.campusassistant;

public class Internship {

    private String company;
    private String position;
    private String location;
    private String duration;
    private String stipend;
    private String description;

    public Internship(String company, String position, String location, String duration, String stipend, String description) {
        this.company = company;
        this.position = position;
        this.location = location;
        this.duration = duration;
        this.stipend = stipend;
        this.description = description;
    }

    public String getCompany() {
        return company;
    }

    public String getPosition() {
        return position;
    }

    public String getLocation() {
        return location;
    }

    public String getDuration() {
        return duration;
    }

    public String getStipend() {
        return stipend;
    }

    public String getDescription() {
        return description;
    }
}
