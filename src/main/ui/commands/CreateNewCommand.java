package ui.commands;

import model.*;

import java.util.Scanner;

public class CreateNewCommand implements Command {
    // the implementation of the Scanner to receive user input was adapted from the B04 SimpleCalculator
    private transient Scanner scanner = new Scanner(System.in);

    public CreateNewCommand() {}

    // EFFECTS: creates a new memo
    @Override
    public void executeCommand(Memo memo) {
        int numBars = getScoreLength();
        int i = 0;
        memo.clearMemo();
        while (i < numBars) {
            Bar newBar = new Bar();
            makeBar(newBar);
            memo.addToMemo(newBar);
            i++;
        }
        memo.printMemo();
        System.out.println("NEW");
    }

    protected Bar makeBar(Bar bar) {
        double i = 0;
        int barLength = getBarLength();
        while (i < barLength) {
            double noteDur = getNoteDuration();
            Note note = makeNote(noteDur);
            bar.addToBar(note);
            i = i + noteDur;
        }
        return bar;
    }

    protected Note makeNote(double noteDur) {
        Note note = null;
        if (noteDur == 1) {
            note = new QuarterNote(getNoteName(), getNoteOctave(), getNoteDegree());
        } else if (noteDur == 2) {
            note = new HalfNote(getNoteName(), getNoteOctave(), getNoteDegree());
        } else if (noteDur == 0.5) {
            note = new EighthNote(getNoteName(), getNoteOctave(), getNoteDegree());
        } else if (noteDur == 0.25) {
            note = new SixteenthNote(getNoteName(), getNoteOctave(), getNoteDegree());
        }
        return note;
    }

    // REQUIRES: input is single character string
    // EFFECTS: returns user input for note name as string
    protected String getNoteName() {
        System.out.println("Enter a note name:");
        String name = scanner.nextLine();
        return name;
    }

    // REQUIRES: input is single character string
    // EFFECTS: returns user input for note name as string
    protected double getNoteDuration() {
        System.out.println("Enter a note duration:\n(Use 2 for half, 1 for quarter, 0.5 for 8th, 0.25 for 16th)");
        double noteDur = scanner.nextDouble();
        scanner.nextLine();
        return noteDur;
    }

    // REQUIRES: input is single character string
    // EFFECTS: returns user input for note name as string
    protected int getNoteOctave() {
        System.out.println("Enter the octave of the note:");
        int octave = scanner.nextInt();
        scanner.nextLine();
        return octave;
    }

    // REQUIRES: input is integer -1, 0, or 1
    // EFFECTS: returns user input for note degree as an integer
    protected int getNoteDegree() {
        System.out.println("Enter an accidental (-1 for flat, 0 for natural, 1 for sharp):");
        int degree = scanner.nextInt();
        scanner.nextLine();
        return degree;
    }

    // EFFECTS: returns user input for the number of bars to create in the memo
    protected int getScoreLength() {
        System.out.println("How many bars would you like to add?");
        int numBars;
        numBars = scanner.nextInt();
        scanner.nextLine();
        return numBars;
    }

    // EFFECTS: returns user input for the number of bars to create in the memo
    protected int getBarLength() {
        System.out.println("How many quarter notes are in your bar?");
        int barLength;
        barLength = scanner.nextInt();
        scanner.nextLine();
        return barLength;
    }

    @Override
    public boolean equals(Object command) {
        return command instanceof CreateNewCommand;
    }

}
