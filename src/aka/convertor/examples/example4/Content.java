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
public final class Content {

    @JsonProperty("1")
	@Nullable
    private String value1;
    @JsonProperty("5")
    @JsonDeserialize(using = URLDeserializer.class)
    private URI value5;
    @JsonProperty("6")
	@Nullable
    private String value6;

	/**
     * Empty Constructor.
     */
    public Content() {
    	// Nothing to do
    }

    /**
     * Filling Constructor.
     *
     * @param value1Param String
     * @param value5Param URI
     * @param value6Param String
     */
    public Content(@Nullable final String value1Param, @Nullable final URI value5Param, @Nullable final String value6Param) {
        this.value1 = value1Param;
        this.value5 = value5Param;
        this.value6 = value6Param;
    }
    
    /**
     * Get value of value1.
     *
     * @return String value of value1
     */
    @Nullable
    public final String getValue1() {
        return this.value1;
    }
    
    /**
     * Get value of value5.
     *
     * @return URI value of value5
     */
    @Nullable
    public final URI getValue5() {
        return this.value5;
    }
    
    /**
     * Get value of value6.
     *
     * @return String value of value6
     */
    @Nullable
    public final String getValue6() {
        return this.value6;
    }

    
    /**
     * Set value of value1.
     *
     * @param value1Param
     */
    public final void setValue1(@Nullable final String value1Param) {
        this.value1 = value1Param;
    }
    
    /**
     * Set value of value5.
     *
     * @param value5Param
     */
    public final void setValue5(@Nullable final URI value5Param) {
        this.value5 = value5Param;
    }
    
    /**
     * Set value of value6.
     *
     * @param value6Param
     */
    public final void setValue6(@Nullable final String value6Param) {
        this.value6 = value6Param;
    }
}
