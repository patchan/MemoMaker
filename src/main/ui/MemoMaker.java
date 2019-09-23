package ui;

import model.Memo;

public class MemoMaker {
    public Memo memo;

    public MemoMaker() {
        memo = new Memo();
        memo.makeMemo(4);
        memo.printMemo();
    }

    public static void main(String[] args) {
        new MemoMaker();
    }
}
