package com.startgames.exploler;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link toolbar#newInstance} factory method to
 * create an instance of this fragment.
 */
public class toolbar extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public toolbar() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment toolbar.
     */
    // TODO: Rename and change types and number of parameters
    public static toolbar newInstance(String param1, String param2) {
        toolbar fragment = new toolbar();
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
        View view=inflater.inflate(R.layout.fragment_toolbar, container, false);


        FrameLayout button1 = view.findViewById(R.id.fon_panel);
        View.OnTouchListener st = new View.OnTouchListener(){
            public boolean onTouch(View view, MotionEvent event)
            {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    MainActivity ma = (MainActivity) getActivity();
                    ma.tool_bar_no();
                }
                return true;
            }

        };
        button1.setOnTouchListener(st);

        LinearLayout button2 = view.findViewById(R.id.setting_pressed);
        View.OnTouchListener st2 = new View.OnTouchListener(){
            public boolean onTouch(View view, MotionEvent event)
            {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    MainActivity ma = (MainActivity) getActivity();
                    ma.tool_bar_no();
                    ma.setting_pressed();
                }
                return true;
            }

        };
        button2.setOnTouchListener(st2);

        LinearLayout button3 = view.findViewById(R.id.new_forder_tool);
        View.OnTouchListener st3 = new View.OnTouchListener(){
            public boolean onTouch(View view, MotionEvent event)
            {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    MainActivity ma = (MainActivity) getActivity();
                    ma.tool_bar_no();
                    ma.window_new_forder();
                }
                return true;
            }

        };
        button3.setOnTouchListener(st3);

        LinearLayout button4 = view.findViewById(R.id.new_file_tool);
        View.OnTouchListener st4 = new View.OnTouchListener(){
            public boolean onTouch(View view, MotionEvent event)
            {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    MainActivity ma = (MainActivity) getActivity();
                    ma.tool_bar_no();
                    ma.window_new_file();
                }
                return true;
            }

        };
        button4.setOnTouchListener(st4);

        LinearLayout button5 = view.findViewById(R.id.global_seach);
        View.OnTouchListener st5 = new View.OnTouchListener(){
            public boolean onTouch(View view, MotionEvent event)
            {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    MainActivity ma = (MainActivity) getActivity();
                    ma.tool_bar_no();
                    ma.seach_pressed("","/storage/emulated/0");
                }
                return true;
            }

        };
        button5.setOnTouchListener(st5);
        return view;
    }
}