package com.example.root.seriessurfer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 28/3/15.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "seriessurfer";

    // forms table name
    private static final String TABLE_NAME = "favorites";

    // forms Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_RATING = "rating";
    private static final String KEY_GENRE = "genre";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_TITLE + " TEXT,"
                + KEY_RATING + " TEXT,"
                + KEY_GENRE + " TEXT" + ")";
        db.execSQL(CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        onCreate(db);
    }



    void addFavs(String title, String rate, String genre ) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, title); // form Name
        values.put(KEY_RATING, rate); // form Phone
        values.put(KEY_GENRE, genre); // form Phone

        // Inserting Row
        db.insert(TABLE_NAME, null, values);
        db.close(); // Closing database connection
    }



    // Getting All forms
    public List<String[]> getAllForms() {
        List<String []> favList = new ArrayList<String []>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME+" GROUP BY title";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        String row[];
        if (cursor.moveToFirst()) {
            do {
                row= new String[4];
                row[0]=cursor.getString(0);
                row[1]=cursor.getString(1);
                row[2]=cursor.getString(2);
                row[3]=cursor.getString(3);
               favList.add(row);
            } while (cursor.moveToNext());
        }

        // return form list
        return favList;
    }

    /*// Updating single form
    public int updateForm(forms form) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, form.getName());
        values.put(KEY_PH_NO, form.getPhoneNumber());

        // updating row
        return db.update(TABLE_formS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(form.getID()) });
    }
*/
    // Deleting single form
  /*  public void deleteForms(forms  f) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_FORMS, KEY_ID + " = ?",
                new String[] { String.valueOf(f.getID()) });
        db.close();
    }

*/
    // Getting forms Count
    public int getMoviesCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }




















}



