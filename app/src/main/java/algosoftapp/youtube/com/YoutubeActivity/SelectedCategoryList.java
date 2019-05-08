package algosoftapp.youtube.com.YoutubeActivity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import algosoftapp.youtube.com.Adapter.CategoryAdapter;
import algosoftapp.youtube.com.Adapter.SelectedCategoryAdapter;
import algosoftapp.youtube.com.ModelClass.CategoryModelClass;
import algosoftapp.youtube.com.R;
import algosoftapp.youtube.com.Service.Network;
import algosoftapp.youtube.com.Service.WebServiceClass;
import algosoftapp.youtube.com.utility.KeyGenerationClass;

public class SelectedCategoryList extends AppCompatActivity implements SearchView.OnQueryTextListener{
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
//    private RecyclerView.Adapter adapter;
    ArrayList<CategoryModelClass> Sub_catlist = new ArrayList<>();
    private String SubCategoryId;
    private SwipeRefreshLayout swipeRefreshLayout;
    private MenuItem menuItem;
    private SearchView searchView;
    private SelectedCategoryAdapter adapter;



//    ArrayList Selected = new ArrayList<>(Arrays.asList("Sandeep Maheshwari", " Deepak Chopra", "Zig Ziglar", "Robin Sharma", "Yogesh Chabria"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_category_list);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        swipeRefreshLayout=(SwipeRefreshLayout)findViewById(R.id.Refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        TextView titlebar = (TextView) findViewById(R.id.Titlebar);
        Intent startingIntent = getIntent();
        String title = startingIntent.getStringExtra("title");
        titlebar.setText(title);
        String CategoryId = startingIntent.getStringExtra("category_id");
        String SubCategoryId = startingIntent.getStringExtra("subcategory_id");
       

        Log.e("IntentData", title + ", " + CategoryId);
//        Toast.makeText(this, "getvalue" + title, Toast.LENGTH_LONG).show();


        ImageView Back = (ImageView) findViewById(R.id.Back_Home);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.RecyclerList);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

//        if (Network.isNetworkStatusAvailable(this)) {
            new CategorylistAPI().execute(KeyGenerationClass.getEncryptedKey(), CategoryId);


//        } else {
//            Toast.makeText(this, "Please Check Your Connection", Toast.LENGTH_LONG).show();
//        }
//
//
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
//                ArrayList<CategoryModelClass> Filter= new ArrayList<>();
//                for(CategoryModelClass categoryModelClass : Sub_catlist){
//                    String name=categoryModelClass.getSubCategory().toLowerCase();
//                    if(name.contains(query)){
//                        Filter.add(categoryModelClass);
//                    }
//
//                }
//                adapter.
//                return true;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//
//                return false;
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
        ArrayList<CategoryModelClass> Filter= new ArrayList<>();
        for(CategoryModelClass categoryModelClass : Sub_catlist){
            String name=categoryModelClass.getSubCategory().toLowerCase();
            if(name.contains(newText)){
                Filter.add(categoryModelClass);
            }

        }
        adapter.setFilter(Filter);

        return true;
    }



    public class ListAPI extends AsyncTask<String, String, JSONObject> {
        private JSONObject jsonobject1;
        private JSONArray SubSubcatlist;

        @Override
        protected JSONObject doInBackground(String... strings) {
            WebServiceClass webServicesUrlClass = new WebServiceClass();
            ArrayList<NameValuePair> SubCategorylistdata = new ArrayList<>();
            try {
                SubCategorylistdata.add(new BasicNameValuePair("api_key", strings[0]));
                SubCategorylistdata.add(new BasicNameValuePair("subcategory_id", strings[1]));
                jsonobject1 = webServicesUrlClass.subcategoryMethod(SubCategorylistdata);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return jsonobject1;
        }


        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            if (jsonObject != null) {
                Log.e("APIRes", jsonObject.toString());//need to delete later this log line before release
                try {
                    if (jsonObject.getString("action").equalsIgnoreCase("success")) {
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }



                }
        }
    }

    public class CategorylistAPI extends AsyncTask<String, String, JSONObject> {
        private JSONObject jsonobject;
        private JSONArray Subcatlist;
        @Override
        protected JSONObject doInBackground(String... strings) {
            WebServiceClass webServicesUrlClass = new WebServiceClass();
            ArrayList<NameValuePair> Categorylistdata = new ArrayList<>();
            try {
                Categorylistdata.add(new BasicNameValuePair("api_key", strings[0]));
                Categorylistdata.add(new BasicNameValuePair("category_id", strings[1]));
                jsonobject = webServicesUrlClass.subcategoryMethod(Categorylistdata);
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
                    Subcatlist = result.getJSONArray("Subcatlist");

                    for (int i = 0; i < Subcatlist.length(); i++) {
                        CategoryModelClass categoryModel = new CategoryModelClass();
                        JSONObject listitem = Subcatlist.getJSONObject(i);
                        String Cat_id = listitem.getString("id");
                        String Cat_title = listitem.getString("title");
                        String Cat_image = listitem.getString("subcat_thumbnail");
                        String Category_id = listitem.getString("category_id");
                        categoryModel.setId(Cat_id);
                        categoryModel.setSubCategory(Cat_title);
                        categoryModel.setImage(Cat_image);
                        categoryModel.setCategoryId(Category_id);

                        Sub_catlist.add(categoryModel);
                    } new ListAPI().execute(KeyGenerationClass.getEncryptedKey(),SubCategoryId);


                    adapter = new SelectedCategoryAdapter(SelectedCategoryList.this, Sub_catlist);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();


//                    adapter = new SelectedCategoryAdapter(SelectedCategoryList.this,Sub_catlist);
//                    recyclerView.setAdapter(adapter);
//                    adapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }
    }
}

