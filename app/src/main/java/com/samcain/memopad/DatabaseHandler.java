package com.samcain.memopad;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

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




















}
