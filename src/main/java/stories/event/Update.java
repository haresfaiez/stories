package stories.event;

class Update {

    private Participant author;
    private String      message;

    public Update(Participant author
                , String      message) {
        this.author = author;
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        Update other = (Update) o;
        return author.equals(other.author)
                && message.equals(other.message);
    }

    @Override
    public int hashCode() {
        return message.hashCode();
    }

    public static Update from(Participant author
                            , String      message) {
        return new Update(author, message);
    }
}
