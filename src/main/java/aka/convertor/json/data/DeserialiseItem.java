package aka.convertor.json.data;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

/**
 * Deserializer item.
 *
 * @author charlottew
 */
public class DeserialiseItem {

    @NonNull
    private final String name;
    @Nullable
    private final String format;

    /**
     * Constructor.
     *
     * @param name
     * @param format
     */
    public DeserialiseItem(@NonNull final String name, @Nullable final String format) {
        this.name = name;
        this.format = format;
    }

    /**
     * Get format.
     *
     * @return format
     */
    @Nullable
    public String getFormat() {
        return this.format;
    }

    /**
     * Get name.
     *
     * @return name
     */
    @NonNull
    public String getName() {
        return this.name;
    }
}
