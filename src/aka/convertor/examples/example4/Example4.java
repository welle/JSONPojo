package aka.convertor.examples.example4;

import org.eclipse.jdt.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * This is a generated file.
 *
 * @author Welle
 */ 
@JsonIgnoreProperties(ignoreUnknown = true) 
public final class Example4 {

	@Nullable
    private Menu menu;

	/**
     * Empty Constructor.
     */
    public Example4() {
    	// Nothing to do
    }

    /**
     * Filling Constructor.
     *
     * @param menuParam Menu
     */
    public Example4(@Nullable final Menu menuParam) {
        this.menu = menuParam;
    }
    
    /**
     * Get value of menu.
     *
     * @return Menu value of menu
     */
    @Nullable
    public final Menu getMenu() {
        return this.menu;
    }

    
    /**
     * Set value of menu.
     *
     * @param menuParam
     */
    public final void setMenu(@Nullable final Menu menuParam) {
        this.menu = menuParam;
    }
}
