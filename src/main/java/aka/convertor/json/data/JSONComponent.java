package aka.convertor.json.data;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

import aka.convertor.json.constants.AnnotationType;

/**
 * JSON component.</br>
 * This component is use to create java object. It can be parameterized with author and annotations if asked.
 *
 * @author Welle Charlotte
 */
public class JSONComponent {

    @NonNull
    private final String name;
    @NonNull
    private ArrayList<@NonNull FieldMetaData> fields = new ArrayList<>();
    @Nullable
    private final String author;
    @NonNull
    private AnnotationType annotation = AnnotationType.NONE;

    /**
     * Constructor.
     *
     * @param name name of the component
     * @param author author that will appear as @author in java file
     */
    public JSONComponent(@NonNull final String name, @Nullable final String author) {
        this.name = name;
        this.author = author;
    }

    /**
     * Constructor.
     *
     * @param name name of the component
     * @param annotationType type of annotation to use
     * @param author author that will appear as @author in java file
     * @see AnnotationType
     */
    public JSONComponent(@NonNull final String name, @NonNull final AnnotationType annotationType, @Nullable final String author) {
        this(name, author);
        this.annotation = annotationType;
    }

    /**
     * Get component name (java object name).
     *
     * @return component name
     */
    @NonNull
    public String getName() {
        return this.name;
    }

    /**
     * Set component fields (java variables).
     *
     * @param fields
     */
    public void setFields(@NonNull final ArrayList<@NonNull FieldMetaData> fields) {
        this.fields = fields;
    }

    /**
     * Get component fields (java variables).
     *
     * @return component fields (java variables)
     */
    @Nullable
    public List<@NonNull FieldMetaData> getFields() {
        return this.fields;
    }

    /**
     * Check if any of the fields (variables) is a list.
     *
     * @return <code>true</code> if any of the fields (variables) is a list
     */
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

    /**
     * Check if any of the field (variable) contain a JSON property.
     *
     * @return <code>true</code> if any of the field (variable) contain a JSON property
     */
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

    /**
     * Get component author.
     *
     * @return component author
     */
    @Nullable
    public String getAuthor() {
        return this.author;
    }

    /**
     * Get component annotation.
     *
     * @return annotation
     */
    @NonNull
    public String getAnnotation() {
        return this.annotation.getName();
    }
}
