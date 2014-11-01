package aka.convertor.examples.example4;

import org.eclipse.jdt.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * This is a generated file.
 *
 * @author Welle
 */ 
@JsonIgnoreProperties(ignoreUnknown = true) 
public final class Status {

	@Nullable
    private String reason;
	@Nullable
    private String value;

	/**
     * Empty Constructor.
     */
    public Status() {
    	// Nothing to do
    }

    /**
     * Filling Constructor.
     *
     * @param reasonParam String
     * @param valueParam String
     */
    public Status(@Nullable final String reasonParam, @Nullable final String valueParam) {
        this.reason = reasonParam;
        this.value = valueParam;
    }
    
    /**
     * Get value of reason.
     *
     * @return String value of reason
     */
    @Nullable
    public final String getReason() {
        return this.reason;
    }
    
    /**
     * Get value of value.
     *
     * @return String value of value
     */
    @Nullable
    public final String getValue() {
        return this.value;
    }

    
    /**
     * Set value of reason.
     *
     * @param reasonParam
     */
    public final void setReason(@Nullable final String reasonParam) {
        this.reason = reasonParam;
    }
    
    /**
     * Set value of value.
     *
     * @param valueParam
     */
    public final void setValue(@Nullable final String valueParam) {
        this.value = valueParam;
    }
}
