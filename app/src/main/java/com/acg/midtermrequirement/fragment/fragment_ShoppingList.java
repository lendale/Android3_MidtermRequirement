package com.acg.midtermrequirement.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.acg.midtermrequirement.R;


/**
 * Created by presciousalago on 03/09/2016.
 */
public class fragment_ShoppingList extends Fragment {


    public fragment_ShoppingList() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View rootVIew = inflater.inflate(R.layout.fragment_shoppinglist,container,false);


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
    public void onDestroy() {
        super.onDestroy();
    }
}
