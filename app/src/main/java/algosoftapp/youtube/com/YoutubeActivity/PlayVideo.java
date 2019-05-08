package algosoftapp.youtube.com.YoutubeActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import algosoftapp.youtube.com.Adapter.VideolistAdapter;
import algosoftapp.youtube.com.R;

public class PlayVideo extends  YouTubeBaseActivity implements YouTubePlayer.PlaybackEventListener
        ,YouTubePlayer.OnInitializedListener,YouTubePlayer.PlayerStateChangeListener{
    private static final String TAG="PlayVideo";
    private RelativeLayout.LayoutParams defaultVideoViewParams;
    private int defaultScreenOrientationMode;
    YouTubePlayerView mYouTubePlayerView;
     VideoView videoView;
     private RecyclerView recycler_videolist;
     private LinearLayoutManager linearLayoutManager;
    private RecyclerView.Adapter adapter;

    String API_Key="AIzaSyDvfJMeG1WoMvaVcQFF3cLDi9GrB234yYU";
    String VEDIO_Id;
    String Video_id;
    String Playlist="PL6MnV_G6g94hbVThkul1Vxu6F8RkEpljW";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);

//        Bundle bundle = getIntent().getExtras();
//        ArrayList<String> mTopics=bundle.getStringArrayList("Array_list");
        Intent startingIntent = getIntent();
        Video_id=startingIntent.getStringExtra("Videolist");
        try {
            VEDIO_Id=extractYoutubeId(Video_id);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Log.e("IntentData",Video_id+""+VEDIO_Id);


//        recycler_videolist=(RecyclerView)findViewById(R.id.RecyclerVideolist);
//        recycler_videolist.setHasFixedSize(false);
//        linearLayoutManager = new LinearLayoutManager(this);
//        recycler_videolist.setLayoutManager(linearLayoutManager);
//        adapter=new VideolistAdapter(PlayVideo.this,Selected);
//        recycler_videolist.setAdapter(adapter);

        mYouTubePlayerView=(YouTubePlayerView) findViewById(R.id.playview);
        mYouTubePlayerView.initialize(API_Key,this);
//        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

    }



    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.setPlayerStateChangeListener(this);
        if (!b){
            youTubePlayer.setFullscreen(true);
            youTubePlayer.cueVideo(VEDIO_Id);
//            youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.MINIMAL);




        }

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }

    @Override
    public void onPlaying() {

    }

    @Override
    public void onPaused() {

    }

    @Override
    public void onStopped() {

    }

    @Override
    public void onBuffering(boolean b) {

    }

    @Override
    public void onSeekTo(int i) {

    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onLoaded(String s) {

    }

    @Override
    public void onAdStarted() {

    }

    @Override
    public void onVideoStarted() {
//        player.setFullscreen(true);


    }

    @Override
    public void onVideoEnded() {

    }

    @Override
    public void onError(YouTubePlayer.ErrorReason errorReason) {

    }
    public String extractYoutubeId(String url) throws MalformedURLException {
        String query = new URL(url).getQuery();
        String[] param = query.split("&");
        String id = null;
        for (String row : param) {
            String[] param1 = row.split("=");
            if (param1[0].equals("v")) {
                id = param1[1];
            }
        }
        return id;
    }
    public void onFullScreen(boolean isFullScreen){
        if (isFullScreen==true){

        }else {

        }

    }

}
