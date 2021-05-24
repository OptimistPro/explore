package com.startgames.exploler;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Environment;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;


import java.io.File;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link forder_manager#newInstance} factory method to
 * create an instance of this fragment.
 */
public class forder_manager extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public forder_manager() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment forder_manager.
     */
    // TODO: Rename and change types and number of parameters
    public static forder_manager newInstance(String param1, String param2) {
        forder_manager fragment = new forder_manager();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    // TODO: Rename and change types and number of parameters
    public static forder_manager newInstance(String param1) {
        forder_manager fragment = new forder_manager();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }
    String path_info="";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forder_manager, container, false);
        String path="/storage/emulated/0";
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            path=mParam1;
        }



        ((Global) getActivity().getApplication()).vse_clear();

        TextView t = view.findViewById(R.id.textView2);
        String path_name = path;
        path_name=path_name.replace("/storage/emulated/0","Внутренний общий накопитель");
        path_name=path_name.replace("/",">");
        t.setText(path_name);
        path_info=path_name;
        File directory = new File(path);
        File[] files = directory.listFiles();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        boolean prov = true;
        if (files!=null) {
            int j = 0;
            for (int i = 0; i < files.length; i++) {

                if (files[i].getName().charAt(0)!='.') {
                    if (files[i].getName().indexOf('.')<=0){
                        forder_element catFragment = forder_element.newInstance(files[i].getPath(), files[i].getName());
                        ft.add(R.id.forder, catFragment);
                        prov=false;
                        ((Global) getActivity().getApplication()).vse_forder_g(catFragment);
                    }
                }
            }
            for (int i = 0; i < files.length; i++) {

                if (files[i].getName().charAt(0)!='.') {
                    if (files[i].getName().indexOf('.')>0){
                        forder_element catFragment = forder_element.newInstance(files[i].getPath(), files[i].getName());
                        ft.add(R.id.forder, catFragment);
                        prov=false;
                        ((Global) getActivity().getApplication()).vse_forder_g(catFragment);
                    }
                }
            }
         }if(prov){
            ErrolForder catFragment = ErrolForder.newInstance("","");
            ft.add(R.id.liner_l, catFragment);
        }
        ft.commit();


        path = path_info;
        String[] st2 = path.split(">");
        path=path.replace(">"+st2[st2.length-1],"");
        path=path.replace("Внутренний общий накопитель","/storage/emulated/0");
        path_info=path.replace(">","/");
        MainActivity ma = (MainActivity) getActivity();
        ma.path_back_new(path_info);

        GridLayout gridLayout =(GridLayout) view.findViewById(R.id.forder);
        gridLayout.setColumnCount(ma.size_windos());

        TextView button1 = t;
        View.OnTouchListener st = new View.OnTouchListener(){
            public boolean onTouch(View view, MotionEvent event)
            {
                //String[] st = path.split(">");
               // path=path.replace(">"+st[st.length-1],"");
               // path=path.replace("Внутренний общий накопитель","/storage/emulated/0");
               // path=path.replace(">","/");
                if (event.getAction()==MotionEvent.ACTION_UP){
                    if (!((Global) getActivity().getApplication()).getMenu_close()) {
                        MainActivity ma = (MainActivity) getActivity();
                        ma.myMetod(path_info);
                    }
                }
                return true;
            }

        };
        button1.setOnTouchListener(st);



        // Inflate the layout for this fragment
        return view;
    }

}