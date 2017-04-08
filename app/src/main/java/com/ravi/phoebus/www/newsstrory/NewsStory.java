package com.ravi.phoebus.www.newsstrory;

public class NewsStory {
    private String mTitle;
    private String mSection;
    private String mPublishedDate;
    private String mWebUrl;
    private String mType;

    public NewsStory(String mTitle, String mSection, String mPublishedDate, String mWebUrl, String mType) {
        this.mTitle = mTitle;
        this.mSection = mSection;
        this.mPublishedDate = mPublishedDate;
        this.mWebUrl = mWebUrl;
        this.mType = mType;

    }

    public String getTitle() {
        return mTitle;
    }

    public String getSection() {
        return mSection;
    }

    public String getPublishedDate() {
        return mPublishedDate;
    }

    public String getWebUrl() {
        return mWebUrl;
    }

    public String getType() {
        return mType;
    }
}
