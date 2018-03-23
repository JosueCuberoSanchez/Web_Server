import javax.activation.MimetypesFileTypeMap;
import java.io.*;

/**
 * Created by josue on 21/03/18.
 */
public class RequestProcesser{

    public RequestProcesser(){}

    private String buildMessage(InputStream is){
        String message = "";
        try {
            InputStreamReader isReader = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isReader);

            //code to read and print headers
            String headerLine = null;
            while ((headerLine = br.readLine()).length() != 0) {
                message += headerLine + "\n";
                //System.out.println(headerLine);
            }

            //code to read the post payload data
            StringBuilder payload = new StringBuilder();
            while (br.ready()) {
                payload.append((char) br.read());
            }
            //System.out.println("Payload data is: " + payload.toString());
            if(!payload.toString().equals("")){
                message += payload.toString();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return message;
    }

    public void handle(PrintWriter outClient, InputStream is){
        String message = this.buildMessage(is);

        /*outClient.println("HTTP/1.1 200 OK");
        outClient.println("Date: Fri, 29 May 2015 13:24:18 GMT");
        outClient.println("Server: MiServidor/1.0");
        outClient.println("Content-Length: 44");
        outClient.println("Content-Type: text");
        outClient.println("\r\n");
        outClient.println("<html><body><h1>¡Hola!</h1></body></html>");*/

        String splitMessage[] = message.split("\n");
        String resource = (splitMessage[0].split("/"))[1];
        resource = (resource.split(" "))[0];

        if (splitMessage[0].startsWith("GET")) {
            if (this.resourceExists(resource)) {
                System.out.println("GET. MimeType: "+this.getMimeType(resource));
            }
            else{
                System.out.println("ERROR 404, NOT FOUND");
            }
        }
        else if (splitMessage[0].startsWith("HEAD")) {
            if (this.resourceExists(resource)) {
                System.out.println("HEAD. Tipo de MimeType: "+this.getMimeType(resource));
            }
            else{
                System.out.println("ERROR 404, NOT FOUND");
            }
        }
        else if (splitMessage[0].startsWith("POST")) {
            if (this.resourceExists(resource)) {
                System.out.println("200: OK, POST. Tipo de MimeType: "+this.getMimeType(resource));
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

    private boolean resourceExists(String resource){
        resource = "src/main/resources/" + resource;
        File f = new File(resource);
        return (f.exists());
    }

    private static String getMimeType(String resource) {
        MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
        String mimeType = mimeTypesMap.getContentType(resource);
        return mimeType;
    }

}