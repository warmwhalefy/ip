package duke.command;

import java.nio.file.Path;

public class ListCommand extends Command {
// to view the list of tasks
    @Override
    public void executeCommand(String userInput, TaskList tasks, Path filePath) throws DukeException {
        tasks.viewTasks();
        Storage.updateFile(tasks, Path.of(Storage.fileName));
    }
}
