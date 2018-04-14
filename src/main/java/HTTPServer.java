import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Universidad de Costa Rica
 * Facultad de Ingenierías
 * Escuela de Ciencias de la Computación e Informática
 * Profesor Braulio Solano
 * @author Josué Cubero Sánchez, B42190
 * @author Renato Mainieri Sáenz, B54076
 * Primer Ciclo, 2018
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

    /**
     * Make the server start to listen for incomming requests on port 8000.
     */
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
