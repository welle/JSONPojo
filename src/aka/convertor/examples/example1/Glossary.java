package aka.convertor.examples.example1;

import org.eclipse.jdt.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This is a generated file.
 *
 * @author Welle
 */ 
@JsonIgnoreProperties(ignoreUnknown = true) 
public final class Glossary {

	@Nullable
    private String title;
    @JsonProperty("GlossDiv")
	@Nullable
    private GlossDiv glossDiv;

	/**
     * Empty Constructor.
     */
    public Glossary() {
    	// Nothing to do
    }

    /**
     * Filling Constructor.
     *
     * @param titleParam String
     * @param glossDivParam GlossDiv
     */
    public Glossary(@Nullable final String titleParam, @Nullable final GlossDiv glossDivParam) {
        this.title = titleParam;
        this.glossDiv = glossDivParam;
    }
    
    /**
     * Get value of title.
     *
     * @return String value of title
     */
    @Nullable
    public final String getTitle() {
        return this.title;
    }
    
    /**
     * Get value of glossDiv.
     *
     * @return GlossDiv value of glossDiv
     */
    @Nullable
    public final GlossDiv getGlossDiv() {
        return this.glossDiv;
    }

    
    /**
     * Set value of title.
     *
     * @param titleParam
     */
    public final void setTitle(@Nullable final String titleParam) {
        this.title = titleParam;
    }
    
    /**
     * Set value of glossDiv.
     *
     * @param glossDivParam
     */
    public final void setGlossDiv(@Nullable final GlossDiv glossDivParam) {
        this.glossDiv = glossDivParam;
    }
}
