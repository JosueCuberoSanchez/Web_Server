import java.io.DataInputStream;
import java.io.InputStream;
import java.io.PrintWriter;

/**
 * Created by josue on 21/03/18.
 */
public class HandlerThread implements Runnable {
    private PrintWriter outClient;
    InputStream is;

    public HandlerThread(PrintWriter outClient, InputStream is){
        this.is = is;
        this.outClient = outClient;
    }

    public void run() {
        RequestProcesser requestProcesser = new RequestProcesser();
        requestProcesser.handle(this.outClient,this.is);
    }
}
