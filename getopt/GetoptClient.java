
import java.util.ArrayList;
import java.util.List;

public class GetoptClient {
    public static void main(String[] args) {
        // Demo 1: Basic short options
        demonstrateBasicShortOptions(new String[]{"-v", "-n", "42", "-o", "output.txt", "file1.txt", "file2.txt"});

        // Demo 2: Long options
        demonstrateLongOptions(new String[]{"--verbose", "--number", "42", "--output", "output.txt", "file1.txt"});

        // Demo 3: Mixed short and long options
        demonstrateMixedOptions(new String[]{"-v", "--output", "output.txt", "--", "-n", "42"});

        // Demo 4: Error handling
        demonstrateErrorHandling(new String[]{"-v", "-x", "-n"});
    }

    /**
     * Demonstrates basic usage with short options
     * Supports: -v (verbose), -n <number>, -o <output-file>
     */
    private static void demonstrateBasicShortOptions(String[] args) {
        System.out.println("\nDemo 1: Basic Short Options");
        System.out.println("Command: program " + String.join(" ", args));

        Getopt g = new Getopt(args, "vn:o:");
        g.setErrorReporting(true);

        boolean verbose = false;
        int number = 0;
        String outputFile = null;
        List<String> files = new ArrayList<>();

        int opt;
        while ((opt = g.getOpt()) != -1) {
            switch (opt) {
                case 'v':
                    verbose = true;
                    break;
                case 'n':
                    try {
                        number = Integer.parseInt(g.getOptArg());
                    } catch (NumberFormatException e) {
                        System.err.println("Error: -n requires a numeric argument");
                        return;
                    }
                    break;
                case 'o':
                    outputFile = g.getOptArg();
                    break;
                case '?':
                    // Error already reported by getOpt()
                    return;
            }
        }

        // Collect remaining non-option arguments
        for (int i = g.getOptIndex(); i < args.length; i++) {
            files.add(args[i]);
        }

        // Print parsed values
        System.out.println("Parsed values:");
        System.out.println("  verbose: " + verbose);
        System.out.println("  number: " + number);
        System.out.println("  output file: " + outputFile);
        System.out.println("  input files: " + files);
    }

    /**
     * Demonstrates usage with long options
     * Supports: --verbose, --number <num>, --output <file>
     */
    private static void demonstrateLongOptions(String[] args) {
        System.out.println("\nDemo 2: Long Options");
        System.out.println("Command: program " + String.join(" ", args));

        // Define long options
        Getopt.LongOption[] longopts = {
            new Getopt.LongOption("verbose", Getopt.LongOption.NO_ARGUMENT, null, 'v'),
            new Getopt.LongOption("number", Getopt.LongOption.REQUIRED_ARGUMENT, null, 'n'),
            new Getopt.LongOption("output", Getopt.LongOption.REQUIRED_ARGUMENT, null, 'o')
        };

        Getopt g = new Getopt(args, "vn:o:", longopts);
        
        boolean verbose = false;
        int number = 0;
        String outputFile = null;
        List<String> files = new ArrayList<>();

        int opt;
        while ((opt = g.getOpt()) != -1) {
            switch (opt) {
                case 'v':
                    verbose = true;
                    break;
                case 'n':
                    try {
                        number = Integer.parseInt(g.getOptArg());
                    } catch (NumberFormatException e) {
                        System.err.println("Error: --number requires a numeric argument");
                        return;
                    }
                    break;
                case 'o':
                    outputFile = g.getOptArg();
                    break;
                case '?':
                    return;
            }
        }

        // Collect remaining arguments
        for (int i = g.getOptIndex(); i < args.length; i++) {
            files.add(args[i]);
        }

        // Print parsed values
        System.out.println("Parsed values:");
        System.out.println("  verbose: " + verbose);
        System.out.println("  number: " + number);
        System.out.println("  output file: " + outputFile);
        System.out.println("  input files: " + files);
    }

    /**
     * Demonstrates mixing short and long options, and using "--" to terminate option processing
     */
    private static void demonstrateMixedOptions(String[] args) {
        System.out.println("\nDemo 3: Mixed Short and Long Options");
        System.out.println("Command: program " + String.join(" ", args));

        Getopt.LongOption[] longopts = {
            new Getopt.LongOption("output", Getopt.LongOption.REQUIRED_ARGUMENT, null, 'o')
        };

        Getopt g = new Getopt(args, "vn:", longopts);

        int opt;
        while ((opt = g.getOpt()) != -1) {
            switch (opt) {
                case 'v':
                    System.out.println("Found -v option");
                    break;
                case 'n':
                    System.out.println("Found -n option with value: " + g.getOptArg());
                    break;
                case 'o':
                    System.out.println("Found --output option with value: " + g.getOptArg());
                    break;
                case '?':
                    // Error already reported
                    break;
            }
        }

        // Print remaining arguments (after --)
        System.out.println("Remaining arguments:");
        for (int i = g.getOptIndex(); i < args.length; i++) {
            System.out.println("  " + args[i]);
        }
    }

    /**
     * Demonstrates error handling
     */
    private static void demonstrateErrorHandling(String[] args) {
        System.out.println("\nDemo 4: Error Handling");
        System.out.println("Command: program " + String.join(" ", args));

        // Use leading ':' in optstring for custom error handling
        Getopt g = new Getopt(args, ":vn:");
        g.setErrorReporting(false); // Handle errors ourselves

        int opt;
        while ((opt = g.getOpt()) != -1) {
            switch (opt) {
                case 'v':
                    System.out.println("Verbose mode enabled");
                    break;
                case 'n':
                    System.out.println("Number set to: " + g.getOptArg());
                    break;
                case ':':
                    System.out.printf("Missing argument for option -%c\n", 
                                    (char)g.getErrorOpt());
                    break;
                case '?':
                    System.out.printf("Unknown option -%c\n", 
                                    (char)g.getErrorOpt());
                    break;
            }
        }

        // Check if specific option was found
        if (g.hasOption('v')) {
            System.out.println("Verbose option was specified");
        }
    }
}