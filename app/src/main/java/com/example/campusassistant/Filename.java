package com.example.campusassistant;

public class Filename {
    private String filename;
    private String url;
    private String key;

    public Filename(String filename, String url, String key) {
        this.filename = filename;
        this.url = url;
    }

    public Filename() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getFilename() {
        return filename;
    }


    public String getUrl() {
        return url;
    }

}