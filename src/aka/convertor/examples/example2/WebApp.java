package aka.convertor.examples.example2;

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
public final class WebApp {

    @JsonProperty("servlet-mapping")
	@Nullable
    private ServletMapping servletMapping;
	@Nullable
    private Taglib taglib;
	@NonNull
    private List<Servlet> servlet = new ArrayList<>();

	/**
     * Empty Constructor.
     */
    public WebApp() {
    	// Nothing to do
    }

    /**
     * Filling Constructor.
     *
     * @param servletMappingParam ServletMapping
     * @param taglibParam Taglib
     * @param servletParam List<Servlet
     */
    public WebApp(@Nullable final ServletMapping servletMappingParam, @Nullable final Taglib taglibParam, @NonNull final List<Servlet> servletParam) {
        this.servletMapping = servletMappingParam;
        this.taglib = taglibParam;
        this.servlet = servletParam;
    }
    
    /**
     * Get value of servletMapping.
     *
     * @return ServletMapping value of servletMapping
     */
    @Nullable
    public final ServletMapping getServletMapping() {
        return this.servletMapping;
    }
    
    /**
     * Get value of taglib.
     *
     * @return Taglib value of taglib
     */
    @Nullable
    public final Taglib getTaglib() {
        return this.taglib;
    }
    
    /**
     * Get value of servlet.
     *
     * @return List<Servlet> value of servlet
     */
    @NonNull
    public final List<Servlet> getServlet() {
        return this.servlet;
    }

    
    /**
     * Set value of servletMapping.
     *
     * @param servletMappingParam
     */
    public final void setServletMapping(@Nullable final ServletMapping servletMappingParam) {
        this.servletMapping = servletMappingParam;
    }
    
    /**
     * Set value of taglib.
     *
     * @param taglibParam
     */
    public final void setTaglib(@Nullable final Taglib taglibParam) {
        this.taglib = taglibParam;
    }
    
    /**
     * Set value of servlet.
     *
     * @param servletParam
     */
    public final void getServlet(@NonNull final List<Servlet> servletParam) {
        this.servlet = servletParam;
    }
}
