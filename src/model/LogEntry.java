package model;

public class LogEntry {
    public final String timestamp;
    public final String ip;
    public final String method;
    public final String path;
    public final int status;
    public final int bytes;

    public LogEntry(String timestamp, String ip, String method, String path, int status, int bytes) {
        this.timestamp = timestamp;
        this.ip = ip;
        this.method = method;
        this.path = path;
        this.status = status;
        this.bytes = bytes;
    }
}
