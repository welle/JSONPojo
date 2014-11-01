package aka.convertor.examples.example3;


import org.eclipse.jdt.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * This is a generated file.
 *
 * @author Welle
 */ 
@JsonIgnoreProperties(ignoreUnknown = true) 
public final class From {

	@Nullable
    private String name;
	@Nullable
    private String id;

	/**
     * Empty Constructor.
     */
    public From() {
    	// Nothing to do
    }

    /**
     * Filling Constructor.
     *
     * @param nameParam String
     * @param idParam String
     */
    public From(@Nullable final String nameParam, @Nullable final String idParam) {
        this.name = nameParam;
        this.id = idParam;
    }
    
    /**
     * Get value of name.
     *
     * @return String value of name
     */
    @Nullable
    public final String getName() {
        return this.name;
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
     * Set value of name.
     *
     * @param nameParam
     */
    public final void setName(@Nullable final String nameParam) {
        this.name = nameParam;
    }
    
    /**
     * Set value of id.
     *
     * @param idParam
     */
    public final void setId(@Nullable final String idParam) {
        this.id = idParam;
    }
}
