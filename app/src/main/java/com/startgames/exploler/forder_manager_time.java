package com.startgames.exploler;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link forder_manager_time#newInstance} factory method to
 * create an instance of this fragment.
 */
public class forder_manager_time extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private ArrayList<String> mParam2;

    public forder_manager_time() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment forder_manager_time.
     */
    // TODO: Rename and change types and number of parameters
    public static forder_manager_time newInstance(String param1, ArrayList<String> param2) {
        forder_manager_time fragment = new forder_manager_time();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putStringArrayList(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getStringArrayList(ARG_PARAM2);
        }
    }
    String path_info="";

    void del(){
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_forder_manager_time, container, false);
        String path="/storage/emulated/0";
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            path=mParam1;
            mParam2 = getArguments().getStringArrayList(ARG_PARAM2);
        }


        ArrayList<File> files=new ArrayList<File>(0);
        for (int i =0;i<mParam2.size();i++){
            File tr = new File(mParam2.get(i));
            files.add(tr);
        }


        ((Global) getActivity().getApplication()).vse_clear();

        //File directory = new File(path);
        //File[] files = directory.listFiles();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        TextView gh =view.findViewById(R.id.name_razdel);
        gh.setText(mParam1);
        boolean prov = true;
        if (files!=null) {
            for (int i = 0; i < files.size(); i++) {

                if (files.get(i).getName().charAt(0)!='.') {
                    if (files.get(i).isFile()){
                        forder_element catFragment = forder_element.newInstance(files.get(i).getPath(), files.get(i).getName());
                        ft.add(R.id.forder_time, catFragment);
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
        MainActivity ma = (MainActivity) getActivity();


        GridLayout gridLayout =(GridLayout) view.findViewById(R.id.forder_time);
        gridLayout.setColumnCount(ma.size_windos());


        ImageButton button2 = view.findViewById(R.id.close_forder_time);
        View.OnTouchListener st2 = new View.OnTouchListener(){
            public boolean onTouch(View view, MotionEvent event)
            {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    MainActivity ma = (MainActivity) getActivity();
                    ma.image_time_close();
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            del();
                        }
                    }, 300);
                }
                return true;
            }

        };
        button2.setOnTouchListener(st2);


        return view;
    }
}