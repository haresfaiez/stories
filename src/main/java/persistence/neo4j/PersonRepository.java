package persistence.neo4j;

import org.javalite.http.Http;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import stories.person.Person;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class PersonRepository {
    private Neo4jContext authentication;

    public PersonRepository(Neo4jContext authentication) {
        this.authentication = authentication;
    }

    public static PersonRepository using(Neo4jContext authentication) {
        return new PersonRepository(authentication);
    }

    public Person personWithId(UUID target) {
        String query = retrievePersonQuery(target);
        return Person.from(dataFrom(responseTo(query), 0));
    }

    private String retrievePersonQuery(UUID target) {
        String queryTemplate = "MATCH (target: Person {id: \\\"%s\\\"}) RETURN target";
        String query = String.format(queryTemplate, target);
        return String.format("{\"query\" : \"%s\", \"params\" : { }}", query);
    }

    public JSONObject dataFrom(String response, int index) {
        try {
            JSONParser parser = new JSONParser();
            JSONArray responseData = (JSONArray) ((JSONObject) parser.parse(response)).get("data");
            JSONObject result = (JSONObject) ((JSONArray) responseData.get(index)).get(0);
            return (JSONObject) result.get("data");
        } catch (ParseException e) {
            throw new RuntimeException("Response not formatted as expected");
        }
    }

    public Set<Person> personsFollowedBy(Person origin) {
        String query = followedByQuery(origin.id);
        String responseRaw = responseTo(query);
        HashSet<Person> result = new HashSet<>();
        result.add(Person.from(dataFrom(responseRaw, 0)));
        result.add(Person.from(dataFrom(responseRaw, 1)));
        return result;
    }

    private String responseTo(String query) {
        return Http.post(authentication.requestURI(), query)
                .header("Accept", "application/json; charset=UTF-8")
                .header("Content-Type", "application/json")
                .header("Authorization", authentication.authorization())
                .text();
    }

    private String followedByQuery(UUID origin) {
        String queryTemplate
                = "MATCH (:Person {id: \\\"%s\\\"}) -[:FOLLOWS]->(followed) RETURN followed";
        String query = String.format(queryTemplate, origin);
        return String.format("{\"query\" : \"%s\", \"params\" : { }}", query);
    }
}
