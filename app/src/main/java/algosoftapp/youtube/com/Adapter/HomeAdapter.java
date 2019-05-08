package algosoftapp.youtube.com.Adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import algosoftapp.youtube.com.Fragment.CategoryFragment;
import algosoftapp.youtube.com.R;
import algosoftapp.youtube.com.YoutubeActivity.SelectedCategoryList;
import algosoftapp.youtube.com.utility.CategoryListDetail;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by abc on 12/12/2018.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {
    private final ArrayList<CategoryListDetail> mcategoryList;
    private CategoryFragment mcontext;
//    SelectedCategory=new ArrayList<String>mcategoryList;

    public HomeAdapter(CategoryFragment context, ArrayList<CategoryListDetail> categorylist) {
        this.mcategoryList = categorylist;
        this.mcontext=context;
    }

    @Override
    public HomeAdapter.MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.homeselected,parent,false);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder( HomeAdapter.MyViewHolder holder, int position) {
     holder.textImage.setText(mcategoryList.get(position).getCategory());
     if (mcategoryList.get(position).getCategoryImage()!=null){
         if (!mcategoryList.get(position).getCategoryImage().isEmpty()){
                Picasso.get()
                        .load(mcategoryList.get(position).getCategoryImage())
                        .placeholder(R.mipmap.appicon)
                        .into(holder.circleImageView);
            }
        }



    }

    @Override
    public int getItemCount() {
        return mcategoryList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textImage;
        CircleImageView circleImageView;
        public MyViewHolder(View itemView) {
            super(itemView);
            this.textImage=(TextView)itemView.findViewById(R.id.TextImage);
            this.circleImageView=(CircleImageView)itemView.findViewById(R.id.SelectedImage);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            Intent i=new Intent(v.getContext(),SelectedCategoryList.class);
//            Bundle b=new Bundle();
//            b.putInt("Integer",);
//            i.putExtras(b);
//            String passingdata = textImage.getText().toString();
//            String categorylist=textImage.getText().toString();
            i.putExtra("category_id",mcategoryList.get(getLayoutPosition()).categoryId);
            i.putExtra("title", mcategoryList.get(getLayoutPosition()).getCategory());
            mcontext.startActivity(i);


        }
    }
}
