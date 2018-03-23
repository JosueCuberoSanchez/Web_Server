import java.io.PrintWriter;

/**
 * Created by josue on 21/03/18.
 */
public class HandlerThread implements Runnable {
    String message;
    private PrintWriter outClient;

    public HandlerThread(String message, PrintWriter outClient){
        this.message = message;
        this.outClient = outClient;
    }

    public void run() {
        RequestProcesser requestProcesser = new RequestProcesser();
        requestProcesser.handle(this.message,this.outClient);
    }
}
