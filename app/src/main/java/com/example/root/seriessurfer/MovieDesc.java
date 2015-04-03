package com.example.root.seriessurfer;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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



public class MovieDesc extends ActionBarActivity {

    String title = "friends";
    String url;
    DatabaseHandler dh;
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

        dh= new DatabaseHandler(this);
        Bundle extras = getIntent().getExtras();

        title = extras.getString("title");
        Log.d("----", title);
        JSONObject c=null;

        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http")
                .authority("www.omdbapi.com")
                .appendQueryParameter("t", title)
                .appendQueryParameter("y", "")
                .appendQueryParameter("plot", "short")
                .appendQueryParameter("r", "json");
        String url = builder.build().toString();

        //url = "http://www.omdbapi.com/?t=" + title + "&y=&plot=short&r=json";


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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);



        return true;




    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_share) {
            final TextView title = (TextView) findViewById(R.id.title);
            final TextView rating = (TextView) findViewById(R.id.rating);


            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Hey! Check out this:"+ title.getText()+" with IMDB rating:"+ rating.getText()+"! @SeriesSurfer");
            sendIntent.setType("text/plain");
            startActivity(sendIntent);



            return true;
        }

        if (id == R.id.action_add) {

            final TextView title = (TextView) findViewById(R.id.title);
            final TextView rating = (TextView) findViewById(R.id.rating);
            final TextView genre = (TextView) findViewById(R.id.genre);



            dh.getWritableDatabase();
            dh.addFavs(title.getText()+"", rating.getText()+"", genre.getText()+"");
            dh.close();
            Toast.makeText(getApplicationContext(), "Added to favorites! <3",
                    Toast.LENGTH_LONG).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
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











