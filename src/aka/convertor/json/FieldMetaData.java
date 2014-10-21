package aka.convertor.json;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import aka.convertor.json.helpers.DateHelper;
import aka.convertor.json.helpers.StringParser;
import aka.convertor.json.helpers.StringUtility;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * see DatabaseMetaData.html#getColumns(java.lang.String,%20java.lang.String,%20
 * java.lang.String,%20java.lang.String) for details on the available metadata
 *
 * @author daniel
 *
 */
public class FieldMetaData {

	private final int size;
	private final int decimals;

	private String localName;
	private String paramName;
	private final String serialisableName;
	private String javaType;
	private String JavaSubType;
	private final JsonMetaData jsonMetaData;
	private String deserName;
	private ObjectMetaData parentObject = null;
	private ObjectMetaData object = null;
	private boolean isObject = false;

	/**
	 * @param fieldName
	 * @param serName
	 * @param dbFormat
	 * @param jsonNode
	 * @param jsonMetaData
	 * @param parentObject
	 */
	public FieldMetaData(final String fieldName, final String serName, final String dbFormat, final JsonNode jsonNode, final JsonMetaData jsonMetaData, final ObjectMetaData parentObject) {
		this.jsonMetaData = jsonMetaData;
		this.localName = StringParser.getLocalName(fieldName);
		this.paramName = StringParser.getLocalName(fieldName);
		this.serialisableName = serName;
		this.deserName = "";
		this.parentObject = parentObject;

		final boolean isArray = jsonNode.isArray();

		if (isArray) {
			// this is a list
			this.javaType = "List";
			this.JavaSubType = StringUtility.firstLetterUpperCase(this.localName);
		} else {
			this.javaType = StringUtility.firstLetterUpperCase(this.localName);
			this.JavaSubType = "";
		}

		// simple field or object ?

		if (((isArray && jsonNode.elements().next().fields().hasNext())) || jsonNode.isObject()) {
			// field is an object or an array of object/field
			this.size = 0;
			this.decimals = 0;
			this.isObject = true;
			this.object = new ObjectMetaData(this.localName, dbFormat, jsonNode, jsonMetaData);
		} else {
			// field is a simple object (date, string, etc)
			this.size = 0;
			this.decimals = 0;

			setJavaType(jsonNode, isArray);
		}
	}

	private <T> void setJavaType(final JsonNode jsonNode, final boolean isArray) {
		if (jsonNode.isBigDecimal()) {
			setType(BigDecimal.class, isArray);
		} else if (jsonNode.isBigInteger()) {
			setType(BigInteger.class, isArray);
		} else if (jsonNode.isBinary()) {
			setType(byte[].class, isArray);
		} else if (jsonNode.isBoolean()) {
			setType(Boolean.class, isArray);
		} else if (jsonNode.isDouble()) {
			setType(Double.class, isArray);
		} else if (jsonNode.isFloatingPointNumber()) {
			setType(Float.class, isArray);
		} else if (jsonNode.isInt()) {
			setType(Integer.class, isArray);
		} else if (jsonNode.isIntegralNumber()) {
			setType(Integer.class, isArray);
		} else if (jsonNode.isLong()) {
			setType(Long.class, isArray);
		} else if (jsonNode.isNumber()) {
			setType(Number.class, isArray);
		} else {
			// check if this is a date
			final String value = jsonNode.textValue();
			if (value != null) {
				final String dateName = DateHelper.parseDate(value);
				if (dateName != null) {
					setType(Date.class, isArray);
					// add deserialiser
					final String pattern = DateHelper.dateNameFormatMap.get(dateName);
					this.jsonMetaData.addDeserialiser("Date", dateName, pattern);
					this.deserName = dateName;
				} else {
					setType(String.class, isArray);
				}
			} else {
				setType(String.class, isArray);
			}

			// check if this is an url string
		}

	}

	private <T> void setType(final Class<T> className, final boolean isArray) {
		try {
			final String type = className.getSimpleName();

			if (isArray) {
				// this is a list
				this.javaType = "List";
				this.JavaSubType = type;
			} else {
				this.javaType = type;
			}
		} catch (final Exception e) {
			System.err.println("[FieldMetaData] FieldMetaData - Error processing column [" + this.localName + "] in field [" + this.localName + "] :: " + e.getMessage());
		}
	}

	/**
	 * @return int
	 */
	public int getDecimals() {
		return this.decimals;
	}

	/**
	 * @return String
	 */
	public String getJavaType() {
		return this.javaType;
	}

	/**
	 * @return String
	 */
	public String getJavaSubType() {
		return this.JavaSubType;
	}

	/**
	 * @return String
	 */
	public String getLocalName() {
		return this.localName;
	}

	/**
	 * @return String
	 */
	public String getParamName() {
		return this.paramName;
	}

	/**
	 * @return String
	 */
	public String getSerName() {
		return this.serialisableName;
	}

	/**
	 * @return String
	 */
	public String getDeserName() {
		return this.deserName;
	}

	/**
	 * @return int
	 */
	public int getSize() {
		return this.size;
	}

	public void setFieldName(final String newName) {
		this.localName = StringParser.getLocalName(newName);
		this.paramName = StringParser.getLocalName(newName);

		if ("List".equals(this.javaType)) {
			// this is a list
			this.JavaSubType = StringUtility.firstLetterUpperCase(this.localName);
		} else {
			this.javaType = StringUtility.firstLetterUpperCase(this.localName);
		}
	}

	boolean isObject() {
		return this.isObject;
	}

	public ObjectMetaData getParentObject() {
		return this.parentObject;
	}

	public ObjectMetaData getObject() {
		return this.object;
	}

	public boolean containList() {
		return "List".equals(this.javaType);
	}
}
