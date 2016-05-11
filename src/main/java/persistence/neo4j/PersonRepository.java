package persistence.neo4j;

import org.javalite.http.Http;
import org.javalite.http.Post;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import stories.person.Person;

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
        Post response = Http.post(authentication.requestURI(), retrievePersonQuery(target))
                .header("Accept", "application/json; charset=UTF-8")
                .header("Content-Type", "application/json")
                .header("Authorization", authentication.authorization());
        return Person.from(firstData(response.text()));
    }

    private String retrievePersonQuery(UUID target) {
        String queryTemplate = "MATCH (target: Person {id: \\\"%s\\\"}) RETURN target";
        String query = String.format(queryTemplate, target);
        return String.format("{\"query\" : \"%s\", \"params\" : { }}", query);
    }

    private JSONObject firstData(String response) {
        try {
            JSONParser parser = new JSONParser();
            JSONArray responseData = (JSONArray) ((JSONObject) parser.parse(response)).get("data");
            JSONObject result = (JSONObject) ((JSONArray) responseData.get(0)).get(0);
            return (JSONObject) result.get("data");
        } catch (ParseException e) {
            throw new RuntimeException("Response not formatted as expected");
        }
    }

}
