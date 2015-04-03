package com.example.root.seriessurfer;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 3/4/15.
 */
public class FavoritesList extends Activity {
    List<RowItem> rowItems;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        ListView listView = (ListView) findViewById(R.id.contentlist);

        DatabaseHandler dh = new DatabaseHandler(this);
        dh.getReadableDatabase();
        List<String []> favList = new ArrayList<String []>();
        favList = dh.getAllForms();

        int size= favList.size();

        if(size==0)
        {
            Toast.makeText(getApplicationContext(), "Oops, list is empty! Add new favorites!",
                    Toast.LENGTH_LONG).show();
        }

        String title[]= new String[size];
        String desc[]= new String[size];

        for(int i=0; i<size;i++)
        {
            title[i]=favList.get(i)[1];
            desc[i]=favList.get(i)[2]+"   "+ favList.get(i)[3];
        }

        rowItems = new ArrayList<RowItem>();
        for (int i = 0; i < size; i++) {
            RowItem item = new RowItem(title[i], desc[i]);
            rowItems.add(item);
        }

        listView = (ListView) findViewById(R.id.contentlist);
        CustomArrayAdapter adapter = new CustomArrayAdapter(this,
                R.layout.row, rowItems);
        listView.setAdapter(adapter);



        dh.close();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.submenu, menu);



        return true;




    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.clear) {
           DatabaseHandler dh=new DatabaseHandler(this);
           dh.deleteMovies();
           this.finish();
            Intent i = new Intent(getApplicationContext(), FavoritesList.class);


            startActivity(i);





            return true;
        }

        if (id == R.id.action_add) {

            final TextView title = (TextView) findViewById(R.id.title);
            final TextView rating = (TextView) findViewById(R.id.rating);
            final TextView genre = (TextView) findViewById(R.id.genre);


            DatabaseHandler dh=new DatabaseHandler(this);


            dh.getWritableDatabase();
            dh.addFavs(title.getText()+"", rating.getText()+"", genre.getText()+"");
            dh.close();
            Toast.makeText(getApplicationContext(), "Added to favorites! <3",
                    Toast.LENGTH_LONG).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}