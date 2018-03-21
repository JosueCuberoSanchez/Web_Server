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
        String response = "12345";
        Headers headers = t.getResponseHeaders();
        headers.add("Server", "MonkeyLabsServer");
        headers.add("Content-Type", "text/html");
        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}