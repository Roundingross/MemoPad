package com.samcain.memopad;

import android.content.Context;
import android.util.Log;
import java.util.List;

/**
 * Handles the business logic for the app.
 * Stands as intermediary between the view (MainActivity) and the database (DatabaseHandler).
 */
public class MemoPresenter implements MemoContract.Presenter {
    private final MemoContract.View view;
    private final DatabaseHandler database;

    /**
     * Constructs a new instance of the presenter.
     * @param view - view interface
     * @param context - application context
     */
    public MemoPresenter(MemoContract.View view, Context context) {
        this.view = view;
        this.database = new DatabaseHandler(context, null, null, 3);
    }

    /**
     * Connects to the database and adds a new memo.
     * @param memoText - text of the new memo
     */
    @Override
    public void addMemo(String memoText) {
        if (!memoText.isEmpty()) {
            database.addMemo(new Memo(memoText));
            view.clearInput();
            loadMemos();
        }
    }

    /**
     * Connects to the database and deletes a memo.
     * @param id - id of the memo to delete
     */
    @Override
    public void deleteMemo(int id) {
        database.deleteMemo(id);
        loadMemos();
    }

    /**
     * Connects to the database and loads all memos.
     */
    @Override
    public void loadMemos() {
        List<Memo> memoList = database.getAllMemosAsList();
        Log.d("DatabaseDebug", "Loaded " + memoList.size() + " memos from database.");
        view.updateRecyclerView(memoList);
    }

}
