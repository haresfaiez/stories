package persistence.neo4j;

import org.javalite.http.Http;
import org.javalite.http.Post;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;
import persistence.Neo4jContext;

import static org.junit.Assert.assertEquals;

public class CypherOverHttpTest {
    public static final String COMMIT_END_POINT
            = String.format("http://%s:%s/db/data/transaction/commit",
                            Configuration.HOST,
                            Configuration.PORT);

    Neo4jContext authentication;
    @Before
    public void setUp() throws Exception {
        authentication = Neo4jContext.on(Configuration.HOST,
                                                Configuration.PORT,
                                                Configuration.USERNAME,
                                                Configuration.PASSWORD);
    }

    @Test
    public void createNode() throws ParseException {
        String createANodeQuery
                = "{\"statements\" : [ {\"statement\" : \"CREATE (b) RETURN id(b)\"} ]}";
        Post response = Http.post(COMMIT_END_POINT, createANodeQuery)
                            .header("Accept", "application/json; charset=UTF-8")
                            .header("Content-Type", "application/json")
                            .header("Authorization", authentication.authorization("neo4j", "faiez"));
        assertEquals(200, response.responseCode());
    }
}
