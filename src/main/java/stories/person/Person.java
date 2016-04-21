package stories.person;

public class Person {

    private Long id;
    private String name;

    public Person(Long id, String name) {
        this.id = id;
        this.name = name;
    }


    @Override
    public boolean equals(Object o) {
        if (null == o)              return Boolean.FALSE;
        if (!(o instanceof Person)) return Boolean.FALSE;
        Person other = (Person) o;
        return other.hasId(id);
    }

    private Boolean hasId(Long otherId) {
        return id.equals(otherId);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        throw new RuntimeException("toString not implemented on Person");
    }

    public String name() {
        return name;
    }
}
