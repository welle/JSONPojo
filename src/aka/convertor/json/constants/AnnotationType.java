package aka.convertor.json.constants;

import org.eclipse.jdt.annotation.NonNull;

public enum AnnotationType {

	ECLIPSE("eclipse"), JSR("jsr"), NONE("none");

	@NonNull
	private String name;

	private AnnotationType(@NonNull String name) {
		this.name = name;
	}

	@NonNull
	public String getName() {
		return this.name;
	}
}
