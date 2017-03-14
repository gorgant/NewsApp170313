package com.gorgant.newsapp170313;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.Z;

public class MainActivity extends AppCompatActivity {

    private static final String GUARDIAN_REQUEST_URL =
            "http://content.guardianapis.com/search?order-by=newest&use-date=published&api-key=033f7a2f-9a84-40ff-9a25-c02e2104a01f";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Construct the data source
        ArrayList<NewsArticle> newsArticleArrayList = new ArrayList<>();

        //Some sample data
        newsArticleArrayList.add(new NewsArticle("Fun Title Yo!","Dailies Section","2017-03-14T00:37:26Z","www.google.com"));
        newsArticleArrayList.add(new NewsArticle("Fun Title2 Yo!","Dailies Section","2017-02-14T00:37:26Z","www.google.com"));
        newsArticleArrayList.add(new NewsArticle("Fun Title3 Yo!","Dailies Section","2017-03-14T00:37:26Z","www.google.com"));
        newsArticleArrayList.add(new NewsArticle("Fun Title Yo!","Dailies Section","2017-03-14T00:37:26Z","www.google.com"));
        newsArticleArrayList.add(new NewsArticle("Fun Title2 Yo!","Dailies Section","2017-03-14T00:37:26Z","www.google.com"));
        newsArticleArrayList.add(new NewsArticle("Fun Title3 Yo!","Dailies Section","2017-03-14T00:37:26Z","www.google.com"));
        newsArticleArrayList.add(new NewsArticle("Fun Title Yo!","Dailies Section","2017-03-14T00:37:26Z","www.google.com"));
        newsArticleArrayList.add(new NewsArticle("Fun Title2 Yo!","Dailies Section","2017-03-14T00:37:26Z","www.google.com"));
        newsArticleArrayList.add(new NewsArticle("Fun Title3 Yo!","Dailies Section","2017-03-14T00:37:26Z","www.google.com"));



        //Create the adapter to convert the array to views
        NewsArticleAdapter newsArticleAdapter = new NewsArticleAdapter(this,newsArticleArrayList);
        //Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.activity_main);
        listView.setAdapter(newsArticleAdapter);


    }
}
