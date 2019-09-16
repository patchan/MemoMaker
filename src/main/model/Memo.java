package model;

import java.util.ArrayList;

public class Memo {
    private ArrayList<String> memo;

    public Memo() {
        memo = new ArrayList<>();
    }

    public void add(String note) {
        memo.add(note);
    }

    public ArrayList<String> printMemo() {
        return memo;
    }
}