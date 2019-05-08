package algosoftapp.youtube.com.Fragment;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import algosoftapp.youtube.com.Adapter.HomeAdapter;
import algosoftapp.youtube.com.R;
import algosoftapp.youtube.com.Service.Network;
import algosoftapp.youtube.com.Service.WebServiceClass;
import algosoftapp.youtube.com.utility.CategoryListDetail;
import algosoftapp.youtube.com.utility.KeyGenerationClass;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment {
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private ArrayList<String> categorylist;
    private SwipeRefreshLayout swipeRefreshLayout;
    ArrayList<CategoryListDetail> cat_list = new ArrayList<>();

    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        categorylist = new ArrayList<>();
        categorylist.add("AutoMobiles");
        categorylist.add("Comedy");
        categorylist.add("Education");
        categorylist.add("Finance");
        categorylist.add("Motivation");
        categorylist.add("Nature");
        categorylist.add("Shopping");
        categorylist.add("Spiritual");
        categorylist.add("Tourism");

        swipeRefreshLayout=(SwipeRefreshLayout)view.findViewById(R.id.Refresh_Home);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);

            }
        });

        recyclerView = (RecyclerView) view.findViewById(R.id.RecyclerCategorylist);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        if (Network.isNetworkStatusAvailable(getActivity())) {
            new CategoryAPI().execute(KeyGenerationClass.getEncryptedKey());

        } else {
            Toast.makeText(getActivity(), "Please Check Your Connection", Toast.LENGTH_LONG).show();
        }
        return view;

    }


    public class CategoryAPI extends AsyncTask<String, String, JSONObject> {

        private JSONObject jsonobject;
        private JSONArray catlist;

        @Override
        protected JSONObject doInBackground(String... strings) {
            WebServiceClass webServicesUrlClass = new WebServiceClass();
            ArrayList<NameValuePair> Categorydata = new ArrayList<>();
            try {
                Categorydata.add(new BasicNameValuePair("api_key", strings[0]));
                jsonobject = webServicesUrlClass.categoryMethod(Categorydata);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return jsonobject;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
//            Log.e("APIRes", jsonObject.toString());
            if (jsonObject != null) {
                try {
                    JSONObject result = jsonObject.getJSONObject("result");
                    catlist = result.getJSONArray("catlist");

                    for(int i=0;i<catlist.length();i++){
                        CategoryListDetail catlistdetail = new CategoryListDetail();
                        JSONObject itemvalue = catlist.getJSONObject(i);
                        String cat_id = itemvalue.getString("category_id");
                        String category = itemvalue.getString("category");
                        String cat_image = itemvalue.getString("cat_thumbnail");
                        catlistdetail.setCategoryId(cat_id);
                        catlistdetail.setCategory(category);
                        catlistdetail.setCategoryImage(cat_image);

                        cat_list.add(catlistdetail);


                    }
                    adapter = new HomeAdapter(CategoryFragment.this, cat_list);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();



                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }

        }
    }
}







