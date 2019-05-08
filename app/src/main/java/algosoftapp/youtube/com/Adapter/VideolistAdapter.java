package algosoftapp.youtube.com.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeThumbnailView;

import java.util.ArrayList;

import algosoftapp.youtube.com.R;

/**
 * Created by abc on 10/01/2019.
 */

public class VideolistAdapter extends RecyclerView.Adapter<VideolistAdapter.MyViewHolder>{
    private final ArrayList<String> mTopics;
    private Context mcontext;

    public VideolistAdapter(Context context,ArrayList<String> mTopics) {
        this.mTopics = mTopics;
        this.mcontext=context;
    }


    @Override
    public VideolistAdapter.MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.playvideolist, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder( VideolistAdapter.MyViewHolder holder, int position) {
        holder.textView.setText(mTopics.get(position));


    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        public MyViewHolder(View itemView) {
            super(itemView);
            imageView=(ImageView)itemView.findViewById(R.id.playview);
            textView=(TextView)itemView.findViewById(R.id.PlayVideo_Title);
        }
    }
}
