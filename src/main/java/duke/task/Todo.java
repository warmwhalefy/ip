package duke.task;

public class Todo extends Task{
     public Todo(String description) {
         super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
    public boolean isDescriptionEmpty(String s){
        if (s.isEmpty()){
            System.out.println("OOPS!!! The description of a todo cannot be empty.");
            return true;
        }
        return false;
    }
}
