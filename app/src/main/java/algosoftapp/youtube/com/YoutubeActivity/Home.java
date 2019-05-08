package algosoftapp.youtube.com.YoutubeActivity;


import android.app.Dialog;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;

import algosoftapp.youtube.com.Fragment.CategoryFragment;
import algosoftapp.youtube.com.Fragment.ContactUsFragment;
import algosoftapp.youtube.com.Fragment.PrivacyFragment;
import algosoftapp.youtube.com.Fragment.TermsConditionFragment;
import algosoftapp.youtube.com.R;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private ArrayList<String> category;
    FragmentManager fm;
    ArrayList<String> selectedvalueList = new ArrayList<>();
    ImageView imageView;
    TextView textName,textId;
    NavigationView navigationView;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-6911912195923365/4260210801");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());


//        Bundle b = getIntent().getExtras();
//        String SelectedCategory = b.getString("SelectedCategory");
////        Toast.makeText(Home.this,"Selected"+SelectedCategory, Toast.LENGTH_LONG).show();
////
//        String[] items = SelectedCategory.split(",");
//        for (String item : items)
//        {
//            Log.e("SelectedValue",item);
//
//            selectedvalueList.add(item);
//        }
//
//        Log.e("SelectedValuelistSize",""+selectedvalueList.size());









        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        SharedPreferences mSharedPreference= PreferenceManager.getDefaultSharedPreferences(this);
        String strEmail= mSharedPreference.getString("Email","");
        String strUserName=(mSharedPreference.getString("Name",""));
//        String strImage=mSharedPreference.getString("Image","");
//        Uri Image=Uri.parse(strImage);
        Log.e("Email",strEmail+strUserName);



        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
        textName=(TextView)navigationView.getHeaderView(0).findViewById(R.id.TextName);
        textName.setText(strUserName);
        textId=(TextView)navigationView.getHeaderView(0).findViewById(R.id.textViewId);
        textId.setText(strEmail);

//        getCurrentinfo();


        navigationView.setCheckedItem(R.id.Home);
        Fragment fragment = new CategoryFragment();
        displaySelectedFragment(fragment);

         fm = getFragmentManager();
        for (int i = 0; i<fm.getBackStackEntryCount(); i++){
            fm.popBackStack();
        }

    }





    @Override
    public void onBackPressed() {


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
//        } {
//            Log.d("TAG", "The interstitial wasn't loaded yet.");
//        }
        }else {
//            super.onBackPressed();
            final Dialog dialog = new Dialog(Home.this);
            dialog.setContentView(R.layout.alertbox);
//            dialog.setTitle("Are you sure you want to exit ?");
            Button dialogButton_yes = (Button) dialog.findViewById(R.id.Button_Yes);
            Button dialogButton_no = (Button) dialog.findViewById(R.id.Button_No);

            dialogButton_no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            // if button is clicked, close the custom dialog
            dialogButton_yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                Intent i=new Intent(Home.this,MainActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                            Intent.FLAG_ACTIVITY_CLEAR_TASK );
//                            startActivity(i);
                    finishAffinity();
                    dialog.dismiss();

                }
            });
            dialog.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.Home) {
            item.setVisible(true);
        } else {
            item.setVisible(false);
        }
//        if (id == R.id.add_Category) {
//
//        //noinspection SimplifiableIfStatement
//            Intent i = new Intent(Home.this, Category.class);
//            startActivity(i);
//            return true;
//        }

        return super.onOptionsItemSelected(item);


}

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment=null;

        if (id == R.id.Home) {
            fragment = new CategoryFragment();
            displaySelectedFragment(fragment);
            setTitle(item.getTitle());


//        } else if (id == R.id.My_favourites) {
//            fragment =new FavouritesFragment();
//            displaySelectedFragment(fragment);
//            setTitle(item.getTitle());

        } else if (id == R.id.Share) {
//            item.setVisible(false);
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_TEXT,"I suggest this app for you : https://play.google.com/store/apps/details?id=algosoft.com.trumpcard");
            intent.setType("text/plain");
            startActivity(intent);

//            fragment=new ShareFragment();
//            displaySelectedFragment(fragment);
//            setTitle(item.getTitle());

        } else if (id == R.id.Privacy) {
//            item.setVisible(false);
            fragment=new PrivacyFragment();
            displaySelectedFragment(fragment);
            setTitle(item.getTitle());

        } else if (id == R.id.Termscondition) {
            fragment=new TermsConditionFragment();
            displaySelectedFragment(fragment);
            setTitle(item.getTitle());

        } else if (id == R.id.Contact) {
            fragment=new ContactUsFragment();
            displaySelectedFragment(fragment);
            setTitle(item.getTitle());

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void getCurrentinfo() {

    }

    private void displaySelectedFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }



}
