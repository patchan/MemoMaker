package ui;

import org.junit.jupiter.api.Test;
import ui.commands.*;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserInterfaceTest {

    int inputNewMemo = 1;
    int inputAddTo = 2;
    int inputLoad = 3;
    int inputSave = 4;
    int inputQuit = 5;

    @Test
    public void parseNewCommandTest() {
        Command command = new CreateNewMemo();
        assertEquals(command, UserInterface.parseUserCommand(inputNewMemo));
    }

    @Test
    public void parseAddCommandTest() {
        Command command = new AddToMemo();
        assertEquals(command, UserInterface.parseUserCommand(inputAddTo));
    }

    @Test
    public void parseLoadCommandTest() {
        Command command = new LoadCommand();
        assertEquals(command, UserInterface.parseUserCommand(inputLoad));
    }

    @Test
    public void parseSaveCommandTest() {
        Command command = new SaveCommand();
        assertEquals(command, UserInterface.parseUserCommand(inputSave));
    }

    @Test
    public void parseQuitCommandTest() {
        Command command = new QuitCommand();
        assertEquals(command, UserInterface.parseUserCommand(inputQuit));
    }
}
