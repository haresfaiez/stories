package stories.event;

class Participant {
    private String person;
    private Event  destination;

    public Participant(String person
                     , Event  destination) {
        this.person = person;
        this.destination = destination;
    }

    public static Participant named(String person
                                  , Event  destination) {
        return new Participant(person, destination);
    }
}
