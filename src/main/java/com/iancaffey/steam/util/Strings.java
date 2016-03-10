package com.iancaffey.steam.util;

/**
 * Strings
 * <p>
 * A utility class containing methods that deal with strings.
 *
 * @author Ian Caffey
 * @since 1.0
 */
public class Strings {
    private Strings() {

    }

    /**
     * Retrieves the nth index of the specified string.
     *
     * @param string the string to look for the substring
     * @param check  the substring to look for
     * @param n      the index of the occurrence of the substring
     * @return <code>-1</code> if the substring wasn't found, the index of the nth occurrence otherwise.
     */
    public static int nthIndexOf(String string, String check, int n) {
        if (string == null || check == null || n < 0)
            return -1;
        int pos = string.indexOf(check, 0);
        while (n-- > 0 && pos != -1)
            pos = string.indexOf(check, pos + 1);
        return pos;
    }
}
