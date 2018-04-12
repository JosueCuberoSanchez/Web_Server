import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Josue on 21/03/18.
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
