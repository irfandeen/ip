package walle.task;

/**
 * Represents a to-do task.
 * Inherits from the {@link Task} class and specifies task type as "to-do".
 */
public class Todo extends Task {
    /**
     * Constructs a Todo task with the specified description.
     *
     * @param description The description of the to-do task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the type of the to-do task as a string.
     *
     * @return A string representing the task type ("[T]").
     */
    @Override
    public String getType() {
        return "[T]";
    }

    /**
     * Returns the type icon for the to-do task.
     *
     * @return A string representing the type icon ("T").
     */
    public String getTypeIcon() {
        return "T";
    }
}
