package model;

public enum NoteName {
    A("A"), B("B"), C("C"), D("D"), E("E"), F("F"), G("G");

    private String noteName;

    NoteName(String s) {
        this.noteName = s;
    }

    public String getNoteName() {
        return this.noteName;
    }
}
