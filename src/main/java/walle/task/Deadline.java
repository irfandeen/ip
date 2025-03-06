package walle.task;

public class Deadline extends Task {
    protected String dueDate;

    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    @Override
    public String getType() {
        return "[D]";
    }

    public String getTypeIcon() {
        return "D";
    }

    public String getDueDate() {
        return dueDate;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + dueDate + ")";
    }
}
