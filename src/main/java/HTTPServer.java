/**
 * Created by Renato on 21/03/2018.
 */


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;

/**
 * Created by Renato on 21/10/2017.
 */

public class HTTPServer{

    private ServerSocket ss;
    private Socket cs;
    private PrintWriter outClient;

    public HTTPServer(){
        try {
            this.cs = new Socket();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void startServer() {
        try {
            while(true) {
                ss = new ServerSocket(8000);
                System.out.println("\nServidor  esperando...");
                this.cs = this.ss.accept();
                System.out.println("Cliente conectado en el servidor ");
                /*PrintWriter outClient = new PrintWriter(this.cs.getOutputStream(), true);
                OutputStream outputStream = cs.getOutputStream();
                File file = new File("src/main/resources/head.jpg");
                System.out.println(file.length());
                BufferedImage image = ImageIO.read(file);

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ImageIO.write(image, "jpg", byteArrayOutputStream);

                byte[] size = ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array();
                outClient.println("HTTP/1.1 200 OK");
                outClient.println("Content-Type: image/jpeg");
                outClient.println("Content-Length: "+file.length());
                outClient.println();
                //outputStream.write();
                outputStream.write(byteArrayOutputStream.toByteArray());
                outputStream.flush();
                System.out.println("Flushed: " + System.currentTimeMillis());


                System.out.println("Closing: " + System.currentTimeMillis());
                outClient.close();
                outputStream.close();*/
                Thread thread =
                        new Thread(new HandlerThread(this.ss,this.cs.getOutputStream(), this.cs.getInputStream()));
                thread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
