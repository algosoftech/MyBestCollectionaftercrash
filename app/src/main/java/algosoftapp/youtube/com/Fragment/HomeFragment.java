package algosoftapp.youtube.com.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import algosoftapp.youtube.com.Category;
import algosoftapp.youtube.com.Adapter.HomeAdapter;
import algosoftapp.youtube.com.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment  {

    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    View mView;
//    List<String>selectedvaluelist;
    ArrayList<String> alldataList = new ArrayList<>();



    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        try {
         //   if (mView != null){
               alldataList.clear();
                mView = inflater.inflate(R.layout.fragment_home, container, false);


                alldataList = Category.getSetList();
                Log.e("AlldataList",alldataList+"");



//                String[]selected=new String[alldataList.size()];
//
//
//                if(selected!=null){
//                    String[] items = selected.split(",");
//
//                    for (String item : items) {
//                        Log.e("SelectedValue", item);
//
//                        selected.add(item);
//                    }
//
//                }


                Log.e("SelectedValuelistSize", "" + alldataList.size());
            Log.e("SelectedValuelistSize",""+alldataList);






                recyclerView = (RecyclerView) mView.findViewById(R.id.RecyclerHome);
                recyclerView.setHasFixedSize(false);

                layoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
//                adapter = new HomeAdapter(HomeFragment.this,alldataList);
//                recyclerView.setAdapter(adapter);
//                adapter.notifyDataSetChanged();
          //  }
        }catch (Exception e){
            e.printStackTrace();
        }



        return mView;
    }




}
