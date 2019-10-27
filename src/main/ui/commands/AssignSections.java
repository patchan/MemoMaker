package ui.commands;

import model.Bar;
import model.Memo;
import model.Section;

import java.util.Scanner;

public class AssignSections implements Command {
    // the implementation of the Scanner to receive user input was adapted from the B04 SimpleCalculator
    private transient Scanner scanner = new Scanner(System.in);
    private Section section;

    @Override
    public void executeCommand(Memo memo) {
        section = new Section(getSectionName());
        memo.addSection(section);
        for (Bar b : memo.getBars()) {
            b.printBar();
            System.out.println("Would you like to assign this bar to Section: " + section.getName() + "?");
            if (assignToSection()) {
                b.setSection(section);
                System.out.println("The bar has been added to Section: " + section.getName());
            }
        }
        System.out.println("Your memo has " + memo.countSections() + " sections");
    }

    private String getSectionName() {
        System.out.println("Enter a new section name:");
        return scanner.nextLine();
    }

    private boolean assignToSection() {
        String input = scanner.nextLine();
        if (input == "Y") {
            return true;
        } else {
            return false;
        }
    }
}
