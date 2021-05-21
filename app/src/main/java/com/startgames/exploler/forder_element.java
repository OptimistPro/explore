package com.startgames.exploler;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.print.PrintAttributes;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link forder_element#newInstance} factory method to
 * create an instance of this fragment.
 */
public class forder_element extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public forder_element() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment forder_element.
     */
    // TODO: Rename and change types and number of parameters
    public static forder_element newInstance(String param1, String param2) {
        forder_element fragment = new forder_element();
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
    String type_file="";

    void file_open(String path,String type){
        try {
            String localUri = path;
            File file = new File(localUri);
            Uri contentUri = FileProvider.getUriForFile(getActivity(), getActivity().getApplicationContext().getPackageName() + ".provider", file);
            Intent openFileIntent = new Intent(Intent.ACTION_VIEW);
            openFileIntent.setDataAndTypeAndNormalize(contentUri, type);
            openFileIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivity(openFileIntent);
        }catch(Exception e){
            Toast.makeText(getActivity(), "Отсутсвует приложение", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_forder_element, container, false);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            TextView names = view.findViewById(R.id.name_forder);
            names.setText(mParam2);
            String[] st2 = mParam2.split("\\.");
            if (st2.length>1) {
                type_file = st2[st2.length - 1];
                ImageButton but = view.findViewById(R.id.forder_buttom_perexod);
                if (type_file.equals("mp3")) {
                    but.setImageResource(R.drawable.muzik_buttom);
                }
                else if (type_file.equals("MP3")) {
                    type_file="mp3";
                    but.setImageResource(R.drawable.muzik_buttom);
                }
                else if (type_file.equals("mp4")) {
                    but.setImageResource(R.drawable.video_buttom);
                }
                else if (type_file.equals("xlsx")) {
                    but.setImageResource(R.drawable.xslx_buttom);
                }
                else if (type_file.equals("xls")) {
                    but.setImageResource(R.drawable.xslx_buttom);
                }
                else if (type_file.equals("ppt")) {
                    but.setImageResource(R.drawable.ppt_buttom);
                }
                else if (type_file.equals("pptx")) {
                    but.setImageResource(R.drawable.ppt_buttom);
                }
                else if (type_file.equals("doc")) {
                    but.setImageResource(R.drawable.docx_file);
                }
                else if (type_file.equals("apk")) {
                    but.setImageResource(R.drawable.file_apk_buttom);
                }
                else if (type_file.equals("txt")) {
                    but.setImageResource(R.drawable.file_txt_buttom);
                }
                else if (type_file.equals("xml")) {
                    but.setImageResource(R.drawable.file_xml_buttom);
                }
                else if (type_file.equals("png")||type_file.equals("jpg")) {
                    //but.setImageResource(R.drawable.file_txt_buttom);
                    //but.setImageURI(Uri.parse(mParam1));
                    but.setImageDrawable(Drawable.createFromPath(mParam1));
                }
                else if (type_file.equals("pdf")) {
                    but.setImageResource(R.drawable.pdf_file);
                }
                else if (type_file.equals("docx")) {
                    but.setImageResource(R.drawable.docx_file);
                }
                else if (type_file.equals("zip")) {
                    but.setImageResource(R.drawable.zip_file);
                }else{
                    type_file="none";
                    but.setImageResource(R.drawable.file_buttom);
                }
            }
        }


        ImageButton button1 = view.findViewById(R.id.forder_buttom_perexod);
        View.OnTouchListener st = new View.OnTouchListener(){
            public boolean onTouch(View view, MotionEvent event)
            {
                String path = "";
                if (getArguments() != null) {
                    mParam1 = getArguments().getString(ARG_PARAM1);
                    path=mParam1;
                }
                if (event.getAction()==MotionEvent.ACTION_UP){
                    //if (type_file=="apk"||||type_file=="xml") {
                    if (type_file.equals("pdf")) {
                        file_open(path,"application/pdf");
                    }
                    else if (type_file.equals("txt")) {
                        file_open(path,"text/plain");
                    }
                    else if (type_file.equals("docx")) {
                        file_open(path,"application/vnd.openxmlformats-officedocument.wordprocessingml.document");
                    }
                    else if (type_file.equals("doc")) {
                        file_open(path,"application/msword");
                    }
                    else if (type_file.equals("pptx")) {
                        file_open(path,"application/vnd.openxmlformats-officedocument.presentationml.presentation");
                    }
                    else if (type_file.equals("ppt")) {
                        file_open(path,"application/vnd.ms-powerpoint");
                    }
                    else if (type_file.equals("xls")) {
                        file_open(path,"application/vnd.ms-excel");
                    }
                    else if (type_file.equals("xlsx")) {
                        file_open(path,"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                    }
                    else if (type_file.equals("zip")) {
                        file_open(path,"application/zip");
                    }
                    else if (type_file.equals("apk")) {
                        file_open(path,"application/vnd.android.package-archive");
                    }
                    else if (type_file.equals("png")||type_file.equals("jpg")) {
                        file_open(path,"image/*");
                    }
                    else if (type_file.equals("mp3")) {
                        file_open(path,"audio/*");
                    }
                    else if (type_file.equals("mp4")) {
                        file_open(path,"video/*");
                    }
                    else if (type_file.equals("none")) {
                        file_open(path,"application/*");
                    }
                    else {
                        getActivity();
                        MainActivity ma = (MainActivity) getActivity();
                        ma.myMetod(path);
                    }
                }
                return true;
            }

        };
        button1.setOnTouchListener(st);




        return view;
    }
}