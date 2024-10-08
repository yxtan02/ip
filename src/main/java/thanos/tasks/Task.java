package thanos.tasks;

import java.time.LocalDateTime;

/**
 * Represents a task with a description and a completion status.
 */
public class Task {
    /**
     * The description of this task.
     */
    private final String description;

    /**
     * Indicates whether this task has been completed.
     */
    private boolean isDone = false;

    /**
     * Constructs a {@code Task} with the specified description.
     *
     * @param description the description of the task.
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Sets the completion status of this task.
     *
     * @param isDone {@code true} if the task is completed; {@code false} otherwise.
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Checks if this task matches the given date.
     *
     * @param date the date to be checked.
     * @return {@code true} if the task matches the date; {@code false} otherwise.
     */
    public boolean checkDate(LocalDateTime date) {
        return false;
    }

    /**
     * Returns a string representation of this task for display purposes.
     *
     * @return a string representation of this task.
     */
    @Override
    public String toString() {
        String status = this.isDone ? "X" : " ";
        return String.format("[%s] %s", status, this.description);
    }

    /**
     * Returns a string representation of this task for storage purposes.
     *
     * @return a string representation of this task suitable for storage.
     */
    public String toFileString() {
        String status = this.isDone ? "1" : "0";
        return String.format("%s | %s", status, this.description);
    }
}
