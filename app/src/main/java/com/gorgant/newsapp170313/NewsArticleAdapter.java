package com.gorgant.newsapp170313;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


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

        // Find the TextView with view ID date
        TextView date = (TextView) convertView.findViewById(R.id.date);
        // Create a new Date String from the time String of the news article
        String dateString = newsArticle.getDate();
        // Format the time string (i.e. "4:30PM") using the helper method formatDate
        String formattedDate = null;
        try {
            formattedDate = formatDate(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // Display the time of the current news article in that TextView
        date.setText(formattedDate);


        return convertView;

    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a raw String by converting
     * it to a date object and then simplifying the format.
     */
    private String formatDate(String dateObject) throws ParseException {
        SimpleDateFormat rawDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date d = rawDateFormat.parse(dateObject);

        SimpleDateFormat cleanDateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return cleanDateFormat.format(d);

        //2017-03-14T00:37:26Z
    }

}



