import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;

public class DeadlineCommand extends Command {
    private String description;
    private String by;

    public DeadlineCommand(String action, String description, String by) {
        super(action);
        this.description = description;
        this.by = by;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws CommandException {
        try {
            StringBuilder str = new StringBuilder();
            LocalDate temp = LocalDate.parse(by);
            Deadline current = new Deadline(description, temp);
            tasks.addTask(current);
            str.append("Got it bro, I've added this task:\n  ").append(current.toString() + "\n").append(
                    "Now you have ").append(tasks.getCount()).append(" tasks in the list.");
            ui.setMessage(str.toString());
            storage.write(tasks.getTasks());
        } catch (IOException e) {
            throw new CommandException(e.getMessage());
        } catch (DateTimeException e) {
            throw new CommandException("Sorry, Invalid date format!");
        }
    }
}
