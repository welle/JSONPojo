package aka.convertor.json.helpers;

import org.eclipse.jdt.annotation.NonNull;

import aka.convertor.json.constants.ReservedWord;

/**
 * @author daniel
 *
 */
public class StringUtility {

	/**
	 * @param fieldName
	 * @return String
	 */
	@NonNull
	public static String getVariableName(@NonNull final String fieldName) {
		final String dbName2 = fieldName.substring(0, 1).toLowerCase() + fieldName.substring(1);
		return dbName2;
	}

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

			final StringBuilder sb = new StringBuilder();
			sb.append(StringUtility.firstLetterLowerCase(array[0]));

			for (int i = 1; i < array.length; i++) {
				final String current = array[i];
				assert current != null;
				sb.append(StringUtility.firstLetterUpperCase(current));
			}

			result = sb.toString();
			assert result != null;
		}

		if (isNumber(result)) {
			result = "value" + result;
		}

		if (ReservedWord.RESERVED_WORDS.contains(result.toLowerCase())) {
			result = result.concat("Res");
			assert result != null;
		}

		return result;
	}

	private static boolean isNumber(@NonNull String string) {
		try {
			Long.parseLong(string);
		} catch (final Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * @param arg
	 * @return String
	 */
	@NonNull
	static public String firstLetterUpperCase(@NonNull final String arg) {
		String pre = arg.substring(0, 1);
		pre = pre.toUpperCase();

		return pre + arg.substring(1);
	}

	/**
	 * @param arg
	 * @return String
	 */
	@NonNull
	static public String firstLetterLowerCase(final String arg) {
		String pre = arg.substring(0, 1);
		pre = pre.toLowerCase();

		return pre + arg.substring(1);
	}
}
