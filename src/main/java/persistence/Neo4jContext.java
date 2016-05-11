package persistence;

import org.javalite.common.Base64;
import org.javalite.http.Get;
import org.javalite.http.Http;
import static java.lang.String.format;

public class Neo4jContext {
    public static final String AUTHENTICATION_URI = "http://%s:%s/user/neo4j";

    private String host;
    private Integer port;
    private String username;
    private String password;

    private Neo4jContext(String host, Integer port, String username, String password) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    public Get authenticationResponse(String username, String password) {
        return getHttp(format(AUTHENTICATION_URI, host, port))
                   .header("Accept",        "application/json; charset=UTF-8")
                   .header("Authorization", authorization(username, password));
    }

    public String authorization(String username, String password) {
        return Base64.getEncoder()
                     .encodeToString(String.format("%s:%s", username, password).getBytes());
    }

    public String authorization() {
        return Base64.getEncoder()
                .encodeToString(String.format("%s:%s", username, password).getBytes());
    }

    private Get getHttp(String uri) {
        return Http.get(uri);
    }

    public static Neo4jContext on(String host, Integer port, String username, String password) {
        return new Neo4jContext(host, port,  username, password);
    }

    public String commitURI() {
        return String.format("http://%s:%s/db/data/transaction/commit", host, port);
    }

    public String requestURI() {
        return String.format("http://%s:%s/db/data/cypher", host, port);
    }


}
