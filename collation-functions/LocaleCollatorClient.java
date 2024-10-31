import java.util.Locale;

public class LocaleCollatorClient {
    public static void main(String[] args) {
        demonstrateBasicComparison(new String[]{"-l", "en_US", "apple", "banana"});

        demonstrateStringTransformation(new String[]{"-t", "en_US", "apple"});

        demonstrateInvalidLocale(new String[]{"-l", "invalid_locale", "apple", "banana"});
    }

    private static void demonstrateBasicComparison(String[] args) {
        System.out.println("\nDemo 1: Basic String Comparison");
        System.out.println("Command line: " + String.join(" ", args));

        if (args.length != 4) {
            System.out.println("Usage: -l <locale> <string1> <string2>");
            return;
        }

        Locale locale = Locale.forLanguageTag(args[1]);
        String s1 = args[2];
        String s2 = args[3];

        int comparisonResult = LocaleCollator.compareStrings(locale, s1, s2);

        if (comparisonResult < 0) {
            System.out.println(s1 + " comes before " + s2);
        } else if (comparisonResult > 0) {
            System.out.println(s1 + " comes after " + s2);
        } else {
            System.out.println(s1 + " is equal to " + s2);
        }
    }

    private static void demonstrateStringTransformation(String[] args) {
        System.out.println("\nDemo 2: String Transformation into Collation Key");
        System.out.println("Command line: " + String.join(" ", args));

        if (args.length != 3) {
            System.out.println("Usage: -t <locale> <string>");
            return;
        }

        Locale locale = Locale.forLanguageTag(args[1]);
        String input = args[2];

        String collationKey = LocaleCollator.transformString(locale, input);
        System.out.println("Collation key for \"" + input + "\": " + collationKey);
    }

    private static void demonstrateInvalidLocale(String[] args) {
        System.out.println("\nDemo 3: Invalid Locale Handling");
        System.out.println("Command line: " + String.join(" ", args));

        if (args.length != 4) {
            System.out.println("Usage: -l <locale> <string1> <string2>");
            return;
        }

        Locale locale = Locale.forLanguageTag(args[1]);
        String s1 = args[2];
        String s2 = args[3];

        try {
            int comparisonResult = LocaleCollator.compareStrings(locale, s1, s2);
            System.out.println("Comparison result: " + comparisonResult);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
