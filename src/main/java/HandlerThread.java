/**
 * Created by josue on 21/03/18.
 */
public class HandlerThread implements Runnable {
    String message;

    public HandlerThread(String message){
        this.message = message;
    }

    public void run() {
        RequestProcesser requestProcesser = new RequestProcesser();
        requestProcesser.handle(this.message);
    }
}
