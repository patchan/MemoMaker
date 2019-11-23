package model;

public class BarCounter implements Observer {
    private int barCount;

    public BarCounter() {}

    // EFFECTS: returns barCount
    public int getCount() {
        return barCount;
    }

    // MODIFIES: this
    // EFFECTS: increments barCount and prints out bar counter results
    @Override
    public void update() {
        barCount++;
        System.out.println("Bar Counter:");
        System.out.println("This memo has " + barCount + " bars.");
    }
}
