package pl.dritms.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(@Nullable Context context) {
        super(context, "szo.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table role(" +
                "id integer primary key autoincrement, " +
                "name text," +
                "description text" +
                ")");

        db.execSQL("create table behaviour(" +
                "id integer primary key autoincrement, " +
                "name text," +
                "description text" +
                ")");

        db.execSQL("create table setting(" +
                "id integer primary key autoincrement, " +
                "name text," +
                "value text" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
