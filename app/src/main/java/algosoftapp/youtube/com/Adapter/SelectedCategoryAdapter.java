package algosoftapp.youtube.com.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import algosoftapp.youtube.com.YoutubeActivity.PlayVideoList;
import algosoftapp.youtube.com.R;
import algosoftapp.youtube.com.ModelClass.CategoryModelClass;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by abc on 12/12/2018.
 */

public class SelectedCategoryAdapter extends RecyclerView.Adapter<SelectedCategoryAdapter.MyViewHolder> {
    private  ArrayList<CategoryModelClass> mSelected;
    private Context mcontext;

    public SelectedCategoryAdapter(Context context, ArrayList<CategoryModelClass> Selected) {
        this.mSelected = Selected;
        this.mcontext = context;
    }



    @Override
    public SelectedCategoryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.categorylist, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(SelectedCategoryAdapter.MyViewHolder holder, int position) {
        holder.textName.setText(mSelected.get(position).getSubCategory());
//        holder.uploadedList.setText(mSelected.get(position).getUploadedVideo());
        if (mSelected.get(position).getImage() != null) {
            if (!mSelected.get(position).getImage().isEmpty()) {
                Picasso.get().load(mSelected.get(position).getImage()).into(holder.Image);
            }
        }

//        holder.Image.setImageBitmap(mSelected.get(position).getImage());
//        holder.like.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                FavouritesFragment optionsFrag=new FavouritesFragment();
//                ((SelectedCategoryList)mcontext).getFragmentManager().beginTransaction().replace(R.id.container,optionsFrag,"OptionsFragment").addToBackStack(null).commit();
//
//                return false;
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return mSelected.size();
    }

    public void setFilter(ArrayList<CategoryModelClass> filter) {
        mSelected = new ArrayList<>();
        mSelected.addAll(filter);
        notifyDataSetChanged();
    }


//    @Override
//    public Filter getFilter() {
//        return new Filter() {
//
//            @Override
//            protected FilterResults performFiltering(CharSequence charSequence) {
//                String query = charSequence.toString();
//
//                ArrayList<CategoryModelClass> filtered = new ArrayList<>();
//
//                if (query.isEmpty()) {
//                    filtered = mSelected;
//                } else {
//                    for (String movie : mSelected) {
//                        if (movie.toLowerCase().contains(query.toLowerCase())) {
//                            filtered.add(movie);
//                        }
//                    }
//                }
//
//                FilterResults results = new FilterResults();
//                results.count = filtered.size();
//                results.values = filtered;
//                return results;
//            }
//            @Override
//            protected void publishResults(CharSequence charSequence, FilterResults results) {
//                itemsFiltered = (ArrayList<String>) results.values;
//                notifyDataSetChanged();
//            }
//        };
//    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout linearLayout;
        TextView textName,Watch,uploadedList;
        ImageView Image;
        public MyViewHolder(View itemView) {
            super(itemView);
            linearLayout=(LinearLayout)itemView.findViewById(R.id.LinearWatch);
            Watch=(TextView)itemView.findViewById(R.id.WatchNow);
            textName=(TextView)itemView.findViewById(R.id.TextName);
            Image=(ImageView)itemView.findViewById(R.id.CircleImage);



//                    SharedPreferences pref=mcontext.getSharedPreferences("Preference_reference", Context.MODE_PRIVATE);
//                    if (v==like){
//                        AddFavoutites();
//
//                        SharedPreferences sharedPref = getApplication().getSharedPreferences(PREFS_NAME,0);
//                        SharedPreferences.Editor editor = sharedPref.edit();
//                        editor.putString("key", like);
//                        editor.apply();




            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    String titlename = textName.getText().toString();
                    Intent i=new Intent(v.getContext(), PlayVideoList.class);
                    i.putExtra("subcategoryId",mSelected.get(getLayoutPosition()).getId());
                    i.putExtra("SubcategoryTitle",mSelected.get(getLayoutPosition()).getSubCategory());
                    mcontext.startActivity(i);

                }
            });

//
        }

//        private void AddFavoutites() {
//            like.setImageResource(R.mipmap.favorites);
//
//        }



    }
//    public void setFilter( ArrayList<CategoryModelClass> filterList) {
//
//
//
//    }


}

