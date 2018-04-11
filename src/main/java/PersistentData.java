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

    public void setMethod(String method) {
        this.method = method;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getRefer() {
        return refer;
    }

    public void setRefer(String refer) {
        this.refer = refer;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
