package com.startgames.exploler;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SeachFile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SeachFile extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SeachFile() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SeachFile.
     */
    // TODO: Rename and change types and number of parameters
    public static SeachFile newInstance(String param1, String param2) {
        SeachFile fragment = new SeachFile();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    void del(){
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }
    public void seach_element(ArrayList<String> res, String path, String name){
        Pattern pattern = Pattern.compile(name+"*");
        File directory = new File(path);
        File[] files = directory.listFiles();
        for (int i =0;i<files.length;i++){
            Matcher matcher=pattern.matcher(files[i].getName());

            if (files[i].isDirectory()){
                while (matcher.find()) {
                    res.add(files[i].getPath());
                }
                seach_element(res,files[i].getPath(),name);
            }else{
                while (matcher.find()) {
                    res.add(files[i].getPath());
                }
            }

        }
    }


    public ArrayList<String> seach_file(String name,String path){
        ArrayList<String> res=new ArrayList<String>(0);

        String start_path = "/storage/emulated/0";

        seach_element(res,path,name);


        View view = getView();

        ArrayList<String> t = res;

        ArrayList<File> files=new ArrayList<File>(0);
        for (int i =0;i<t.size();i++){
            File tr = new File(t.get(i));
            files.add(tr);
        }

        FragmentTransaction ft = getFragmentManager().beginTransaction();

        boolean prov = true;
        if (files!=null) {
            for (int i = 0; i < files.size(); i++) {

                if (files.get(i).getName().charAt(0)!='.') {
                    if (files.get(i).isDirectory()){
                        forder_element catFragment = forder_element.newInstance(files.get(i).getPath(), files.get(i).getName());
                        ft.add(R.id.forder_seach, catFragment);
                        prov=false;
                        ((Global) getActivity().getApplication()).vse_forder_g(catFragment);
                    }
                }
                if (files.get(i).getName().charAt(0)!='.') {
                    if (files.get(i).isFile()){
                        forder_element catFragment = forder_element.newInstance(files.get(i).getPath(), files.get(i).getName());
                        ft.add(R.id.forder_seach, catFragment);
                        prov=false;
                        ((Global) getActivity().getApplication()).vse_forder_g(catFragment);
                    }
                }
            }
        }if(prov){
            ErrolForder catFragment = ErrolForder.newInstance("","");
            ft.add(R.id.liner_ls, catFragment);
        }
        ft.commit();

        return res;
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
        View view =inflater.inflate(R.layout.fragment_seach_file, container, false);


        if (!mParam1.equals("")) {
            seach_file(mParam1,mParam2);
        }
        MainActivity ma = (MainActivity) getActivity();


        GridLayout gridLayout =(GridLayout) view.findViewById(R.id.forder_seach);
        gridLayout.setColumnCount(ma.size_windos());


        ImageButton button2 = view.findViewById(R.id.close_forder_time);
        View.OnTouchListener st2 = new View.OnTouchListener(){
            public boolean onTouch(View view, MotionEvent event)
            {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    MainActivity ma = (MainActivity) getActivity();
                    ma.seach_close();
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
        final EditText namekey= (EditText) view.findViewById(R.id.text_seach);
        namekey.setText(mParam1);
        ImageButton button3 = view.findViewById(R.id.seac_panel_buttom);
        View.OnTouchListener st3 = new View.OnTouchListener(){
            public boolean onTouch(View view, MotionEvent event)
            {
                if (event.getAction()==MotionEvent.ACTION_DOWN){

                    //String ty = String.valueOf(namekey.getText());
                    //seach_file(String.valueOf(namekey.getText()));
                    //FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                   // SeachFile catFragment = SeachFile.newInstance(String.valueOf(namekey.getText()),"");
                    //ft.replace(R.id.seach_fraim, catFragment);
                   // ft.commit();
                    if (!String.valueOf(namekey.getText()).equals("")) {
                        MainActivity ma = (MainActivity) getActivity();
                        ma.seach_start(String.valueOf(namekey.getText()),mParam2);
                    }
                }
                return true;
            }

        };
        button3.setOnTouchListener(st3);
        return view;
    }
}