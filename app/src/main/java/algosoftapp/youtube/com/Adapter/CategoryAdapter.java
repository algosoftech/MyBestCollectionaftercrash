package algosoftapp.youtube.com.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import algosoftapp.youtube.com.ModelClass.ModelCategory;
import algosoftapp.youtube.com.R;

/**
 * Created by abc on 12/12/2018.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {
    private List<ModelCategory> mcategory;
    private Context mcontext;
//    private static final String PREFS_NAME = "preferenceName";
    public static SharedPreferences msharedPreferences;
//

    public CategoryAdapter(Context context, List<ModelCategory> category) {
        this.mcontext=context;
        this.mcategory=category;


    }


    @Override
    public CategoryAdapter.MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.categoryadapter, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder( CategoryAdapter.MyViewHolder holder, int position) {
        final int pos = position;
        holder.textname.setText(mcategory.get(position).getType());
        holder.Select.setChecked(mcategory.get(position).isSelected());
        holder.Select.setTag(mcategory.get(position));
        holder.Select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox cb = (CheckBox) v;
                ModelCategory contact = (ModelCategory) cb.getTag();
                contact.setSelected(cb.isChecked());


                mcategory.get(pos).setSelected(cb.isChecked());


            }
        });

    }

    @Override
    public int getItemCount() {
        return mcategory.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textname;
        CheckBox Select;
        Button buttonSave;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.textname = (TextView) itemView.findViewById(R.id.TextName);
            this.buttonSave = (Button) itemView.findViewById(R.id.ButtonSave);
            this.Select = (CheckBox) itemView.findViewById(R.id.CheckBoxSelect);
        }
    }

    public List<ModelCategory> getSelectedList() {
        return mcategory;
    }


}

