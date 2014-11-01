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
public final class Player {

    @JsonProperty("default")
    @JsonDeserialize(using = URLDeserializer.class)
    private URI defaultRes;

	/**
     * Empty Constructor.
     */
    public Player() {
    	// Nothing to do
    }

    /**
     * Filling Constructor.
     *
     * @param defaultResParam URI
     */
    public Player(@Nullable final URI defaultResParam) {
        this.defaultRes = defaultResParam;
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
     * Set value of defaultRes.
     *
     * @param defaultResParam
     */
    public final void setDefaultRes(@Nullable final URI defaultResParam) {
        this.defaultRes = defaultResParam;
    }
}
