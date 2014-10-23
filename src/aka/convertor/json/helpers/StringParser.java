package aka.convertor.json.helpers;

import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;

import org.apache.regexp.RE;

import aka.convertor.json.constants.ReservedWord;

/**
 * @author daniel
 *
 */
public final class StringParser {

	/**
	 * @param databaseName
	 * @return String
	 */
	public static String getName(final String databaseName) {
		final String dbName = databaseName.toLowerCase();
		String ret = "";// new String();

		RE re;
		try {
			try {
				re = AccessController.doPrivileged((PrivilegedExceptionAction<RE>) () -> new RE("_"));
			} catch (final PrivilegedActionException pae) {
				throw new Exception(pae.getCause().getMessage(), pae.getCause());
			}
			// re = new RE("_");
			final String[] name = re.split(dbName);
			for (int i = 0; i < name.length; i++) {
				ret += name[i].substring(0, 1).toUpperCase() + name[i].substring(1, name[i].length());
			}
			return ret;
		} catch (final Exception e) {
			System.out.println("[StringParser] getJetName - error processing :: " + dbName);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @param dbName
	 * @param dbFormat
	 * @return String
	 */
	public static String getVariableName(final String dbName, final String dbFormat) {
		assert dbFormat != null;

		if ("OLD".equals(dbFormat)) {
			final String dbName2 = dbName.toLowerCase();
			String ret = "";// new String();

			RE re;
			try {
				re = new RE("_");
				final String[] name = re.split(dbName2);
				if (name.length > 0) {
					ret += name[0];
				}
				for (int i = 1; i < name.length; i++) {
					ret += name[i].substring(0, 1).toUpperCase() + name[i].substring(1, name[i].length());
				}
				return ret;
			} catch (final Exception e) {
				System.out.println("[StringParser] getJetVariableName - error processing :: " + dbName2);
				e.printStackTrace();
			}
		} else if ("NEW".equals(dbFormat)) {
			final String dbName2 = dbName.substring(0, 1).toLowerCase() + dbName.substring(1);
			return dbName2;
		} else {
			System.err.println("Unknown DB format type :: " + dbFormat);
		}
		return null;
	}

	public static String getLocalName(final String fieldName) {
		String result = fieldName;

		result = result.replace("@", "");
		result = result.replace(" ", "");
		result = result.replace(".", "_");
		result = result.replace("-", "_");
		result = result.replace(":", "_");

		if ("$".equals(fieldName)) {
			result = "value";
		} else if ("class".equals(fieldName)) {
			result = "classe";
		} else {
			final String[] array = result.split("_");

			final StringBuilder sb = new StringBuilder();
			sb.append(StringUtility.firstLetterLowerCase(array[0]));

			for (int i = 1; i < array.length; i++) {
				sb.append(StringUtility.firstLetterUpperCase(array[i]));
			}

			result = sb.toString();
		}

		if (ReservedWord.RESERVED_WORDS.contains(result.toLowerCase())) {
			result = result.concat("Res");
		}

		return result;
	}

}
