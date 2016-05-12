package stories.person;

import org.json.simple.JSONObject;
import stories.event.Event;

import java.io.Serializable;
import java.util.UUID;

public class Person implements Serializable {
    public UUID   id;
    public String name;

    public Person(UUID id,
                  String name) {
        this.id   = id;
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
        return other.isIdentified(id);
    }

    protected Boolean isIdentified(UUID other) {
        return id.equals(other);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return String.format("%s, %s", id, name);
    }

    public static Person from(JSONObject json) {
        UUID id = UUID.fromString(json.get("id").toString());
        String name = json.get("name").toString();
        return new Person(id, name);
    }
}
