package aka.convertor.json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Charlotte
 *
 */
public class JsonMetaData {

	private final Map<@NonNull String, @Nullable ObjectMetaData> objects = new HashMap<>();
	private final Map<@NonNull String, @Nullable Deserialiser> deserialiseList = new HashMap<>();
	private boolean isRootAnArray;

	/**
	 * @param name
	 * @param jsonToParse
	 */
	public JsonMetaData(@NonNull final String name, @NonNull final String jsonToParse) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final JsonNode rootNode = mapper.readTree(jsonToParse);

			this.isRootAnArray = rootNode.isArray();
			assert rootNode != null;
			// multiple children
			final ObjectMetaData tmd = new ObjectMetaData(name, rootNode, this);
			this.objects.put(name, tmd);

			final Map<@NonNull String, @NonNull FieldMetaData> objectsList = tmd.getObjects();
			getAllObject(objectsList);
		} catch (final JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void getAllObject(@NonNull final Map<@NonNull String, @NonNull FieldMetaData> objectsList) {
		for (final Entry<@NonNull String, @NonNull FieldMetaData> entry : objectsList.entrySet()) {
			String name = entry.getKey();
			final FieldMetaData fieldMetaData = entry.getValue();
			final ObjectMetaData object = fieldMetaData.getObject();
			if (object != null) {
				final ObjectMetaData parentObject = fieldMetaData.getParentObject();
				if (this.objects.containsKey(name)) {
					// change name
					int i = 1;
					while (this.objects.containsKey(name + i)) {
						i++;
					}
					final String newName = name + i;

					fieldMetaData.setFieldName(newName);
					object.changeName(newName);
					this.objects.put(newName, object);
					parentObject.changeField(name, newName, fieldMetaData);

					name = newName;
				}
				this.objects.put(name, object);
				getAllObject(object.objects);
			}
		}
	}

	/**
	 * @return ArrayList<TableMetaData>
	 */
	@NonNull
	public ArrayList<@NonNull ObjectMetaData> getObjects() {
		return new ArrayList(Arrays.asList(this.objects.values().toArray()));
	}

	@NonNull
	public List<@NonNull Deserialiser> getDeserialisers() {
		return new ArrayList(Arrays.asList(this.deserialiseList.values().toArray()));
	}

	public void addDeserialiser(@NonNull final String type, @NonNull final String name, @Nullable final String pattern) {
		Deserialiser deserialiser = this.deserialiseList.get(type);
		if (deserialiser == null) {
			deserialiser = new Deserialiser(type);
			this.deserialiseList.put(type, deserialiser);
		}

		final DeserialiseItem item = new DeserialiseItem(name, pattern);
		deserialiser.addDeserialiseItem(name, item);
	}

	public boolean isRootAnArray() {
		return this.isRootAnArray;
	}
}
