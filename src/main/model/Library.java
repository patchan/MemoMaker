package model;

import java.io.*;
import java.util.HashMap;
import java.util.Set;

public class Library implements Serializable, Readable, Writeable {
    private HashMap<String, Memo> memos;

    public Library() {
        memos = new HashMap<>();
    }

    // MODIFIES: this
    // EFFECTS: adds a new memo with name to the library
    public void addNewMemo(String name) {
        memos.put(name, new Memo());
    }

    // MODIFIES: this
    // EFFECTS: removes the memo with name from the library
    public void deleteMemo(String name) {
        memos.remove(name);
    }

    // MODIFIES: this
    // EFFECTS: clears all memos in the library
    public void clearLibrary() {
        memos.clear();
    }

    // EFFECTS: returns the Memo with the given name
    public Memo getMemo(String name) {
        return memos.get(name);
    }

    // EFFECTS: produce true if library contains memo with the given name
    public boolean contains(String name) {
        return memos.containsKey(name);
    }

    // EFFECTS: returns the total number of memos in the library
    public int size() {
        return memos.size();
    }

    // EFFECTS: prints the memo with the given name
    public void printMemo(String name) {
        Memo memo = memos.get(name);
        System.out.println("Your memo contains " + memo.barCount() + " bars:");
        for (Bar b : memo.getBars()) {
            b.printBar();
        }
    }

    // EFFECTS: prints the name of all memos in the library
    public void printLibraryMemos() {
        Set<String> memoNames = memos.keySet();
        System.out.println(memoNames);
    }

    // EFFECTS: returns a HashMap of all the memos in the library
    //          this method is a helper for testing the load libraries method
    public HashMap<String, Memo> returnLibrary() {
        return memos;
    }

    // this implementation of loadMemo() and saveMemo() was implemented based on the tutorial found here:
    // url = https://www.mkyong.com/java/how-to-read-and-write-java-object-to-a-file/
    // REQUIRES: file containing memo to load
    // EFFECTS: loads a memo object
    @Override
    public void load() throws IOException, ClassNotFoundException {
        FileInputStream loadedFile = new FileInputStream(new File("my_library"));
        ObjectInputStream loadedObjects = new ObjectInputStream(loadedFile);

        clearLibrary();
        memos = (HashMap<String, Memo>) loadedObjects.readObject();

        loadedObjects.close();
        loadedFile.close();
    }

    // REQUIRES: non-empty memo
    // MODIFIES: overwrites file being saved to
    // EFFECTS: saves the memo as a java object to a json file
    @Override
    public void save() throws IOException {
        FileOutputStream saveFile = new FileOutputStream(new File("my_library"));
        ObjectOutputStream saveObjects = new ObjectOutputStream(saveFile);

        saveObjects.writeObject(memos);

        saveObjects.close();
        saveFile.close();
    }
}
