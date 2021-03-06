package algosoftapp.youtube.com.Fragment;


import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import algosoftapp.youtube.com.R;
import algosoftapp.youtube.com.Service.Network;
import algosoftapp.youtube.com.Service.WebServiceClass;
import algosoftapp.youtube.com.utility.KeyGenerationClass;

import static android.net.wifi.WifiConfiguration.Status.strings;


/**
 * A simple {@link Fragment} subclass.
 */
public class PrivacyFragment extends Fragment {
WebView webView;
    private String htmlString;

    public PrivacyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (Network.isNetworkStatusAvailable(getActivity())) {
            new PRIVACYAPI().execute(KeyGenerationClass.getEncryptedKey(), "about");
        } else {
            Toast.makeText(getActivity(), "Please Check Your Connection", Toast.LENGTH_LONG).show();
        }
        View view= inflater.inflate(R.layout.fragment_privacy2, container, false);
        webView=(WebView)view.findViewById(R.id.web_view_content);
        return view;

    }

    public class PRIVACYAPI extends AsyncTask<String, String, JSONObject> {

        @Override
        protected JSONObject doInBackground(String... strings) {
            WebServiceClass webServicesUrlClass = new WebServiceClass();
            ArrayList<NameValuePair>privacydata = new ArrayList<>();
            privacydata.add(new BasicNameValuePair("api_key", strings[0]));
            privacydata.add(new BasicNameValuePair("pagetitle", strings[1]));
            return webServicesUrlClass.privacyMethod(privacydata);
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            try {
                Log.e("APIRes", jsonObject.toString());//need to delete later this log line before release
                if (jsonObject.getString("action").equalsIgnoreCase("success")) {

                    String strContent=jsonObject.getString("content");
                    webView.getSettings().setJavaScriptEnabled(true);
                    webView.loadData(strContent, "text/html", "UTF-8");

                }
            } catch (JSONException e) {
                e.printStackTrace();

            }
        }

    }
}
