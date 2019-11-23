package model;

public class NoteCounter implements Observer {

    private int noteCount;

    public NoteCounter() {

    }

    // EFFECTS: returns noteCount
    public int getCount() {
        return noteCount;
    }

    // EFFECTS: increments noteCount and prints out note counter information
    @Override
    public void update() {
        noteCount++;
        System.out.println("Note Counter:");
        System.out.println("This bar has " + noteCount + " notes.");
    }
}
