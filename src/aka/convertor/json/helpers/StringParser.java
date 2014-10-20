package aka.convertor.json.helpers;

import java.io.File;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.regexp.RE;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import aka.convertor.json.ReservedWord;

/**
 * @author daniel
 *
 */
public final class StringParser {

	private static Map<String, Element> mappingType = new HashMap<>();

	/**
	 * @param databaseName
	 * @return String
	 */
	public static String getJetName(final String databaseName) {
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
	public static String getJetVariableName(final String dbName, final String dbFormat) {
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

	/**
	 * @param dbName
	 * @return String
	 * @throws JETException
	 */
	public static String getJetDbType(final String dbName) throws Exception {
		if (mappingType == null) {
			initMappingType();
		}
		final Element model = mappingType.get(dbName);
		if (model == null) {
			throw new Exception("[StringParser] getJetDbType - " + dbName);
		}
		return model.getAttribute("jettype");
	}

	private static void initMappingType() {
		try {
			final File file = new File("./xml/json.xml");
			final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			final DocumentBuilder db = dbf.newDocumentBuilder();
			final Document doc = db.parse(file);
			doc.getDocumentElement().normalize();
			System.out.println("Root element " + doc.getDocumentElement().getNodeName());
			final NodeList nodeLst = doc.getElementsByTagName("TYPE");
			for (int s = 0; s < nodeLst.getLength(); s++) {
				final Element fstNode = (Element) nodeLst.item(s);
				final String key = fstNode.getAttribute("localtype");
				mappingType.put(key, fstNode);
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @param dbName
	 * @return String
	 * @throws JETException
	 */
	public static String getJetJavaType(final String dbName) throws Exception {
		if (mappingType == null) {
			initMappingType();
		}
		final Element model = mappingType.get(dbName);
		if (model == null) {
			throw new Exception("[StringParser] getJetJavaType - " + dbName);
		}
		return model.getAttribute("javatype");
	}

	public static String getLocalName(final String fieldName) {
		String result = fieldName;

		result = result.replace("@", "");
		result = result.replace(" ", "");
		result = result.replace(".", "_");

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
