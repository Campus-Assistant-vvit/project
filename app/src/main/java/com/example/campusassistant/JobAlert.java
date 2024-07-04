package com.example.campusassistant;

public class JobAlert {

    private String company;
    private String position;
    private String location;
    private String description;

    public JobAlert(String company, String position, String location, String description) {
        this.company = company;
        this.position = position;
        this.location = location;
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

    public String getDescription() {
        return description;
    }
}
