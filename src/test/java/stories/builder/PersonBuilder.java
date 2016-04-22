package stories.builder;

import stories.person.Person;

public class PersonBuilder {
    public static Person somePerson(Long id) {
        return new Person(id, "Bob");
    }

    public static Person mike(Long id) {
        return new Person(id, "Mike");
    }

    public static Person bill(Long id) {
        return new Person(id, "Bill");
    }
}
