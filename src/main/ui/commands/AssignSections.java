package ui.commands;

import model.Bar;
import model.Library;
import model.Memo;
import model.Section;

import java.util.Scanner;

public class AssignSections implements Command {
    // the implementation of the Scanner to receive user input was adapted from the B04 SimpleCalculator
    private transient Scanner scanner = new Scanner(System.in);
    private Section section;

    // EFFECTS: assigns sections to each bar in the memo
    @Override
    public void executeCommand(Library library) {
        library.printMemosInLibrary();
        Memo memo = library.getMemo(getMemoToEdit());
        section = new Section(getSectionName());
        for (Bar b : memo.getBars()) {
            int barNum = b.getBarNum();
            String secName = section.getName();
            System.out.println("Would you like to assign bar" + barNum + " to Section: " + secName + "?");
            System.out.println("Enter 'Y' or 'N'");
            if (assignToSection()) {
                b.setSection(section);
                System.out.println("Bar " + barNum + " has been added to Section: " + secName);
                memo.addSection(section);
            }
        }
        System.out.println("Your memo has " + memo.countSections() + " sections:");
        System.out.println(memo.listSectionNames());
    }

    // EFFECTS: returns user input for memo name to edit
    public String getMemoToEdit() {
        System.out.println("Enter the memo name you would like to assign sections to:");
        return scanner.nextLine();
    }

    // EFFECTS: returns user input for the section name
    private String getSectionName() {
        System.out.println("Enter a new section name:");
        return scanner.nextLine();
    }

    // EFFECTS: produces true user input is "Y"
    private boolean assignToSection() {
        String input = scanner.nextLine();
        return input.equals("Y");
    }
}
