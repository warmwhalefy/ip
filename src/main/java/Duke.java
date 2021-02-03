import java.util.Scanner;

public class Duke {
    static String[] StringArray= new String[100];
    static int noOfTasks = 0;
    static String sectionDivider = "____________________________________________________________";
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(sectionDivider);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
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
                for(int i= 1; i!=noOfTasks+1; i++){
                    System.out.println(i + "." + StringArray[i-1]);
                }
                System.out.println(sectionDivider);
            }
            else {
                System.out.println(sectionDivider);
                StringArray[noOfTasks]= str;
                noOfTasks = noOfTasks + 1;
                System.out.println("added: " + str);
                System.out.println(sectionDivider);
                continue;
            }
        }

    }
}
