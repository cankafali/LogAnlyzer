package app;

import analyze.Report;
import Logio.LogReader;
import Logparse.LogParser;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        CliArgs cli = CliArgs.parse(args);

        LogReader reader = new LogReader();
        LogParser parser = new LogParser();

        List<String> lines;
        try {
            lines = reader.readAllLines(cli.filePath());
        } catch (IOException e) {
            System.out.println("Failed to read file: " + e.getMessage());
            return;
        }

        Report report = Report.fromLines(lines, parser, cli.topK());
        System.out.println(report.render(cli.filePath()));
    }
}
