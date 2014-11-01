package aka.convertor.examples.example3;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import aka.convertor.examples.example3.deserializers.DateYearMonthDayTHourDashDeserializer;
import aka.convertor.examples.example3.deserializers.DateYearMonthDayTHourDashTimeZoneDeserializer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * This is a generated file.
 *
 * @author Welle
 */ 
@JsonIgnoreProperties(ignoreUnknown = true) 
public final class Data {

    @JsonProperty("created_time")
    @JsonDeserialize(using = DateYearMonthDayTHourDashDeserializer.class)
    private Date createdTime;
    @JsonProperty("updated_time")
    @JsonDeserialize(using = DateYearMonthDayTHourDashTimeZoneDeserializer.class)
    private Date updatedTime;
	@Nullable
    private From from;
	@Nullable
    private String id;
	@Nullable
    private String message;
	@Nullable
    private String type;
	@NonNull
    private List<Actions> actions = new ArrayList<>();

	/**
     * Empty Constructor.
     */
    public Data() {
    	// Nothing to do
    }

    /**
     * Filling Constructor.
     *
     * @param createdTimeParam Date
     * @param updatedTimeParam Date
     * @param fromParam From
     * @param idParam String
     * @param messageParam String
     * @param typeParam String
     * @param actionsParam List<Actions
     */
    public Data(@Nullable final Date createdTimeParam, @Nullable final Date updatedTimeParam, @Nullable final From fromParam, @Nullable final String idParam, @Nullable final String messageParam, @Nullable final String typeParam, @NonNull final List<Actions> actionsParam) {
        this.createdTime = createdTimeParam;
        this.updatedTime = updatedTimeParam;
        this.from = fromParam;
        this.id = idParam;
        this.message = messageParam;
        this.type = typeParam;
        this.actions = actionsParam;
    }
    
    /**
     * Get value of createdTime.
     *
     * @return Date value of createdTime
     */
    @Nullable
    public final Date getCreatedTime() {
        return this.createdTime;
    }
    
    /**
     * Get value of updatedTime.
     *
     * @return Date value of updatedTime
     */
    @Nullable
    public final Date getUpdatedTime() {
        return this.updatedTime;
    }
    
    /**
     * Get value of from.
     *
     * @return From value of from
     */
    @Nullable
    public final From getFrom() {
        return this.from;
    }
    
    /**
     * Get value of id.
     *
     * @return String value of id
     */
    @Nullable
    public final String getId() {
        return this.id;
    }
    
    /**
     * Get value of message.
     *
     * @return String value of message
     */
    @Nullable
    public final String getMessage() {
        return this.message;
    }
    
    /**
     * Get value of type.
     *
     * @return String value of type
     */
    @Nullable
    public final String getType() {
        return this.type;
    }
    
    /**
     * Get value of actions.
     *
     * @return List<Actions> value of actions
     */
    @NonNull
    public final List<Actions> getActions() {
        return this.actions;
    }

    
    /**
     * Set value of createdTime.
     *
     * @param createdTimeParam
     */
    public final void setCreatedTime(@Nullable final Date createdTimeParam) {
        this.createdTime = createdTimeParam;
    }
    
    /**
     * Set value of updatedTime.
     *
     * @param updatedTimeParam
     */
    public final void setUpdatedTime(@Nullable final Date updatedTimeParam) {
        this.updatedTime = updatedTimeParam;
    }
    
    /**
     * Set value of from.
     *
     * @param fromParam
     */
    public final void setFrom(@Nullable final From fromParam) {
        this.from = fromParam;
    }
    
    /**
     * Set value of id.
     *
     * @param idParam
     */
    public final void setId(@Nullable final String idParam) {
        this.id = idParam;
    }
    
    /**
     * Set value of message.
     *
     * @param messageParam
     */
    public final void setMessage(@Nullable final String messageParam) {
        this.message = messageParam;
    }
    
    /**
     * Set value of type.
     *
     * @param typeParam
     */
    public final void setType(@Nullable final String typeParam) {
        this.type = typeParam;
    }
    
    /**
     * Set value of actions.
     *
     * @param actionsParam
     */
    public final void getActions(@NonNull final List<Actions> actionsParam) {
        this.actions = actionsParam;
    }
}
