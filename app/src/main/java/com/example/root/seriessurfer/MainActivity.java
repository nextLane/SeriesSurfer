package com.example.root.seriessurfer;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //  DatabaseHandler dh= new DatabaseHandler(this);
        Button enter = (Button) findViewById(R.id.button);
        final EditText et=(EditText)findViewById(R.id.editText);
// Listening to register new account link
        enter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
// Switching to ListView screen
                Intent i = new Intent(getApplicationContext(), MovieDesc.class);
                i.putExtra("title",""+et.getText());

                startActivity(i);
            }
        });


    }




}
