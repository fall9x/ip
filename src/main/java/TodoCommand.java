import java.io.IOException;

public class TodoCommand extends Command {
    private String description;

    public TodoCommand(String action, String description) {
        super(action);
        this.description = description;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws CommandException {
        try {
            StringBuilder str = new StringBuilder();
            Todo current = new Todo(description);
            tasks.addTask(current);
            str.append("Got it bro, I've added this task:\n  ").append(current.toString() + "\n").append(
                    "Now you have ").append(tasks.getCount()).append(" tasks in the list.");
            ui.setMessage(str.toString());
            storage.write(tasks.getTasks());
        } catch (IOException e) {
            throw new CommandException(e.getMessage());
        } catch (Exception e) {
            throw new CommandException("Sorry, ToDo does not accept this argument!");
        }
    }
}
