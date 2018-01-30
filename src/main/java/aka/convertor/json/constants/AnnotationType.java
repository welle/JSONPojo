package aka.convertor.json.constants;

import org.eclipse.jdt.annotation.NonNull;

/**
 * Enumeration of annotation type.
 *
 * @author Welle Charlotte
 */
public enum AnnotationType {

    /**
     * Eclipse.
     */
    ECLIPSE("eclipse"),

    /**
     * JSR.
     */
    JSR("jsr"),

    /**
     * None.
     */
    NONE("none");

    @NonNull
    private String name;

    private AnnotationType(@NonNull final String name) {
        this.name = name;
    }

    /**
     * Get annotation type name.
     *
     * @return annotation type name
     */
    @NonNull
    public String getName() {
        return this.name;
    }
}
