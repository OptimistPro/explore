package com.startgames.exploler;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link info_forder#newInstance} factory method to
 * create an instance of this fragment.
 */
public class info_forder extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public info_forder() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment info_forder.
     */
    // TODO: Rename and change types and number of parameters
    public static info_forder newInstance(String param1, String param2) {
        info_forder fragment = new info_forder();
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
        View view = inflater.inflate(R.layout.fragment_info_forder, container, false);


        Button button3 = view.findViewById(R.id.buttom_analiz);
        View.OnTouchListener st3 = new View.OnTouchListener(){
            public boolean onTouch(View view, MotionEvent event)
            {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    info_slot catFragment = info_slot.newInstance("Приложение и их данные", 10);
                    ft.add(R.id.liner_info_slot, catFragment);
                    info_slot catFragment2 = info_slot.newInstance("Изображение и видео",5);
                    ft.add(R.id.liner_info_slot, catFragment2);
                    info_slot catFragment3 = info_slot.newInstance("Аудио (музыка, рингтоны...)", 3);
                    ft.add(R.id.liner_info_slot, catFragment3);
                    info_slot catFragment4 = info_slot.newInstance("Данные кеша", 7);
                    ft.add(R.id.liner_info_slot, catFragment4);
                    info_slot catFragment5 = info_slot.newInstance("Другие файлы", 20);
                    ft.add(R.id.liner_info_slot, catFragment5);
                    info_slot catFragment6 = info_slot.newInstance("Системная память", 11);
                    ft.add(R.id.liner_info_slot, catFragment6);
                    ft.commit();
                }
                return true;
            }

        };
        button3.setOnTouchListener(st3);

        return view;
    }
}