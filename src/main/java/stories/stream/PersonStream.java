package stories.stream;

import stories.person.Attendee;

import java.util.Set;

public class PersonStream {
    protected Attendee director;
    protected Set updates;

    public PersonStream(Attendee director, Set updates) {

        this.director = director;
        this.updates = updates;
    }

    public static PersonStream of(Attendee owner) {
        return new PersonStream(null, null);
    }

}
