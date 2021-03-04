package duke.command;

public class DukeException extends Exception {
//to print error message
    public DukeException(String errorMessage) {
        System.out.println(errorMessage);
    }
}