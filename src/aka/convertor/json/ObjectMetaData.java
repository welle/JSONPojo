package aka.convertor.json;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import aka.convertor.json.helpers.StringParser;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * @author daniel
 *
 */
public class ObjectMetaData {

	private String objectName;
	private String javaObjectName;
	public final Map<String, FieldMetaData> objects = new HashMap<String, FieldMetaData>();
	private final Map<String, FieldMetaData> fields = new HashMap<String, FieldMetaData>();
	private boolean containList = false;

	/**
	 * @param objectName
	 * @param dbFormat
	 * @param rootObject
	 * @param jsonMetaData
	 */
	public ObjectMetaData(final String objectName, final String dbFormat, final JsonNode rootObject, final JsonMetaData jsonMetaData) {
		this.objectName = objectName;
		this.javaObjectName = StringParser.getVariableName(objectName, dbFormat);

		if (rootObject.isArray()) {
			// array node = must parse all entries
			final Iterator<JsonNode> elements = rootObject.elements();

			while (elements.hasNext()) {
				final JsonNode currentNode = elements.next();
				parseElements(currentNode, jsonMetaData, dbFormat, this);
			}
		} else {
			// not an object or array node
			parseElements(rootObject, jsonMetaData, dbFormat, this);
		}
	}

	private void parseElements(final JsonNode currentNode, final JsonMetaData jsonMetaData, final String dbFormat, final ObjectMetaData objectMetaData) {
		final Iterator<Entry<String, JsonNode>> elements = currentNode.fields();
		while (elements.hasNext()) {
			final Entry<String, JsonNode> jsonNode = elements.next();
			final String fieldName = jsonNode.getKey();
			final JsonNode currentJsonNode = jsonNode.getValue();
			if (!this.fields.containsKey(fieldName)) {
				final String serName = fieldName;
				final FieldMetaData field = new FieldMetaData(fieldName, serName, dbFormat, currentJsonNode, jsonMetaData, objectMetaData);

				if (field.isObject()) {
					objectMetaData.objects.put(fieldName, field);
				}
				objectMetaData.fields.put(fieldName, field);
				if (field.containList()) {
					this.containList = field.containList();
				}
			} else if (this.objects.containsKey(fieldName)) {
				// update object
				final FieldMetaData fieldMetaData = this.objects.get(fieldName);
				final ObjectMetaData existingObjectMetaData = fieldMetaData.getObject();
				parseElements(currentJsonNode, jsonMetaData, dbFormat, existingObjectMetaData);
			}
		}
	}

	/**
	 * @return ArrayList<FieldMetaData>
	 */
	public ArrayList<FieldMetaData> getFields() {
		return new ArrayList(Arrays.asList(this.fields.values().toArray()));
	}

	/**
	 * @return ArrayList<FieldMetaData>
	 */
	public Map<String, FieldMetaData> getObjects() {
		return this.objects;
	}

	/**
	 * @return String
	 */
	public String getJavaObjectName() {
		return this.javaObjectName;
	}

	/**
	 * @return String
	 */
	public String getObjectName() {
		return this.objectName;
	}

	public void changeField(final String name, final String newName, final FieldMetaData fieldMetaData) {
		this.fields.remove(name);
		this.fields.put(newName, fieldMetaData);
	}

	public void changeName(final String newName, final String dbFormat) {
		this.objectName = newName;
		this.javaObjectName = StringParser.getVariableName(newName, dbFormat);
	}

	public boolean containList() {
		return this.containList;
	}
}
