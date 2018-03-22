import java.io.IOException;

/**
 * Created by josue on 19/03/18.
 */
public class Main {
    public static void main(String[] args) {
        try {
            new Thread(new Server(80)).start();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
