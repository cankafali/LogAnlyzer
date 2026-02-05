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

    public static void main(String[] args) {
        List<String> ips = new ArrayList<>();
        List<String> paths = new ArrayList<>();
        List<Integer> statuses = new ArrayList<>();
        int totalBytes = 0;

        LogEntry e = new LogEntry("2023-10-04T10:15:30", "192.168.1.1", "GET", "/index.html", 200, 1024);
        ips.add(e.getIp());
        paths.add(e.getPath());
        statuses.add(e.getStatus());
        totalBytes += e.getBytes();

        System.out.println("IPs: " + ips);
        System.out.println("Paths: " + paths);
        System.out.println("Statuses: " + statuses);
        System.out.println("Total Bytes: " + totalBytes);
    }
}
