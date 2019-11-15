package model;

public class BarCounter implements Observer {
    private int barCount;

    public BarCounter() {}

    public int getCount() {
        return barCount;
    }

    @Override
    public void update() {
        barCount++;
        System.out.println("Bar Counter:");
        System.out.println("This memo has " + barCount + " bars.");
    }
}
