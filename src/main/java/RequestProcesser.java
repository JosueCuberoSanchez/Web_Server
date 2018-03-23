import java.io.PrintWriter;

/**
 * Created by josue on 21/03/18.
 */
public class RequestProcesser{

    public RequestProcesser(){}

    public void handle(String message, PrintWriter outClient){
        Client client = new Client();
        client.startClient(message,outClient);
    }

}