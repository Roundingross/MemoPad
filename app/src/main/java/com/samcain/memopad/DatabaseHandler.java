package com.samcain.memopad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import java.util.ArrayList;

/**
 * Handles the database operations for the app.
 */
public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "mydatabase.db";
    private static final String TABLE_MEMOPAD = "memopad";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_MEMO = "memo";

    /**
     * Constructs a new instance of the database handler.
     * @param context - application context
     * @param name - name of the database
     * @param factory - cursor factory
     * @param version - database version
     */
    public DatabaseHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name != null ? name : DATABASE_NAME, factory, version > 0 ? version : DATABASE_VERSION);
    }


    // SQLiteOpenHelper methods
    @Override
    public void onCreate(SQLiteDatabase database) {
        String CREATE_TABLE_MEMOS = "CREATE TABLE " + TABLE_MEMOPAD + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, memo TEXT NOT NULL)";
        database.execSQL(CREATE_TABLE_MEMOS);
    }
    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_MEMOPAD);
        onCreate(database);
    }

    /**
     * Adds a new memo to the database.
     * @param memo - new memo
     */
    public void addMemo(Memo memo) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_MEMO, memo.getMemo());

        SQLiteDatabase database = this.getWritableDatabase();
        database.insert(TABLE_MEMOPAD, null, values);
        database.close();
    }

    /**
     * Deletes a memo from the database.
     * @param id - id of the memo
     */
    public void deleteMemo(int id) {
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(TABLE_MEMOPAD, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        database.close();
    }

    /**
     * Retrieves all memos from the database.
     * @return - list of all memos
     */
    public ArrayList<Memo> getAllMemosAsList() {
        SQLiteDatabase database = this.getReadableDatabase();
        ArrayList<Memo> allMemos = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_MEMOPAD, null);

        if (cursor.moveToFirst()) {
            do {
                int newId = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
                String newMemo = cursor.getString(cursor.getColumnIndexOrThrow("memo"));
                allMemos.add(new Memo(newId, newMemo));
            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();
        return allMemos;
    }
}