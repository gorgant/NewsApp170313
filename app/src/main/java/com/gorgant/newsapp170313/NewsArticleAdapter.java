package com.gorgant.newsapp170313;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.gorgant.newsapp170313.R.id.date;

/**
 * Created by Ludeyu on 3/13/2017.
 * Framework from: guides.codepath.com/android/Using-an-ArrayAdapter-with-ListView
 * Date helper from: Udacity earthquake app practice
 */

public class NewsArticleAdapter extends ArrayAdapter<NewsArticle> {
    public NewsArticleAdapter(Context context,List<NewsArticle> newsArticles) {
        super(context,0,newsArticles);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //Get the data item for this position
        NewsArticle newsArticle = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }

        //Lookup view for data population
        TextView title = (TextView) convertView.findViewById(R.id.title);
        title.setText(newsArticle.getTitle());

        TextView section = (TextView) convertView.findViewById(R.id.section);
        section.setText(newsArticle.getSection());





        // Create a new Date object from the time in milliseconds of the news article
        String dateObject = newsArticle.getDate();

                // Find the TextView with view ID date
        TextView date = (TextView) convertView.findViewById(R.id.date);
        // Format the time string (i.e. "4:30PM") using the helper method formatDate

        String formattedDate = null;
        try {
            formattedDate = formatDate(dateObject);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // Display the time of the current news article in that TextView
        date.setText(formattedDate);


        return convertView;



    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(String dateObject) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date d = dateFormat.parse(dateObject);

        SimpleDateFormat dateFormat2 = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat2.format(d);

        //2017-03-14T00:37:26Z
    }

}



