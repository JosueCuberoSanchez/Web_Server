import javax.activation.MimetypesFileTypeMap;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
 * Created by josue on 21/03/18.
 */
public class RequestProcesser{
    private List<String> mimeTypes;
    private String httpResponse;
    private String mimeType;
    private String payload;
    private Long contentLength;
    private PrintWriter printWriter;
    private InputStream is;
    private OutputStream os;
    private byte[] imagePayload;

    public RequestProcesser(OutputStream os,InputStream is){
        this.mimeTypes = new LinkedList<String>();
        this.mimeTypes.add("text/html");
        this.mimeTypes.add("text/plain");
        this.mimeTypes.add("image/jpeg");
        this.mimeTypes.add("text/php");
        this.printWriter = new PrintWriter(os,true);
        this.is = is;
        this.os = os;
        this.httpResponse = "HTTP/1.1 200 OK";
    }

    private String buildMessage(){
        String message = "";
        try {
            InputStreamReader isReader = new InputStreamReader(this.is);
            BufferedReader br = new BufferedReader(isReader);

            //code to read and print headers
            String headerLine = null;
            while ((headerLine = br.readLine()).length() != 0) {
                message += headerLine + "\n";
                //System.out.println(headerLine);
            }

            //code to read the post payload data
            StringBuilder payload = new StringBuilder();
            while (br.ready()) {
                payload.append((char) br.read());
            }
            //System.out.println("Payload data is: " + payload.toString());
            if(!payload.toString().equals("")){
                message += payload.toString();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return message;
    }

    public void handle(){
        try {
            String message = this.buildMessage();
            String splitMessage[] = message.split("\n");
            String resource = (splitMessage[0].split("/"))[1];
            resource = (resource.split(" "))[0];

            if (splitMessage[0].startsWith("GET")) {
                if (this.resourceExists(resource)) {
                    this.mimeType = this.getMimeType(resource);
                    if (this.mimeTypes.contains(this.mimeType)) {
                        this.openFile("src/main/resources/" + resource);
                    } else {
                        this.httpResponse = "HTTP/1.1 406 Not Acceptable";
                    }
                } else {
                    this.httpResponse = "HTTP/1.1 404 Not Found";
                }
            } else if (splitMessage[0].startsWith("HEAD")) {
                if (this.resourceExists(resource)) {
                    this.mimeType = this.getMimeType(resource);
                    if (this.mimeTypes.contains(this.mimeType)) {
                        this.openFile("src/main/resources/" + resource);
                    } else {
                        this.httpResponse = "HTTP/1.1 406 Not Acceptable";
                    }
                } else {
                    this.httpResponse = "HTTP/1.1 404 Not Found";
                }
            } else if (splitMessage[0].startsWith("POST")) {
                if (this.resourceExists(resource)) {
                    this.mimeType = this.getMimeType(resource);
                    if (this.mimeTypes.contains(this.mimeType)) {
                        this.openFile("src/main/resources/" + resource);
                    } else {
                        this.httpResponse = "HTTP/1.1 406 Not Acceptable";
                    }
                } else {
                    this.httpResponse = "HTTP/1.1 404 Not Found";
                }
            } else {
                this.httpResponse = "HTTP/1.1 501 Not Implemented";
            }
            this.printWriter.println(this.httpResponse);
            this.printWriter.println("Content-Type: " + this.mimeType);
            this.printWriter.println("Date: " + this.getServerTime());
            this.printWriter.println("Server: Monkey Labs Server");
            this.printWriter.println("Host: localhost");
            this.printWriter.println("Referer: localhost");
            this.printWriter.println("Content-Length: " + this.contentLength);
            this.printWriter.println();
            if (this.mimeType.equals("image/jpeg")) {
                this.os.write(this.imagePayload);
                this.os.flush();
            } else {
                this.printWriter.println(this.payload);
                this.printWriter.flush();
            }
            this.printWriter.close();
            this.os.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private String getServerTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT-6"));
        return dateFormat.format(calendar.getTime());
    }

    private void openFile(String resource){
        try {
            if (this.mimeType.equals("text/plain")) {
                File txt = new File(resource);
                this.processTxt(txt);
            } else if (this.mimeType.equals("text/html")) {
                File html = new File(resource);
                this.processTxt(html);
            } else if (this.mimeType.equals("image/jpeg")) {
                File jpeg = new File(resource);
                this.contentLength = jpeg.length();
                this.processImage(jpeg);
            } else { //php
                //process PHP
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void processTxt(File file){
        try {
            this.contentLength = file.length();
            BufferedReader br = new BufferedReader(new FileReader(file));
                for (String line; (line = br.readLine()) != null; ) {
                    this.payload += line;
                }
                // line is not visible here.
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void processImage(File jpeg){
        try {
            BufferedImage image = ImageIO.read(jpeg);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", byteArrayOutputStream);
            byte[] size = ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array();
            this.imagePayload = byteArrayOutputStream.toByteArray();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private boolean resourceExists(String resource){
        resource = "src/main/resources/" + resource;
        File f = new File(resource); //no se si sirva con imagenes
        return (f.exists());
    }

    private static String getMimeType(String resource) {
        MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
        String mimeType = mimeTypesMap.getContentType(resource);
        return mimeType;
    }

}