package aka.convertor.examples.example3;

import java.net.URI;

import org.eclipse.jdt.annotation.Nullable;
import aka.convertor.examples.example3.deserializers.URLDeserializer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * This is a generated file.
 *
 * @author Welle
 */ 
@JsonIgnoreProperties(ignoreUnknown = true) 
public final class Actions {

	@Nullable
    private String name;
    @JsonDeserialize(using = URLDeserializer.class)
    private URI link;

	/**
     * Empty Constructor.
     */
    public Actions() {
    	// Nothing to do
    }

    /**
     * Filling Constructor.
     *
     * @param nameParam String
     * @param linkParam URI
     */
    public Actions(@Nullable final String nameParam, @Nullable final URI linkParam) {
        this.name = nameParam;
        this.link = linkParam;
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
     * Get value of link.
     *
     * @return URI value of link
     */
    @Nullable
    public final URI getLink() {
        return this.link;
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
     * Set value of link.
     *
     * @param linkParam
     */
    public final void setLink(@Nullable final URI linkParam) {
        this.link = linkParam;
    }
}
