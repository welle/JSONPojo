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
public final class GlossDiv {

    @JsonProperty("GlossList")
	@Nullable
    private GlossList glossList;
	@Nullable
    private String title;

	/**
     * Empty Constructor.
     */
    public GlossDiv() {
    	// Nothing to do
    }

    /**
     * Filling Constructor.
     *
     * @param glossListParam GlossList
     * @param titleParam String
     */
    public GlossDiv(@Nullable final GlossList glossListParam, @Nullable final String titleParam) {
        this.glossList = glossListParam;
        this.title = titleParam;
    }
    
    /**
     * Get value of glossList.
     *
     * @return GlossList value of glossList
     */
    @Nullable
    public final GlossList getGlossList() {
        return this.glossList;
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
     * Set value of glossList.
     *
     * @param glossListParam
     */
    public final void setGlossList(@Nullable final GlossList glossListParam) {
        this.glossList = glossListParam;
    }
    
    /**
     * Set value of title.
     *
     * @param titleParam
     */
    public final void setTitle(@Nullable final String titleParam) {
        this.title = titleParam;
    }
}
