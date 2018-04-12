
/**
 * Created by Renato on 09/04/18.
 */
public class PersistentData {
    private String method;
    private long timestamp;
    private String server;
    private String refer;
    private String url;
    private String data;

    public PersistentData(String method, long timestamp, String server, String refer, String url, String data) {
        this.method = method;
        this.timestamp = timestamp;
        this.server = server;
        this.refer = refer;
        this.url = url;
        this.data = data;
    }

    public String getMethod() {
        return method;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getServer() {
        return server;
    }

    public String getRefer() {
        return refer;
    }

    public String getUrl() {
        return url;
    }

    public String getData() {
        return data;
    }
}
