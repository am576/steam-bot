package com.iancaffey.steam.trade;

import com.iancaffey.steam.QueryToken;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Currency
 * <p>
 * An object representing a type of currency to return during item lookup calls.
 *
 * @author Ian Caffey
 * @since 1.0
 */
//TODO:Load exchange rates dynamically through fixer.io
public enum Currency implements QueryToken {
    /**
     * The United States Dollar currency
     */
    USD(1, "([0-9|\\.]+)", 1.0D),
    /**
     * The British Pound currency
     */
    GBP(2, "([0-9|\\.]+)", 0.64718),
    /**
     * The European Euro currency
     */
    EUR(3, "([0-9|\\,]+)", 0.89063),
    /**
     * The Russian Ruble currency
     */
    RUB(5, "([0-9|\\,]+)", 65.749);
    private final String token;
    private final Pattern pattern;
    private final double rate;

    /**
     * Creates a new currency with a specific Steam token and currency formatting string.
     *
     * @param token  the Steam query string token
     * @param format the pattern for parsing a currency string
     * @throws IllegalArgumentException if <code>token == -1</code> or <code>format == null</code>
     */
    Currency(int token, String format, double rate) {
        if (token == -1 || format == null)
            throw new IllegalArgumentException();
        this.token = String.valueOf(token);
        this.pattern = Pattern.compile(format);
        this.rate = rate;
    }

    /**
     * Retrieves the currency exchange rate compared to another currency in order to convert a value represented in <code>this</code> currency in the other currency.
     *
     * @param amount   the original amount in <code>this</code> currency
     * @param currency the currency to compare against
     * @return the currency exchange rate between the two currencies
     */
    public float to(float amount, Currency currency) {
        if (currency == null)
            return 0;
        if (this == currency)
            return 1.0f;
        return (float) (amount * getRate() / currency.getRate());
    }

    /**
     * Retrieves the currency exchange rate compared to another currency in order to convert a value represented in <code>this</code> currency from the other currency.
     *
     * @param amount   the original amount in <code>this</code> currency
     * @param currency the currency to compare against
     * @return the currency exchange rate between the two currencies
     */
    public float from(float amount, Currency currency) {
        if (currency == null)
            return 0;
        if (this == currency)
            return 1.0f;
        return (float) (amount * currency.getRate() / getRate());
    }

    public double getRate() {
        return rate;
    }

    /**
     * Parses a floating point value from a currency string.
     *
     * @param value the currency string
     * @return the floating point representation of the currency string, -1 if invalid format
     */
    public float parse(String value) {
        if (value == null)
            return -1;
        Matcher matcher = pattern.matcher(value);
        if (!matcher.find())
            return -1;
        String string = matcher.group(1).replace(',', '.');
        if (string.endsWith("."))
            string += "0";
        try {
            return Float.parseFloat(string);
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * The token to be used in a query string.
     *
     * @return the query string token
     */
    @Override
    public String getToken() {
        return token;
    }
}
