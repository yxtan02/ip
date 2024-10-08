package thanos.commands;

import static thanos.utility.ResponseFormatter.generateTaskAddedResponse;

import java.time.LocalDateTime;

import thanos.exceptions.InvalidCommandException;
import thanos.exceptions.InvalidDateException;
import thanos.tasks.Deadline;
import thanos.tasks.TaskList;
import thanos.utility.DateTimeUtility;

/**
 * Represents a command to add a deadline task to the {@code TaskList}.
 */
public class DeadlineCommand extends Command {
    /**
     * Constructs a {@code DeadlineCommand} with the given argument.
     *
     * @param argument the argument associated with this command.
     */
    public DeadlineCommand(String argument) {
        super(argument);
    }

    /**
     * Executes the command to add a deadline task to the {@code TaskList}.
     *
     * @param taskList the list of tasks to which the new deadline task will be added.
     * @return a formatted string confirming the addition of the deadline task and displaying the updated task count.
     * @throws InvalidCommandException if the argument is not in the correct format or if the date cannot be parsed.
     */
    @Override
    public String execute(TaskList taskList) throws InvalidCommandException {
        try {
            String[] detailsArr = getDetailsArr();
            String description = detailsArr[0];
            LocalDateTime date = DateTimeUtility.parse(detailsArr[1]);

            Deadline deadline = new Deadline(description, date);
            taskList.add(deadline);
            return generateTaskAddedResponse(deadline, taskList.size());
        } catch (InvalidDateException e) {
            throw new InvalidCommandException(e.getMessage());
        }
    }

    /**
     * Splits the input argument string into task details and due date.
     *
     * @return A string array where the first element is the task description and the second element is the due date.
     * @throws InvalidCommandException if the input format does not contain exactly one " /by " delimiter.
     */
    private String[] getDetailsArr() throws InvalidCommandException {
        String[] detailsArr = this.getArgument().split(" /by ");
        if (detailsArr.length != 2) {
            throw new InvalidCommandException("Invalid input format.");
        }
        return detailsArr;
    }
}
