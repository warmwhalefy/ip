### User Guide

Starting up Duke

1. Move ip2.jar file and duke.txt file (if any) into the same folder.
2. Open Command Prompt (on Windows) or Terminal (on Mac).
3. Change directory to where ip2.jar file and duke.txt file (if any) are stored. E.g. : cd Desktop
4. Type java -jar ip2.jar to run Duke.


## Features 
### Feature 1: Add todo task
Add a todo task.

#### Usage: `todo homework`

_Requirements:_
- Description of a todo task cannot be empty.

__Expected outcome:__

- adds a todo task with `homework` as task description.

__Example of usage:__

- input: `todo homework`
- outcome: adds [T][ ] homework to text file.
_________

### Feature 2: Add deadline task
Add a deadline task.

#### Usage: `deadline project123 /by Monday`

_Requirements:_
- Description of a deadline task cannot be empty.
- /by has to be specified.

__Expected outcome:__

- adds a deadline task with `project123` as task description and Monday to be the deadline.

__Example of usage:__

- input: `deadline project123 /by Monday`
- outcome: adds [D][ ] project123 (by: Monday) to text file.
_________

### Feature 3: Add event task
Add an event task.

#### Usage: `event birthday party /at MBS`

_Requirements:_
- Description of an event task cannot be empty.
- /at has to be specified.

__Expected outcome:__

- adds a event task with `birthday party` as task description and MBS to be the location.

__Example of usage:__

- input: `event birthday party /at MBS`
- outcome: adds [E][ ] birthday party (at: MBS) to text file.
_________

### Feature 4: List tasks

#### Usage: `list`

__Expected outcome:__

- prints out all tasks in the list

__Example of usage:__

- input: `list`
- outcome: lists out all tasks
_________

### Feature 5: Mark task as done
Mark a task as done. 

#### Usage: `done 1`

_Requirements:_
- Task number cannot be empty.
- Task number entered must be valid, or will encounter error message.

__Expected outcome:__

- marks task in list as done. 

__Example of usage:__

- input: `done 1`
- outcome: marks task 1 of list of tasks as done. 
    - Status icon for that particular task changes to `[X]` from `[ ]`.
_________

### Feature 6: Delete Task
Delete a task from list of tasks.

#### Usage: `delete 1`

_Requirements:_
- Task number cannot be empty.
- Task number entered must be valid, or will encounter error message.

__Expected outcome:__

- task is removed from list of tasks.

__Example of usage:__

- input: `delete 1`
- outcome: task 1 will be deleted.
_________

### Feature 7: Find tasks
Finds tasks that contains the particular keyword and prints the list of task(s) containing that keyword.

#### Usage: `find sleep`

_Requirements:_
- keyword cannot be empty

__Expected outcome:__

- prints out a list of tasks that contains the `keyword`

__Example of usage:__

- input: `find sleep`
- outcome: prints a list of all tasks containing the word `sleep`.
_________

### Feature 8: Bye
Exits program

#### Usage: `bye`

__Expected outcome:__

- prints goodbye message and exits program

__Example of usage:__

- input: `bye`
- outcome: `Bye. Hope to see you again soon!`
