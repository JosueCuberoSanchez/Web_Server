import java.io.InputStream;
import java.io.OutputStream;

/**
 * Universidad de Costa Rica
 * Facultad de Ingenierías
 * Escuela de Ciencias de la Computación e Informática
 * Profesor Braulio Solano
 * @author Josué Cubero Sánchez, B42190
 * @author Renato Mainieri Sáenz, B54076
 * Primer Ciclo, 2018
 *
 * Thread class that will call RequestProcessor to handle the request.
 */
public class HandlerThread implements Runnable {
    private OutputStream os;
    private InputStream is;
    private LogManager logManager;

    public HandlerThread(OutputStream os, InputStream is, LogManager logManager) {
        this.logManager = logManager;
        this.is = is;
        this.os = os;
    }

    public void run() {
        RequestProcessor requestProcessor = new RequestProcessor(this.os, this.is, this.logManager);
        requestProcessor.handle();
    }
}
