package com.gorgant.newsapp170313;

/**
 * Created by Ludeyu on 3/13/2017.
 */

public class NewsArticle {
    private String mTitle;
    private String mSection;
    private String mDate;
    private String mUrl;

    public NewsArticle(String title, String section, String date, String url) {
        this.mTitle = title;
        this.mSection = section;
        this.mDate = date;
        this.mUrl = url;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getSection() {
        return mSection;
    }

    public String getDate() {
        return mDate;
    }
}
