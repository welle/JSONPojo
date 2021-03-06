package aka.convertor.json.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.jdt.annotation.NonNull;

import com.fasterxml.jackson.databind.JsonNode;

import aka.convertor.json.JsonMetaData;
import aka.convertor.json.helpers.StringUtilities;

/**
 * Object Meta data.
 *
 * @author Welle Charlotte
 */
public class ObjectMetaData {

    @NonNull
    private String javaObjectName;
    /**
     * Objects in the current object.
     */
    @NonNull
    public final Map<@NonNull String, FieldMetaData> objects = new HashMap<>();
    @NonNull
    private final Map<@NonNull String, @NonNull FieldMetaData> fields = new HashMap<>();
    private boolean containList = false;

    /**
     * @param objectName
     * @param rootObject
     * @param jsonMetaData
     */
    public ObjectMetaData(@NonNull final String objectName, @NonNull final JsonNode rootObject, @NonNull final JsonMetaData jsonMetaData) {
        this.javaObjectName = StringUtilities.getVariableName(objectName);

        if (rootObject.isArray()) {
            // array node = must parse all entries
            final Iterator<JsonNode> elements = rootObject.elements();

            while (elements.hasNext()) {
                final JsonNode currentNode = elements.next();
                assert currentNode != null;
                parseElements(currentNode, jsonMetaData, this);
            }
        } else {
            // not an object or array node
            parseElements(rootObject, jsonMetaData, this);
        }
    }

    private void parseElements(@NonNull final JsonNode currentNode, @NonNull final JsonMetaData jsonMetaData, @NonNull final ObjectMetaData objectMetaData) {
        final Iterator<Entry<String, JsonNode>> elements = currentNode.fields();
        while (elements.hasNext()) {
            final Entry<String, JsonNode> jsonNode = elements.next();
            final String fieldName = jsonNode.getKey();
            final JsonNode currentJsonNode = jsonNode.getValue();
            if (fieldName != null && currentJsonNode != null) {
                if (!this.fields.containsKey(fieldName)) {
                    final String serName = fieldName;
                    final FieldMetaData field = new FieldMetaData(fieldName, serName, currentJsonNode, jsonMetaData, objectMetaData);

                    if (field.isObject()) {
                        objectMetaData.objects.put(fieldName, field);
                    }
                    objectMetaData.fields.put(fieldName, field);
                    if (field.isAList()) {
                        this.containList = field.isAList();
                    }
                } else if (this.objects.containsKey(fieldName)) {
                    // update object
                    final FieldMetaData fieldMetaData = this.objects.get(fieldName);
                    final ObjectMetaData existingObjectMetaData = fieldMetaData.getObject();
                    if (existingObjectMetaData != null) {
                        parseElements(currentJsonNode, jsonMetaData, existingObjectMetaData);
                    }
                }
            }
        }
    }

    /**
     * Get fields.
     *
     * @return ArrayList<FieldMetaData>
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @NonNull
    public ArrayList<@NonNull FieldMetaData> getFields() {
        return new ArrayList(Arrays.asList(this.fields.values().toArray()));
    }

    /**
     * Get objects.
     *
     * @return ArrayList<FieldMetaData>
     */
    @NonNull
    public Map<@NonNull String, FieldMetaData> getObjects() {
        return this.objects;
    }

    /**
     * Get java object name.
     *
     * @return String
     */
    @NonNull
    public String getJavaObjectName() {
        return this.javaObjectName;
    }

    /**
     * Change field.
     *
     * @param name
     * @param newName
     * @param fieldMetaData
     */
    public void changeField(@NonNull final String name, @NonNull final String newName, @NonNull final FieldMetaData fieldMetaData) {
        this.fields.remove(name);
        this.fields.put(newName, fieldMetaData);
    }

    /**
     * Change name.
     *
     * @param newName
     */
    public void changeName(@NonNull final String newName) {
        this.javaObjectName = StringUtilities.getVariableName(newName);
    }

    /**
     * Contains a list ?
     *
     * @return <code>true</code> if contains a list.
     */
    public boolean containList() {
        return this.containList;
    }
}
