package stories.event;

import org.joda.time.DateTime;

class Update {

    private Participant author;
    private String      message;
    private DateTime    time;

    public Update(Participant author
                , String      message
                , DateTime    time) {
        this.author  = author;
        this.message = message;
        this.time    = time;
    }

    @Override
    public boolean equals(Object o) {
        Update other = (Update) o;
        return author.equals(other.author)
                && message.equals(other.message)
                && time.equals(other.time);
    }

    @Override
    public int hashCode() {
        return message.hashCode();
    }

}
