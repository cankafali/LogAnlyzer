package analyze;

import model.LogEntry;
import Logparse.LogParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Report {

    private final int parsed;
    private final int skipped;

    private final String topIps;
    private final String topPaths;
    private final String statusDist;

    private final long totalBytes;
    private final double avgBytes;

    private Report(int parsed, int skipped,
                   String topIps, String topPaths, String statusDist,
                   long totalBytes, double avgBytes) {
        this.parsed = parsed;
        this.skipped = skipped;
        this.topIps = topIps;
        this.topPaths = topPaths;
        this.statusDist = statusDist;
        this.totalBytes = totalBytes;
        this.avgBytes = avgBytes;
    }

    public static Report fromLines(List<String> lines, LogParser parser, int topK) {
        List<LogEntry> entries = new ArrayList<>();
        int skipped = 0;

        for (String line : lines) {
            LogEntry e = parser.parse(line);
            if (e == null) skipped++;
            else entries.add(e);
        }

        List<String> ips = new ArrayList<>();
        List<String> paths = new ArrayList<>();
        List<Integer> statuses = new ArrayList<>();

        long totalBytes = 0;
        for (LogEntry e : entries) {
            ips.add(e.ip);
            paths.add(e.path);
            statuses.add(e.status);
            totalBytes += e.bytes;
        }

        Map<String, Integer> ipCounts = Counter.count(ips);
        Map<String, Integer> pathCounts = Counter.count(paths);
        Map<Integer, Integer> statusCounts = Counter.count(statuses);

        String topIps = Counter.formatTopK(Counter.topK(ipCounts, topK));
        String topPaths = Counter.formatTopK(Counter.topK(pathCounts, topK));
        String statusDist = formatStatus(statusCounts);

        double avgBytes = entries.isEmpty() ? 0.0 : (double) totalBytes / entries.size();

        return new Report(entries.size(), skipped, topIps, topPaths, statusDist, totalBytes, avgBytes);
    }

    public String render(String filePath) {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Java Log Analyzer Report ===\n");
        sb.append("File          : ").append(filePath).append("\n");
        sb.append("Parsed entries: ").append(parsed).append("\n");
        sb.append("Skipped lines : ").append(skipped).append("\n\n");

        sb.append("== Top IPs ==\n").append(topIps).append("\n");
        sb.append("== Top Endpoints ==\n").append(topPaths).append("\n");
        sb.append("== Status Code Distribution ==\n").append(statusDist).append("\n");

        sb.append("== Bytes ==\n");
        sb.append("Total bytes   : ").append(totalBytes).append("\n");
        sb.append("Avg bytes/req : ").append(String.format("%.2f", avgBytes)).append("\n");

        return sb.toString();
    }

    private static String formatStatus(Map<Integer, Integer> statusCounts) {
        if (statusCounts.isEmpty()) return "(no data)\n";

        var keys = new ArrayList<>(statusCounts.keySet());
        keys.sort(Integer::compareTo);

        StringBuilder sb = new StringBuilder();
        for (Integer code : keys) {
            sb.append(code).append(" -> ").append(statusCounts.get(code)).append("\n");
        }
        return sb.toString();
    }
}
