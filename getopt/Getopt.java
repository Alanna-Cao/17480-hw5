/**
 * A Java implementation of the C getopt() and getopt_long() functions for parsing command-line options.
 * This class provides functionality similar to the GNU C getopt functions,
 * allowing programs to parse command-line arguments according to Unix conventions.
 * 
 * <p>This class is not thread-safe. Each thread should use its own Getopt instance.
 *
 * <p>Usage example for short options:
 * <pre>{@code
 * Getopt g = new Getopt(args, "ab:c");
 * int c;
 * while ((c = g.getOpt()) != -1) {
 *     switch (c) {
 *         case 'a': handleA(); break;
 *         case 'b': handleB(g.getOptArg()); break;
 *         case 'c': handleC(); break;
 *         case '?': handleError(); break;
 *     }
 * }
 * }</pre>
 *
 * <p>Usage example for long options:
 * <pre>{@code
 * LongOption[] longopts = {
 *     new LongOption("help", false, null, 'h'),
 *     new LongOption("output", true, null, 'o')
 * };
 * Getopt g = new Getopt(args, "ho:", longopts);
 * int c;
 * while ((c = g.getOpt()) != -1) {
 *     switch (c) {
 *         case 'h': showHelp(); break;
 *         case 'o': setOutput(g.getOptArg()); break;
 *         case '?': handleError(); break;
 *     }
 * }
 * }</pre>
 */
public final class Getopt {

    /**
     * Describes a single long option for getopt_long support.
     * This class is immutable.
     */
    public static final class LongOption {
        /**
         * Constant indicating the option takes no argument
         */
        public static final int NO_ARGUMENT = 0;

        /**
         * Constant indicating the option requires an argument
         */
        public static final int REQUIRED_ARGUMENT = 1;

        /**
         * Constant indicating the option takes an optional argument
         */
        public static final int OPTIONAL_ARGUMENT = 2;

        /**
         * Constructs a new LongOption.
         *
         * @param name      The long option name without leading '--'
         * @param hasArg    Whether option takes an argument: NO_ARGUMENT, REQUIRED_ARGUMENT, or OPTIONAL_ARGUMENT
         * @param flag      If not null, this flag will be set to val when the option is found
         * @param val       Value to return or to load into flag
         * @throws IllegalArgumentException if name is null or empty, or if hasArg is not one of the valid constants
         */
        public LongOption(String name, int hasArg, boolean[] flag, int val);

        /**
         * Returns the name of this long option.
         *
         * @return The name of this long option without leading '--'
         */
        public String getName();

        /**
         * Returns the argument requirement for this option.
         *
         * @return NO_ARGUMENT, REQUIRED_ARGUMENT, or OPTIONAL_ARGUMENT
         */
        public int getHasArg();
    }

    /**
     * Constructs a new Getopt object for parsing command-line arguments with short options only.
     *
     * @param args The command-line arguments array to be parsed
     * @param optstring A string containing the legitimate option characters.
     *                 A character followed by a colon (':') indicates that the
     *                 option requires an argument. If the first character of
     *                 optstring is ':', then getopt() returns ':' instead of '?'
     *                 when a missing option argument is detected.
     * @throws IllegalArgumentException if args or optstring is null, or if
     *         optstring contains invalid option specifications
     */
    public Getopt(String[] args, String optstring);

    /**
     * Constructs a new Getopt object for parsing both short and long options.
     *
     * @param args The command-line arguments array to be parsed
     * @param optstring A string containing the legitimate short option characters
     * @param longOptions Array of LongOption objects describing the valid long options
     * @throws IllegalArgumentException if any parameter is null, or if
     *         optstring contains invalid option specifications
     */
    public Getopt(String[] args, String optstring, LongOption[] longOptions);

    /**
     * Parses the next command-line option.
     * If this Getopt instance was constructed without long options and encounters
     * an argument starting with "--", it will stop processing options at that point
     * (treating it and all subsequent arguments as non-options).
     * 
     * @return Returns an integer representing either:
     *         - The ASCII value of the short option character found
     *         - The val specified for a long option in the LongOption array
     *         - -1 when the argument list is exhausted or a non-option argument is found
     *         - '?' (ASCII 63) when an unknown option is found or an option is missing its argument
     *         - ':' (ASCII 58) when an option is missing its argument and optstring begins with ':'
     */
    public int getOpt();

    /**
     * Returns the current option-argument if the last option required an argument.
     *
     * @return The argument value for the last processed option that required an
     *         argument, or null if the last option did not require an argument
     */
    public String getOptArg();

    /**
     * Returns the current index in the argument array.
     * After getopt() has processed all options, this value indicates where the
     * remaining non-option arguments begin in the args array.
     *
     * @return The index of the next element of the args array to be processed
     */
    public int getOptIndex();

    /**
     * Controls whether error messages are printed to stderr.
     * 
     * @param enable true to enable error reporting (default), false to disable
     */
    public void setErrorReporting(boolean enable);

    /**
     * Returns the ASCII value of the option character that caused the last error.
     * When getOpt() returns '?' or ':', this value contains the ASCII value
     * of the option character that caused the error.
     *
     * @return The ASCII value of the option character that caused the last error
     */
    public int getErrorOpt();

    /**
     * Returns the index of the matched long option in the long options array.
     * Only meaningful after getOpt() has returned a long option value.
     *
     * @return The index in the long options array of the last matched long option,
     *         or -1 if the last option was a short option
     */
    public int getLongOptIndex();

    /**
     * Checks if a particular option was encountered during parsing.
     *
     * @param option the option character to check
     * @return true if the option was found during parsing, false otherwise
     */
    public boolean hasOption(char option);
}