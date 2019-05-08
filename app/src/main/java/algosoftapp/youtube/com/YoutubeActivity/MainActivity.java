package algosoftapp.youtube.com.YoutubeActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import algosoftapp.youtube.com.R;
import algosoftapp.youtube.com.YoutubeActivity.Home;
//import algosoftapp.youtube.com.YoutubeActivity.Login;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread thread=new Thread(){
            @Override
            public void run() {
                try {
                    sleep(2000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                finally {
                    Intent intentLogin=new Intent(MainActivity.this,Home.class);
                    startActivity(intentLogin);

                }

            }
        };thread.start();

// for saving the login details and direct open home activity

//        try {
//            SharedPreferences pref = getSharedPreferences("ActivityPREF", Context.MODE_PRIVATE);
//
//            boolean isloggedvalue = pref.getBoolean("activity_executed", false);
//            Toast.makeText(this, "loggedval"+isloggedvalue, Toast.LENGTH_SHORT).show();
//
//            if(isloggedvalue==true){
//                Intent intent = new Intent(this, Home.class);
//                startActivity(intent);
//            }else if(isloggedvalue==false) {
//                Intent intent = new Intent(this, Login.class);
//                startActivity(intent);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }



//        if (pref.getBoolean("activity_executed", false)) {
//            Intent intent = new Intent(this, Home.class);
//            startActivity(intent);
//            finish();
//        } else {
//            Intent intent = new Intent(this, Login.class);
//            startActivity(intent);
//            finish();
//        }

//        finish();
    }
}
