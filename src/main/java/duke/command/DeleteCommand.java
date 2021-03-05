package duke.command;

import duke.parser.Parser;

import java.nio.file.Path;

public class DeleteCommand extends Command {
// to delete task
    @Override
    public void executeCommand(String userInput, TaskList tasks, Path filePath) throws DukeException {
        int taskNo = Parser.getTaskNo(userInput);
        tasks.deleteTask(taskNo);
        Storage.updateFile(tasks, Path.of(Storage.fileName));
    }
}
