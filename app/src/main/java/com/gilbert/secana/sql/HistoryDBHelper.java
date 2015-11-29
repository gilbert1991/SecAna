package com.gilbert.secana.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ${Gilbert} on ${31/3/15}.
 */
public class HistoryDBHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "history.db";
    public static final int VERSION = 1;
    public static SQLiteDatabase database;

    public HistoryDBHelper(Context context, String name, CursorFactory factory,
                           int version) {
        super(context, name, factory, version);
    }

    public static SQLiteDatabase getDatabase(Context context) {
        if (database == null || !database.isOpen()) {
            database = new HistoryDBHelper(context, DATABASE_NAME,
                    null, VERSION).getWritableDatabase();
        }

        return database;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SmsDAO.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + SmsDAO.TABLE_NAME);

        onCreate(sqLiteDatabase);
    }
}
