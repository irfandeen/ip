package walle.task;

/**
 * Represents an event task with a start and end date.
 * Inherits from the {@link Task} class and adds start and end dates.
 */
public class Event extends Task {
    protected String startDate;
    protected String endDate;

    /**
     * Constructs an Event task with a description, start date, and end date.
     *
     * @param description The description of the event.
     * @param startDate The start date of the event.
     * @param endDate The end date of the event.
     */
    public Event(String description, String startDate, String endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Returns the type of the event task as a string.
     *
     * @return A string representing the task type ("[E]").
     */
    @Override
    public String getType() {
        return "[E]";
    }

    /**
     * Returns the type icon for the event task.
     *
     * @return A string representing the type icon ("E").
     */
    public String getTypeIcon() {
        return "E";
    }

    /**
     * Returns the start date of the event.
     *
     * @return The start date as a string.
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Returns the end date of the event.
     *
     * @return The end date as a string.
     */
    public String getEndDate() {
        return endDate;
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + startDate + ", to: " + endDate + ")";
    }
}