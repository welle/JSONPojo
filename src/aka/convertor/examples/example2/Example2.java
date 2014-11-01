package aka.convertor.examples.example2;

import org.eclipse.jdt.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This is a generated file.
 *
 * @author Welle
 */ 
@JsonIgnoreProperties(ignoreUnknown = true) 
public final class Example2 {

    @JsonProperty("web-app")
	@Nullable
    private WebApp webApp;

	/**
     * Empty Constructor.
     */
    public Example2() {
    	// Nothing to do
    }

    /**
     * Filling Constructor.
     *
     * @param webAppParam WebApp
     */
    public Example2(@Nullable final WebApp webAppParam) {
        this.webApp = webAppParam;
    }
    
    /**
     * Get value of webApp.
     *
     * @return WebApp value of webApp
     */
    @Nullable
    public final WebApp getWebApp() {
        return this.webApp;
    }

    
    /**
     * Set value of webApp.
     *
     * @param webAppParam
     */
    public final void setWebApp(@Nullable final WebApp webAppParam) {
        this.webApp = webAppParam;
    }
}
