package com.startgames.exploler;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link time_layout#newInstance} factory method to
 * create an instance of this fragment.
 */
public class time_layout extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public time_layout() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment time_layout.
     */
    // TODO: Rename and change types and number of parameters
    public static time_layout newInstance(String param1, String param2) {
        time_layout fragment = new time_layout();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_time_layout, container, false);
        ImageButton button2 = view.findViewById(R.id.dowload_forder_buttom);
        View.OnTouchListener st2 = new View.OnTouchListener(){
            public boolean onTouch(View view, MotionEvent event)
            {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    if (!((Global) getActivity().getApplication()).getMenu_close()) {
                        MainActivity ma = (MainActivity) getActivity();
                        ma.myMetod("/storage/emulated/0/Download/");
                    }
                }
                return true;
            }

        };
        button2.setOnTouchListener(st2);

        ImageButton button3 = view.findViewById(R.id.image_time_buttom);
        View.OnTouchListener st3 = new View.OnTouchListener(){
            public boolean onTouch(View view, MotionEvent event)
            {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    if (!((Global) getActivity().getApplication()).getMenu_close()) {
                        MainActivity ma = (MainActivity) getActivity();
                        ma.image_time_pressed("image");
                    }
                }
                return true;
            }

        };
        button3.setOnTouchListener(st3);

        ImageButton button8 = view.findViewById(R.id.zip_bottum_time);
        View.OnTouchListener st8 = new View.OnTouchListener(){
            public boolean onTouch(View view, MotionEvent event)
            {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    if (!((Global) getActivity().getApplication()).getMenu_close()) {
                        MainActivity ma = (MainActivity) getActivity();
                        ma.image_time_pressed("zip");
                    }
                }
                return true;
            }

        };
        button8.setOnTouchListener(st8);

        ImageButton button4 = view.findViewById(R.id.video_buttom_time);
        View.OnTouchListener st4 = new View.OnTouchListener(){
            public boolean onTouch(View view, MotionEvent event)
            {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    if (!((Global) getActivity().getApplication()).getMenu_close()) {
                        MainActivity ma = (MainActivity) getActivity();
                        ma.image_time_pressed("video");
                    }
                }
                return true;
            }

        };
        button4.setOnTouchListener(st4);

        ImageButton button5 = view.findViewById(R.id.document_buttom_time);
        View.OnTouchListener st5 = new View.OnTouchListener(){
            public boolean onTouch(View view, MotionEvent event)
            {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    if (!((Global) getActivity().getApplication()).getMenu_close()) {
                        MainActivity ma = (MainActivity) getActivity();
                        ma.image_time_pressed("document");
                    }
                }
                return true;
            }

        };
        button5.setOnTouchListener(st5);

        ImageButton button6 = view.findViewById(R.id.myzik_buttom_time);
        View.OnTouchListener st6 = new View.OnTouchListener(){
            public boolean onTouch(View view, MotionEvent event)
            {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    if (!((Global) getActivity().getApplication()).getMenu_close()) {
                        MainActivity ma = (MainActivity) getActivity();
                        ma.image_time_pressed("myzik");
                    }
                }
                return true;
            }

        };
        button6.setOnTouchListener(st6);

        ImageButton button7 = view.findViewById(R.id.apk_buttom_time);
        View.OnTouchListener st7 = new View.OnTouchListener(){
            public boolean onTouch(View view, MotionEvent event)
            {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    if (!((Global) getActivity().getApplication()).getMenu_close()) {
                        MainActivity ma = (MainActivity) getActivity();
                        ma.image_time_pressed("apk");
                    }
                }
                return true;
            }

        };
        button7.setOnTouchListener(st7);

        return view;
    }
}