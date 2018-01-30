package aka.convertor.json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import aka.convertor.json.data.DeserialiseItem;
import aka.convertor.json.data.Deserialiser;
import aka.convertor.json.data.FieldMetaData;
import aka.convertor.json.data.ObjectMetaData;

/**
 * @author Charlotte
 *
 */
public class JsonMetaData {

    @NonNull
    private final static Logger LOGGER = Logger.getLogger(JsonMetaData.class.getCanonicalName());
    private final Map<@NonNull String, @Nullable ObjectMetaData> objects = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
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
            assert rootNode != null;

            this.isRootAnArray = rootNode.isArray();
            // multiple children
            final ObjectMetaData tmd = new ObjectMetaData(name, rootNode, this);
            this.objects.put(name, tmd);

            final Map<@NonNull String, FieldMetaData> objectsList = tmd.getObjects();
            getAllObject(objectsList);

            checkFieldsName(tmd.getFields());
        } catch (final JsonProcessingException e) {
            LOGGER.logp(Level.SEVERE, "JsonMetaData", "JsonMetaData", e.getMessage(), e);
        } catch (final IOException e) {
            LOGGER.logp(Level.SEVERE, "JsonMetaData", "JsonMetaData", e.getMessage(), e);
        }
    }

    private void checkFieldsName(@NonNull final ArrayList<@NonNull FieldMetaData> fieldsList) {
        final InsensitiveStringList fields = new InsensitiveStringList();
        for (final @NonNull FieldMetaData fieldMetaData : fieldsList) {
            String name = fieldMetaData.getParamName();

            if (fields.contains(name)) {
                // change field name
                int i = 1;
                while (fields.contains(name + i)) {
                    i++;
                }
                final String newName = name + i;

                fieldMetaData.setParamName(newName);

                name = newName;
            }
            fields.add(name);

        }
    }

    private void getAllObject(@NonNull final Map<String, FieldMetaData> objectsList) {
        for (final Entry<String, FieldMetaData> entry : objectsList.entrySet()) {
            String name = entry.getKey();
            if (name != null) {
                final FieldMetaData fieldMetaData = entry.getValue();
                final ObjectMetaData object = fieldMetaData.getObject();
                if (object != null) {
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
                        final ObjectMetaData parentObject = fieldMetaData.getParentObject();
                        parentObject.changeField(name, newName, fieldMetaData);

                        name = newName;
                    }
                    this.objects.put(name, object);
                    getAllObject(object.objects);
                }
            }
        }
    }

    /**
     * @return ArrayList<TableMetaData>
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @NonNull
    public ArrayList<ObjectMetaData> getObjects() {
        return new ArrayList(Arrays.asList(this.objects.values().toArray()));
    }

    /**
     * Get List of deserialisers.
     *
     * @return list of deserialiser
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @NonNull
    public List<@NonNull Deserialiser> getDeserialisers() {
        return new ArrayList(Arrays.asList(this.deserialiseList.values().toArray()));
    }

    /**
     * Add deserialiser.
     *
     * @param type
     * @param name
     * @param pattern
     */
    public void addDeserialiser(@NonNull final String type, @NonNull final String name, @Nullable final String pattern) {
        Deserialiser deserialiser = this.deserialiseList.get(type);
        if (deserialiser == null) {
            deserialiser = new Deserialiser(type);
            this.deserialiseList.put(type, deserialiser);
        }

        final DeserialiseItem item = new DeserialiseItem(name, pattern);
        deserialiser.addDeserialiseItem(name, item);
    }

    /**
     * Is root is an array ?
     *
     * @return <code>true</code> if root is an array
     */
    public boolean isRootAnArray() {
        return this.isRootAnArray;
    }

    private class InsensitiveStringList extends ArrayList<@NonNull String> {
        private static final long serialVersionUID = 2495762265652730221L;

        @Override
        public boolean contains(final Object o) {
            final String paramStr = (String) o;
            for (final String s : this) {
                if (paramStr.equalsIgnoreCase(s)) {
                    return true;
                }
            }
            return false;
        }
    }
}
