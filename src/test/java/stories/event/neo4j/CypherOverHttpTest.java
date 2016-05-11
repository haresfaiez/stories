package stories.event.neo4j;

import org.javalite.common.Base64;
import org.javalite.http.Get;
import org.javalite.http.Http;
import org.javalite.http.Post;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CypherOverHttpTest {

    public static final String HOST = "localhost";
    public static final int PORT = 7474;
    public static final String COMMIT_END_POINT
            = String.format("http://%s:%s/db/data/transaction/commit", HOST, PORT);
    public static final String SUCCESS_RESPONSE = "OK";
    private static final String USERNAME = "neo4j";
    private static final byte[] AUTHORIZATION_TOKEN
            = String.format("%s:faiez", USERNAME).getBytes();
    private static final String AUTHORIZATION = Base64.getEncoder()
            .encodeToString(AUTHORIZATION_TOKEN);
    private static final String AUTHENTICATION_END_POINT
        = String.format("http://%s:%s/user/neo4j", HOST, PORT);

    @Test
    public void authentication() throws ParseException {
        Get responseRaw = Http.get(AUTHENTICATION_END_POINT)
                            .header("Accept", "application/json; charset=UTF-8")
                            .header("Authorization", AUTHORIZATION);
        JSONObject response = (JSONObject) new JSONParser().parse(responseRaw.text());
        assertEquals(Boolean.FALSE, response.get("password_change_required"));
        assertEquals(USERNAME, response.get("username"));
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
