
import sun.rmi.runtime.Log;

import java.io.*;

/**
 * Created by Renato & Vladimir on 21/10/2017.
 */
public class Client extends Connection implements Runnable {
    private int port;
    private String hostAddress;


    public Client(int port, String host) throws IOException {
        this.port = port;
        this.hostAddress = host;
        this.PORT = port;
        this.HOST = host;
        this.connectedAS = "";
    }

    public void startClient() {
        try {
            this.initConnection("client", this.port, this.hostAddress);
            if (this.cs != null && !this.cs.isClosed()) {
                this.outServer = new PrintWriter(cs.getOutputStream(),true);
                BufferedReader input = new BufferedReader(new InputStreamReader(cs.getInputStream()));
                String writing = "";
                while (this.active) {
                    //System.out.println(writing);
                    this.outServer.println("");
                    this.serverMessage = input.readLine();
                    if (this.serverMessage == null) {
                        //System.out.println("Lost connection");
                    } else {
                        writing = "Server message: " + this.serverMessage + "\r\n";
                        //System.out.println(writing);
                    }

                    Thread.sleep(10000);
                    // System.out.println(serverMessage);
                    // serverMessage = input.readLine();
                    //System.out.println(serverMessage);
                    // Guarda el mapeo
                }
            }
            //cs.close();
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }


    @Override
    public void run() {
        this.startClient();
    }

}
