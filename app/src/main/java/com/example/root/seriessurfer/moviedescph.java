package com.example.root.seriessurfer;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * Created by root on 8/5/15.
 */
public class moviedescph extends FragmentActivity {



        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            String value = null;
            MovieDesc  mainFragment = new MovieDesc();
            Bundle args = new Bundle();
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                value = extras.getString("title");
            }
            args.putString("title","" + value);
            mainFragment.setArguments(args);
            getSupportFragmentManager().beginTransaction()
                    .add(android.R.id.content, mainFragment).commit();


            // if (savedInstanceState == null) {
            //   this.getSupportFragmentManager().beginTransaction()
            //         .add(R.id.container, new Placeholder())
            //.add(R.id.container, new MovieDesc())
            //       .commit();
            //}
        }
}
