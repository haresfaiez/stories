package stories.event;

public class Note {
    private final String body;

    private Note(String body) {
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (null == o)            return Boolean.FALSE;
        if (!(o instanceof Note)) return Boolean.FALSE;
        Note other = (Note) o;
        return other.hasBody(body);
    }

    @Override
    public int hashCode() {
        return body.hashCode();
    }

    @Override
    public String toString() {
        return String.format("%s", body);
    }

    private Boolean hasBody(String otherMessage) {
        return body.equals(otherMessage);
    }

    public static Note from(String body) {
        return new Note(body);
    }
}
