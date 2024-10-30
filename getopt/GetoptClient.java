package getopt;

public class GetoptClient {
    public static void main(String[] args) {
        // Example 1: Basic option parsing
        demonstrateBasicParsing(new String[]{"-a", "-b", "-c", "foo", "arg1", "arg2"});

        // Example 2: Error handling
        demonstrateErrorHandling(new String[]{"-a", "-x", "-c"});

        // Example 3: Mixed options and arguments
        demonstrateMixedUsage(new String[]{"-a", "file1", "-b", "--", "-c", "file2"});
    }

    private static void demonstrateBasicParsing(String[] args) {
        System.out.println("\nDemo 1: Basic Option Parsing");
        System.out.println("Command line: " + String.join(" ", args));

        Getopt g = new Getopt(args, "abc:");
        int c;
        String optarg;
        boolean aflag = false;
        boolean bflag = false;
        String cvalue = null;

        while ((c = g.getOpt()) != -1) {
            switch (c) {
                case 'a':
                    aflag = true;
                    break;
                case 'b':
                    bflag = true;
                    break;
                case 'c':
                    cvalue = g.getOptArg();
                    break;
            }
        }

        // Print results
        System.out.println("aflag = " + aflag);
        System.out.println("bflag = " + bflag);
        System.out.println("cvalue = " + cvalue);

        // Print remaining arguments
        for (int i = g.getOptIndex(); i < args.length; i++) {
            System.out.println("Non-option argument: " + args[i]);
        }
    }

    private static void demonstrateErrorHandling(String[] args) {
        System.out.println("\nDemo 2: Error Handling");
        System.out.println("Command line: " + String.join(" ", args));

        Getopt g = new Getopt(args, ":abc:"); // Leading ':' for custom error handling
        g.setOptError(0); // Disable automatic error reporting
        int c;

        while ((c = g.getOpt()) != -1) {
            switch (c) {
                case 'a':
                case 'b':
                    System.out.println("Found option: -" + (char)c);
                    break;
                case 'c':
                    System.out.println("Found option -c with value: " + g.getOptArg());
                    break;
                case ':':
                    System.out.println("Missing argument for option: -" + (char)g.getErrorOpt());
                    break;
                case '?':
                    System.out.println("Unknown option: -" + (char)g.getErrorOpt());
                    break;
            }
        }
    }

    private static void demonstrateMixedUsage(String[] args) {
        System.out.println("\nDemo 3: Mixed Usage");
        System.out.println("Command line: " + String.join(" ", args));

        Getopt g = new Getopt(args, "ab");
        int c;

        // Process options
        while ((c = g.getOpt()) != -1) {
            switch (c) {
                case 'a':
                case 'b':
                    System.out.println("Processing option: -" + (char)c);
                    break;
                case '?':
                    System.out.println("Unknown option");
                    break;
            }
        }

        // Process remaining arguments
        System.out.println("Remaining arguments:");
        for (int i = g.getOptIndex(); i < args.length; i++) {
            System.out.println(args[i]);
        }
    }
}