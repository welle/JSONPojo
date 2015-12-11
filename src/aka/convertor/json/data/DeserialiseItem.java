package aka.convertor.json.data;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

public class DeserialiseItem {

    @NonNull
    private final String name;
    @Nullable
    private final String format;

    /**
     * @param name
     * @param format
     */
    public DeserialiseItem(@NonNull final String name, @Nullable final String format) {
        this.name = name;
        this.format = format;
    }

    @Nullable
    public String getFormat() {
        return this.format;
    }

    @NonNull
    public String getName() {
        return this.name;
    }
}
