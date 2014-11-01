package aka.convertor.examples.example1;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This is a generated file.
 *
 * @author Welle
 */ 
@JsonIgnoreProperties(ignoreUnknown = true) 
public final class GlossDef {

	@Nullable
    private String para;
    @JsonProperty("GlossSeeAlso")
	@NonNull
    private List<String> glossSeeAlso = new ArrayList<>();

	/**
     * Empty Constructor.
     */
    public GlossDef() {
    	// Nothing to do
    }

    /**
     * Filling Constructor.
     *
     * @param paraParam String
     * @param glossSeeAlsoParam List<String
     */
    public GlossDef(@Nullable final String paraParam, @NonNull final List<String> glossSeeAlsoParam) {
        this.para = paraParam;
        this.glossSeeAlso = glossSeeAlsoParam;
    }
    
    /**
     * Get value of para.
     *
     * @return String value of para
     */
    @Nullable
    public final String getPara() {
        return this.para;
    }
    
    /**
     * Get value of glossSeeAlso.
     *
     * @return List<String> value of glossSeeAlso
     */
    @NonNull
    public final List<String> getGlossSeeAlso() {
        return this.glossSeeAlso;
    }

    
    /**
     * Set value of para.
     *
     * @param paraParam
     */
    public final void setPara(@Nullable final String paraParam) {
        this.para = paraParam;
    }
    
    /**
     * Set value of glossSeeAlso.
     *
     * @param glossSeeAlsoParam
     */
    public final void getGlossSeeAlso(@NonNull final List<String> glossSeeAlsoParam) {
        this.glossSeeAlso = glossSeeAlsoParam;
    }
}
