import java.util.Scanner;

public class Duke {
    static int noOfTasks = 0;
    static String sectionDivider = "____________________________________________________________";
    public static boolean invalidInput = false;

    public static void main(String[] args) {
        Task[] TaskArray = new Task[100];
        Task newItem = null;
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(sectionDivider);
        System.out.println("Hello! I'm Duke");
        System.out.println("Please type tasks to do OR (list) to list all the tasks OR (bye) to exit.");
        Scanner sc= new Scanner(System.in);
        String userInput;

        userInput = getUserInput(sc);

        // Clean text input, handle errors
        while (invalidInput) {
            userInput = getUserInput(sc);
        }

        while (true) {
            if (userInput.equals("bye")) {
                System.out.println(sectionDivider);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(sectionDivider);
                break;
            }
            if (userInput.equals("list")){
                System.out.println(sectionDivider);
                System.out.println("Here are the tasks in your to-do list:");
                for(int i= 1; i!=noOfTasks+1; i++){
                    System.out.println(i + "." + TaskArray[i-1].toString());
                }
                System.out.println(sectionDivider);
            }
            if (userInput.contains("done")){
                System.out.println(sectionDivider);
                String[] splitInput = userInput.split(" ");
                int taskNumber = Integer.parseInt(splitInput[1]);
                TaskArray[taskNumber-1].markAsDone();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(taskNumber + ". [" + TaskArray[taskNumber-1].getStatusIcon() + " ]" + TaskArray[taskNumber-1].getDescription());
                System.out.println(sectionDivider);
            } else {
                System.out.println(sectionDivider);
                String[] splitInput = userInput.split(" ");
                if (splitInput[0].equals("deadline")) {
                    int wordsCount = splitInput.length;
                    String descriptionString = "";
                    String byString = "";
                    int byIndex = 0;
                    for (int i = 1; i != wordsCount; i++) {
                        if (splitInput[i].equals("/by")) {
                            byIndex = i + 1;
                            break;
                        } else {
                            descriptionString = descriptionString + splitInput[i] + " ";
                        }
                    }
                    while (byIndex != wordsCount) {
                        byString = byString + splitInput[byIndex] + " ";
                        byIndex++;
                    }
                    newItem = new Deadline(descriptionString, byString);
                    TaskArray[noOfTasks] = newItem;
                    noOfTasks = noOfTasks + 1;
                    System.out.println("Got it. I've added this task:");
                    System.out.println(TaskArray[noOfTasks - 1].toString());
                    System.out.println("Now you have " + noOfTasks + " tasks in the list.");
                    System.out.println(sectionDivider);
                }
                if (splitInput[0].equals("event")) {
                    int wordsCount = splitInput.length;
                    String descriptionString = "";
                    String atString = "";
                    int atIndex = 0;
                    for (int i = 1; i != wordsCount; i++) {
                        if (splitInput[i].equals("/at")) {
                            atIndex = i + 1;
                            break;
                        } else {
                            descriptionString = descriptionString + splitInput[i] + " ";
                        }
                    }
                    while (atIndex != wordsCount) {
                        atString = atString + splitInput[atIndex] + " ";
                        atIndex++;
                    }
                    newItem = new Event(descriptionString, atString);
                    TaskArray[noOfTasks] = newItem;
                    noOfTasks = noOfTasks + 1;
                    System.out.println("Got it. I've added this task:");
                    System.out.println(TaskArray[noOfTasks - 1].toString());
                    System.out.println("Now you have " + noOfTasks + " tasks in the list.");
                    System.out.println(sectionDivider);
                }
                if (splitInput[0].equals("todo")) {
                    int wordsCount = splitInput.length;
                    String descriptionString = "";
                    for (int i = 1; i != wordsCount; i++) {
                        descriptionString = descriptionString + splitInput[i] + " ";
                    }
                    newItem = new Todo(descriptionString);
                    TaskArray[noOfTasks] = newItem;
                    noOfTasks = noOfTasks + 1;
                    System.out.println("Got it. I've added this task:");
                    System.out.println(TaskArray[noOfTasks - 1].toString());
                    System.out.println("Now you have " + noOfTasks + " tasks in the list.");
                    System.out.println(sectionDivider);
                }
            }
        }
    }
    public static String getUserInput(Scanner sc) {
        String userInput;
        userInput = sc.nextLine();
        try {
            handleErrorUserInputs(userInput);
        } catch (TaskException e) {
            invalidInput = true;
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
            System.out.println(sectionDivider);
        } catch (EventException e) {
            invalidInput = true;
            System.out.println("☹ OOPS!!! The description of an event cannot be empty or is incomplete (/at).");
            System.out.println(sectionDivider);
        } catch (DeadlineException e) {
            invalidInput = true;
            System.out.println("☹ OOPS!!! The description of a deadline cannot be empty or is incomplete (/by).");
            System.out.println(sectionDivider);
        }
        return userInput;
    }
    public static void handleErrorUserInputs(String userInput) throws TaskException, EventException, DeadlineException {
        userInput = userInput.toLowerCase().trim();

        // check if todo description is empty
        if (userInput.contains("todo") && !(userInput.contains("event")) && !(userInput.contains("deadline"))) {
            validateTodo(userInput);
            // check if event description is empty
        } else if (userInput.contains("event") && !(userInput.contains("todo")) && !(userInput.contains("deadline"))) {
            validateEvent(userInput);
            // check if deadline description is empty
        } else if (userInput.contains("deadline") && !(userInput.contains("todo")) && !(userInput.contains("event"))) {
            validateDeadline(userInput);
            // check if keyword were used in userInput
        } else if (userInput.contains("list")
                || userInput.contains("bye")
                || userInput.contains("done")
                || userInput.contains("delete")) {
            invalidInput = false;
            // incorrect input
        } else {
            invalidInput = true;
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            System.out.println(sectionDivider);
        }
    }
    public static void validateDeadline(String userInput) throws DeadlineException {
        if (userInput.substring(9 - 1).trim().isEmpty()
                || !(userInput.contains("/by"))
                || userInput.substring(userInput.indexOf("/by") + 3).trim().isEmpty() ) {
            throw new DeadlineException();
        } else {
            invalidInput = false;
        }
    }

    public static void validateEvent(String userInput) throws EventException {
        if (userInput.substring(6 - 1).trim().isEmpty()
                || !(userInput.contains("/at"))
                || userInput.substring(userInput.indexOf("/at") + 3).trim().isEmpty()) {
            throw new EventException();
        } else {
            invalidInput = false;
        }
    }

    public static void validateTodo(String userInput) throws TaskException {
        if (userInput.substring(5 - 1).trim().isEmpty()) {
            throw new TaskException();
        } else {
            invalidInput = false;
        }
    }
}