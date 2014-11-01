package aka.convertor.examples.example3;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * This is a generated file.
 *
 * @author Welle
 */ 
@JsonIgnoreProperties(ignoreUnknown = true) 
public final class Example3 {

	@NonNull
    private List<Data> data = new ArrayList<>();

	/**
     * Empty Constructor.
     */
    public Example3() {
    	// Nothing to do
    }

    /**
     * Filling Constructor.
     *
     * @param dataParam List<Data
     */
    public Example3(@NonNull final List<Data> dataParam) {
        this.data = dataParam;
    }
    
    /**
     * Get value of data.
     *
     * @return List<Data> value of data
     */
    @NonNull
    public final List<Data> getData() {
        return this.data;
    }

    
    /**
     * Set value of data.
     *
     * @param dataParam
     */
    public final void getData(@NonNull final List<Data> dataParam) {
        this.data = dataParam;
    }
}
