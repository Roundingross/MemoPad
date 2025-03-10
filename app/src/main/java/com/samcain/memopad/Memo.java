package com.samcain.memopad;

import androidx.annotation.NonNull;

/**
 * Represents a memo object.
 */
public class Memo {
    private final int id;
    private final String memo;

    /**
     * Constructs a new instance of the memo.
     * @param id
     * @param memo
     */
    public Memo(int id, String memo) {
        this.id = id;
        this.memo = memo;
    }

    /**
     * Constructs a new instance of the memo, and sets the id to -1.
     * @param memo - memo text
     */
    public Memo(String memo) {
        this.id = -1;
        this.memo = memo;
    }

    // Getters
    public int getId() {
        return id;
    }
    public String getMemo() {
        return memo;
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("ID: #").append(id).append("\n");
        s.append("Memo: ").append(memo).append("\n");
        return s.toString();
    }
}
