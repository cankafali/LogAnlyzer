package model;

import java.util.ArrayList;
import java.util.List;

public class LogEntry {
    private final String timestamp;
    private final String ip;
    private final String method;
    private final String path;
    private final int status;
    private final int bytes;

    public LogEntry(String timestamp, String ip, String method, String path, int status, int bytes) {
        this.timestamp = timestamp;
        this.ip = ip;
        this.method = method;
        this.path = path;
        this.status = status;
        this.bytes = bytes;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getIp() {
        return ip;
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public int getStatus() {
        return status;
    }

    public int getBytes() {
        return bytes;
    }

    // ... existing code ...
}
