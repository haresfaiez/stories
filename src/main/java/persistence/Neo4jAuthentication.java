package persistence;

import org.javalite.common.Base64;
import org.javalite.http.Get;
import org.javalite.http.Http;
import static java.lang.String.format;

public class Neo4jAuthentication {
    public static final String AUTHENTICATION_URI = "http://%s:%s/user/neo4j";

    private String host;
    private Integer port;

    private Neo4jAuthentication(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    public Get authenticationResponse(String username, String password) {
        String AUTHORIZATION = Base64.getEncoder()
                                     .encodeToString(String.format("%s:%s", username, password).getBytes());
        return getHttp(format(AUTHENTICATION_URI, host, port))
                   .header("Accept",        "application/json; charset=UTF-8")
                   .header("Authorization", AUTHORIZATION);
    }

    private Get getHttp(String uri) {
        return Http.get(uri);
    }

    public static Neo4jAuthentication on(String host, Integer port) {
        return new Neo4jAuthentication(host, port);
    }
}
