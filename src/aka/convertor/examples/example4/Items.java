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
public final class Items {

	@Nullable
    private AccessControl accessControl;
	@Nullable
    private Thumbnail thumbnail;
	@Nullable
    private Double rating;
	@Nullable
    private String description;
	@Nullable
    private String aspectRatio;
	@Nullable
    private String title;
	@Nullable
    private Integer ratingCount;
	@Nullable
    private Content content;
	@NonNull
    private List<String> tags = new ArrayList<>();
	@Nullable
    private Integer commentCount;
	@Nullable
    private Integer duration;
	@Nullable
    private String uploader;
	@Nullable
    private String uploaded;
	@Nullable
    private String id;
	@Nullable
    private Integer viewCount;
	@Nullable
    private String category;
	@Nullable
    private String updated;
	@Nullable
    private Player player;
	@Nullable
    private Integer favoriteCount;
	@Nullable
    private Status status;

	/**
     * Empty Constructor.
     */
    public Items() {
    	// Nothing to do
    }

    /**
     * Filling Constructor.
     *
     * @param accessControlParam AccessControl
     * @param thumbnailParam Thumbnail
     * @param ratingParam Double
     * @param descriptionParam String
     * @param aspectRatioParam String
     * @param titleParam String
     * @param ratingCountParam Integer
     * @param contentParam Content
     * @param tagsParam List<String
     * @param commentCountParam Integer
     * @param durationParam Integer
     * @param uploaderParam String
     * @param uploadedParam String
     * @param idParam String
     * @param viewCountParam Integer
     * @param categoryParam String
     * @param updatedParam String
     * @param playerParam Player
     * @param favoriteCountParam Integer
     * @param statusParam Status
     */
    public Items(@Nullable final AccessControl accessControlParam, @Nullable final Thumbnail thumbnailParam, @Nullable final Double ratingParam, @Nullable final String descriptionParam, @Nullable final String aspectRatioParam, @Nullable final String titleParam, @Nullable final Integer ratingCountParam, @Nullable final Content contentParam, @NonNull final List<String> tagsParam, @Nullable final Integer commentCountParam, @Nullable final Integer durationParam, @Nullable final String uploaderParam, @Nullable final String uploadedParam, @Nullable final String idParam, @Nullable final Integer viewCountParam, @Nullable final String categoryParam, @Nullable final String updatedParam, @Nullable final Player playerParam, @Nullable final Integer favoriteCountParam, @Nullable final Status statusParam) {
        this.accessControl = accessControlParam;
        this.thumbnail = thumbnailParam;
        this.rating = ratingParam;
        this.description = descriptionParam;
        this.aspectRatio = aspectRatioParam;
        this.title = titleParam;
        this.ratingCount = ratingCountParam;
        this.content = contentParam;
        this.tags = tagsParam;
        this.commentCount = commentCountParam;
        this.duration = durationParam;
        this.uploader = uploaderParam;
        this.uploaded = uploadedParam;
        this.id = idParam;
        this.viewCount = viewCountParam;
        this.category = categoryParam;
        this.updated = updatedParam;
        this.player = playerParam;
        this.favoriteCount = favoriteCountParam;
        this.status = statusParam;
    }
    
    /**
     * Get value of accessControl.
     *
     * @return AccessControl value of accessControl
     */
    @Nullable
    public final AccessControl getAccessControl() {
        return this.accessControl;
    }
    
    /**
     * Get value of thumbnail.
     *
     * @return Thumbnail value of thumbnail
     */
    @Nullable
    public final Thumbnail getThumbnail() {
        return this.thumbnail;
    }
    
    /**
     * Get value of rating.
     *
     * @return Double value of rating
     */
    @Nullable
    public final Double getRating() {
        return this.rating;
    }
    
    /**
     * Get value of description.
     *
     * @return String value of description
     */
    @Nullable
    public final String getDescription() {
        return this.description;
    }
    
    /**
     * Get value of aspectRatio.
     *
     * @return String value of aspectRatio
     */
    @Nullable
    public final String getAspectRatio() {
        return this.aspectRatio;
    }
    
    /**
     * Get value of title.
     *
     * @return String value of title
     */
    @Nullable
    public final String getTitle() {
        return this.title;
    }
    
    /**
     * Get value of ratingCount.
     *
     * @return Integer value of ratingCount
     */
    @Nullable
    public final Integer getRatingCount() {
        return this.ratingCount;
    }
    
    /**
     * Get value of content.
     *
     * @return Content value of content
     */
    @Nullable
    public final Content getContent() {
        return this.content;
    }
    
    /**
     * Get value of tags.
     *
     * @return List<String> value of tags
     */
    @NonNull
    public final List<String> getTags() {
        return this.tags;
    }
    
    /**
     * Get value of commentCount.
     *
     * @return Integer value of commentCount
     */
    @Nullable
    public final Integer getCommentCount() {
        return this.commentCount;
    }
    
