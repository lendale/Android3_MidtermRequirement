package com.acg.midtermrequirement.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.acg.midtermrequirement.R;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;


/**
 * Created by presciousalago on 03/09/2016.
 */
public class FragmentShoppingList extends Fragment {

    ListView mListView;
    TextView mTitle;
    Bundle bundle = new Bundle();

    ArrayList<String> shopList =  new ArrayList<>();
    ArrayAdapter<String> adapter;


    public FragmentShoppingList() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View rootVIew = inflater.inflate(R.layout.fragment_shoppinglist,container,false);

        mListView = (ListView) rootVIew.findViewById(R.id.listView);
        mTitle = (TextView) rootVIew.findViewById(R.id.label);


         bundle = getArguments();

        if(bundle == null){
            Toast.makeText(getContext(), "Data Empty", Toast.LENGTH_SHORT).show();
        }
        else {
            String strtext = String.valueOf(bundle.get("edttext"));
            mTitle.setText(strtext);
        }


        return  rootVIew ;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onStart() {
        super.onStart();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(),android.R.layout.simple_list_item_1,shopList);
        mListView.setAdapter(adapter);
    }
}
