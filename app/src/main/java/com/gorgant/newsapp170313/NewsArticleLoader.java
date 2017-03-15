package com.gorgant.newsapp170313;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ludeyu on 3/14/2017.
 * References this framework: http://www.concretepage.com/android/android-asynctaskloader-example-with-listview-and-baseadapter
 */

public class NewsArticleLoader extends AsyncTaskLoader<List<NewsArticle>> {

    /** Tag for log messages */
    private static final String LOG_TAG = NewsArticleLoader.class.getName();

    /** Query URL */
    private String mUrl;

    /**
     * Constructs a new {@link NewsArticleLoader}.
     *
     * @param context of the activity
     * @param url to load data from
     */
    public NewsArticleLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    /**
     * Subclasses must implement this to take care of loading their data,
     * as per {@link #startLoading()}.  This is not called by clients directly,
     * but as a result of a call to {@link #startLoading()}.
     */
    @Override
    protected void onStartLoading() {
        Log.e(LOG_TAG,"Loader Starting to Load");
        forceLoad();
    }

    @Override
    public List<NewsArticle> loadInBackground() {

        Log.i(LOG_TAG,"fetching data");
        List<NewsArticle> data = new ArrayList<NewsArticle>(QueryUtils.fetchArticleData(mUrl));
        Log.i(LOG_TAG,"done fetching data");
        return data;
    }
}
