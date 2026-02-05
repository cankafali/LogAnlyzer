package app;

import model.LogEntry;

import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        List<String> ips = new ArrayList<>();
        List<String> paths = new ArrayList<>();
        List<Integer> statuses = new ArrayList<>();
        int totalBytes = 0;

        LogEntry e = new LogEntry(
                "2023-10-04T10:15:30",
                "192.168.1.1",
                "GET",
                "/index.html",
                200,
                1024
        );

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
