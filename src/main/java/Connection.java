import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;

/**
 * Created by Renato on 21/10/2017.
 */
public abstract class Connection {
    protected int PORT;
    protected String HOST;
    protected String serverMessage;
    protected ServerSocket ss;
    protected Socket cs;
    //protected DataOutputStream outServer, outClient;
    protected PrintWriter outServer, outClient;
    protected String connectedAS;
    protected boolean active;
    protected boolean turnOff;

    public Connection(){
        this.active = true;
    }

    public void initConnection(String tipo, int PORT, String HOST) throws IOException{
        this.PORT = PORT;
        this.HOST = HOST;
        this.active = true;
        this.turnOff = false;
        if (tipo.equalsIgnoreCase("server")) {

            ss = new ServerSocket(this.PORT);
            cs = new Socket();
        }
        else {
            boolean connected = false;

            while (!connected && active){
                try {
                    InetAddress inetAddress =InetAddress.getByName(HOST);
                    cs = new Socket(inetAddress,PORT);

                }catch (ConnectException e){
                }
                if(cs != null){
                    connected = true;
                }
            }

        }
    }

    public int getPORT() {
        return PORT;
    }

    public void setPORT(int PORT) {
        this.PORT = PORT;
    }

    public String getHOST() {
        return HOST;
    }

    public void setHOST(String HOST) {
        this.HOST = HOST;
    }
}
