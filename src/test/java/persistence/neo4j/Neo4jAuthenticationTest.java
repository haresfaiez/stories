package persistence.neo4j;

import org.javalite.http.Get;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Test;
import persistence.Neo4jAuthentication;

import static org.junit.Assert.assertEquals;

public class Neo4jAuthenticationTest {
    public static final String USERNAME = "neo4j";
    public static final String PASSWORD = "faiez";

    JSONParser parser = new JSONParser();

    @Test
    public void authentication() throws Exception {
        Neo4jAuthentication authentication
                = Neo4jAuthentication.on(Configuration.HOST,
                                         Configuration.PORT,
                                         Configuration.USERNAME,
                                         Configuration.PASSWORD);

        Get response = authentication.authenticationResponse(USERNAME, PASSWORD);

        JSONObject responseJson = (JSONObject) parser.parse(response.text());
        assertEquals(Boolean.FALSE, responseJson.get("password_change_required"));
        assertEquals(USERNAME,      responseJson.get("username"));
    }
}
