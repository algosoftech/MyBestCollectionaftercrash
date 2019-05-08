package algosoftapp.youtube.com.Service;

import org.apache.http.NameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;


public class WebServiceClass {

    public static final String BASE_URL="http://algosoftech.in/demo/myvideoapp/api/";
    public static final String LOGIN_URL=BASE_URL+"register";

    public static final String CONTACTUS_URL=BASE_URL+"add-contactus-data";
    public static final String CATAGORY_URL=BASE_URL+"get-category-list";
    public static final String SUB_CATEGORY_URL=BASE_URL+"get-sub-category-list";
    public static final String VIDEO_LIST=BASE_URL+"get-video-list";
    public static  final String PRIVACY_URL=BASE_URL+"get-cms-data";
    public static final String TERMSCONDITION_URl=BASE_URL+"get-cms-data";
    public static final String UPLOADED_VIDEO=BASE_URL+"get-all-author-video-list-by-id";
    public static final String NOTIFICATION_URL=BASE_URL+"get-notification-list";
    public static final String LIKE_URL=BASE_URL+"add-favorite-video";
    public static final String FAVOURITES_URL=BASE_URL+"get-favorite-video-list-id";

    HttpResponseClass httpResponseClass=new HttpResponseClass();

    public JSONObject registerMethod(ArrayList<NameValuePair> params){
        return httpResponseClass.getResponseByPost(CONTACTUS_URL,params);
    }

    public JSONObject loginMethod(ArrayList<NameValuePair> parameter){
        return httpResponseClass.getResponseByPost(LOGIN_URL,parameter);
    }
    public JSONObject categoryMethod(ArrayList<NameValuePair> categoryparams){
        return httpResponseClass.getResponseByPost(CATAGORY_URL,categoryparams);
    }
    public JSONObject subcategoryMethod(ArrayList<NameValuePair>subparams){
        return httpResponseClass.getResponseByPost(SUB_CATEGORY_URL,subparams);
    }
    public JSONObject videolistMethod(ArrayList<NameValuePair>videoparams){
        return httpResponseClass.getResponseByPost(VIDEO_LIST,videoparams);
    }
    public JSONObject privacyMethod(ArrayList<NameValuePair>privacyparam){
        return httpResponseClass.getResponseByPost(PRIVACY_URL,privacyparam);
    }
    public JSONObject termconditionMethod(ArrayList<NameValuePair>termparam){
        return httpResponseClass.getResponseByPost(TERMSCONDITION_URl,termparam);
    }
    public JSONObject uploadedMethod(ArrayList<NameValuePair>uploadparam){
        return httpResponseClass.getResponseByPost(UPLOADED_VIDEO,uploadparam);
    }
    public JSONObject notificationMethod(ArrayList<NameValuePair>notificationparam){
        return httpResponseClass.getResponseByPost(NOTIFICATION_URL,notificationparam);
    }
    public JSONObject favouritesMethod(ArrayList<NameValuePair>favouritesparam){
        return httpResponseClass.getResponseByPost(LIKE_URL,favouritesparam);
    }
    public JSONObject AllFavouritesMethod(ArrayList<NameValuePair>listparam){
        return httpResponseClass.getResponseByPost(FAVOURITES_URL,listparam);
    }


}
