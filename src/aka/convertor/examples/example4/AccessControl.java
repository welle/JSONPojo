package aka.convertor.examples.example4;

import org.eclipse.jdt.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This is a generated file.
 *
 * @author Welle
 */ 
@JsonIgnoreProperties(ignoreUnknown = true) 
public final class AccessControl {

	@Nullable
    private String rate;
	@Nullable
    private String syndicate;
	@Nullable
    private String videoRespond;
	@Nullable
    private String comment;
	@Nullable
    private String commentVote;
	@Nullable
    private String embed;
    @JsonProperty("list")
	@Nullable
    private String listRes;

	/**
     * Empty Constructor.
     */
    public AccessControl() {
    	// Nothing to do
    }

    /**
     * Filling Constructor.
     *
     * @param rateParam String
     * @param syndicateParam String
     * @param videoRespondParam String
     * @param commentParam String
     * @param commentVoteParam String
     * @param embedParam String
     * @param listResParam String
     */
    public AccessControl(@Nullable final String rateParam, @Nullable final String syndicateParam, @Nullable final String videoRespondParam, @Nullable final String commentParam, @Nullable final String commentVoteParam, @Nullable final String embedParam, @Nullable final String listResParam) {
        this.rate = rateParam;
        this.syndicate = syndicateParam;
        this.videoRespond = videoRespondParam;
        this.comment = commentParam;
        this.commentVote = commentVoteParam;
        this.embed = embedParam;
        this.listRes = listResParam;
    }
    
    /**
     * Get value of rate.
     *
     * @return String value of rate
     */
    @Nullable
    public final String getRate() {
        return this.rate;
    }
    
    /**
     * Get value of syndicate.
     *
     * @return String value of syndicate
     */
    @Nullable
    public final String getSyndicate() {
        return this.syndicate;
    }
    
    /**
     * Get value of videoRespond.
     *
     * @return String value of videoRespond
     */
    @Nullable
    public final String getVideoRespond() {
        return this.videoRespond;
    }
    
    /**
     * Get value of comment.
     *
     * @return String value of comment
     */
    @Nullable
    public final String getComment() {
        return this.comment;
    }
    
    /**
     * Get value of commentVote.
     *
     * @return String value of commentVote
     */
    @Nullable
    public final String getCommentVote() {
        return this.commentVote;
    }
    
    /**
     * Get value of embed.
     *
     * @return String value of embed
     */
    @Nullable
    public final String getEmbed() {
        return this.embed;
    }
    
    /**
     * Get value of listRes.
     *
     * @return String value of listRes
     */
    @Nullable
    public final String getListRes() {
        return this.listRes;
    }

    
    /**
     * Set value of rate.
     *
     * @param rateParam
     */
    public final void setRate(@Nullable final String rateParam) {
        this.rate = rateParam;
    }
    
    /**
     * Set value of syndicate.
     *
     * @param syndicateParam
     */
    public final void setSyndicate(@Nullable final String syndicateParam) {
        this.syndicate = syndicateParam;
    }
    
    /**
     * Set value of videoRespond.
     *
     * @param videoRespondParam
     */
    public final void setVideoRespond(@Nullable final String videoRespondParam) {
        this.videoRespond = videoRespondParam;
    }
    
    /**
     * Set value of comment.
     *
     * @param commentParam
     */
    public final void setComment(@Nullable final String commentParam) {
        this.comment = commentParam;
    }
    
    /**
     * Set value of commentVote.
     *
     * @param commentVoteParam
     */
    public final void setCommentVote(@Nullable final String commentVoteParam) {
        this.commentVote = commentVoteParam;
    }
    
    /**
     * Set value of embed.
     *
     * @param embedParam
     */
    public final void setEmbed(@Nullable final String embedParam) {
        this.embed = embedParam;
    }
    
    /**
     * Set value of listRes.
     *
     * @param listResParam
     */
    public final void setListRes(@Nullable final String listResParam) {
        this.listRes = listResParam;
    }
}
