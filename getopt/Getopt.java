
/**
 * A Java implementation of the C getopt() function for parsing command-line options.
 * This class provides functionality similar to the GNU C getopt() function,
 * allowing programs to parse command-line arguments according to Unix conventions.
 * <Example code>
 */
public class Getopt {
    /**
     * Constructs a new Getopt object for parsing command-line arguments.
     *
     * @param args The command-line arguments array to be parsed
     * @param optstring A string containing the legitimate option characters.
     *                 A character followed by a colon (':') indicates that the
     *                 option requires an argument. If the first character of
     *                 optstring is ':', then getopt() returns ':' instead of '?'
     *                 when a missing option argument is detected.
     */
    public Getopt(String[] args, String optstring);

    /**
     * Parses the next command-line option.
     * 
     * @return Returns an integer representing either:
     *         - The ASCII value of the option character found
     *         - -1 when the argument list is exhausted or a non-option argument is found
     *         - '?' (ASCII 63) when an unknown option is found or an option is missing its argument
     *         - ':' (ASCII 58) when an option is missing its argument and optstring begins with ':'
     */
    public int getOpt();

    /**
     * Returns the current option-argument if the last option required an argument.
     *
     * @return The argument value for the last processed option that required an
     *         argument, or null if the last option did not require an argument.
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
     * Controls error reporting behavior.
     * If set to 1 (default), error messages will be printed to stderr when
     * unknown options or missing required arguments are encountered.
     * If set to 0, no error messages will be printed.
     *
     * @param opterr 1 to enable error reporting, 0 to disable
     */
    public void setOptError(int opterr);

    /**
     * Returns the ASCII value of the option character that caused the last error.
     * When getopt() returns '?' or ':', this variable contains the ASCII value
     * of the option character that caused the error.
     *
     * @return The ASCII value of the option character that caused the last error
     */
    public int getErrorOpt();
}