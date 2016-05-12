package persistence.neo4j;

import org.javalite.http.Http;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import stories.person.Person;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PersonNeo4jRepository {
    private Neo4jContext context;

    public PersonNeo4jRepository(Neo4jContext authentication) {
        this.context = authentication;
    }

    public Person personWithId(UUID target) {
        return Person.from(personsFrom(responseTo(retrievePersonQuery(target))).get(0));
    }

    public Set<Person> personsFollowedBy(Person origin) {
        String response = responseTo(followedByQuery(origin.id));
        return personsFrom(response).stream()
                                    .map(Person::from)
                                    .collect(Collectors.toSet());
    }

    private String responseTo(String query) {
        return Http.post(context.requestURI(), query)
                   .header("Accept", "application/json; charset=UTF-8")
                   .header("Content-Type", "application/json")
                   .header("Authorization", context.authorization())
                   .text();
    }

    private String retrievePersonQuery(UUID target) {
        String template = "MATCH (target: Person {id: \\\"%s\\\"}) RETURN target";
        return queryFrom(String.format(template, target));
    }

    private String followedByQuery(UUID origin) {
        String template = "MATCH (:Person {id: \\\"%s\\\"}) -[:FOLLOWS]->(followed) RETURN followed";
        return queryFrom(String.format(template, origin));
    }

    private String queryFrom(String template) {
        return String.format("{\"query\" : \"%s\", \"params\" : { }}", template);
    }

    public List<JSONObject> personsFrom(String response) {
        try {
            JSONParser parser = new JSONParser();
            JSONArray responseData = (JSONArray) ((JSONObject) parser.parse(response)).get("data");
            Stream result = responseData.stream().map(each -> ((JSONObject) ((JSONArray) each).get(0)).get("data"));
            return (List<JSONObject>) result.collect(Collectors.toList());
        } catch (ParseException e) {
            throw new RuntimeException("Response not formatted as expected");
        }
    }

    public static PersonNeo4jRepository using(Neo4jContext authentication) {
        return new PersonNeo4jRepository(authentication);
    }
}
