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
    private static final String TABLE_FORMS = "movies";

    // forms Table Columns names
    private static final String KEY_ID = "fid";
    private static final String KEY_TITLE = "title";
    private static final String KEY_XML = "xml";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_FORMS_TABLE = "CREATE TABLE " + TABLE_FORMS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TITLE + " TEXT,"
                + KEY_XML + " TEXT" + ")";
        db.execSQL(CREATE_FORMS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FORMS);

        // Create tables again
        onCreate(db);
    }


/*
    void addForms(forms form) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, form.getTitle()); // form Name
        values.put(KEY_XML, form.getXML()); // form Phone

        // Inserting Row
        db.insert(TABLE_FORMS, null, values);
        db.close(); // Closing database connection
    }

    // Getting single form
    forms getForm(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_FORMS, new String[] { KEY_ID,
                        KEY_TITLE, KEY_XML }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Log.d("__---id asked for:",""+id);
        forms f = new forms(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        // return form

        return f;
    }

    // Getting All forms
    public List<forms> getAllForms() {
        List<forms> formList = new ArrayList<forms>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_FORMS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                forms f = new forms();
                f.setID(Integer.parseInt(cursor.getString(0)));
                f.setTitle(cursor.getString(1));
                f.setXML(cursor.getString(2));
                // Adding form to list
                formList.add(f);
            } while (cursor.moveToNext());
        }

        // return form list
        return formList;
    }
*/
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
    public int getFormsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_FORMS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }




















}



