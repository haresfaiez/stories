package cli;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import stories.event.Event;
import stories.person.Person;

import java.util.Set;
import java.util.UUID;

public class Request {
    @Parameter(names = "-request", description = "Target entity")
    public String request;

    @Parameter(names = "-identity", description = "Identity of requested entity")
    public String identity;

    private Service  service;
    private String[] arguments;

    public Request(Service  service,
                   String[] arguments) {
        this.service   = service;
        this.arguments = arguments;
    }

    public String response() {
        new JCommander(this, arguments);

        if (request.equals("person"))
            return person();

        if(request.equals("event"))
            return event();

        throw new RuntimeException("request not supported");
    }

    private String event() {
        Event target = service.event(UUID.fromString(identity));
        return target.toString();
    }

    private String person() {
        Person target = service.person(UUID.fromString(identity));
        Set<Person> followed = service.followedBy(target);
        return new PersonView(target, followed).output();
    }
}
