package aka.convertor.json.data;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

import aka.convertor.json.FieldMetaData;
import aka.convertor.json.constants.AnnotationType;

public class Component {

	@NonNull
	private final String name;
	private ArrayList<FieldMetaData> fields;
	private final String author;
	@NonNull
	private AnnotationType annotation = AnnotationType.NONE;

	public Component(@NonNull final String name, @Nullable final String author) {
		this.name = name;
		this.author = author;
	}

	public Component(@NonNull final String name, @NonNull final AnnotationType annotationType, @Nullable final String author) {
		this(name, author);
		this.annotation = annotationType;
	}

	@NonNull
	public String getName() {
		return this.name;
	}

	public void setNodes(@NonNull final ArrayList<@NonNull FieldMetaData> fields) {
		this.fields = fields;
	}

	@Nullable
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

	@Nullable
	public String getAuthor() {
		return this.author;
	}

	@NonNull
	public String getAnnotations() {
		return this.annotation.getName();
	}
}
