/**
 * Created by josue on 22/03/18.
 */
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Client{

    private Socket cs;
    private DataOutputStream outServer;

    public Client() {
        try {
            this.cs = new Socket("localhost", 8000);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void startClient(String message){
        try {
            this.outServer = new DataOutputStream(cs.getOutputStream());
            this.outServer.writeUTF(message);
            this.cs.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
