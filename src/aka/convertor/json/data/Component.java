package aka.convertor.json.data;

import java.util.ArrayList;
import java.util.List;

import aka.convertor.json.FieldMetaData;

public class Component {

	private String name;
	private ArrayList<FieldMetaData> fields;
	private final boolean containList = true;

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
		return true;
	}
}
