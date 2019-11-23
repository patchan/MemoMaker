package model;

import java.util.ArrayList;

public abstract class Subject {
    ArrayList<Observer> observers = new ArrayList<>();

    // MODIFIES: this
    // EFFECTS: adds an observer to observers
    public void addObserver(Observer o) {
        if (!observers.contains(o)) {
            observers.add(o);
        }
    }

    // EFFECTS: updates each observer in observers
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update();
        }
    }
}
