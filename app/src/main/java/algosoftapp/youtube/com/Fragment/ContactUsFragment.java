package algosoftapp.youtube.com.Fragment;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;

import algosoftapp.youtube.com.R;
import algosoftapp.youtube.com.Service.Network;
import algosoftapp.youtube.com.Service.WebServiceClass;
import algosoftapp.youtube.com.utility.KeyGenerationClass;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContactUsFragment extends Fragment {
    EditText edit_Name, edit_Email, edit_Subject, edit_Message;
    Button buttonSave;
    private ProgressDialog pDialog;

    public ContactUsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact_us, container, false);
        edit_Name = (EditText) view.findViewById(R.id.Edit_Name);
        edit_Email = (EditText) view.findViewById(R.id.Edit_Email);
        edit_Message = (EditText) view.findViewById(R.id.Edit_Message);
        edit_Subject = (EditText) view.findViewById(R.id.Edit_Subject);

        buttonSave = (Button) view.findViewById(R.id.Button_submit);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strName = edit_Name.getText().toString().trim();
                String strEmail = edit_Email.getText().toString().trim();
                String strMessage = edit_Message.getText().toString().trim();
                String strSubject = edit_Subject.getText().toString().trim();
                if (!strName.isEmpty() && !strEmail.isEmpty() && !strMessage.isEmpty() && !strSubject.isEmpty()) {
                    if (Patterns.EMAIL_ADDRESS.matcher(strEmail).matches()) {
                        if (Network.isNetworkStatusAvailable(getActivity())) {
                            new CallRegisterApi().execute(strName, strEmail, strMessage, strSubject, "test", KeyGenerationClass.getEncryptedKey());

                        } else {
                            Toast.makeText(getActivity(), "Please check your Network Connection", Toast.LENGTH_LONG).show();
                        }

                    } else {
                        Toast.makeText(getActivity(), "Please Enter a valid Email Id", Toast.LENGTH_LONG).show();
                    }

                } else if (strName.isEmpty()) {
                    Toast.makeText(getActivity(), "Please Enter your Name", Toast.LENGTH_LONG).show();
                } else if (strEmail.isEmpty()) {
                    Toast.makeText(getActivity(), "Please Enter your Email Id", Toast.LENGTH_LONG).show();
                } else if (strSubject.isEmpty()) {
                    Toast.makeText(getActivity(), "Please Enter your Subject", Toast.LENGTH_LONG).show();

                } else if (strMessage.isEmpty()) {
                    Toast.makeText(getActivity(), "Please Enter what you want to asked", Toast.LENGTH_LONG).show();
                }
            }

        });


        return view;
    }

    public class CallRegisterApi extends AsyncTask<String, String, JSONObject> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage(" Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected JSONObject doInBackground(String... strings) {
            WebServiceClass webServicesUrlClass = new WebServiceClass();
            ArrayList<NameValuePair> paramsData = new ArrayList<>();
            paramsData.add(new BasicNameValuePair("user_id", strings[0]));
            paramsData.add(new BasicNameValuePair("name", strings[1]));
            paramsData.add(new BasicNameValuePair("email", strings[2]));
            paramsData.add(new BasicNameValuePair("subject", strings[3]));
            paramsData.add(new BasicNameValuePair("message",strings[4]));
            paramsData.add(new BasicNameValuePair("api_key",strings[5]));
            return webServicesUrlClass.registerMethod(paramsData);
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            try {
                pDialog.dismiss();
                Log.e("APIRes", jsonObject.toString());//need to delete later this log line before release
                if (jsonObject.getString("action").equalsIgnoreCase("success")) {
                    edit_Name.setText("");
                    edit_Email.setText("");
                    edit_Subject.setText("");
                    edit_Message.setText("");

                } Toast.makeText(getActivity(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    }

