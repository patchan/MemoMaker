package ui;

import model.Memo;

import java.util.Scanner;

public class Main {
    // the implementation of the Scanner to receive user input was adapted from the B04 SimpleCalculator
    Scanner scanner = new Scanner(System.in);
    String userInput;
    Memo memo;

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        int barLength = 4;
        int i = 0;
        memo = new Memo();
        while (i < barLength) {
            this.userInput = keyInput();
            addNote(this.userInput);
            i++;
        }
        System.out.println("One bar has been entered containing: " + memo.printMemo());
    }

    public String keyInput() {
        System.out.println("Enter a quarter note:");
        userInput = scanner.nextLine();
        System.out.println("You have entered an: " + userInput);
        return userInput;
    }

    public void addNote(String note) {
        memo.add(note);
        System.out.println("The note \"" + note + "\" has been added to your memo.");
    }

}