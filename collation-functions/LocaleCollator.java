import java.util.Locale;

/**
 * A Java utility class for locale-sensitive string collation.
 * This class provides methods for comparing and transforming strings 
 * according to the collation rules of a specified locale, similar to 
 * the C functions strcoll(), strxfrm(), wcscoll(), and wcsxfrm().
 * 
 * <Example Code>
 * <pre>
 * int result = LocaleCollator.compareStrings(Locale.US, "apple", "banana");
 * String key = LocaleCollator.transformString(Locale.US, "apple");
 * </pre>
 */
public final class LocaleCollator {

    private LocaleCollator() {}

    /**
     * Compares two strings based on the collation order of the specified locale.
     * This method corresponds to the C function `strcoll()`.
     *
     * @param locale The locale to use for collation.
     * @param s1 The first string to compare.
     * @param s2 The second string to compare.
     * @return An integer: negative if s1 < s2, zero if s1 == s2, 
     *         positive if s1 > s2 in collation order.
     */
    public static int compareStrings(Locale locale, String s1, String s2);

    /**
     * Transforms a string into a collation key based on the specified locale.
     * This method corresponds to the C function `strxfrm()`.
     * The returned collation key is used for comparing strings according to the
     * collation rules of the specified locale.
     *
     * @param locale The locale to use for collation.
     * @param input The string to transform.
     * @return A transformed string representing the collation key, to be
     *          used for ordering and comparing strings in collation order.
     */
    public static String transformString(Locale locale, String input);
}
