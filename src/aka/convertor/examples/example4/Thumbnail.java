package aka.convertor.examples.example4;

import java.net.URI;
import org.eclipse.jdt.annotation.Nullable;
import aka.convertor.examples.example4.deserializers.URLDeserializer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * This is a generated file.
 *
 * @author Welle
 */ 
@JsonIgnoreProperties(ignoreUnknown = true) 
public final class Thumbnail {

    @JsonProperty("default")
    @JsonDeserialize(using = URLDeserializer.class)
    private URI defaultRes;
    @JsonDeserialize(using = URLDeserializer.class)
    private URI hqDefault;

	/**
     * Empty Constructor.
     */
    public Thumbnail() {
    	// Nothing to do
    }

    /**
     * Filling Constructor.
     *
     * @param defaultResParam URI
     * @param hqDefaultParam URI
     */
    public Thumbnail(@Nullable final URI defaultResParam, @Nullable final URI hqDefaultParam) {
        this.defaultRes = defaultResParam;
        this.hqDefault = hqDefaultParam;
    }
    
    /**
     * Get value of defaultRes.
     *
     * @return URI value of defaultRes
     */
    @Nullable
    public final URI getDefaultRes() {
        return this.defaultRes;
    }
    
    /**
     * Get value of hqDefault.
     *
     * @return URI value of hqDefault
     */
    @Nullable
    public final URI getHqDefault() {
        return this.hqDefault;
    }

    
    /**
     * Set value of defaultRes.
     *
     * @param defaultResParam
     */
    public final void setDefaultRes(@Nullable final URI defaultResParam) {
        this.defaultRes = defaultResParam;
    }
    
    /**
     * Set value of hqDefault.
     *
     * @param hqDefaultParam
     */
    public final void setHqDefault(@Nullable final URI hqDefaultParam) {
        this.hqDefault = hqDefaultParam;
    }
}
