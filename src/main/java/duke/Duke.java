package duke;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static final int INDEX_TODO = 5;
    public static final int INDEX_EVENT = 6;
    public static final int INDEX_DEADLINE = 9;
    public static final String SECTION_DIVIDER = "____________________";
    public static boolean invalidInput = false;
    private static ArrayList<Task> TaskArray = new ArrayList<>();

    public static void main(String[] args) {
        String logo = " __        _        \n"
                + "|  _ \\ _   | | __ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| || | || |   <  __/\n"
                + "|_/ \\,||\\\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke.");
        System.out.println("What can I do for you?");
        System.out.println(SECTION_DIVIDER);

        Scanner scan = new Scanner(System.in);
        String userInput;
        userInput = getUserInput(scan);
        while (invalidInput) {
            userInput = getUserInput(scan);
        }
        while (sayBye(userInput) == 0) {
            if (userInput.contains("list")) {
                viewTasks(TaskArray);
            } else if (userInput.contains("done")) {
                markTaskAsDone(userInput);
            } else if (userInput.contains("delete")) {
                deleteTask(userInput);
            } else {
                addTaskToArray(userInput);
            }
            userInput = getUserInput(scan);
            while (invalidInput) {
                userInput = getUserInput(scan);
            }
        }
    }

    public static String getUserInput(Scanner scan) {
        String userInput;
        userInput = scan.nextLine();
        try {
            handleErrorUserInputs(userInput);
        } catch (TaskException e) {
            invalidInput = true;
            System.out.println("☹️ OOPS!!! The description of a todo cannot be empty.");
            System.out.println(SECTION_DIVIDER);
        } catch (EventException e) {
            invalidInput = true;
            System.out.println("☹️ OOPS!!! The description of an event cannot be empty or is incomplete (/at).");
            System.out.println(SECTION_DIVIDER);
        } catch (DeadlineException e) {
            invalidInput = true;
            System.out.println("☹️ OOPS!!! The description of a deadline cannot be empty or is incomplete (/by).");
            System.out.println(SECTION_DIVIDER);
        }
        return userInput;
    }


    public static void addTaskToArray(String userInput) {
        Task taskAdded = null;
        if (userInput.contains("deadline")) {
            taskAdded = addDeadlineToArray(userInput);
        } else if (userInput.contains("event")) {
            taskAdded = addEventToArray(userInput);
        } else if (userInput.contains("todo")){
            taskAdded = addTodoToArray(userInput);
        }
        System.out.println("Got it. I've added this task:" + System.lineSeparator() + taskAdded);
        System.out.println("Now you have " + TaskArray.size() + " tasks in the list.");
        System.out.println(SECTION_DIVIDER);
    }

    public static Todo addTodoToArray(String userInput) {
        int descriptionIndex = userInput.indexOf("todo") + INDEX_TODO;
        Todo todoAdded = new Todo(userInput.substring(descriptionIndex));
        TaskArray.add(todoAdded);
        return todoAdded;
    }

    public static Event addEventToArray(String userInput) {
        int descriptionIndex = userInput.indexOf("event") + INDEX_EVENT;
        int byIndex = userInput.indexOf("/at");
        Event eventAdded = new Event(userInput.substring(descriptionIndex, byIndex-1), userInput.substring(byIndex + 4));
        TaskArray.add(eventAdded);
        return eventAdded;
    }

    public static Deadline addDeadlineToArray(String userInput) {
        int descriptionIndex = userInput.indexOf("deadline") + INDEX_DEADLINE;
        int byIndex = userInput.indexOf("/by");
        Deadline deadlineAdded = new Deadline(userInput.substring(descriptionIndex, byIndex-1), userInput.substring(byIndex + 4));
        TaskArray.add(deadlineAdded);
        return deadlineAdded;
    }

    public static void markTaskAsDone(String userInput) {
        userInput = userInput.replaceAll("[^0-9]", "");
        try {
            int taskNo = Integer.parseInt(userInput);
            if (taskNo > TaskArray.size()) {
                System.out.println("You only have " + TaskArray.size() + " task(s)!");
            } else {
                TaskArray.get(taskNo - 1).markAsDone();
                System.out.println("Nice! I have marked this task as done:" + TaskArray.get(taskNo - 1));
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid Input! Input format should have an integer e.g. done 2");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid Input! Integer cannot be 0!");
        }
        System.out.println(SECTION_DIVIDER);
    }

    private static void deleteTask(String userInput) {
        userInput = userInput.replaceAll("[^0-9]", "");
        try {
            int taskNo = Integer.parseInt(userInput);
            if (taskNo > TaskArray.size()) {
                System.out.println("You only have " + TaskArray.size() + " task(s)!");
            } else {
                System.out.println("Noted. I've removed this task: " + System.lineSeparator() + TaskArray.get(taskNo - 1));
                TaskArray.remove(taskNo - 1);
                System.out.println("Now you have " + TaskArray.size() + " tasks in the list.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid Input! Input format should have an integer e.g. done 2");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid Input! Integer cannot be 0!");
        } finally {
            System.out.println(SECTION_DIVIDER);
        }
    }

    public static void viewTasks(ArrayList<Task> arrayOfTasks) {
        if (arrayOfTasks.size() == 0) {
            System.out.println("There are no tasks in your list.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 1; i <= arrayOfTasks.size(); i++) {
                System.out.println(i + ". " + arrayOfTasks.get(i - 1));
            }
        }
        System.out.println(SECTION_DIVIDER);
    }

    public static int sayBye(String bye) {
        if (bye.toLowerCase().contains("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
            System.out.println(SECTION_DIVIDER);
            return 1;
        } else {
            return 0;
        }
    }

    public static void handleErrorUserInputs(String userInput) throws TaskException, EventException, DeadlineException {
        userInput = userInput.toLowerCase().trim();

        if (userInput.contains("todo") && !(userInput.contains("event")) && !(userInput.contains("deadline"))) {
            validateTodo(userInput);
        } else if (userInput.contains("event") && !(userInput.contains("todo")) && !(userInput.contains("deadline"))) {
            validateEvent(userInput);
        } else if (userInput.contains("deadline") && !(userInput.contains("todo")) && !(userInput.contains("event"))) {
            validateDeadline(userInput);
        } else if (userInput.contains("list")
                || userInput.contains("bye")
                || userInput.contains("done")
                || userInput.contains("delete")) {
            invalidInput = false;
        } else {
            invalidInput = true;
            System.out.println("☹️ OOPS!!! I'm sorry, but I don't know what that means :-(");
            System.out.println(SECTION_DIVIDER);
        }
    }

    public static void validateDeadline(String userInput) throws DeadlineException {
        if (userInput.substring(INDEX_DEADLINE - 1).trim().isEmpty()
                || !(userInput.contains("/by"))
                || userInput.substring(userInput.indexOf("/by") + 3).trim().isEmpty() ) {
            throw new DeadlineException();
        } else {
            invalidInput = false;
        }
    }

    public static void validateEvent(String userInput) throws EventException {
        if (userInput.substring(INDEX_EVENT - 1).trim().isEmpty()
                || !(userInput.contains("/at"))
                || userInput.substring(userInput.indexOf("/at") + 3).trim().isEmpty()) {
            throw new EventException();
        } else {
            invalidInput = false;
        }
    }

    public static void validateTodo(String userInput) throws TaskException {
        if (userInput.substring(INDEX_TODO - 1).trim().isEmpty()) {
            throw new TaskException();
        } else {
            invalidInput = false;
        }
    }
}