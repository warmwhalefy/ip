package duke.command;

import duke.command.TaskList;
import duke.command.DukeException;
import duke.task.Task;
import duke.parser.Parser;

import java.nio.file.Path;
import java.util.ArrayList;

import static duke.Duke.SECTION_DIVIDER;

public class FindCommand extends Command {
    @Override
    public void executeCommand(String userInput, TaskList tasks, Path filePath) throws DukeException {
        String keyword = Parser.findKeyword(userInput);
        ArrayList<Task> relatedTasks = tasks.findTasks(keyword);
        printRelatedTasks(relatedTasks);
    }

    private void printRelatedTasks(ArrayList<Task> relatedTasks) {
        if (relatedTasks.isEmpty()){
            System.out.println("There are no matching tasks in your list.");
            System.out.println(SECTION_DIVIDER);
        }
        else {
            System.out.println("Here are the matching tasks in your list:");
            int i = 1;
            for (Task t : relatedTasks) {
                System.out.println(i + ": " + t.toString());
                i++;
            }
            System.out.println(SECTION_DIVIDER);
        }
    }
}