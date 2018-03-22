import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by josue on 21/03/18.
 */
public class RequestProcesser implements HttpHandler {

    public void handle(HttpExchange t) throws IOException {
        /*String method = t.getRequestMethod();
        if(method.equalsIgnoreCase("POST")) {

        } else if(method.equalsIgnoreCase("GET")){

        } else if(method.equalsIgnoreCase("HEAD")){

        } else { //501

        }*/
        String response = "12345";
        Headers headers = t.getResponseHeaders();
        String path = t.getHttpContext().getPath();
        headers.add("Server", "MonkeyLabsServer");
        headers.add("Content-Type", "text/html");
        t.sendResponseHeaders(501, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();

    }
}