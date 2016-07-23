package stories.event;

public class Update {


    private Participant author;
    private String      message;
    private Event       target;

    public Update(Participant author
                , String      message
                , Event       target) {
        this.author = author;
        this.message = message;
        this.target = target;
    }

    @Override
    public boolean equals(Object o) {
        Update other = (Update) o;
        return author.equals(other.author)
                && message.equals(other.message)
                && target.equals(other.target);
    }

    @Override
    public int hashCode() {
        return message.hashCode();
    }

    public static Update from(Participant author
                            , String      message
                            , Event       target) {
        return new Update(author, message, target);
    }
}
