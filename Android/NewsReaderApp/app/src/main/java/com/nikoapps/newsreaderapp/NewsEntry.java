package com.nikoapps.newsreaderapp;

public class NewsEntry {

    String newsID;
    String newsHeadline;
    String newsDate;
    String newsScore;
    String newsURL;

    public NewsEntry(String newsID, String newsHeadline, String newsDate, String newsScore, String newsURL) {
        this.newsID = newsID;
        this.newsHeadline = newsHeadline;
        this.newsDate = newsDate;
        this.newsScore = newsScore;
        this.newsURL = newsURL;
    }

    public String getNewsURL() {
        return newsURL;
    }

    public void setNewsURL(String newsURL) {
        this.newsURL = newsURL;
    }

    public String getNewsID() {
        return newsID;
    }

    public void setNewsID(String newsID) {
        this.newsID = newsID;
    }

    public String getNewsHeadline() {
        return newsHeadline;
    }

    public void setNewsHeadline(String newsHeadline) {
        this.newsHeadline = newsHeadline;
    }

    public String getNewsDate() {
        return newsDate;
    }

    public void setNewsDate(String newsDate) {
        this.newsDate = newsDate;
    }

    public String getNewsScore() {
        return newsScore;
    }

    public void setNewsScore(String newsScore) {
        this.newsScore = newsScore;
    }
}
