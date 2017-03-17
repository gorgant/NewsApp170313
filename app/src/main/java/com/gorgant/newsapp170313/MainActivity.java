package com.gorgant.newsapp170313;

import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<NewsArticle>> {

    /** Tag for the log messages */
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    /**
     * URL for news article data from the Guardian website
     */
    private static final String GUARDIAN_REQUEST_URL =
            "http://content.guardianapis.com/search?api-key=033f7a2f-9a84-40ff-9a25-c02e2104a01f";
            //"http://content.guardianapis.com/search?order-by=newest&use-date=published&api-key=033f7a2f-9a84-40ff-9a25-c02e2104a01f"
    /**
     * Constant value for the earthquake loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    private static final int NEWSARTICLE_LOADER_ID = 1;

    /** Adapter for the list of news articles */
    private NewsArticleAdapter mAdapter;

    /** TextView that is displayed when the list is empty */
    private TextView mEmptyStateView;

    /** Progress bar that is displayed when data is loading */
    private View mLoadingIndicator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.activity_main);

        //Initialize the empty view
        mEmptyStateView = (TextView) findViewById(R.id.empty_view);
        listView.setEmptyView(mEmptyStateView);

        //Initialize the progress bar
        mLoadingIndicator = findViewById(R.id.loading_indicator);


        //Create the adapter to convert the array to views
        mAdapter = new NewsArticleAdapter(this, new ArrayList<NewsArticle>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        listView.setAdapter(mAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Find the current news article that was clicked on
                NewsArticle currentNewsArticle = mAdapter.getItem(position);
                //Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri newsArticleUri = Uri.parse(currentNewsArticle.getmUrl());
                //Create a new intent to view the earthquake URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW,newsArticleUri);
                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });

        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager cm = (ConnectivityManager)this.getSystemService(this.CONNECTIVITY_SERVICE);
        // Get details on the currently active default data network
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        // Initialize the boolean for if a network connection is valid
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        // If there is a network connection, fetch data
        if(isConnected)
        {
            Log.i(LOG_TAG,"Network is live");
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();
            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(NEWSARTICLE_LOADER_ID,null,this);
        }
        else {
            Log.i(LOG_TAG,"Network is dead");
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            mLoadingIndicator.setVisibility(View.GONE);

            // Update empty state with no connection error message
            mEmptyStateView.setText(R.string.no_internet);
        }

    }

    @Override
    public Loader<List<NewsArticle>> onCreateLoader(int id, Bundle args) {

        Log.i(LOG_TAG,"Loader being created");

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);

        String section = "";
        if(sharedPrefs.contains(getString(R.string.settings_section_key))) {
            section = sharedPrefs.getString(
                    getString(R.string.settings_section_key),
                    getString(R.string.settings_section_default));
        }

        String orderBy = sharedPrefs.getString(
                getString(R.string.settings_order_by_key),
                getString(R.string.settings_order_by_default));

        Uri baseUri = Uri.parse(GUARDIAN_REQUEST_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();

        Log.i(LOG_TAG,"Pre-appended version" + uriBuilder.toString());

        if(section.length()>0){
            uriBuilder.appendQueryParameter("section", section);
        }

        uriBuilder.appendQueryParameter("order-by", orderBy);

        Log.i(LOG_TAG,"Post-appended version" + uriBuilder.toString());

        return new NewsArticleLoader(this, uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<NewsArticle>> loader, List<NewsArticle> newsArticles) {

        Log.i(LOG_TAG,"Loader finished loading, adding data");

        //Hide the loading indicator now that the data has finished loading
        mLoadingIndicator.setVisibility(View.GONE);

        // Set empty state text to display "No news articles found."
        mEmptyStateView.setText(R.string.no_articles);

        // Clear the adapter of previous news article data
        mAdapter.clear();

        // If there is a valid list of {@link NewsArticle}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if ( newsArticles != null && !newsArticles.isEmpty()) {
            mAdapter.addAll(newsArticles);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<NewsArticle>> loader) {
        // Loader reset, so we can clear out our existing data.
        mAdapter.clear();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
