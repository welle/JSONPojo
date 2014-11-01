package aka.convertor.examples.example4;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * This is a generated file.
 *
 * @author Welle
 */ 
@JsonIgnoreProperties(ignoreUnknown = true) 
public final class Menu {

	@Nullable
    private String header;
	@NonNull
    private List<Items> items = new ArrayList<>();

	/**
     * Empty Constructor.
     */
    public Menu() {
    	// Nothing to do
    }

    /**
     * Filling Constructor.
     *
     * @param headerParam String
     * @param itemsParam List<Items
     */
    public Menu(@Nullable final String headerParam, @NonNull final List<Items> itemsParam) {
        this.header = headerParam;
        this.items = itemsParam;
    }
    
    /**
     * Get value of header.
     *
     * @return String value of header
     */
    @Nullable
    public final String getHeader() {
        return this.header;
    }
    
    /**
     * Get value of items.
     *
     * @return List<Items> value of items
     */
    @NonNull
    public final List<Items> getItems() {
        return this.items;
    }

    
    /**
     * Set value of header.
     *
     * @param headerParam
     */
    public final void setHeader(@Nullable final String headerParam) {
        this.header = headerParam;
    }
    
    /**
     * Set value of items.
     *
     * @param itemsParam
     */
    public final void getItems(@NonNull final List<Items> itemsParam) {
        this.items = itemsParam;
    }
}
