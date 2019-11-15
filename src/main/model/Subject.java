package model;

import java.util.ArrayList;

public abstract class Subject {
    ArrayList<Observer> observers = new ArrayList<>();

    public void addObserver(Observer o) {
        if (!observers.contains(o)) {
            observers.add(o);
        }
    }

    public void notifyObservers() {
        for (Observer o : observers) {
            o.update();
        }
    }
}
