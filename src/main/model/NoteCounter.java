package model;

public class NoteCounter implements Observer {

    private int noteCount;

    public NoteCounter() {

    }

    public int getCount() {
        return noteCount;
    }

    @Override
    public void update() {
        noteCount++;
        System.out.println("Note Counter:");
        System.out.println("This bar has " + noteCount + " notes.");
    }
}
