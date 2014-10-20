package aka.convertor.json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Charlotte
 * 
 */
public class JsonMetaData {

	private String packageName = null;
	private String projectName = null;
	private final Map<String, ObjectMetaData> objects = new HashMap<String, ObjectMetaData>();
	private String root;
	private final Map<String, Deserialiser> deserialiseList = new HashMap<String, Deserialiser>();

	/**
	 * @param name
	 * @param projectName
	 * @param dbFormat
	 * @param jsonToParse
	 */
	public JsonMetaData(final String name, final String projectName, final String dbFormat, final String jsonToParse) {
		this.packageName = name;
		this.projectName = projectName;

		try {
			final ObjectMapper mapper = new ObjectMapper();
			final JsonNode rootNode = mapper.readTree(jsonToParse);
			// multiple children
			final ObjectMetaData tmd = new ObjectMetaData(name, dbFormat, rootNode, this);
			this.objects.put(name, tmd);
			this.root = name;

			final Map<String, FieldMetaData> objectsList = tmd.getObjects();
			getAllObject(objectsList, dbFormat);
		} catch (final JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void getAllObject(final Map<String, FieldMetaData> objectsList, final String dbFormat) {
		for (final Entry<String, FieldMetaData> entry : objectsList.entrySet()) {
			String name = entry.getKey();
			final FieldMetaData fieldMetaData = entry.getValue();
			final ObjectMetaData object = fieldMetaData.getObject();
			final ObjectMetaData parentObject = fieldMetaData.getParentObject();
			if (this.objects.containsKey(name)) {
				// change name
				int i = 1;
				while (this.objects.containsKey(name + i)) {
					i++;
				}
				final String newName = name + i;

				fieldMetaData.setFieldName(newName);
				object.changeName(newName, dbFormat);
				this.objects.put(newName, object);
				parentObject.changeField(name, newName, fieldMetaData);

				name = newName;
			}
			this.objects.put(name, object);
			getAllObject(object.objects, dbFormat);
		}

	}

	/**
	 * @return ArrayList<TableMetaData>
	 */
	public ArrayList<ObjectMetaData> getObjects() {
		return new ArrayList(Arrays.asList(this.objects.values().toArray()));
	}

	/**
	 * @return String
	 */
	public String getPackageName() {
		return this.packageName;
	}

	/**
	 * @return String
	 */
	public String getProjectName() {
		return this.projectName;
	}

	public String getRoot() {
		return this.root;
	}

	public List<Deserialiser> getDeserialises() {
		return new ArrayList(Arrays.asList(this.deserialiseList.values().toArray()));
	}

	public void addDeserialiser(final String type, final String dateName, final String pattern) {
		Deserialiser deserialiser = this.deserialiseList.get(type);
		if (deserialiser == null) {
			deserialiser = new Deserialiser(type);
			this.deserialiseList.put(type, deserialiser);
		}

		final DeserialiseItem item = new DeserialiseItem(dateName, pattern);
		deserialiser.addDeserialiseItem(dateName, item);
	}

}