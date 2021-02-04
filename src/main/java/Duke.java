import java.util.Scanner;

public class Duke {
    static int noOfTasks = 0;
    static String sectionDivider = "____________________________________________________________";
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
        while (true) {
            String str = sc.nextLine();
            if (str.equals("bye")) {
                System.out.println(sectionDivider);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(sectionDivider);
                break;
            }
            if (str.equals("list")){
                System.out.println(sectionDivider);
                System.out.println("Here are the tasks in your to-do list:");
                System.out.println(TaskArray[0].getStatusIcon());
                for(int i= 1; i!=noOfTasks+1; i++){
                    System.out.println(i + "." + TaskArray[i-1].toString());
                    //System.out.println(i + ". [" + TaskArray[i-1].getStatusIcon() + " ]" + TaskArray[i-1].getDescription());
                }
                System.out.println(sectionDivider);
            }
            if (str.contains("done")){
                System.out.println(sectionDivider);
                String[] splitInput = str.split(" ");
                int taskNumber = Integer.parseInt(splitInput[1]);
                TaskArray[taskNumber-1].markAsDone();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(taskNumber + ". [" + TaskArray[taskNumber-1].getStatusIcon() + " ]" + TaskArray[taskNumber-1].getDescription());
                System.out.println(sectionDivider);
            } else {
                System.out.println(sectionDivider);
                String[] splitInput = str.split(" ");
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
                    //System.out.println("printing results:");
                    //System.out.println(descriptionString);
                    //System.out.println(byString);
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
}