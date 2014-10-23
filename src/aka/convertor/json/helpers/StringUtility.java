package aka.convertor.json.helpers;

/**
 * @author daniel
 *
 */
public class StringUtility {

	/**
	 * @param type
	 *            java type
	 * @return String annotated type
	 */
	static public String getNullableJavaType(final String type) {
		String result = null;
		if ("java.lang.Object".equals(type)) {
			result = "java.lang.@Nullable Object";
		} else if ("java.math.BigDecimal".equals(type)) {
			result = "java.math.@Nullable BigDecimal";
		} else if ("java.util.Date".equals(type)) {
			result = "java.util.@Nullable Date";
		} else if ("Object[]".equals(type)) {
			result = "Object @Nullable []";
		} else if ("byte[]".equals(type)) {
			result = "byte[]";
		} else {
			result = "@Nullable " + type;
		}
		return result;
	}

	/**
	 * @param type
	 *            java type
	 * @return String annotated type
	 */
	static public String getNonNullJavaType(final String type) {
		String result = null;
		if ("java.lang.Object".equals(type)) {
			result = "java.lang.@NonNull Object";
		} else if ("java.math.BigDecimal".equals(type)) {
			result = "java.math.@NonNull BigDecimal";
		} else if ("java.util.Date".equals(type)) {
			result = "java.util.@NonNull Date";
		} else if ("Object[]".equals(type)) {
			result = "Object @NonNull []";
		} else if ("byte[]".equals(type)) {
			result = "byte[]";
		} else {
			result = "@NonNull " + type;
		}
		return result;
	}

	/**
	 * @param intValue
	 * @return Integer
	 */
	static public Integer toInt(final String intValue) {
		if (intValue == null || intValue.length() == 0) {
			return Integer.valueOf(0);
		} else {
			return Integer.valueOf(intValue);
		}
	}

	/**
	 * @param javaType
	 * @return boolean
	 */
	static public boolean isBlob(final String javaType) {
		return "byte[]".equals(javaType);
	}

	/**
	 * @param javaType
	 * @return boolean
	 */
	static public boolean isInteger(final String javaType) {
		return "Long".equals(javaType) || "Integer".equals(javaType);
	}

	/**
	 * @param javaType
	 * @return boolean
	 */
	static public boolean isDouble(final String javaType) {
		return "Double".equals(javaType) || "java.math.BigDecimal".equals(javaType);
	}

	/**
	 * @param javaType
	 * @return boolean
	 */
	static public boolean isDate(final String javaType) {
		return "Date".equals(javaType) || "java.util.Date".equals(javaType);
	}

	/**
	 * @param javaType
	 * @return boolean
	 */
	static public boolean isText(final String javaType) {
		return "String".equals(javaType);
	}

	/**
	 * @param arg
	 * @return String
	 */
	static public String firstLetterUpperCase(final String arg) {
		String pre = arg.substring(0, 1);
		pre = pre.toUpperCase();

		return pre + arg.substring(1);
	}

	static public String derbyName(final String arg) {

		final String upperCase = arg.toUpperCase();
		if (upperCase.equals(arg)) {
			return arg;
		}

		final StringBuffer sb = new StringBuffer();

		final int size = arg.length();
		for (int i = 0; i < size; i++) {
			final char c = arg.charAt(i);

			if (Character.isUpperCase(c)) {
				if (i > 0) {
					sb.append("_");
				}
				sb.append(c);
			} else {
				final char[] cList = { c };
				final String s = new String(cList);
				sb.append(s.toUpperCase());
			}
		}

		return sb.toString();
	}

	/**
	 * @param arg
	 * @return String
	 */
	static public String toUpperCase(final String arg) {
		return arg.toUpperCase();
	}

	/**
	 * @param arg
	 * @return String
	 */
	static public String firstLetterLowerCase(final String arg) {
		String pre = arg.substring(0, 1);
		pre = pre.toLowerCase();

		return pre + arg.substring(1);
	}

	/**
	 * @param arg
	 * @return String
	 */
	static public String toLowerCase(final String arg) {
		return arg.toLowerCase();
	}

	/**
	 * Replace all instances of given substring with given string
	 *
	 * @param src
	 *            the original string
	 * @param substring
	 *            the substring to be replaced
	 * @param newSubstring
	 *            the new string to replace substrings by
	 * @return String with all substring instances replaced
	 */
	public static String replace(String src, String substring, String newSubstring) {

		final StringBuffer sb = new StringBuffer();

		if (substring == null) {
			substring = "";
		}
		if (newSubstring == null) {
			newSubstring = "";
		}

		if (src == null) {
			return null;
		} else {

			int index = src.indexOf(substring);

			// System.out.println("[StringHelper.java] - Testing... hmmm no test yet");

			while (index != -1) {

				sb.append(src.substring(0, index));
				sb.append(newSubstring);
				if ((index + 1) < src.length()) {
					src = src.substring(index + substring.length());
					index = src.indexOf(substring);
				} else {
					index = -1;
				}

			}
			sb.append(src);
			return sb.toString();
		}
	}

	/**
	 * @param type
	 * @return String
	 */
	public String getJavaTypeForXML(final String type) {
		if ("byte[]".equals(type)) {
			return "byteArray";
		}
		return type;
	}

}
