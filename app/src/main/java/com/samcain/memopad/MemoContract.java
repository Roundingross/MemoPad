package com.samcain.memopad;

import java.util.List;

/**
 * Defines the contract between the view and the presenter.
 */
public interface MemoContract {
    /**
     * View interface implemented by MainActivity.
     */
    interface View {
        void updateRecyclerView(List<Memo> memos);
        void clearInput();
    }

    /**
     * Presenter interface implemented by MemoPresenter.
     */
    interface Presenter {
        void addMemo(String memoText);

        void deleteMemo(int id);

        void loadMemos();
    }
}
