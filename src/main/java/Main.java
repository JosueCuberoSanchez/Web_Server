/**
 * Created by josue on 19/03/18.
 */
public class Main {
    public static void main(String[] args) {
        //HTTPServer httpServer = new HTTPServer();
        //httpServer.startServer();
       LogManager logManager = new LogManager();
       logManager.write("GET", 12345678, "Monkey Labs Server", "localhost", "/head.jpg", "mensaje=\"Hola\"");
        //logManager.write("GET", "14415357", "localhost", "localhost", "/imagen.gif", "");
    }
}
