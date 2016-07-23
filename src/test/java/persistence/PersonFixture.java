package persistence;

import stories.person.Person;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class PersonFixture {
    public static Person brook() {
        return new Person(UUID.fromString("32b0a8e0-0a3d-11e6-8cf0-2d237e461978"),
                "Brook");
    }

    public static Person emma() {
        return new Person(UUID.fromString("32b0a8e0-0a3d-11e6-8cf0-2d237e461988"),
                "Emma");
    }

    public static Person bill() {
        return new Person(UUID.fromString("32b0a8e0-0a3d-11e6-8cf0-2d237e461979"),
                                          "Bill");
    }

    public static Set<Person> followedByBill() {
        Set<Person> result = new HashSet<>();
        result.add(emma());
        result.add(brook());
        return result;
    }
}
