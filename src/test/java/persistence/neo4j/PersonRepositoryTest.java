package persistence.neo4j;

import junit.framework.Assert;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;
import stories.person.Person;

import java.util.UUID;

public class PersonRepositoryTest {
    Neo4jContext authentication;
    PersonRepository repository;

    @Before
    public void setUp() throws Exception {
        authentication = Neo4jContext.on(Configuration.HOST,
                Configuration.PORT,
                Configuration.USERNAME,
                Configuration.PASSWORD);
        repository = PersonRepository.using(authentication);
    }

    @Test
    public void retrievePersonById() throws ParseException {
        Person expected = existing();
        Person actual   = repository.personWithId(expected.id);

        Assert.assertEquals(expected, actual);
        Assert.assertEquals("Bill", actual.name);
    }

    private Person existing() {
        return new Person(UUID.fromString("32b0a8e0-0a3d-11e6-8cf0-2d237e461979"),
                                          "Bill");
    }
}
