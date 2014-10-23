package aka.convertor.json.data;

import java.util.ArrayList;
import java.util.List;

import aka.convertor.json.FieldMetaData;

public class Component {

	private String name;
	private ArrayList<FieldMetaData> fields;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNodes(ArrayList<FieldMetaData> fields) {
		this.fields = fields;
	}

	public List<FieldMetaData> getNodes() {
		return this.fields;
	}

	public boolean containList() {
		boolean result = false;
		for (final FieldMetaData fieldMetaData : this.fields) {
			result = fieldMetaData.isAList();
			if (result) {
				// found, just break;
				break;
			}
		}
		return result;
	}

	public boolean containJsonProperty() {
		boolean result = false;
		for (final FieldMetaData fieldMetaData : this.fields) {
			result = !fieldMetaData.getParamName().equals(fieldMetaData.getDeserName());
			if (result) {
				// found, just break;
				break;
			}
		}
		return result;
	}

	public boolean containDeserialiser() {
		boolean result = false;
		for (final FieldMetaData fieldMetaData : this.fields) {
			result = !fieldMetaData.getJsonMetaData().getDeserialises().isEmpty();
			if (result) {
				// found, just break;
				break;
			}
		}
		return result;
	}
}
