package aka.convertor.examples.example4;

import org.eclipse.jdt.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * This is a generated file.
 *
 * @author Welle
 */ 
@JsonIgnoreProperties(ignoreUnknown = true) 
public final class Example4 {

	@Nullable
    private String apiVersion;
	@Nullable
    private Data data;

	/**
     * Empty Constructor.
     */
    public Example4() {
    	// Nothing to do
    }

    /**
     * Filling Constructor.
     *
     * @param apiVersionParam String
     * @param dataParam Data
     */
    public Example4(@Nullable final String apiVersionParam, @Nullable final Data dataParam) {
        this.apiVersion = apiVersionParam;
        this.data = dataParam;
    }
    
    /**
     * Get value of apiVersion.
     *
     * @return String value of apiVersion
     */
    @Nullable
    public final String getApiVersion() {
        return this.apiVersion;
    }
    
    /**
     * Get value of data.
     *
     * @return Data value of data
     */
    @Nullable
    public final Data getData() {
        return this.data;
    }

    
    /**
     * Set value of apiVersion.
     *
     * @param apiVersionParam
     */
    public final void setApiVersion(@Nullable final String apiVersionParam) {
        this.apiVersion = apiVersionParam;
    }
    
    /**
     * Set value of data.
     *
     * @param dataParam
     */
    public final void setData(@Nullable final Data dataParam) {
        this.data = dataParam;
    }
}
