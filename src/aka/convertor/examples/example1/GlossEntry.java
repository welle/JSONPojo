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
public final class GlossEntry {

    @JsonProperty("GlossTerm")
	@Nullable
    private String glossTerm;
    @JsonProperty("GlossSee")
	@Nullable
    private String glossSee;
    @JsonProperty("SortAs")
	@Nullable
    private String sortAs;
    @JsonProperty("GlossDef")
	@Nullable
    private GlossDef glossDef;
    @JsonProperty("ID")
	@Nullable
    private String iD;
    @JsonProperty("Acronym")
	@Nullable
    private String acronym;
    @JsonProperty("Abbrev")
	@Nullable
    private String abbrev;

	/**
     * Empty Constructor.
     */
    public GlossEntry() {
    	// Nothing to do
    }

    /**
     * Filling Constructor.
     *
     * @param glossTermParam String
     * @param glossSeeParam String
     * @param sortAsParam String
     * @param glossDefParam GlossDef
     * @param iDParam String
     * @param acronymParam String
     * @param abbrevParam String
     */
    public GlossEntry(@Nullable final String glossTermParam, @Nullable final String glossSeeParam, @Nullable final String sortAsParam, @Nullable final GlossDef glossDefParam, @Nullable final String iDParam, @Nullable final String acronymParam, @Nullable final String abbrevParam) {
        this.glossTerm = glossTermParam;
        this.glossSee = glossSeeParam;
        this.sortAs = sortAsParam;
        this.glossDef = glossDefParam;
        this.iD = iDParam;
        this.acronym = acronymParam;
        this.abbrev = abbrevParam;
    }
    
    /**
     * Get value of glossTerm.
     *
     * @return String value of glossTerm
     */
    @Nullable
    public final String getGlossTerm() {
        return this.glossTerm;
    }
    
    /**
     * Get value of glossSee.
     *
     * @return String value of glossSee
     */
    @Nullable
    public final String getGlossSee() {
        return this.glossSee;
    }
    
    /**
     * Get value of sortAs.
     *
     * @return String value of sortAs
     */
    @Nullable
    public final String getSortAs() {
        return this.sortAs;
    }
    
    /**
     * Get value of glossDef.
     *
     * @return GlossDef value of glossDef
     */
    @Nullable
    public final GlossDef getGlossDef() {
        return this.glossDef;
    }
    
    /**
     * Get value of iD.
     *
     * @return String value of iD
     */
    @Nullable
    public final String getID() {
        return this.iD;
    }
    
    /**
     * Get value of acronym.
     *
     * @return String value of acronym
     */
    @Nullable
    public final String getAcronym() {
        return this.acronym;
    }
    
    /**
     * Get value of abbrev.
     *
     * @return String value of abbrev
     */
    @Nullable
    public final String getAbbrev() {
        return this.abbrev;
    }

    
    /**
     * Set value of glossTerm.
     *
     * @param glossTermParam
     */
    public final void setGlossTerm(@Nullable final String glossTermParam) {
        this.glossTerm = glossTermParam;
    }
    
    /**
     * Set value of glossSee.
     *
     * @param glossSeeParam
     */
    public final void setGlossSee(@Nullable final String glossSeeParam) {
        this.glossSee = glossSeeParam;
    }
    
    /**
     * Set value of sortAs.
     *
     * @param sortAsParam
     */
    public final void setSortAs(@Nullable final String sortAsParam) {
        this.sortAs = sortAsParam;
    }
    
    /**
     * Set value of glossDef.
     *
     * @param glossDefParam
     */
    public final void setGlossDef(@Nullable final GlossDef glossDefParam) {
        this.glossDef = glossDefParam;
    }
    
    /**
     * Set value of iD.
     *
     * @param iDParam
     */
    public final void setID(@Nullable final String iDParam) {
        this.iD = iDParam;
    }
    
    /**
     * Set value of acronym.
     *
     * @param acronymParam
     */
    public final void setAcronym(@Nullable final String acronymParam) {
        this.acronym = acronymParam;
    }
    
    /**
     * Set value of abbrev.
     *
     * @param abbrevParam
     */
    public final void setAbbrev(@Nullable final String abbrevParam) {
        this.abbrev = abbrevParam;
    }
}
