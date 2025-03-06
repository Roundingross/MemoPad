package com.samcain.memopad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "mydatabase.db";
    private static final String TABLE_MEMOPAD = "memopad";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_MEMO = "memo";

    public DatabaseHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        String CREATE_TABLE_MEMOS = "CREATE TABLE memos ( _id INTEGER PRIMARY KEY AUTOINCREMENT, memo TEXT NOT NULL)";
        database.execSQL(CREATE_TABLE_MEMOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_MEMOPAD);
        onCreate(database);
    }

    public void addMemo(Memo memo) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, memo.getId());
        values.put(COLUMN_MEMO, memo.getMemo());

        SQLiteDatabase database = this.getWritableDatabase();
        database.insert(TABLE_MEMOPAD, null, null);
        database.close();
    }

    public Memo getMemo(int id) {
        Memo memo = null;
        String query = "SELECT * FROM " +TABLE_MEMOPAD + " WHERE " + COLUMN_ID + " = " + id;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            int newId = cursor.getInt(0);
            String newMemo = cursor.getString(1);
            cursor.close();;
            memo = new Memo(newId, newMemo);
        }

        database.close();
        return memo;
    }

    public String getAllMemos() {
        String query = "SELECT * FROM " + TABLE_MEMOPAD;
        StringBuilder stringBuilder = new StringBuilder();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            cursor.moveToFirst();
            do {
                int id = cursor.getInt(0);
                stringBuilder.append(getMemo(id)).append("\n");
            } while (cursor.moveToNext());
        }

        database.close();
        return stringBuilder.toString();
    }

    public ArrayList<Memo> getAllMemosAsList() {
        String query = "SELECT * FROM " + TABLE_MEMOPAD;
        ArrayList<Memo> allMemos = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            do {
                int newId = cursor.getInt(0);
                String newMemo = cursor.getString(1);
                allMemos.add(new Memo(newId, newMemo));
            } while (cursor.moveToNext());
        }

        database.close();
        return allMemos;
    }

}