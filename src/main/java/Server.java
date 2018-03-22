/**
 * Created by Renato on 21/03/2018.
 */


import java.io.DataOutputStream;
import java.io.IOException;
import java.io.*;
/**
 * Created by Renato on 21/10/2017.
 */

public class Server extends Connection implements Runnable {
    private int port;

    public Server(int PORT) throws IOException {
        this.port = PORT;
    }

    public void startServer() {
        try {
            String writing = "";
            this.initConnection("server", this.port, "localhost");
            //System.out.println(writing);

            cs = ss.accept();
            //System.out.println(writing);
            outClient = new PrintWriter(cs.getOutputStream(),true);
            BufferedReader input = new BufferedReader(new InputStreamReader(cs.getInputStream()));

            //System.out.println(serverMessage);
            while (this.active) {
                if (!(serverMessage = input.readLine()).equals("exit")) {
                    writing = "Received Message: " + serverMessage + "\r\n";
                    System.out.println(writing);
                } else {
                    //System.out.println(writing);
                    ss.close();
                    this.connectedAS = "";
                    cs = ss.accept();
                    //System.out.println(writing);
                }
            }
        } catch (Exception e) {
        }
    }



    public void run() {
        this.startServer();
    }
}
