package duke.command;

import duke.parser.Parser;

import java.nio.file.Path;

public class DoneCommand extends Command {

    @Override
    //marking task as done
    public void executeCommand(String userInput, TaskList tasks, Path filePath) throws DukeException {
        int taskNo = Parser.getTaskNo(userInput);
        tasks.markTaskAsDone(taskNo);
        Storage.updateFile(tasks, Path.of(Storage.fileName));
    }
}
