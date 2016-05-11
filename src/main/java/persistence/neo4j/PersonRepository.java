package persistence.neo4j;

import org.javalite.http.Http;
import org.javalite.http.Post;
import persistence.Neo4jAuthentication;

import java.util.UUID;

public class PersonRepository {
    private Neo4jAuthentication authentication;

    public PersonRepository(Neo4jAuthentication authentication) {
        this.authentication = authentication;
    }

    public static PersonRepository using(Neo4jAuthentication authentication) {
        return new PersonRepository(authentication);
    }

    public String personWithId(UUID target) {
        Post response = Http.post(authentication.requestURI(), retrievePersonQuery(target))
                .header("Accept", "application/json; charset=UTF-8")
                .header("Content-Type", "application/json")
                .header("Authorization", authentication.authorization());
        return response.text();
    }

    public String retrievePersonQuery(UUID target) {
        String query = String.format("MATCH (target: Person {id: \\\"%s\\\"}) RETURN target",
                                     target);
        return String.format("{\"query\" : \"%s\", \"params\" : { }}", query);
    }
}
