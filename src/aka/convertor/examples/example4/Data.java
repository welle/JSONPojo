package aka.convertor.examples.example4;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * This is a generated file.
 *
 * @author Welle
 */ 
@JsonIgnoreProperties(ignoreUnknown = true) 
public final class Data {

	@Nullable
    private Integer totalItems;
	@Nullable
    private Integer startIndex;
	@Nullable
    private Integer itemsPerPage;
	@Nullable
    private String updated;
	@NonNull
    private List<Items> items = new ArrayList<>();

	/**
     * Empty Constructor.
     */
    public Data() {
    	// Nothing to do
    }

    /**
     * Filling Constructor.
     *
     * @param totalItemsParam Integer
     * @param startIndexParam Integer
     * @param itemsPerPageParam Integer
     * @param updatedParam String
     * @param itemsParam List<Items
     */
    public Data(@Nullable final Integer totalItemsParam, @Nullable final Integer startIndexParam, @Nullable final Integer itemsPerPageParam, @Nullable final String updatedParam, @NonNull final List<Items> itemsParam) {
        this.totalItems = totalItemsParam;
        this.startIndex = startIndexParam;
        this.itemsPerPage = itemsPerPageParam;
        this.updated = updatedParam;
        this.items = itemsParam;
    }
    
    /**
     * Get value of totalItems.
     *
     * @return Integer value of totalItems
     */
    @Nullable
    public final Integer getTotalItems() {
        return this.totalItems;
    }
    
    /**
     * Get value of startIndex.
     *
     * @return Integer value of startIndex
     */
    @Nullable
    public final Integer getStartIndex() {
        return this.startIndex;
    }
    
    /**
     * Get value of itemsPerPage.
     *
     * @return Integer value of itemsPerPage
     */
    @Nullable
    public final Integer getItemsPerPage() {
        return this.itemsPerPage;
    }
    
    /**
     * Get value of updated.
     *
     * @return String value of updated
     */
    @Nullable
    public final String getUpdated() {
        return this.updated;
    }
    
    /**
     * Get value of items.
     *
     * @return List<Items> value of items
     */
    @NonNull
    public final List<Items> getItems() {
        return this.items;
    }

    
    /**
     * Set value of totalItems.
     *
     * @param totalItemsParam
     */
    public final void setTotalItems(@Nullable final Integer totalItemsParam) {
        this.totalItems = totalItemsParam;
    }
    
    /**
     * Set value of startIndex.
     *
     * @param startIndexParam
     */
    public final void setStartIndex(@Nullable final Integer startIndexParam) {
        this.startIndex = startIndexParam;
    }
    
    /**
     * Set value of itemsPerPage.
     *
     * @param itemsPerPageParam
     */
    public final void setItemsPerPage(@Nullable final Integer itemsPerPageParam) {
        this.itemsPerPage = itemsPerPageParam;
    }
    
    /**
     * Set value of updated.
     *
     * @param updatedParam
     */
    public final void setUpdated(@Nullable final String updatedParam) {
        this.updated = updatedParam;
    }
    
    /**
     * Set value of items.
     *
     * @param itemsParam
     */
    public final void getItems(@NonNull final List<Items> itemsParam) {
        this.items = itemsParam;
    }
}
