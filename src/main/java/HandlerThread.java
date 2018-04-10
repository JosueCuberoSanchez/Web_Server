import java.io.DataInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;

/**
 * Created by josue on 21/03/18.
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
        RequestProcesser requestProcesser = new RequestProcesser(this.os, this.is, this.logManager);
        requestProcesser.handle();
    }
}
