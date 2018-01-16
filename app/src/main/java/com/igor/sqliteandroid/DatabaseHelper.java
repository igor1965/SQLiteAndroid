package com.igor.sqliteandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Igor on 1/7/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "phonebook.db";
    public static final String TABLE_NAME = "phone_table";
    public static final String COLUMN_1 = "ID";
    public static final String COLUMN_2 = "FIRST_NAME";
    public static final String COLUMN_3 = "LAST_NAME";
    public static final String COLUMN_4 = "NUMBER";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ TABLE_NAME +"(ID INTEGER PRIMARY KEY AUTOINCREMENT,FIRST_NAME TEXT,LAST_NAME TEXT,NUMBER INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);

    }
    public boolean insertData(String first_name,String last_name,String number){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_2,first_name);
        contentValues.put(COLUMN_3,last_name);
        contentValues.put(COLUMN_4,number);
       long result = database.insert(TABLE_NAME,null,contentValues);
       if (result == -1)
           return false;
           else
               return true;

    }
    public Cursor getAllData(){
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("select * from "+ TABLE_NAME,null);
        return cursor;
    }
    public boolean updateData(String id,String first_name,String last_name,String number){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_1,id);
        contentValues.put(COLUMN_2,first_name);
        contentValues.put(COLUMN_3,last_name);
        contentValues.put(COLUMN_4,number);
        database.update(TABLE_NAME,contentValues,"id = ?",new String[]{id});
        return true;

    }
    public Integer deleteData(String id){
        SQLiteDatabase database = this.getWritableDatabase();
        return database.delete(TABLE_NAME,"ID = ?",new String[]{id});

    }
}
