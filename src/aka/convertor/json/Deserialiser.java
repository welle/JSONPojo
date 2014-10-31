package aka.convertor.json;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Deserialiser {

	private final String type;
	private final Map<String, DeserialiseItem> deserialiseItemMap = new HashMap<String, DeserialiseItem>();

	/**
	 * @param type
	 */
	public Deserialiser(final String type) {
		this.type = type;
	}

	public String getType() {
		return this.type;
	}

	public List<DeserialiseItem> getItems() {
		return new ArrayList(Arrays.asList(this.deserialiseItemMap.values().toArray()));
	}

	public void addDeserialiseItem(final String name, final DeserialiseItem item) {
		if (!this.deserialiseItemMap.containsKey(name)) {
			this.deserialiseItemMap.put(name, item);
		}
	}

}
