package aka.convertor.json.helpers;

import org.eclipse.jdt.annotation.NonNull;

import aka.convertor.json.constants.ReservedWord;

/**
 * Class to manage and test strings.
 *
 * @author Welle Charlotte
 */
public final class StringUtilities {

    /**
     * Get variable name based on given field name.
     *
     * @param fieldName
     * @return variable name
     */
    @NonNull
    public static String getVariableName(@NonNull final String fieldName) {
        return firstLetterLowerCase(fieldName);
    }

    /**
     * Get local name of the given field name.
     *
     * @param fieldName
     * @return local name
     */
    @NonNull
    public static String getLocalName(@NonNull final String fieldName) {
        String result = fieldName;

        result = result.replace("@", "");
        result = result.replace(" ", "");
        result = result.replace(".", "_");
        result = result.replace("-", "_");
        result = result.replace(":", "_");

        if ("$".equals(fieldName)) {
            result = "value";
        } else if ("class".equals(fieldName)) {
            result = "clazz";
        } else {
            final String[] array = result.split("_");

            if (array != null && array.length > 0) {
                final StringBuilder sb = new StringBuilder();
                final String first = array[0];
                assert first != null : "It should not be possible.";
                sb.append(StringUtilities.firstLetterLowerCase(first));

                for (int i = 1; i < array.length; i++) {
                    final String current = array[i];
                    assert current != null;
                    sb.append(StringUtilities.firstLetterUpperCase(current));
                }
                result = sb.toString();
            }
        }

        if (StringUtilities.isNumber(result)) {
            result = "value" + result;
        }

        if (ReservedWord.RESERVED_WORDS.contains(result.toLowerCase())) {
            result = result.concat("Res");
        }

        return result;
    }

    private static boolean isNumber(@NonNull final String string) {
        try {
            Long.parseLong(string);
        } catch (@SuppressWarnings("unused") final Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Convert first letter of the given string to upper case.
     *
     * @param string
     * @return String with first letter to upper case
     */
    @NonNull
    static public String firstLetterUpperCase(@NonNull final String string) {
        String result = string;
        if (string.length() > 0) {
            String pre = string.substring(0, 1);
            pre = pre.toUpperCase();

            result = pre + string.substring(1);
        }

        return result;
    }

    /**
     * Convert first letter of the given string to lower case.
     *
     * @param string
     * @return String with first letter to lower case
     */
    @NonNull
    static public String firstLetterLowerCase(@NonNull final String string) {
        String result = string;
        if (string.length() > 0) {
            String pre = string.substring(0, 1);
            pre = pre.toLowerCase();
            result = pre + string.substring(1);
        }

        return result;
    }
}
