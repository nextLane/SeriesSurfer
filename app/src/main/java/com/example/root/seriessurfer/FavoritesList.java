package com.example.root.seriessurfer;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBarActivity;
import android.widget.AdapterView;
import android.widget.ListView;

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
}