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
public final class Servlet {

    @JsonProperty("init-param")
	@Nullable
    private InitParam initParam;
    @JsonProperty("servlet-name")
	@Nullable
    private String servletName;
    @JsonProperty("servlet-class")
	@Nullable
    private String servletClass;

	/**
     * Empty Constructor.
     */
    public Servlet() {
    	// Nothing to do
    }

    /**
     * Filling Constructor.
     *
     * @param initParamParam InitParam
     * @param servletNameParam String
     * @param servletClassParam String
     */
    public Servlet(@Nullable final InitParam initParamParam, @Nullable final String servletNameParam, @Nullable final String servletClassParam) {
        this.initParam = initParamParam;
        this.servletName = servletNameParam;
        this.servletClass = servletClassParam;
    }
    
    /**
     * Get value of initParam.
     *
     * @return InitParam value of initParam
     */
    @Nullable
    public final InitParam getInitParam() {
        return this.initParam;
    }
    
    /**
     * Get value of servletName.
     *
     * @return String value of servletName
     */
    @Nullable
    public final String getServletName() {
        return this.servletName;
    }
    
    /**
     * Get value of servletClass.
     *
     * @return String value of servletClass
     */
    @Nullable
    public final String getServletClass() {
        return this.servletClass;
    }

    
    /**
     * Set value of initParam.
     *
     * @param initParamParam
     */
    public final void setInitParam(@Nullable final InitParam initParamParam) {
        this.initParam = initParamParam;
    }
    
    /**
     * Set value of servletName.
     *
     * @param servletNameParam
     */
    public final void setServletName(@Nullable final String servletNameParam) {
        this.servletName = servletNameParam;
    }
    
    /**
     * Set value of servletClass.
     *
     * @param servletClassParam
     */
    public final void setServletClass(@Nullable final String servletClassParam) {
        this.servletClass = servletClassParam;
    }
}
