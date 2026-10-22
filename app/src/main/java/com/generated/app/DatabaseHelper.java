package com.generated.app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "zochrim.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_COUNTERS = "counters";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_START_DATE = "start_date";
    public static final String COLUMN_IS_HISTORICAL = "is_historical";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_COUNTERS_TABLE = "CREATE TABLE " + TABLE_COUNTERS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_START_DATE + " TEXT,"
                + COLUMN_IS_HISTORICAL + " INTEGER" + ")";
        db.execSQL(CREATE_COUNTERS_TABLE);

        // Insert default historical counter: Destruction of the Second Temple (~70 CE or traditional 0068-08-04 approx or similar fixed anchor)
        // Let's use a standard traditional calculation anchor: Av 9, 3828 (August 6, 70 CE -> approximate days calculation)
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, "חורבן בית המקדש השני");
        values.put(COLUMN_START_DATE, "0070-08-06");
        values.put(COLUMN_IS_HISTORICAL, 1);
        db.insert(TABLE_COUNTERS, null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COUNTERS);
        onCreate(db);
    }

    public long addCounter(CounterModel counter) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, counter.getName());
        values.put(COLUMN_START_DATE, counter.getStartDate());
        values.put(COLUMN_IS_HISTORICAL, counter.isHistorical() ? 1 : 0);
        long id = db.insert(TABLE_COUNTERS, null, values);
        db.close();
        return id;
    }

    public List<CounterModel> getAllCounters() {
        List<CounterModel> counterList = new ArrayList<CounterModel>();
        String selectQuery = "SELECT * FROM " + TABLE_COUNTERS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                CounterModel counter = new CounterModel(
                        cursor.getLong(cursor.getColumnIndex(COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_NAME)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_START_DATE)),
                        cursor.getInt(cursor.getColumnIndex(COLUMN_IS_HISTORICAL)) == 1
                );
                counterList.add(counter);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return counterList;
    }

    public void deleteCounter(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_COUNTERS, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }
}
