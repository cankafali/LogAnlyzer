package app;

public class CliArgs {
    private final String filePath;
    private final int topK;

    private CliArgs(String filePath, int topK) {
        this.filePath = filePath;
        this.topK = topK;
    }

    public String filePath() { return filePath; }
    public int topK() { return topK; }

    public static CliArgs parse(String[] args) {
        String file = "C:\\Users\\Hakkı\\Desktop\\javaProjects\\analyzer\\sample-data\\acces.log";
        int top = 5;

        for (int i = 0; i < args.length; i++) {
            String a = args[i];

            if (a.equals("--file") && i + 1 < args.length) {
                file = args[++i];
            } else if (a.equals("--top") && i + 1 < args.length) {
                try {
                    top = Integer.parseInt(args[++i]);
                } catch (NumberFormatException ignored) {
                    // keep default
                }
            } else if (a.equals("--help") || a.equals("-h")) {
                printHelpAndExit();
            }
        }

        return new CliArgs(file, top);
    }

    private static void printHelpAndExit() {
        System.out.println("Java Log Analyzer");
        System.out.println("Usage:");
        System.out.println("  Run app.Main with optional args:");
        System.out.println("    --file <path>   (default: sample-data/access.log)");
        System.out.println("    --top <k>       (default: 5)");
        System.out.println("    --help          show help");
        System.exit(0);
    }
}
