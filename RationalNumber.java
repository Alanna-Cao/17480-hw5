import java.math.BigInteger;

/**
 * A class that represents rational numbers (fractions) as exact values in Java.
 * Numbers are stored in reduced form and all arithmetic operations maintain exact precision.
 * This class is immutable - all operations return new RationalNumber instances.
 *
 * Example uses:
 * <pre>
 * // Basic arithmetic
 * RationalNumber a = new RationalNumber(1, 2);    // 1/2
 * RationalNumber b = new RationalNumber(1, 3);    // 1/3
 * RationalNumber c = a.plus(b);                   // 5/6
 * RationalNumber d = a.times(b);                  // 1/6
 *
 * // Chain operations
 * RationalNumber result = a.plus(b).minus(c).times(d);
 *
 * // Working with integers
 * RationalNumber e = new RationalNumber(5);       // 5/1
 * RationalNumber f = e.dividedBy(new RationalNumber(2)); // 5/2
 * </pre>
 *
 * Numbers are always stored in reduced form with a positive denominator.
 * For example, -1/2 is stored as (-1,2) not as (1,-2) or (-2,4).
 */
public final class RationalNumber {
    private final BigInteger numerator;
    private final BigInteger denominator;

    /**
     * Creates a rational number from an integer numerator and denominator.
     * The fraction will be reduced to lowest terms.
     * @param numerator the numerator
     * @param denominator the denominator
     * @throws ArithmeticException if denominator is zero
     */
    public RationalNumber(long numerator, long denominator);

    /**
     * Returns a new RationalNumber that is the sum of this and n.
     * @param n the number to add
     * @return a new RationalNumber representing this + n
     * @throws NullPointerException if n is null
     */
    public RationalNumber plus(RationalNumber n);

    /**
     * Returns a new RationalNumber that is this minus n.
     * @param n the number to subtract
     * @return a new RationalNumber representing this - n
     * @throws NullPointerException if n is null
     */
    public RationalNumber minus(RationalNumber n);

    /**
     * Returns a new RationalNumber that is the product of this and n.
     * @param n the number to multiply by
     * @return a new RationalNumber representing this × n
     * @throws NullPointerException if n is null
     */
    public RationalNumber multiply(RationalNumber n);

    /**
     * Returns a new RationalNumber that is this divided by n.
     * @param n the number to divide by
     * @return a new RationalNumber representing this ÷ n
     * @throws NullPointerException if n is null
     * @throws ArithmeticException if n is zero
     */
    public RationalNumber divide(RationalNumber n);

    /**
     * Returns a new RationalNumber that is this modulo n.
     * The result r satisfies: this = n.times(q).plus(r) for some q,
     * where r has the same sign as n and abs(r) &lt; abs(n).
     * @param n the modulus to use
     * @return a new RationalNumber representing this mod n
     * @throws NullPointerException if n is null
     * @throws ArithmeticException if n is zero
     */
    public RationalNumber mod(RationalNumber n);

    /**
     * Checks if this RationalNumber is equal to another RationalNumber.
     * @param obj the object to compare to
     * @return true if obj is a RationalNumber and equal to this
     */
    public boolean equals(Object obj);

    /**
     * Checks if this RationalNumber is greater than another RationalNumber.
     * @param n the number to compare to
     * @return true if this is greater than n
     */
    public boolean isGreaterThan(RationalNumber n);

    /**
     * Checks if this RationalNumber is less than another RationalNumber.
     * @param n the number to compare to
     * @return true if this is less than n
     */
    public boolean isLessThan(RationalNumber n);

    /**
     * Checks if this RationalNumber is greater than or equal to another RationalNumber.
     * @param n the number to compare to
     * @return true if this is greater than or equal to n
     */
    public boolean isGreaterThanOrEqualTo(RationalNumber n);

    /**
     * Checks if this RationalNumber is less than or equal to another RationalNumber.
     * @param n the number to compare to
     * @return true if this is less than or equal to n
     */
    public boolean isLessThanOrEqualTo(RationalNumber n);
}
    