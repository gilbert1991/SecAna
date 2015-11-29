package com.gilbert.secana.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gilbert.secana.data.Sms;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${Gilbert} on ${31/3/15}.
 */
public class SmsDAO {

    public static final String TABLE_NAME = "sms";

    public static final String KEY_ID = "_id";

    public static final String NUMBER_COLUMN = "number";
    public static final String CONTENT_COLUMN = "content";
    public static final String DATE_COLUMN = "date";
    public static final String LEVEL_COLUMN = "level";
    public static final String REASON_COLUMN = "reason";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    NUMBER_COLUMN + " TEXT NOT NULL, " +
                    CONTENT_COLUMN + " TEXT NOT NULL, " +
                    DATE_COLUMN + " TEXT NOT NULL, " +
                    LEVEL_COLUMN + " INTEGER, " +
                    REASON_COLUMN + " TEXT)";

    private SQLiteDatabase db;

    public SmsDAO(Context context) {
        db = HistoryDBHelper.getDatabase(context);
    }

    public void close() {
        db.close();
    }

    public Sms insert(Sms sms) {
        ContentValues cv = new ContentValues();

        cv.put(NUMBER_COLUMN, sms.number);
        cv.put(CONTENT_COLUMN, sms.content);
        cv.put(DATE_COLUMN, sms.date);
        cv.put(LEVEL_COLUMN, sms.level);
        cv.put(REASON_COLUMN, sms.reason);

        sms.id = db.insert(TABLE_NAME, null, cv);

        return sms;
    }

    public boolean update(Sms sms) {
        ContentValues cv = new ContentValues();

        cv.put(NUMBER_COLUMN, sms.number);
        cv.put(CONTENT_COLUMN, sms.content);
        cv.put(DATE_COLUMN, sms.date);
        cv.put(LEVEL_COLUMN, sms.level);
        cv.put(REASON_COLUMN, sms.reason);

        String where = KEY_ID + "=" + sms.id;

        return db.update(TABLE_NAME, cv, where, null) > 0;
    }

    public boolean delete(double id) {
        String where = KEY_ID + "=" + id;

        return db.delete(TABLE_NAME, where, null) > 0;
    }

    public List<Sms> getAll() {
        List<Sms> smsList = new ArrayList<>();

        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            smsList.add(getRecord(cursor));
        }

        cursor.close();

        return smsList;
    }

    public Sms get(double id) {
        Sms sms = null;

        String where = KEY_ID + "=" + id;

        Cursor cursor = db.query(TABLE_NAME, null, where, null, null, null, null, null);

        if(cursor.moveToFirst()) {
            sms = getRecord(cursor);
        }

        cursor.close();

        return sms;
    }

    public Sms getRecord(Cursor cursor) {

        Sms sms = new Sms();

        sms.id = cursor.getDouble(0);
        sms.number = cursor.getString(1);
        sms.content = cursor.getString(2);
        sms.date = cursor.getString(3);
        sms.level = cursor.getDouble(4);
        sms.reason = cursor.getString(5);

        return sms;
    }

    public int getCount() {
        int count = 0;

        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_NAME, null);

        if(cursor.moveToNext()) {
            count = cursor.getInt(0);
        }

        return count;
    }
}
