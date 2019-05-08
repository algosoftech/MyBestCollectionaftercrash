package algosoftapp.youtube.com.ModelClass;

/**
 * Created by abc on 07/01/2019.
 */

public class VideolistModelClass  {
    private String VideoId;
    private String SubCategoryId;
    private String VideoTitle;
    private String link;

    public String getVideoImage() {
        return VideoImage;
    }

    public void setVideoImage(String videoImage) {
        VideoImage = videoImage;
    }

    private String VideoImage;

    public String getVideoId() {
        return VideoId;
    }

    public void setVideoId(String cat_id) {
        VideoId = cat_id;
    }

    public String getSubCategoryId() {
        return SubCategoryId;
    }

    public void setSubCategoryId(String sub_cat_id) {
        SubCategoryId = sub_cat_id;
    }

    public String getVideoTitle() {
        return VideoTitle;
    }

    public void setVideoTitle(String category) {
        VideoTitle = category;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String video_link) {
        this.link = video_link;
    }
}
