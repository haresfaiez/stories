package cli;

import stories.person.Person;

import java.util.Set;
import java.util.UUID;


public class PersonView {
    public final String NEW_LINE = "\n";
    private Person origin;
    private Set<Person> followed;

    public PersonView(Person origin, Set<Person> followed) {
        this.origin = origin;
        this.followed = followed;
    }

    public String personHeader(UUID identity, String name) {
        StringBuilder result = new StringBuilder();
        result.append(NEW_LINE);
        result.append("name:");
        result.append(name);
        return result.toString();
    }

    public StringBuilder personBody() {
        StringBuilder result = new StringBuilder();
        result.append("IS FOLLOWING:");
        for (Person eachFollowed : followed)
            result.append(personHeader(eachFollowed.id, eachFollowed.name));
        return result;
    }

    public String output() {
        StringBuilder result = new StringBuilder();
        result.append(personHeader(origin.id, origin.name));
        result.append(NEW_LINE);
        result.append(personBody());
        return result.toString();
    }
}
