package algosoftapp.youtube.com;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import algosoftapp.youtube.com.Adapter.CategoryAdapter;
import algosoftapp.youtube.com.ModelClass.ModelCategory;
import algosoftapp.youtube.com.Service.Network;
import algosoftapp.youtube.com.Service.WebServiceClass;
import algosoftapp.youtube.com.YoutubeActivity.Home;
import algosoftapp.youtube.com.utility.KeyGenerationClass;

public class Category extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TextView SelectAll;
    private RecyclerView.Adapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private List<ModelCategory> category;
   public static ArrayList<String> sleList = new ArrayList<>();
    private static final String TAG="Category";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
//        SelectAll=(TextView)findViewById(R.id.SelectAll);


        category = new ArrayList<ModelCategory>();
        for (int i = 1; i <= 10; i++) {
            ModelCategory ms = new ModelCategory("Motivation" + i, false);
            category.add(ms);
        }

        recyclerView = (RecyclerView) findViewById(R.id.RecyclerSelect);
        recyclerView.setHasFixedSize(false);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new CategoryAdapter(this,category);
        recyclerView.setAdapter(adapter);

        Button buttonSave = (Button) findViewById(R.id.ButtonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationManager notif=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                Notification notify=new Notification.Builder
                        (getApplicationContext()).setContentTitle("tittle").setContentText("body").
                        setContentTitle("We Missing you").setSmallIcon(R.drawable.favorite).build();

                notify.flags |= Notification.FLAG_AUTO_CANCEL;
                notif.notify(0, notify);







//
                String data = "";
                List<ModelCategory> SelectedCategory = ((CategoryAdapter) adapter).getSelectedList();
                for (int i = 0; i < SelectedCategory.size(); i++) {
                    ModelCategory Selected = SelectedCategory.get(i);
                    if (Selected.isSelected() == true) {
                        data = Selected.getType()+"";

                        Toast.makeText(Category.this,"SelectedCategory"+data,Toast.LENGTH_LONG).show();

                        sleList.add(data);
                    }
                }



                   setselData(sleList);

                if (Network.isNetworkStatusAvailable(Category.this)){
                    new CategoryAPI().execute(KeyGenerationClass.getEncryptedKey());
                }


//                SharedPreferences sharedPref = getApplication().getSharedPreferences(PREFS_NAME,0);
//                SharedPreferences.Editor editor = sharedPref.edit();
//                editor.putString("key", data);
//                editor.apply();


                Toast.makeText(Category.this,""+ data, Toast.LENGTH_LONG).show();
                Intent i=new Intent(Category.this,Home.class);
//
                startActivity(i);
                finish();

            }
        });
    }

    public void setselData(ArrayList<String> dataList){
        this.sleList = dataList;
    }

    public static ArrayList<String> getSetList() {
        return sleList;
    }


    public class CategoryAPI extends AsyncTask<String,String,JSONObject>{

        @Override
        protected JSONObject doInBackground(String... strings) {
            WebServiceClass webServicesUrlClass = new WebServiceClass();
            ArrayList<NameValuePair>Categorydata=new ArrayList<>();
            try {
                Categorydata.add(new BasicNameValuePair("api_key",strings[0]));
            }catch (Exception e){
                e.printStackTrace();
            }return webServicesUrlClass.categoryMethod(Categorydata);
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            Log.e("APIRes", jsonObject.toString());
            if (jsonObject!=null){
                JSONObject mJsonObject = new JSONObject();
                try {
                    mJsonObject.getJSONObject("result");
                    mJsonObject.put("category_id","1");
                    mJsonObject.put("category","Automobiles");
                    mJsonObject.put("cat_thumbnail","");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JSONArray mJsonArray = new JSONArray();
                mJsonArray.put(mJsonObject);
//                mJsonArray.getJSONArray("catlist");
                for (int i=0;i<=mJsonArray.length();i++){

//                    adapter=new CategoryAdapter(Category.this,mJsonArray);

                }




            }

        }
    }
}


