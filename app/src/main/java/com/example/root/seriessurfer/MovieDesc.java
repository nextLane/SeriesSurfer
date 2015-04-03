package com.example.root.seriessurfer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 2/4/15.
 */
public class MovieDesc extends ActionBarActivity {

    String title = "friends";
    String url;
    //URL to get JSON Array

    //JSON Node Names
    final String TITLE = "Title";
    final String GENRE = "Genre";
    final String YEAR = "Year";
    final String RUNTIME = "Runtime";
    final String ACTORS = "Actors";
    final String COUNTRY = "Country";
    final String AWARDS = "Awards";
    final String WRITER = "Writer";
    final String PLOT = "Plot";

    JSONArray user = null;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Bundle extras = getIntent().getExtras();
        title = extras.getString("title");
        Log.d("----", title);
        JSONObject c=null;

        url = "http://www.omdbapi.com/?t=" + title + "&y=&plot=short&r=json";


        // Creating new JSON Parser
        JSONParser jParser = new JSONParser();
        // Getting JSON from URL
       c = jParser.getJSONFromUrl(url);
        try {


            // Storing  JSON item in a Variable

            //Importing TextView
            final TextView title = (TextView) findViewById(R.id.title);
            final ImageView pic = (ImageView) findViewById(R.id.poster);
            final TextView cast = (TextView) findViewById(R.id.cast);
            final TextView runtime = (TextView) findViewById(R.id.runtime);
            final TextView year = (TextView) findViewById(R.id.year);
            final TextView writer = (TextView) findViewById(R.id.writer);
            final TextView country = (TextView) findViewById(R.id.country);
            final TextView plot = (TextView) findViewById(R.id.plot);
            final TextView awards = (TextView) findViewById(R.id.awards);
            final TextView genre = (TextView) findViewById(R.id.genre);
            final TextView rating = (TextView) findViewById(R.id.rating);

            //Set JSON Data in TextView
            title.setText(c.getString("Title"));

            cast.setText(c.getString(ACTORS));
            runtime.setText(c.getString(RUNTIME));
            year.setText(c.getString(YEAR));
            writer.setText(c.getString(WRITER));
            country.setText(c.getString(COUNTRY));
            plot.setText(c.getString(PLOT));
            awards.setText(c.getString(AWARDS));
            genre.setText(c.getString(GENRE));
            rating.setText(c.getString("imdbRating"));

            new ImageLoadTask(c.getString("Poster"), pic).execute();



        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public class ImageLoadTask extends AsyncTask<Void, Void, Bitmap> {

        private String url;
        private ImageView imageView;

        public ImageLoadTask(String url, ImageView imageView) {
            this.url = url;
            this.imageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(Void... params) {
            try {
                URL urlConnection = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) urlConnection
                        .openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                return myBitmap;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            imageView.setImageBitmap(result);
        }

    }




        }











