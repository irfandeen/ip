package walle.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate dueDate;
    protected LocalTime dueTime;

    public Deadline(String description, LocalDate dueDate, LocalTime dueTime) {
        super(description);
        this.dueDate = dueDate;
        this.dueTime = dueTime;
    }

    public Deadline(String description, String dueDate, String dueTime) {
        super(description);
        this.dueDate = LocalDate.parse(dueDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.dueTime = LocalTime.parse(dueTime, DateTimeFormatter.ofPattern("HH:mm"));
    }

    @Override
    public String getType() {
        return "[D]";
    }

    public String getTypeIcon() {
        return "D";
    }

    public String getDueDate() {
        return dueDate.toString();
    }

    public String getDueTime() {
        return dueTime.toString();
    }

    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");

        return super.toString() + " (by: " + dueDate.format(dateFormatter) + " " +  dueTime.format(timeFormatter) + ")";
    }
}
