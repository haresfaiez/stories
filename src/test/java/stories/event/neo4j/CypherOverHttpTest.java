package stories.event.neo4j;

import org.javalite.http.Get;
import org.javalite.http.Http;
import org.javalite.http.Post;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Ignore;
import org.junit.Test;
import persistence.Neo4jAuthentication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CypherOverHttpTest {
    public static final String SUCCESS_RESPONSE = "OK";
    public static final String USERNAME = "neo4j";
    public static final String PASSWORD = "faiez";
    public static final String HOST = "0.0.0.0";
    public static final Integer PORT = 7474;

    public static final String COMMIT_END_POINT
            = String.format("http://%s:%s/db/data/transaction/commit", HOST, PORT);

    JSONParser parser = new JSONParser();

    @Test
    public void authentication() throws Exception {
        Neo4jAuthentication authentication = Neo4jAuthentication.on(HOST, PORT);

        Get response = authentication.authenticationResponse(USERNAME, PASSWORD);

        JSONObject responseJson = (JSONObject) parser.parse(response.text());
        assertEquals(Boolean.FALSE, responseJson.get("password_change_required"));
        assertEquals(USERNAME,      responseJson.get("username"));
    }

    @Test
    @Ignore
    public void createNode() {
        String createANodeQuery
                = "{'statements' : [ {'statement' : 'CREATE (n) RETURN id(n)'} ]}";
        Post response = Http.post(COMMIT_END_POINT, createANodeQuery);
        if (response.text().equals(SUCCESS_RESPONSE))
            assertTrue(Boolean.TRUE);
        else
            assertTrue(Boolean.FALSE);

    }
}
