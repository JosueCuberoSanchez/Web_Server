/**
 * Created by josue on 22/03/18.
 */
import java.io.*;
import java.net.Socket;
import java.io.DataOutputStream;
import java.io.IOException;

public class Client{

    private Socket cs;
    private DataOutputStream outServer;

    public Client() {
        try {
            this.cs = new Socket("127.0.0.1", 8000);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void startClient(String message, PrintWriter out){
        try {
            out.println("HTTP/1.1 200 OK");
            out.println("Content-Type: text/html");
            out.println("Content-Length: " + message.length());
            out.println();
            out.println(message);
            out.flush();
            out.close();
            out.close();
            this.cs.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
