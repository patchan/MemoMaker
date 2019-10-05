package model;

public class HalfNote extends Note {

    public HalfNote(String name, int octave, int degree) {
        super(name, octave, degree);
        duration = 2;
    }
}
