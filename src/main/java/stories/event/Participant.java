package stories.event;

class Participant {
    private String person;
    private Event  destination;

    public Participant(String person
                     , Event  destination) {
        this.person = person;
        this.destination = destination;
    }

    @Override
    public boolean equals(Object o) {
        Participant other = (Participant) o;
        return person.equals(other.person)
                && destination.equals(other.destination);
    }

    @Override
    public int hashCode() {
        return person.hashCode();
    }

    public static Participant named(String person
                                  , Event  destination) {
        return new Participant(person, destination);
    }
}
