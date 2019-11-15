package model;

import java.util.ArrayList;

public abstract class Subject {
    ArrayList<Observer> observers = new ArrayList<>();

    public void addObserver(Observer o) {
        observers.add(o);
    }

    public abstract void notifyObservers();
}
