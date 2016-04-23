package stories.event;

public class AttendeeUpdate {
    private final String message;

    private AttendeeUpdate(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (null == o)                    return Boolean.FALSE;
        if (!(o instanceof AttendeeUpdate)) return Boolean.FALSE;
        AttendeeUpdate other = (AttendeeUpdate) o;
        return other.hasMessage(message);
    }

    @Override
    public int hashCode() {
        return message.hashCode();
    }

    @Override
    public String toString() {
        return String.format("%s", message);
    }

    private Boolean hasMessage(String otherMessage) {
        return message.equals(otherMessage);
    }

    public static AttendeeUpdate from(String message) {
        return new AttendeeUpdate(message);
    }
}
