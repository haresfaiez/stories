package persistence.neo4j;

import org.junit.Before;
import org.junit.Test;
import persistence.Neo4jAuthentication;

import java.util.UUID;

import static groovy.util.GroovyTestCase.assertEquals;

public class PersonRepositoryTest {
    Neo4jAuthentication authentication;
    PersonRepository repository;

    @Before
    public void setUp() throws Exception {
        authentication = Neo4jAuthentication.on(Configuration.HOST,
                                                Configuration.PORT,
                                                Configuration.USERNAME,
                                                Configuration.PASSWORD);
        repository = PersonRepository.using(authentication);
    }

    @Test
    public void retrievePersonById() {
        String person = repository.personWithId(existingID());

        assertEquals("", person);
    }

    private UUID existingID() {
        return UUID.fromString("32b0a8e0-0a3d-11e6-8cf0-2d237e461979");
    }

}
