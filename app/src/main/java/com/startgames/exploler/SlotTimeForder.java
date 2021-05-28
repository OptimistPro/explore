package com.startgames.exploler;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SlotTimeForder#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SlotTimeForder extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private List<String> mParam2;

    public SlotTimeForder() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SlotTimeForder.
     */
    // TODO: Rename and change types and number of parameters
    public static SlotTimeForder newInstance(String param1, ArrayList<String> param2) {
        SlotTimeForder fragment = new SlotTimeForder();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_slot_time_forder, container, false);

        if (mParam1.equals("Папки")){
            view = inflater.inflate(R.layout.fragment_slot_time_forder, container, false);
        }
        if (mParam1.equals("Документы")){
            view = inflater.inflate(R.layout.fragment_slot_time_forder_doc, container, false);
        }
        if (mParam1.equals("Изображения")){
            view = inflater.inflate(R.layout.fragment_slot_time_forder_img, container, false);
        }
        if (mParam1.equals("Музыка")){
            view = inflater.inflate(R.layout.fragment_slot_time_forder_audio, container, false);
        }
        if (mParam1.equals("Видео")){
            view = inflater.inflate(R.layout.fragment_slot_time_forder_video, container, false);
        }
        if (mParam1.equals("APK файлы")){
            view = inflater.inflate(R.layout.fragment_slot_time_forder_apk, container, false);
        }


        TextView name = view.findViewById(R.id.NamTimeSlot);
        name.setText(mParam1);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        for (int i = mParam2.size()-1;i>=0;i--){
            File file = new File(mParam2.get(i));
            if (file.exists()){
                forder_element catFragment = forder_element.newInstance(file.getPath(), file.getName());
                if (mParam1.equals("Папки")){
                    ft.add(R.id.forderTimes, catFragment);
                }
                if (mParam1.equals("Документы")){
                    ft.add(R.id.forderTimes_doc, catFragment);
                }
                if (mParam1.equals("Изображения")){
                    ft.add(R.id.forderTimes_img, catFragment);
                }
                if (mParam1.equals("Музыка")){
                    ft.add(R.id.forderTimes_audio, catFragment);
                }
                if (mParam1.equals("Видео")){
                    ft.add(R.id.forderTimes_video, catFragment);
                }
                if (mParam1.equals("APK файлы")){
                    ft.add(R.id.forderTimes_apk, catFragment);
                }
            }
        }
        ft.commit();


        return view;
    }
}