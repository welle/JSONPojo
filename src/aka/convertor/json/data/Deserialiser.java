package aka.convertor.json.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;

public class Deserialiser {

    @NonNull
    private final String type;
    private final Map<@NonNull String, DeserialiseItem> deserialiseItemMap = new HashMap<>();

    /**
     * @param type
     */
    public Deserialiser(@NonNull final String type) {
        this.type = type;
    }

    @NonNull
    public String getType() {
        return this.type;
    }

    @NonNull
    public List<@NonNull DeserialiseItem> getItems() {
        return new ArrayList(Arrays.asList(this.deserialiseItemMap.values().toArray()));
    }

    public void addDeserialiseItem(@NonNull final String name, @NonNull final DeserialiseItem item) {
        if (!this.deserialiseItemMap.containsKey(name)) {
            this.deserialiseItemMap.put(name, item);
        }
    }
}
