package aka.convertor.examples.example4;

import org.eclipse.jdt.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * This is a generated file.
 *
 * @author Welle
 */ 
@JsonIgnoreProperties(ignoreUnknown = true) 
public final class Items {

	@Nullable
    private String id;
	@Nullable
    private String label;

	/**
     * Empty Constructor.
     */
    public Items() {
    	// Nothing to do
    }

    /**
     * Filling Constructor.
     *
     * @param idParam String
     * @param labelParam String
     */
    public Items(@Nullable final String idParam, @Nullable final String labelParam) {
        this.id = idParam;
        this.label = labelParam;
    }
    
    /**
     * Get value of id.
     *
     * @return String value of id
     */
    @Nullable
    public final String getId() {
        return this.id;
    }
    
    /**
     * Get value of label.
     *
     * @return String value of label
     */
    @Nullable
    public final String getLabel() {
        return this.label;
    }

    
    /**
     * Set value of id.
     *
     * @param idParam
     */
    public final void setId(@Nullable final String idParam) {
        this.id = idParam;
    }
    
    /**
     * Set value of label.
     *
     * @param labelParam
     */
    public final void setLabel(@Nullable final String labelParam) {
        this.label = labelParam;
    }
}
