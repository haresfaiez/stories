package stories.stream;

import stories.person.Person;

public class StreamRepository {
    public PersonStream streamOf(Person director) {
        return PersonStream.empty(director);
    }
}
