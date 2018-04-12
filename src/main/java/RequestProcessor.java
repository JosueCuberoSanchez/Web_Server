import javax.activation.MimetypesFileTypeMap;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
 * Created by Josue on 21/03/18.
 */
public class RequestProcessor {
    private LogManager logManager;
    private List<String> mimeTypes;
    private String httpResponse;
    private String mimeType;
    private String payload;
    private Long contentLength;
    private PrintWriter printWriter;
    private InputStream is;
    private OutputStream os;
    private byte[] imagePayload;

    public RequestProcessor(OutputStream os, InputStream is, LogManager logManager) {
        this.logManager = logManager;
        this.mimeTypes = new LinkedList<String>();
        this.mimeTypes.add("text/html");
        this.mimeTypes.add("text/plain");
        this.mimeTypes.add("image/jpeg");
        this.mimeTypes.add("text/php");
        this.printWriter = new PrintWriter(os, true);
        this.is = is;
        this.os = os;
        this.httpResponse = "HTTP/1.1 200 OK";
    }

    private String buildMessage() {
        String message = "";
        try {
            InputStreamReader isReader = new InputStreamReader(this.is);
            BufferedReader br = new BufferedReader(isReader);

            //code to read and print headers
            String headerLine = null;
            while ((headerLine = br.readLine()).length() != 0) {
                message += headerLine + "\n";
            }

            //code to read the post payload data
            StringBuilder payload = new StringBuilder();
            while (br.ready()) {
                payload.append((char) br.read());
            }
            if (!payload.toString().equals("")) {
                message += payload.toString() + "\n";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }

    public void handle() {
        try {
            String message = this.buildMessage();
            System.out.println(message);
            String splitMessage[] = message.split("\n"); // Splits the message by lines
            String data = "";
            if (splitMessage.length > 6) {
                data = splitMessage[6];
            }
            String resource = (splitMessage[0].split("/"))[1]; // Retrieve the name of the file and its extension, with other content
            String method = (splitMessage[0].split("/"))[0]; // Retrieve the method
            resource = (resource.split(" "))[0]; // Retrieve JUST the name of the file and its extension, without other content
            if (method.startsWith("GET")) {
                if (this.resourceExists(resource)) {
                    this.mimeType = this.getMimeType(resource);
                    if (this.mimeTypes.contains(this.mimeType)) {
                        this.openFile("src/main/resources/" + resource);
                        this.logManager.write(method, (new Timestamp(System.currentTimeMillis())).getTime(), "Monkey Labs Server", "localhost", "/" + resource, data);
                    } else {
                        if (!resource.equals("")) {
                            this.httpResponse = "HTTP/1.1 406 Not Acceptable";
                        } else {
                            this.logManager.write(method, (new Timestamp(System.currentTimeMillis())).getTime(), "Monkey Labs Server", "localhost", "/" + resource, data);
                        }
                    }
                } else {
                    this.httpResponse = "HTTP/1.1 404 Not Found";
                }
            } else if (method.startsWith("HEAD")) {
                if (this.resourceExists(resource)) {
                    this.mimeType = this.getMimeType(resource);
                    if (this.mimeTypes.contains(this.mimeType)) {
                        this.openFile("src/main/resources/" + resource);
                        this.logManager.write(method, (new Timestamp(System.currentTimeMillis())).getTime(), "Monkey Labs Server", "localhost", "/" + resource, data);
                    } else {
                        if (!resource.equals("")) {
                            this.httpResponse = "HTTP/1.1 406 Not Acceptable";
                        } else {
                            this.logManager.write(method, (new Timestamp(System.currentTimeMillis())).getTime(), "Monkey Labs Server", "localhost", "/" + resource, data);
                        }
                    }
                } else {
                    this.httpResponse = "HTTP/1.1 404 Not Found";
                }
            } else if (method.startsWith("POST")) {
                if (this.resourceExists(resource)) {
                    this.mimeType = this.getMimeType(resource);
                    if (this.mimeTypes.contains(this.mimeType)) {
                        this.openFile("src/main/resources/" + resource);
                        this.logManager.write(method, (new Timestamp(System.currentTimeMillis())).getTime(), "Monkey Labs Server", "localhost", "/" + resource, data);
                    } else {
                        if (!resource.equals("")) {
                            this.httpResponse = "HTTP/1.1 406 Not Acceptable";
                        } else {
                            this.logManager.write(method, (new Timestamp(System.currentTimeMillis())).getTime(), "Monkey Labs Server", "localhost", "/" + resource, data);
                        }
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getServerTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT-6"));
        return dateFormat.format(calendar.getTime());
    }

    private void openFile(String resource) {
        try {
            if (this.mimeType.equals("text/plain")) {
                File txt = new File(resource);
                this.processTxt(txt);
            } else if (this.mimeType.equals("text/html")) {
                File html = new File(resource);
                this.processTxt(html);
            } else if (this.mimeType.equals("image/jpeg")) {
                File jpeg = new File(resource);
                this.processImage(jpeg);
            } else { //php
                //process PHP
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void processTxt(File file) {
        try {
            this.contentLength = file.length();
            this.payload = "";
            BufferedReader br = new BufferedReader(new FileReader(file));
            for (String line; (line = br.readLine()) != null; ) {
                this.payload += line + "\n";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void processImage(File jpeg) {
        try {
            BufferedImage image = ImageIO.read(jpeg);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", byteArrayOutputStream);
            this.imagePayload = byteArrayOutputStream.toByteArray();
            this.contentLength = (long) byteArrayOutputStream.size();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean resourceExists(String resource) {
        resource = "src/main/resources/" + resource;
        File f = new File(resource);
        return (f.exists());
    }

    private static String getMimeType(String resource) {
        MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
        String mimeType = mimeTypesMap.getContentType(resource);
        return mimeType;
    }

}