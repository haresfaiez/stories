package stories.person;

import stories.event.Event;
import stories.event.PersonUpdate;

public class Person {

    private Long   id;
    private String name;

    public Person(Long id,
                  String name) {
        this.id   = id;
        this.name = name;
    }

    public void update(Event concert, PersonUpdate billUpdate) {
    }

    public void attend(Event event) {
        event.attendee(this);
    }

    @Override
    public boolean equals(Object o) {
        if (null == o)              return Boolean.FALSE;
        if (!(o instanceof Person)) return Boolean.FALSE;
        Person other = (Person) o;
        return other.hasId(id);
    }

    private Boolean hasId(Long otherId) {
        return id.equals(otherId);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return String.format("%s, %s", id, name);
    }

    public String name() {
        return name;
    }
}
