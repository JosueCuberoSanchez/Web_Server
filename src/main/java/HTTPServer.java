/**
 * Created by Renato on 21/03/2018.
 */


import java.io.DataOutputStream;
import java.io.IOException;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Renato on 21/10/2017.
 */

public class HTTPServer{

    private ServerSocket ss;
    private Socket cs;
    private PrintWriter outClient;

    public HTTPServer(){
        try {
            this.ss = new ServerSocket(8000);
            this.cs = new Socket();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void startServer() {
        try {
            while(true) {
                System.out.println("\nServidor  esperando...");
                cs = ss.accept();
                System.out.println("Cliente conectado en el servidor ");
                this.outClient = new PrintWriter(this.cs.getOutputStream(), true);
                BufferedReader input = new BufferedReader(new InputStreamReader(cs.getInputStream()));
                String line = input.readLine();
                String message = "";
                while (line!=null &&  !(line).equals("") ) {
                    message += line + "\n";
                    line = input.readLine();
                }
                outClient.println(message);
                //Thread thread = new Thread(new HandlerThread(message));
                //thread.start();
                ss.close();
                cs.close();
                ss = new ServerSocket(8000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