    /**
     * Get value of duration.
     *
     * @return Integer value of duration
     */
    @Nullable
    public final Integer getDuration() {
        return this.duration;
    }
    
    /**
     * Get value of uploader.
     *
     * @return String value of uploader
     */
    @Nullable
    public final String getUploader() {
        return this.uploader;
    }
    
    /**
     * Get value of uploaded.
     *
     * @return String value of uploaded
     */
    @Nullable
    public final String getUploaded() {
        return this.uploaded;
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
     * Get value of viewCount.
     *
     * @return Integer value of viewCount
     */
    @Nullable
    public final Integer getViewCount() {
        return this.viewCount;
    }
    
    /**
     * Get value of category.
     *
     * @return String value of category
     */
    @Nullable
    public final String getCategory() {
        return this.category;
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
     * Get value of player.
     *
     * @return Player value of player
     */
    @Nullable
    public final Player getPlayer() {
        return this.player;
    }
    
    /**
     * Get value of favoriteCount.
     *
     * @return Integer value of favoriteCount
     */
    @Nullable
    public final Integer getFavoriteCount() {
        return this.favoriteCount;
    }
    
    /**
     * Get value of status.
     *
     * @return Status value of status
     */
    @Nullable
    public final Status getStatus() {
        return this.status;
    }

    
    /**
     * Set value of accessControl.
     *
     * @param accessControlParam
     */
    public final void setAccessControl(@Nullable final AccessControl accessControlParam) {
        this.accessControl = accessControlParam;
    }
    
    /**
     * Set value of thumbnail.
     *
     * @param thumbnailParam
     */
    public final void setThumbnail(@Nullable final Thumbnail thumbnailParam) {
        this.thumbnail = thumbnailParam;
    }
    
    /**
     * Set value of rating.
     *
     * @param ratingParam
     */
    public final void setRating(@Nullable final Double ratingParam) {
        this.rating = ratingParam;
    }
    
    /**
     * Set value of description.
     *
     * @param descriptionParam
     */
    public final void setDescription(@Nullable final String descriptionParam) {
        this.description = descriptionParam;
    }
    
    /**
     * Set value of aspectRatio.
     *
     * @param aspectRatioParam
     */
    public final void setAspectRatio(@Nullable final String aspectRatioParam) {
        this.aspectRatio = aspectRatioParam;
    }
    
    /**
     * Set value of title.
     *
     * @param titleParam
     */
    public final void setTitle(@Nullable final String titleParam) {
        this.title = titleParam;
    }
    
    /**
     * Set value of ratingCount.
     *
     * @param ratingCountParam
     */
    public final void setRatingCount(@Nullable final Integer ratingCountParam) {
        this.ratingCount = ratingCountParam;
    }
    
    /**
     * Set value of content.
     *
     * @param contentParam
     */
    public final void setContent(@Nullable final Content contentParam) {
        this.content = contentParam;
    }
    
    /**
     * Set value of tags.
     *
     * @param tagsParam
     */
    public final void getTags(@NonNull final List<String> tagsParam) {
        this.tags = tagsParam;
    }
    
    /**
     * Set value of commentCount.
     *
     * @param commentCountParam
     */
    public final void setCommentCount(@Nullable final Integer commentCountParam) {
        this.commentCount = commentCountParam;
    }
    
    /**
     * Set value of duration.
     *
     * @param durationParam
     */
    public final void setDuration(@Nullable final Integer durationParam) {
        this.duration = durationParam;
    }
    
    /**
     * Set value of uploader.
     *
     * @param uploaderParam
     */
    public final void setUploader(@Nullable final String uploaderParam) {
        this.uploader = uploaderParam;
    }
    
    /**
     * Set value of uploaded.
     *
     * @param uploadedParam
     */
    public final void setUploaded(@Nullable final String uploadedParam) {
        this.uploaded = uploadedParam;
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
     * Set value of viewCount.
     *
     * @param viewCountParam
     */
    public final void setViewCount(@Nullable final Integer viewCountParam) {
        this.viewCount = viewCountParam;
    }
    
    /**
     * Set value of category.
     *
     * @param categoryParam
     */
    public final void setCategory(@Nullable final String categoryParam) {
        this.category = categoryParam;
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
     * Set value of player.
     *
     * @param playerParam
     */
    public final void setPlayer(@Nullable final Player playerParam) {
        this.player = playerParam;
    }
    
    /**
     * Set value of favoriteCount.
     *
     * @param favoriteCountParam
     */
    public final void setFavoriteCount(@Nullable final Integer favoriteCountParam) {
        this.favoriteCount = favoriteCountParam;
    }
    
    /**
     * Set value of status.
     *
     * @param statusParam
     */
    public final void setStatus(@Nullable final Status statusParam) {
        this.status = statusParam;
    }
}
