package com.enamnotes.enamapppartfour;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;

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

    public ArrayList<ParticipantModal> readParticipant() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorParticipant = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        ArrayList<ParticipantModal> participantModalArrayList = new ArrayList<>();

        if (cursorParticipant.moveToFirst()) {
            do {
                participantModalArrayList.add(new ParticipantModal(
                        cursorParticipant.getString(1)));
            } while (cursorParticipant.moveToNext());
        }
        cursorParticipant.close();
        return participantModalArrayList;
    }



    public void updateParticipant(String originalParticipantName,String ParticipantName) {

        // calling a method to get writable database.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(NAME_COL, ParticipantName);


        // on below line we are calling a update method to update our database and passing our values.
        // and we are comparing it with name of our course which is stored in original name variable.
        /*db.update(TABLE_NAME, values, "name=?", new String[]{originialParticipantName});*/
        db.update(TABLE_NAME, values, "name=?", new String[]{originalParticipantName});
        db.close();
    }

    // below is the method for deleting our participant.
    public void deleteParticipant(String participantName) {

        // on below line we are creating
        // a variable to write our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are calling a method to delete our data

        db.delete(TABLE_NAME, "name=?", new String[]{participantName});
        db.close();
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
}
