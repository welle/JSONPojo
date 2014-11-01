package aka.convertor.examples.example2;

import org.eclipse.jdt.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This is a generated file.
 *
 * @author Welle
 */ 
@JsonIgnoreProperties(ignoreUnknown = true) 
public final class Taglib {

    @JsonProperty("taglib-location")
	@Nullable
    private String taglibLocation;
    @JsonProperty("taglib-uri")
	@Nullable
    private String taglibUri;

	/**
     * Empty Constructor.
     */
    public Taglib() {
    	// Nothing to do
    }

    /**
     * Filling Constructor.
     *
     * @param taglibLocationParam String
     * @param taglibUriParam String
     */
    public Taglib(@Nullable final String taglibLocationParam, @Nullable final String taglibUriParam) {
        this.taglibLocation = taglibLocationParam;
        this.taglibUri = taglibUriParam;
    }
    
    /**
     * Get value of taglibLocation.
     *
     * @return String value of taglibLocation
     */
    @Nullable
    public final String getTaglibLocation() {
        return this.taglibLocation;
    }
    
    /**
     * Get value of taglibUri.
     *
     * @return String value of taglibUri
     */
    @Nullable
    public final String getTaglibUri() {
        return this.taglibUri;
    }

    
    /**
     * Set value of taglibLocation.
     *
     * @param taglibLocationParam
     */
    public final void setTaglibLocation(@Nullable final String taglibLocationParam) {
        this.taglibLocation = taglibLocationParam;
    }
    
    /**
     * Set value of taglibUri.
     *
     * @param taglibUriParam
     */
    public final void setTaglibUri(@Nullable final String taglibUriParam) {
        this.taglibUri = taglibUriParam;
    }
}
