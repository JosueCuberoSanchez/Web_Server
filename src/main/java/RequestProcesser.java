

/**
 * Created by josue on 21/03/18.
 */
public class RequestProcesser{

    public RequestProcesser(){}

    public void handle(String message){
        Client client = new Client();
        client.startClient(message);
    }

}