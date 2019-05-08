package algosoftapp.youtube.com.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import algosoftapp.youtube.com.ModelClass.VideolistModelClass;
import algosoftapp.youtube.com.YoutubeActivity.PlayVideo;
import algosoftapp.youtube.com.R;
import algosoftapp.youtube.com.Service.Network;

/**
 * Created by abc on 12/12/2018.
 */

public class PlayVideoAdapter extends RecyclerView.Adapter<PlayVideoAdapter.MyViewHolder> {
    private  ArrayList<VideolistModelClass> mTopics;
    private Context mcontext;


    public PlayVideoAdapter(Context context,ArrayList<VideolistModelClass> mTopics) {
        this.mTopics = mTopics;
        this.mcontext=context;
    }

    @Override
    public PlayVideoAdapter.MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.playvideoadapter, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder( PlayVideoAdapter.MyViewHolder holder, int position) {
        holder.textTitle.setText(mTopics.get(position).getVideoTitle());
       if (mTopics.get(position).getVideoImage()!=null){
           if (!mTopics.get(position).getVideoImage().isEmpty()){
               Picasso.get().load(mTopics.get(position).getVideoImage()).into(holder.playVideo);
           }
       }
    }

    @Override
    public int getItemCount() {
        return mTopics.size();
    }

    public void setFilter(ArrayList<VideolistModelClass> filter) {
        mTopics = new ArrayList<>();
        mTopics.addAll(filter);
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textTitle;
        ImageView Share,like;
        LinearLayout play;
        ImageView playVideo;
        public MyViewHolder(View itemView) {
            super(itemView);
            textTitle=(TextView)itemView.findViewById(R.id.TextView_Title);
            Share=(ImageView)itemView.findViewById(R.id.ShareVideo);
            play=(LinearLayout)itemView.findViewById(R.id.LinearPlayVideo);
            playVideo=(ImageView) itemView.findViewById(R.id.youtube_player);
            Share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent share=new Intent(Intent.ACTION_SEND);
                    share.setType("text/plain");
                    String shareBody =mTopics.get(getLayoutPosition()).getLink();
                    String shareSub = "MyBestCollection";
                    share.putExtra(Intent.EXTRA_SUBJECT, shareBody);
                    share.putExtra(Intent.EXTRA_TEXT, shareBody);
                    mcontext.startActivity(Intent.createChooser(share, "Share using"));

                }
            });
//            like.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    String save="";
//                    boolean isFavourite = readStae();
//                    if (isFavourite) {
//                        like.setBackgroundResource(R.mipmap.favorites);
//                        isFavourite = false;
//                        saveStae(isFavourite);
//
//                    } else {
//                        like.setBackgroundResource(R.drawable.favorite);
//                        isFavourite = true;
//                        saveStae(isFavourite);
//
//                    }
//                }
//            });

            play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Network.isNetworkStatusAvailable(mcontext)) {
                        Intent i=new Intent(v.getContext(),PlayVideo.class);
                        i.putExtra("Videolist",mTopics.get(getLayoutPosition()).getLink());
                        i.putExtra("force_fullscreen",true);
//                        i.putExtra("arraylist",mTopics);
                        mcontext.startActivity(i);
                    }else {
                        Toast.makeText(mcontext,"Check your Connection",Toast.LENGTH_LONG).show();
                    }



                }
            });
        }
        private void saveStae(boolean isFavourite) {
            SharedPreferences aSharedPreferenes = mcontext.getSharedPreferences(
                    "Favourite", Context.MODE_PRIVATE);
            SharedPreferences.Editor aSharedPreferenesEdit = aSharedPreferenes
                    .edit();
            aSharedPreferenesEdit.putBoolean("State", isFavourite);
            aSharedPreferenesEdit.commit();
        }

        private boolean readStae() {
            SharedPreferences aSharedPreferenes = mcontext.getSharedPreferences(
                    "Favourite", Context.MODE_PRIVATE);
            return aSharedPreferenes.getBoolean("State", true);
        }
    }
}
