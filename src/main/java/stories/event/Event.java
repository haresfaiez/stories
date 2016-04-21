package stories.event;

import stories.person.Person;

import java.time.LocalDateTime;

public class Event {
    private Long          id;
    private String        title;
    private LocalDateTime time;
    private Attendees     attendees;

    public Event(Long id, String title, LocalDateTime time) {
        this.id         = id;
        this.title      = title;
        this.time       = time;
        this.attendees  = Attendees.noOne();
    }

    public void attendee(Person newAttendee) {
        attendees = attendees.with(newAttendee);
    }

    public Boolean hasAttendee(Person potentialAttendee) {
        return attendees.contains(potentialAttendee);
    }

    @Override
    public boolean equals(Object o) {
        if (null == o)             return Boolean.FALSE;
        if (!(o instanceof Event)) return Boolean.FALSE;
        Event other = (Event) o;
        return other.hasId(id);
    }

    private Boolean hasId(Long otherId) {
        return id.equals(otherId);
    }

    protected Boolean hasTittle(String otherTitle) {
        return title.equals(otherTitle);
    }

    protected Boolean hasTime(LocalDateTime otherTime) {
        return time.equals(otherTime);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s", id, title, time);
    }

}
