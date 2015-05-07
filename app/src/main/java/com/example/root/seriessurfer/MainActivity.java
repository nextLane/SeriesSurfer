package com.example.root.seriessurfer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Movie;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);


       // if (savedInstanceState == null) {
         //   this.getSupportFragmentManager().beginTransaction()
           //         .add(R.id.container, new Placeholder())
                    //.add(R.id.container, new MovieDesc())
             //       .commit();
        //}
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }


    public static class Placeholder extends Fragment {


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            return inflater.inflate(R.layout.activity_main, container, false);
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            View v = getView();
            Button enter = (Button) v.findViewById(R.id.button);
            Button fav = (Button) v.findViewById(R.id.button2);
            final EditText et = (EditText) v.findViewById(R.id.editText);
// Listening to register new account link
            enter.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {


                    if (haveNetworkConnection()) {

                        MovieDesc displayFrag = (MovieDesc) getFragmentManager()
                                .findFragmentById(R.id.frag);
                        if (displayFrag == null) {
                            // DisplayFragment (Fragment B) is not in the layout (handset layout),
                            // so start DisplayActivity (Activity B)
                            // and pass it the info about the selected item
                            Intent intent = new Intent(getActivity(),moviedescph.class);
                            intent.putExtra("title", ""+et.getText());

                            startActivity(intent);
                        } else {

                        try {

// Switching to ListView screen
                            //Put the value
                            MovieDesc ldf = new MovieDesc ();
                            Bundle args = new Bundle();
                            args.putString("title","" + et.getText());
                            ldf.setArguments(args);

//Inflate the fragment
                            getFragmentManager().beginTransaction().add(R.id.frag, ldf).commit();



                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(), "Enter the correct name!",
                                    Toast.LENGTH_SHORT).show();

                        }
                        }
                    } else {
                        Toast.makeText(getActivity(), "Please get connected to Internet, then try!",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });


            fav.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
// Switching to ListView screen
                    Intent i = new Intent(getActivity(), FavoritesList.class);


                    startActivity(i);
                }
            });


        }


        private boolean haveNetworkConnection() {
            boolean haveConnectedWifi = false;
            boolean haveConnectedMobile = false;
            Context m = getActivity();
            ConnectivityManager cm = (ConnectivityManager) m.getSystemService(Context.CONNECTIVITY_SERVICE);
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
}
