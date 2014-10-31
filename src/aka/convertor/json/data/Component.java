package aka.convertor.json.data;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;

import aka.convertor.json.FieldMetaData;
import aka.convertor.json.constants.AnnotationType;

import com.sun.istack.internal.Nullable;

public class Component {

	private String name;
	private ArrayList<FieldMetaData> fields;
	private String author;
	@NonNull
	private final AnnotationType annotation;

	public Component(@NonNull AnnotationType annotationType) {
		this.annotation = annotationType;
	}

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
			result = !fieldMetaData.getParamName().equals(fieldMetaData.getSerName());
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

	public void setAuthor(@Nullable String author) {
		this.author = author;
	}

	@Nullable
	public String getAuthor() {
		return this.author;
	}

	@NonNull
	public String getAnnotations() {
		return this.annotation.getName();
	}
}
