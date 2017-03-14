package com.gorgant.newsapp170313;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Construct the data source
        ArrayList<NewsArticle> newsArticleArrayList = new ArrayList<>();

        //Some sample data
        newsArticleArrayList.add(new NewsArticle("Fun Title Yo!","Dailies Section",1467331200));
        newsArticleArrayList.add(new NewsArticle("Fun Title2 Yo!","Dailies Section",1467331200));
        newsArticleArrayList.add(new NewsArticle("Fun Title3 Yo!","Dailies Section",1467331200));
        newsArticleArrayList.add(new NewsArticle("Fun Title Yo!","Dailies Section",1467331200));
        newsArticleArrayList.add(new NewsArticle("Fun Title2 Yo!","Dailies Section",1467331200));
        newsArticleArrayList.add(new NewsArticle("Fun Title3 Yo!","Dailies Section",1467331200));
        newsArticleArrayList.add(new NewsArticle("Fun Title Yo!","Dailies Section",1467331200));
        newsArticleArrayList.add(new NewsArticle("Fun Title2 Yo!","Dailies Section",1467331200));
        newsArticleArrayList.add(new NewsArticle("Fun Title3 Yo!","Dailies Section",1467331200));



        //Create the adapter to convert the array to views
        NewsArticleAdapter newsArticleAdapter = new NewsArticleAdapter(this,newsArticleArrayList);
        //Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.activity_main);
        listView.setAdapter(newsArticleAdapter);


    }
}
