package cli;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

import java.util.UUID;

public class Request {
    @Parameter(names = "-request", description = "Target entity")
    public String request;

    @Parameter(names = "-identity", description = "Identity of requested entity")
    public String identity;

    private EventDetails eventDetails;
    private String[] arguments;

    public Request(EventDetails eventDetails, String[] arguments) {
        this.eventDetails = eventDetails;
        this.arguments = arguments;
    }

    public String response() {
        new JCommander(this, arguments);
        eventDetails.of(UUID.fromString(identity));
        return "";
    }
}
