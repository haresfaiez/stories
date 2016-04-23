package stories.person;

import stories.event.Event;

public class Person {
    private Long   id;
    private String name;

    public Person(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void attend(Event target) {
        target.attendee(this);
    }

    @Override
    public boolean equals(Object o) {
        if (null == o)                return Boolean.FALSE;
        if (!(o instanceof Person))   return Boolean.FALSE;
        Person other = (Person) o;
        return other.hasId(id);
    }

    protected Boolean hasId(Long otherId) {
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
}
