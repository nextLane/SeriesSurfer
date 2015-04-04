package com.example.root.seriessurfer;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //  DatabaseHandler dh= new DatabaseHandler(this);
        Button enter = (Button) findViewById(R.id.button);
        Button fav =(Button) findViewById(R.id.button2);
        final EditText et=(EditText)findViewById(R.id.editText);
// Listening to register new account link
        enter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                MovieDesc md = new MovieDesc();
                if (haveNetworkConnection()) {
                    if (md.movieExists(et.getText() + "").equals("True")) {

// Switching to ListView screen
                        Intent i = new Intent(getApplicationContext(), MovieDesc.class);
                        i.putExtra("title", "" + et.getText());

                        startActivity(i);


                    } else {
                        Toast.makeText(getApplicationContext(), "Enter the correct name!",
                                Toast.LENGTH_SHORT).show();

                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Please get connected to Internet, then try!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });



        fav.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
// Switching to ListView screen
                Intent i = new Intent(getApplicationContext(), FavoritesList.class);


                startActivity(i);
            }
        });






    }

    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }


}
