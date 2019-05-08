//package algosoftapp.youtube.com.YoutubeActivity;
//
//import android.content.Context;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.net.Uri;
//import android.os.AsyncTask;
//import android.preference.PreferenceManager;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ProgressBar;
//import android.widget.Toast;
//import android.provider.Settings.Secure;
//
//import com.google.android.gms.auth.api.signin.GoogleSignIn;
//import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
//import com.google.android.gms.auth.api.signin.GoogleSignInClient;
//import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
//import com.google.android.gms.common.ConnectionResult;
//import com.google.android.gms.common.SignInButton;
//import com.google.android.gms.common.api.ApiException;
//import com.google.android.gms.common.api.GoogleApiClient;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//
//import org.apache.http.NameValuePair;
//import org.apache.http.message.BasicNameValuePair;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//
//import algosoftapp.youtube.com.R;
//import algosoftapp.youtube.com.Service.Network;
//import algosoftapp.youtube.com.Service.WebServiceClass;
//import algosoftapp.youtube.com.YoutubeActivity.Home;
//import algosoftapp.youtube.com.utility.KeyGenerationClass;
//
//public class Login extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
//    private static final String TAG = "SingInActivity";
//    private GoogleSignInClient mGoogleSignInClient;
//    static final int RC_SIGN_IN = 9001;
//    private Button SignOut;
//    private SignInButton google;
//    private String  android_id;
//    private ProgressBar progressBar;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//
//
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestEmail()
//                .build();
//        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
//        SignOut = (Button) findViewById(R.id.Sign_Out);
//        progressBar=(ProgressBar)findViewById(R.id.Progress_Bar);
//        google = (SignInButton) findViewById(R.id.Google_SignIn);
//        google.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                switch (v.getId()) {
//                    case R.id.Google_SignIn:
//                        signIn();
//                        break;
//
//                    case R.id.Sign_Out:
//                        signOut();
//                        break;
//                    case R.id.Disconnected:
//                        revokeAccess();
//                        break;
//                }
//
//            }
//        });
//Toast.makeText(this,"deviceID"+android_id,Toast.LENGTH_LONG).show();
////
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//
//
//        // [START on_start_sign_in]
//        // Check for existing Google Sign In account, if the user is already signed in
//        // the GoogleSignInAccount will be non-null.
//        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
////        if(account!=null)
//        updateUI(account);
//    }
//
//        // [END on_start_sign_in]
//
//    private void signIn() {
//        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
//        startActivityForResult(signInIntent, RC_SIGN_IN);
//
//        SharedPreferences pref = getSharedPreferences("ActivityPREF", Context.MODE_PRIVATE);
//        SharedPreferences.Editor edt = pref.edit();
//        edt.putBoolean("activity_executed", true);
//        edt.commit();
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
//        if (requestCode == RC_SIGN_IN) {
//            // The Task returned from this call is always completed, no need to attach
//            // a listener.
//            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
//            handleSignInResult(task);
//        }
//    }
//
//    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
//        try {
//            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
//
//            // Signed in successfully, show authenticated UI.
//            updateUI(account);
//            startActivity(new Intent(this, Home.class));
//        } catch (ApiException e) {
//
//            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
//            updateUI(null);
//        }
//    }
//
//    private void updateUI(@Nullable GoogleSignInAccount account) {
//        if (account!=null) {
//
//            String strUID = account.getId();
//            String strUserName = account.getDisplayName();
//            String strEmail = account.getEmail();
//            Uri strImage=account.getPhotoUrl();
//
//            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
//                SharedPreferences.Editor editor = sharedPref.edit();
//                editor.putString("Name",strUserName );
//                editor.putString("Email",strEmail);
////                editor.putString("strImage",strImage.toString());
//                editor.apply();
//                Log.e("Email",strEmail);
//
//            android_id = Secure.getString(getApplicationContext().getContentResolver(),Secure.ANDROID_ID);
//            if (Network.isNetworkStatusAvailable(this)) {
//                new LoginAPI().execute(KeyGenerationClass.getEncryptedKey(), strEmail,android_id,strUserName,strUID);
//            } else {
//                Toast.makeText(this, "Please Check Your Connection", Toast.LENGTH_LONG).show();
//            }
//        }
//    }
//
//
//    @Override
//    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
//
//    }
//
//    private void signOut() {
//        mGoogleSignInClient.signOut()
//                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        finish();
//                        google.setVisibility(View.GONE);
//                        // [START_EXCLUDE]
//                        updateUI(null);
//                        // [END_EXCLUDE]
//                    }
//                });
//    }
//
//    private void revokeAccess() {
//        mGoogleSignInClient.revokeAccess()
//                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        // [START_EXCLUDE]
//                        updateUI(null);
//                        // [END_EXCLUDE]
//                    }
//                });
//    }
//
////    public void ButtonOnClick(View view) {
////        if (Network.isNetworkStatusAvailable(this)){
////
////        }
////
////    }
//
//
//    public class LoginAPI extends AsyncTask<String, String, JSONObject> {
//
//
//
//        @Override
//        protected JSONObject doInBackground(String... strings) {
//            WebServiceClass webServicesUrlClass = new WebServiceClass();
//            ArrayList<NameValuePair> loginData=new ArrayList<>();
//            try {
//                loginData.add(new BasicNameValuePair("api_key",strings[0]));
//                loginData.add(new BasicNameValuePair("userEmail",strings[1]));
//                loginData.add(new BasicNameValuePair("deviceId",strings[2]));
//                loginData.add(new BasicNameValuePair("userName",strings[3]));
//                loginData.add(new BasicNameValuePair("accountID",strings[4]));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//            return webServicesUrlClass.loginMethod(loginData);
//        }
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            progressBar.setVisibility(View.VISIBLE);
//        }
//
//
//        @Override
//        protected void onPostExecute(JSONObject object) {
//            super.onPostExecute(object);
//            try {
//                Log.e("APIRes", object.toString());//need to delete later this log line before release
//                if (object.getString("action").equalsIgnoreCase("success")) {
//
//
//            }
//        } catch (JSONException e) {
//                e.printStackTrace();
//
//            }
//            new NotificationAPI().execute(KeyGenerationClass.getEncryptedKey(),"1");
//        }
//}
//
//public class NotificationAPI extends AsyncTask<String,String,JSONObject>{
//
//    @Override
//    protected JSONObject doInBackground(String... strings) {
//        WebServiceClass webServicesUrlClass = new WebServiceClass();
//        ArrayList<NameValuePair> notification=new ArrayList<>();
//        notification.add(new BasicNameValuePair("api_key",strings[0]));
//        notification.add(new BasicNameValuePair("user_id",strings[1]));
//        return webServicesUrlClass.notificationMethod(notification);
//    }
//
//    @Override
//    protected void onPreExecute() {
//        super.onPreExecute();
//    }
//
//    @Override
//    protected void onPostExecute(JSONObject object) {
//        super.onPostExecute(object);
//        try {
//            Log.e("APIRes", object.toString());//need to delete later this log line before release
//            if (object.getString("action").equalsIgnoreCase("success")) {
//
//
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//
//        }
//    }
//}
//
//}
//
//
