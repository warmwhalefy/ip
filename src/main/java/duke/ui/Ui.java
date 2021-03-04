package duke.ui;

import duke.command.DukeException;
import duke.parser.Parser;

import java.util.Scanner;
import static duke.Duke.SECTION_DIVIDER;
import static duke.parser.Parser.KEYWORD_BYE;

public class Ui {
    private static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public void greetUser(){
        System.out.println("Hello from\n" + logo);
        System.out.println(SECTION_DIVIDER);
        System.out.println("Hello! I'm Duke.");
        System.out.println("What can I do for you?");
        System.out.println(SECTION_DIVIDER);
    }

    public boolean sayBye(String byeInput) {
        if (byeInput.toLowerCase().contains(KEYWORD_BYE)) {
            System.out.println("Bye. Hope to see you again soon!");
            System.out.println(SECTION_DIVIDER);
            return true;
        } else {
            return false;
        }
    }

    public static String getUserInput() throws DukeException {
        Scanner scan = new Scanner(System.in);
        String userInput = scan.nextLine();
        Parser.handleErrorUserInputs(userInput);
        while (Parser.isInvalidInput) {
            userInput = scan.nextLine();
            try {
                Parser.handleErrorUserInputs(userInput);
            } catch (DukeException e) {
                Parser.isInvalidInput = true;
            }
        }
        return userInput;
    }

    public Ui() {
    }

    public void showLoadingError(String message) {
        System.out.println(message);
        System.out.println(SECTION_DIVIDER);
    }

    public void showErrorMessage(String message) {
        System.out.println(message);
        System.out.println(SECTION_DIVIDER);
    }
}
