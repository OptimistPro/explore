package com.startgames.exploler;

import android.net.Uri;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link foto_smotr#newInstance} factory method to
 * create an instance of this fragment.
 */
public class foto_smotr extends Fragment implements imega_view_smotr {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public foto_smotr() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment foto_smotr.
     */
    // TODO: Rename and change types and number of parameters
    public static foto_smotr newInstance(String param1, String param2) {
        foto_smotr fragment = new foto_smotr();
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
    public void del(){
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }

    private boolean black=false;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_foto_smotr, container, false);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            ImageView t = view.findViewById(R.id.foto_vidget);
            t.setImageURI(Uri.parse(mParam1));
            TextView t2 = view.findViewById(R.id.foto_name);
            t2.setText(mParam2);
        }



        ImageButton button2 = view.findViewById(R.id.close_buttom);
        View.OnTouchListener st2 = new View.OnTouchListener(){
            public boolean onTouch(View view, MotionEvent event)
            {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    MainActivity ma = (MainActivity) getActivity();
                    ma.image_close();
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

        ConstraintLayout button3 = view.findViewById(R.id.fon_list);
        View.OnTouchListener st3 = new View.OnTouchListener(){
            public boolean onTouch(final View view, MotionEvent event)
            {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    if (black){
                     ConstraintLayout thi = view.findViewById(R.id.fon_list);
                     thi.setBackgroundColor(getResources().getColor(R.color.colorAccent_bel,null));
                     view.findViewById(R.id.toolbar_images).setVisibility(View.VISIBLE);
                        view.findViewById(R.id.toolbar_images).startAnimation(AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.images_bal_open));
                     black=false;
                    }
                    else{
                        ConstraintLayout thi = view.findViewById(R.id.fon_list);
                        thi.setBackgroundColor(getResources().getColor(R.color.colorAccent_black,null));
                        view.findViewById(R.id.toolbar_images).startAnimation(AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.images_bal_close));
                        new Timer().schedule(new TimerTask() {
                            @Override
                            public void run() {
                                view.findViewById(R.id.toolbar_images).setVisibility(View.INVISIBLE);
                            }
                        }, 300);
                        black=true;
                    }
                }
                return true;
            }

        };
        button3.setOnTouchListener(st3);



        return view;
    }
}