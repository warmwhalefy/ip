import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String[] StringArray= new String[100];
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        Scanner sc= new Scanner(System.in);
        while (true) {
            String str = sc.nextLine();
            if (str.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            else if (str.equals("list")){
                System.out.println("list");
                continue;
            }
            else if (str.equals("blah")){
                System.out.println("blah");
                continue;
            }
        }

    }
}
