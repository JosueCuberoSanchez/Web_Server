import java.io.PrintWriter;

/**
 * Created by josue on 21/03/18.
 */
public class RequestProcesser{

    public RequestProcesser(){}

    public void handle(String message, PrintWriter outClient){
        outClient.println("HTTP/1.1 200 OK");
        outClient.println("Content-Type: text/html");
        outClient.println("Content-Length: " + message.length());
        outClient.println();
        outClient.println(message);
    }

}