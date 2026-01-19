package Logparse;

import model.LogEntry;

public class LogParser {

    // Expected format: timestamp ip method path status bytes
    // Example:
    // 2026-01-17T10:01:02Z 192.168.1.10 GET /api/login 200 123
    public LogEntry parse(String line) {
        if (line == null) return null;

        line = line.trim();
        if (line.isEmpty() || line.startsWith("#")) return null;

        String[] parts = line.split("\\s+");
        if (parts.length < 6) return null;

        String ts = parts[0];
        String ip = parts[1];
        String method = parts[2];
        String path = parts[3];

        int status;
        int bytes;
        try {
            status = Integer.parseInt(parts[4]);
            bytes = Integer.parseInt(parts[5]);
        } catch (NumberFormatException e) {
            return null;
        }

        return new LogEntry(ts, ip, method, path, status, bytes);
    }
}
