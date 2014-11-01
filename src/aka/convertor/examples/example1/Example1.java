package aka.convertor.examples.example1;

import org.eclipse.jdt.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * This is a generated file.
 *
 * @author Welle
 */ 
@JsonIgnoreProperties(ignoreUnknown = true) 
public final class Example1 {

	@Nullable
    private Glossary glossary;

	/**
     * Empty Constructor.
     */
    public Example1() {
    	// Nothing to do
    }

    /**
     * Filling Constructor.
     *
     * @param glossaryParam Glossary
     */
    public Example1(@Nullable final Glossary glossaryParam) {
        this.glossary = glossaryParam;
    }
    
    /**
     * Get value of glossary.
     *
     * @return Glossary value of glossary
     */
    @Nullable
    public final Glossary getGlossary() {
        return this.glossary;
    }

    
    /**
     * Set value of glossary.
     *
     * @param glossaryParam
     */
    public final void setGlossary(@Nullable final Glossary glossaryParam) {
        this.glossary = glossaryParam;
    }
}
