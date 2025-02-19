package com.enamnotes.enamapppartone;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DB_NAME="participantdb";
    private static final int DB_VERSION=3;
    private static final String TABLE_NAME="participantlist";
    private static final String ID_COL="id";
    private static final String NAME_COL="name";

    /*public DBHandler(Context context){

        super(context, DB_NAME,null,DB_VERSION);

    }*/

    public DBHandler(Context context){

        super(context, getCustomDatabasePath(context),null,DB_VERSION);

    }

    private static String getCustomDatabasePath(Context context) {
        File directory = context.getExternalFilesDir(null);
        String customDbPath = directory.getAbsolutePath() + "/participantdatabase.db";
        Log.d(TAG, "Custom Database Path: " + customDbPath);
        return customDbPath;
    }





    public void onCreate(SQLiteDatabase db){

        String query="CREATE TABLE " + TABLE_NAME +"("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT)";

        db.execSQL(query);

    }

    // Now creating a method to save data to database

    public void addNewParticipant(String participantName){


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(NAME_COL, participantName);
        db.insert(TABLE_NAME, null, values);

        db.close();


    }




    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }




}
