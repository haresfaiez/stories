package persistence.neo4j;

import junit.framework.Assert;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;
import stories.person.Person;

import java.util.Set;

import static org.junit.Assert.assertEquals;

public class PersonNeo4jRepositoryTest {
    Neo4jContext authentication;
    PersonNeo4jRepository repository;

    @Before
    public void setUp() throws Exception {
        authentication = Neo4jContext.on(Configuration.HOST,
                Configuration.PORT,
                Configuration.USERNAME,
                Configuration.PASSWORD);
        repository = PersonNeo4jRepository.using(authentication);
    }

    @Test
    public void retrieveFollowedPersons() {
        Set<Person> actual = repository.personsFollowedBy(PersonFixture.bill());
        Set<Person> expected = PersonFixture.followedByBill();

        assertEquals(expected, actual);
    }

    @Test
    public void retrievePersonById() throws ParseException {
        Person expected = PersonFixture.bill();
        Person actual = repository.personWithId(expected.id);

        Assert.assertEquals(expected, actual);
        Assert.assertEquals("Bill", actual.name);
    }

}
