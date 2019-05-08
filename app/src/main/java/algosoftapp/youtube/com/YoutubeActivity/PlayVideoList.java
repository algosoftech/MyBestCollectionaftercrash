package algosoftapp.youtube.com.YoutubeActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import algosoftapp.youtube.SpaceTokenizer;
import algosoftapp.youtube.com.Adapter.PlayVideoAdapter;
import algosoftapp.youtube.com.ModelClass.CategoryModelClass;
import algosoftapp.youtube.com.ModelClass.VideolistModelClass;
import algosoftapp.youtube.com.R;
import algosoftapp.youtube.com.Service.Network;
import algosoftapp.youtube.com.Service.WebServiceClass;
import algosoftapp.youtube.com.utility.KeyGenerationClass;

public class PlayVideoList extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private PlayVideoAdapter adapter;
    private ProgressBar progressBar;
    private Toolbar toolbar;
    private TextView hint;
    private AdView mAdView;
    private MenuItem menuItem;
    private SearchView searchView;
    private ImageView imageView;

    ArrayList<VideolistModelClass> Video_list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video_list);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        imageView=(ImageView)findViewById(R.id.Image_back);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        MobileAds.initialize(this, "ca-app-pub-6911912195923365~3446539191");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        TextView texttitle = (TextView) findViewById(R.id.Titlebar);
        Intent startingIntent = getIntent();
        String Id = startingIntent.getStringExtra("subcategoryId");
        String title=startingIntent.getStringExtra("SubcategoryTitle");
        texttitle.setText(title);

        Log.e("IntentData",title+""+Id);

//        ArrayAdapter<String> adapterlist = new ArrayAdapter<String>(this,
//                android.R.layout.simple_dropdown_item_1line, COUNTRIES);
//        multiAutoCompleteTextView=(MultiAutoCompleteTextView) findViewById(R.id.Search_Hint);
//        multiAutoCompleteTextView.setAdapter(adapterlist);
//        multiAutoCompleteTextView.setThreshold(2);
//        multiAutoCompleteTextView.setTokenizer(new SpaceTokenizer());





//        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp));
//        toolbar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });



        recyclerView = (RecyclerView) findViewById(R.id.RecyclerPlayList);
        recyclerView.setHasFixedSize(false);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        if (Network.isNetworkStatusAvailable(this)) {
            new PLAYVIDEOAPI().execute(KeyGenerationClass.getEncryptedKey(), Id);

        } else {
            Toast.makeText(this, "Please Check Your Connection", Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.searchview,menu);
        menuItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) menuItem.getActionView();
        searchView.setIconified(true);
        searchView.setMaxWidth(Integer.MAX_VALUE);
        changeSearchViewTextColor(searchView);
        searchView.setOnQueryTextListener(this);
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                ArrayList<VideolistModelClass> Filter= new ArrayList<>();
//                for( VideolistModelClass categoryModelClass : Video_list){
//                    String name=categoryModelClass.getVideoTitle().toLowerCase();
//                    if(name.contains(newText)){
//                        Filter.add(categoryModelClass);
//                    }
//
//                }
//                adapter.setFilter(Filter);
//
//                return true;
//            }
//        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
    private void changeSearchViewTextColor(View view) {
        if (view != null) {
            if (view instanceof TextView) {
                ((TextView) view).setTextColor(Color.WHITE);
                return;
            } else if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    changeSearchViewTextColor(viewGroup.getChildAt(i));
                }
            }
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        ArrayList<VideolistModelClass> Filter= new ArrayList<>();
                for( VideolistModelClass categoryModelClass : Video_list){
                    String name=categoryModelClass.getVideoTitle().toLowerCase();
                    if(name.contains(newText)){
                        Filter.add(categoryModelClass);
                    }

                }
                adapter.setFilter(Filter);
        return true;
    }

    public class PLAYVIDEOAPI extends AsyncTask<String, String, JSONObject> {
        private JSONObject jsonobject;
        private JSONArray videolist;

        @Override
        protected JSONObject doInBackground(String... strings) {
            WebServiceClass webServicesUrlClass = new WebServiceClass();
            ArrayList<NameValuePair> Videolist = new ArrayList<>();
            try {
                Videolist.add(new BasicNameValuePair("api_key", strings[0]));
                Videolist.add(new BasicNameValuePair("subcategory_id", strings[1]));
                jsonobject = webServicesUrlClass.videolistMethod(Videolist);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return jsonobject;
        }


        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            if (jsonObject != null) {
                try {
                    JSONObject result = jsonObject.getJSONObject("result");
                    videolist = result.getJSONArray("videolist");

                    for (int i = 0; i < videolist.length(); i++) {
                        VideolistModelClass videolistdetails = new VideolistModelClass();
                        JSONObject itemvalue = videolist.getJSONObject(i);
                        String cat_id = itemvalue.getString("video_id");
                        String category = itemvalue.getString("video_title");
                        String sub_cat_id = itemvalue.getString("sub_categoryid");
                        String video_link=itemvalue.getString("link");
                        String video_image=itemvalue.getString("video_thumbnail");
                        videolistdetails.setVideoId(cat_id);
                        videolistdetails.setVideoTitle(category);
                        videolistdetails.setSubCategoryId(sub_cat_id);
                        videolistdetails.setVideoImage(video_image);
                        videolistdetails.setLink(video_link);

                        Video_list.add(videolistdetails);

                    }
                    adapter = new PlayVideoAdapter(PlayVideoList.this, Video_list);
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }


    }
}
