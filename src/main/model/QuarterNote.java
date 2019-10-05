package model;

public class QuarterNote extends Note {

    public QuarterNote(String name, int octave, int degree) {
        super(name, octave, degree);
        duration = 1;
    }
}
