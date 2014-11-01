package aka.convertor.examples.example2;

import org.eclipse.jdt.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * This is a generated file.
 *
 * @author Welle
 */ 
@JsonIgnoreProperties(ignoreUnknown = true) 
public final class ServletMapping {

	@Nullable
    private String cofaxAdmin;
	@Nullable
    private String cofaxCDS;
	@Nullable
    private String cofaxEmail;
	@Nullable
    private String fileServlet;
	@Nullable
    private String cofaxTools;

	/**
     * Empty Constructor.
     */
    public ServletMapping() {
    	// Nothing to do
    }

    /**
     * Filling Constructor.
     *
     * @param cofaxAdminParam String
     * @param cofaxCDSParam String
     * @param cofaxEmailParam String
     * @param fileServletParam String
     * @param cofaxToolsParam String
     */
    public ServletMapping(@Nullable final String cofaxAdminParam, @Nullable final String cofaxCDSParam, @Nullable final String cofaxEmailParam, @Nullable final String fileServletParam, @Nullable final String cofaxToolsParam) {
        this.cofaxAdmin = cofaxAdminParam;
        this.cofaxCDS = cofaxCDSParam;
        this.cofaxEmail = cofaxEmailParam;
        this.fileServlet = fileServletParam;
        this.cofaxTools = cofaxToolsParam;
    }
    
    /**
     * Get value of cofaxAdmin.
     *
     * @return String value of cofaxAdmin
     */
    @Nullable
    public final String getCofaxAdmin() {
        return this.cofaxAdmin;
    }
    
    /**
     * Get value of cofaxCDS.
     *
     * @return String value of cofaxCDS
     */
    @Nullable
    public final String getCofaxCDS() {
        return this.cofaxCDS;
    }
    
    /**
     * Get value of cofaxEmail.
     *
     * @return String value of cofaxEmail
     */
    @Nullable
    public final String getCofaxEmail() {
        return this.cofaxEmail;
    }
    
    /**
     * Get value of fileServlet.
     *
     * @return String value of fileServlet
     */
    @Nullable
    public final String getFileServlet() {
        return this.fileServlet;
    }
    
    /**
     * Get value of cofaxTools.
     *
     * @return String value of cofaxTools
     */
    @Nullable
    public final String getCofaxTools() {
        return this.cofaxTools;
    }

    
    /**
     * Set value of cofaxAdmin.
     *
     * @param cofaxAdminParam
     */
    public final void setCofaxAdmin(@Nullable final String cofaxAdminParam) {
        this.cofaxAdmin = cofaxAdminParam;
    }
    
    /**
     * Set value of cofaxCDS.
     *
     * @param cofaxCDSParam
     */
    public final void setCofaxCDS(@Nullable final String cofaxCDSParam) {
        this.cofaxCDS = cofaxCDSParam;
    }
    
    /**
     * Set value of cofaxEmail.
     *
     * @param cofaxEmailParam
     */
    public final void setCofaxEmail(@Nullable final String cofaxEmailParam) {
        this.cofaxEmail = cofaxEmailParam;
    }
    
    /**
     * Set value of fileServlet.
     *
     * @param fileServletParam
     */
    public final void setFileServlet(@Nullable final String fileServletParam) {
        this.fileServlet = fileServletParam;
    }
    
    /**
     * Set value of cofaxTools.
     *
     * @param cofaxToolsParam
     */
    public final void setCofaxTools(@Nullable final String cofaxToolsParam) {
        this.cofaxTools = cofaxToolsParam;
    }
}
