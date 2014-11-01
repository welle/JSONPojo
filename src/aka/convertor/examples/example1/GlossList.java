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
public final class GlossList {

    @JsonProperty("GlossEntry")
	@Nullable
    private GlossEntry glossEntry;

	/**
     * Empty Constructor.
     */
    public GlossList() {
    	// Nothing to do
    }

    /**
     * Filling Constructor.
     *
     * @param glossEntryParam GlossEntry
     */
    public GlossList(@Nullable final GlossEntry glossEntryParam) {
        this.glossEntry = glossEntryParam;
    }
    
    /**
     * Get value of glossEntry.
     *
     * @return GlossEntry value of glossEntry
     */
    @Nullable
    public final GlossEntry getGlossEntry() {
        return this.glossEntry;
    }

    
    /**
     * Set value of glossEntry.
     *
     * @param glossEntryParam
     */
    public final void setGlossEntry(@Nullable final GlossEntry glossEntryParam) {
        this.glossEntry = glossEntryParam;
    }
}
