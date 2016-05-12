package cli;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

import java.util.UUID;

public class Request {
    @Parameter(names = "-request", description = "Target entity")
    public String request;

    @Parameter(names = "-identity", description = "Identity of requested entity")
    public String identity;

    private Service service;
    private String[]     arguments;

    public Request(Service service,
                   String[]     arguments) {
        this.service = service;
        this.arguments    = arguments;
    }

    public String response() {
        new JCommander(this, arguments);
        if (request.equals("person"))
            return service.person(UUID.fromString(identity)).toString();
        return service.of(UUID.fromString(identity)).toString();
    }
}
