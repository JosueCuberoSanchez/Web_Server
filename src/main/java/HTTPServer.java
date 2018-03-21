/**
 * Created by Renato on 19/03/2018.
 */
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class HTTPServer {

    public HTTPServer(){}

    public void startServer(){
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
            server.createContext("/", new RequestProcesser());
            server.setExecutor(null); // creates a default executor
            server.start();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}