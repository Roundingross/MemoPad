package com.samcain.memopad;

public class Memo {
    private int id;
    private String memo;

    public Memo(int id, String memo) {
        this.id = id;
        this.memo = memo;
    }

    // Getters
    public int getId() {
        return id;
    }
    public String getMemo() {
        return memo;
    }

    // Setters
    public void setId() {
        this.id = id;
    }
    public void setMemo() {
        this.memo = memo;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("ID: #").append(id).append("\n");
        s.append("Memo: ").append(memo).append("\n");
        return s.toString();
    }
}
