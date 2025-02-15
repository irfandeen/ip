package walle;

public class Event extends Task {
    protected String startDate;
    protected String endDate;

    public Event(String description, String startDate, String endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String getType() {
        return "[E]";
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + startDate + ", to: " + endDate + ")";
    }
}
