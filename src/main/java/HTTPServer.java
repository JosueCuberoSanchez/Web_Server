import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Renato on 21/03/2018.
 */

public class HTTPServer {

    private ServerSocket ss;
    private Socket cs;
    private LogManager logManager;

    public HTTPServer() {
        try {
            this.logManager = new LogManager();
            this.ss = new ServerSocket(8000);
            this.cs = new Socket();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startServer() {
        try {
            while (true) {
                System.out.println("\nServidor  esperando...");
                this.cs = this.ss.accept();
                System.out.println("Cliente conectado en el servidor");
                Thread thread = new Thread(new HandlerThread(this.cs.getOutputStream(), this.cs.getInputStream(), this.logManager));
                thread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
