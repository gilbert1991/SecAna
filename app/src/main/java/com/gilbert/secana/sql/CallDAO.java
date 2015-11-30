package com.gilbert.secana.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gilbert.secana.data.Call;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${Gilbert} on ${31/3/15}.
 */
public class CallDAO {

    public static final String TABLE_NAME = "call";

    public static final String KEY_ID = "_id";

    public static final String NUMBER_COLUMN = "number";
    public static final String DATE_COLUMN = "date";
    public static final String LEVEL_COLUMN = "level";
    public static final String REASON_COLUMN = "reason";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    NUMBER_COLUMN + " TEXT NOT NULL, " +
                    DATE_COLUMN + " INTEGER NOT NULL, " +
                    LEVEL_COLUMN + " INTEGER, " +
                    REASON_COLUMN + " TEXT)";

    private SQLiteDatabase db;

    public CallDAO(Context context) {
        db = HistoryDBHelper.getDatabase(context);
    }

    public void close() {
        db.close();
    }

    public Call insert(Call call) {
        ContentValues cv = new ContentValues();

        cv.put(NUMBER_COLUMN, call.number);
        cv.put(DATE_COLUMN, call.date);
        cv.put(LEVEL_COLUMN, call.level);
        cv.put(REASON_COLUMN, call.reason);

        call.id = db.insert(TABLE_NAME, null, cv);

        return call;
    }

    public boolean update(Call call) {
        ContentValues cv = new ContentValues();

        cv.put(NUMBER_COLUMN, call.number);
        cv.put(DATE_COLUMN, call.date);
        cv.put(LEVEL_COLUMN, call.level);
        cv.put(REASON_COLUMN, call.reason);

        String where = KEY_ID + "=" + call.id;

        return db.update(TABLE_NAME, cv, where, null) > 0;
    }

    public boolean delete(double id) {
        String where = KEY_ID + "=" + id;

        return db.delete(TABLE_NAME, where, null) > 0;
    }

    public List<Call> getAll() {
        List<Call> callList = new ArrayList<>();

        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            callList.add(getRecord(cursor));
        }

        cursor.close();

        return callList;
    }

    public Call get(double id) {
        Call call = null;

        String where = KEY_ID + "=" + id;

        Cursor cursor = db.query(TABLE_NAME, null, where, null, null, null, null, null);

        if(cursor.moveToFirst()) {
            call = getRecord(cursor);
        }

        cursor.close();

        return call;
    }

    public Call getRecord(Cursor cursor) {

        Call call = new Call();

        call.id = cursor.getDouble(0);
        call.number = cursor.getString(1);
        call.date = cursor.getLong(2);
        call.level = cursor.getDouble(3);
        call.reason = cursor.getString(4);

        return call;
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
