package algosoftapp.youtube.com.ModelClass;

/**
 * Created by abc on 03/01/2019.
 */

public class CategoryModelClass {
    private String id;
    private String SubCategory;
    private String Image;
    private String CategoryId;

    public String getUploadedVideo() {
        return UploadedVideo;
    }

    public void setUploadedVideo(String uploadedVideo) {
        UploadedVideo = uploadedVideo;
    }

    private String UploadedVideo;

    public String getSubCategory(){
        return this.SubCategory;
    }
    public void setSubCategory(String cat_title){
         this.SubCategory=cat_title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Cat_image) {
        Image = Cat_image;
    }

    public String getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(String categoryId) {
        CategoryId = categoryId;
    }
}
