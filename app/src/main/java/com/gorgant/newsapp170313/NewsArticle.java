package com.gorgant.newsapp170313;

/**
 * Created by Ludeyu on 3/13/2017.
 */

public class NewsArticle {
    private String title;
    private String section;
    private long date;

    public NewsArticle(String title, String section, long date) {
        this.title = title;
        this.section = section;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public String getSection() {
        return section;
    }

    public long getDate() {
        return date;
    }
}
