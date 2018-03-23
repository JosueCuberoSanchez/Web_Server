import java.io.File;
import java.io.PrintWriter;

/**
 * Created by josue on 21/03/18.
 */
public class RequestProcesser{

    public RequestProcesser(){}

    public void handle(String message, PrintWriter outClient){

        String splitMessage[] = message.split("\n");
        String resource = (splitMessage[0].split("/"))[1];
        resource = (resource.split(" "))[0];

        if (splitMessage[0].startsWith("GET")) {
            if (this.resourceExist(resource)) {
                System.out.println("GET");
            }
            else{
                System.out.println("ERROR 404, NOT FOUND");
            }
        }
        else if (splitMessage[0].startsWith("HEAD")) {
            if (this.resourceExist(resource)) {
                System.out.println("HEAD");
            }
            else{
                System.out.println("ERROR 404, NOT FOUND");
            }
        }
        else if (splitMessage[0].startsWith("POST")) {
            if (this.resourceExist(resource)) {
                System.out.println("200: OK, POST");
            }
            else{
                System.out.println("ERROR 404, NOT FOUND");
            }
        }
        else {
            System.out.println("ERROR 501, NOT IMPLEMENTED");
        }

        outClient.println(message);
    }

    private boolean resourceExist(String resource){
        resource = "src/main/resources/" + resource;
        File f = new File(resource);
        return (f.exists());
    }

}