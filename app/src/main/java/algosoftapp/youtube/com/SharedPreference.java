package algosoftapp.youtube.com;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abc on 17/12/2018.
 */

public class SharedPreference {

        private static Context context;

        public SharedPreference(Context context){
            this.context = context;
        }

        public final static String PREFS_NAME = "appname_prefs";

        public static void setInt( String key, int value) {
            SharedPreferences sharedPref = context.getSharedPreferences(PREFS_NAME,0);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt(key, value);
            editor.apply();
        }

        public static int getInt(String key) {
            SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
            return prefs.getInt(key, 0);
        }

        public static void setStr(String key, String value) {
            SharedPreferences sharedPref = context.getSharedPreferences(PREFS_NAME,0);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(key, value);
            editor.apply();
        }

        public static String getStr(String key) {
            SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
            return prefs.getString(key,"DNF");
        }

        public static void setBool(String key, boolean value) {
            SharedPreferences sharedPref = context.getSharedPreferences(PREFS_NAME,0);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putBoolean(key, value);
            editor.apply();
        }

        public static boolean getBool(String key) {
            SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
            return prefs.getBoolean(key,false);
        }

        public static void setList (ArrayList<String> data){
            SharedPreferences sharedPref = context.getSharedPreferences(PREFS_NAME,0);
            SharedPreferences.Editor editor = sharedPref.edit();
            List<String> textList = new ArrayList<String>();
            textList.addAll(data);

        }

}
