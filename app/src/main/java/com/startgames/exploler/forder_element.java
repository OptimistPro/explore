package com.startgames.exploler;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link forder_element#newInstance} factory method to
 * create an instance of this fragment.
 */
public class forder_element extends Fragment implements forder_file{

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

    public forder_element ts = this;
    public String name_file="";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
        ts=this;
        name_file=mParam2;
    }
    String type_file="";
    boolean vadel_prov = false;



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

    public void del_vabor(){
        View view = getView();
        ImageView t =view.findViewById(R.id.vadel);
        t.setImageResource(R.drawable.vabor_forder);
    }


    public void kryg(boolean provs){
        View view = getView();
        if(provs){
            view.findViewById(R.id.vadel).setVisibility(View.VISIBLE);
        }else{
            view.findViewById(R.id.vadel).setVisibility(View.INVISIBLE);
        }
    }

    public void del_object(){
        File file = new File(mParam1);
        file.delete();
    }


    public void copyFile(File sourceFile, File destFile) throws IOException {
        if(!destFile.exists()) {
            destFile.createNewFile();
        }

        FileChannel source = null;
        FileChannel destination = null;

        try {
            source = new FileInputStream(sourceFile).getChannel();
            destination = new FileOutputStream(destFile).getChannel();
            destination.transferFrom(source, 0, source.size());
        }
        finally {
            if(source != null) {
                source.close();
            }
            if(destination != null) {
                destination.close();
            }
        }
    }

    public void copyForder(File sourceFile, File destFile) throws IOException {
        if(!destFile.exists()) {
            destFile.mkdir();
        }

        FileChannel source = null;
        FileChannel destination = null;

        try {
            source = new FileInputStream(sourceFile).getChannel();
            destination = new FileOutputStream(destFile).getChannel();
            destination.transferFrom(source, 0, source.size());
        }
        finally {
            if(source != null) {
                source.close();
            }
            if(destination != null) {
                destination.close();
            }
        }
    }



    public void cope_element(String path,String cope_direct){
        File directory = new File(path);
        if (directory.isFile()){
            try {
                copyFile(directory, new File(cope_direct, directory.getName()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            try {
                copyForder(directory, new File(cope_direct+"/"+ directory.getName()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        File[] files = directory.listFiles();
        for (int i =0;i<files.length;i++){
            if (files[i].isDirectory()){
              //  try {
               //     copyForder(files[i], new File(cope_direct+"/"+ directory.getName()+"/"+ files[i].getName()));
               // } catch (IOException e) {
                //    e.printStackTrace();
                //}
                cope_element(files[i].getPath(),cope_direct+"/"+ directory.getName());
            }else{
                try {
                    copyFile(new File(files[i].getPath()), new File(cope_direct+"/"+ directory.getName(), files[i].getName()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void copy_filse(){
        try {
            String new_path=((Global) getActivity().getApplication()).getPath_cope();
            if (new File(mParam1).isFile()) {
                copyFile(new File(mParam1), new File(new_path, mParam2));
            }else{
                //copyForder(new File(mParam1), new File(new_path+"/"+ mParam2));
                cope_element(mParam1,new_path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void rename_object(String name){
        MainActivity ma = (MainActivity) getActivity();
        String pt = ma.get_real_forder();
        File file = new File(mParam1);
        if (file.isFile()) {
            file.renameTo(new File(pt, name));
        }else{
            file.renameTo(new File(pt+File.separator+name));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.fragment_forder_element, container, false);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            TextView names = view.findViewById(R.id.name_forder);
            names.setText(mParam2);
            File file = new File(mParam1);
            if (file.isFile()) {
                String[] st2 = mParam2.split("\\.");
                if (st2.length > 1) {
                    type_file = st2[st2.length - 1];
                    ImageButton but = view.findViewById(R.id.forder_buttom_perexod);

                    //Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.forder_buttom);
                    //bitmap.setPixel(0,0, Color.argb(1,0,0,0));
                   // but.setImageBitmap(bitmap);

                    if (type_file.equals("mp3")) {
                        but.setImageResource(R.drawable.muzik_buttom);
                    } else if (type_file.equals("MP3")) {
                        type_file = "mp3";
                        but.setImageResource(R.drawable.muzik_buttom);
                    } else if (type_file.equals("mp4")||type_file.equals("MP4")) {
                        type_file = "mp4";
                        but.setImageResource(R.drawable.video_buttom);
                    } else if (type_file.equals("xlsx")) {
                        but.setImageResource(R.drawable.xslx_buttom);
                    } else if (type_file.equals("xls")) {
                        but.setImageResource(R.drawable.xslx_buttom);
                    } else if (type_file.equals("ppt")) {
                        but.setImageResource(R.drawable.ppt_buttom);
                    } else if (type_file.equals("pptx")) {
                        but.setImageResource(R.drawable.ppt_buttom);
                    } else if (type_file.equals("doc")) {
                        but.setImageResource(R.drawable.docx_file);
                    } else if (type_file.equals("apk")) {
                        but.setImageResource(R.drawable.file_apk_buttom);
                    } else if (type_file.equals("txt")) {
                        but.setImageResource(R.drawable.file_txt_buttom);
                    } else if (type_file.equals("xml")) {
                        but.setImageResource(R.drawable.file_xml_buttom);
                    } else if (type_file.equals("png") || type_file.equals("jpg")) {
                        //but.setImageResource(R.drawable.file_txt_buttom);
                        //but.setImageURI(Uri.parse(mParam1));
                        but.setImageDrawable(Drawable.createFromPath(mParam1));
                    } else if (type_file.equals("pdf")) {
                        but.setImageResource(R.drawable.pdf_file);
                    } else if (type_file.equals("docx")) {
                        but.setImageResource(R.drawable.docx_file);
                    } else if (type_file.equals("zip")) {
                        but.setImageResource(R.drawable.zip_file);
                    } else {
                        type_file = "none";
                        but.setImageResource(R.drawable.file_buttom);
                    }
                }else{
                    ImageButton but = view.findViewById(R.id.forder_buttom_perexod);
                    type_file = "none";
                    but.setImageResource(R.drawable.file_buttom);
                }
            }
        }


        ImageButton button1 = view.findViewById(R.id.forder_buttom_perexod);


        button1.setOnLongClickListener(
                new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                       // Toast.makeText(getActivity(), "Файл уже существует", Toast.LENGTH_SHORT).show();
                        if (!((Global) getActivity().getApplication()).getCopy_view()) {
                            ImageView iv = view.findViewById(R.id.vadel);
                            iv.setImageResource(R.drawable.vabor_forder_open);
                            MainActivity ma = (MainActivity) getActivity();
                            ma.vadel_forders();
                            ((Global) getActivity().getApplication()).setvadel_forder(true);
                            ((Global) getActivity().getApplication()).vabor_forder_g(ts);
                            ((Global) getActivity().getApplication()).del_vse(true);
                            vadel_prov = true;
                        }
                        return true;
                    }
                }
        );

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = "";
                if (getArguments() != null) {
                    mParam1 = getArguments().getString(ARG_PARAM1);
                    path=mParam1;
                }
                    if (!((Global) getActivity().getApplication()).getMenu_close()&&!((Global) getActivity().getApplication()).getvadel_forder()) {

                        if (type_file.equals("pdf")) {
                            ((Global) getActivity().getApplication()).add_doc_memory(mParam1);
                            file_open(path, "application/pdf");
                        } else if (type_file.equals("txt")) {
                            ((Global) getActivity().getApplication()).add_doc_memory(mParam1);
                            file_open(path, "text/plain");
                        } else if (type_file.equals("docx")) {
                            ((Global) getActivity().getApplication()).add_doc_memory(mParam1);
                            file_open(path, "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
                        } else if (type_file.equals("doc")) {
                            ((Global) getActivity().getApplication()).add_doc_memory(mParam1);
                            file_open(path, "application/msword");
                        } else if (type_file.equals("pptx")) {
                            ((Global) getActivity().getApplication()).add_doc_memory(mParam1);
                            file_open(path, "application/vnd.openxmlformats-officedocument.presentationml.presentation");
                        } else if (type_file.equals("ppt")) {
                            ((Global) getActivity().getApplication()).add_doc_memory(mParam1);
                            file_open(path, "application/vnd.ms-powerpoint");
                        } else if (type_file.equals("xls")) {
                            ((Global) getActivity().getApplication()).add_doc_memory(mParam1);
                            file_open(path, "application/vnd.ms-excel");
                        } else if (type_file.equals("xlsx")) {
                            ((Global) getActivity().getApplication()).add_doc_memory(mParam1);
                            file_open(path, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                        } else if (type_file.equals("zip")) {
                            file_open(path, "application/zip");
                        } else if (type_file.equals("apk")) {
                            ((Global) getActivity().getApplication()).add_apk_memory(mParam1);
                            file_open(path, "application/vnd.android.package-archive");
                        } else if (type_file.equals("png") || type_file.equals("jpg")) {
                            //file_open(path, "image/*");
                            ((Global) getActivity().getApplication()).add_images_memory(mParam1);
                            MainActivity ma = (MainActivity) getActivity();
                            ma.image_pressed(mParam1,mParam2);
                        } else if (type_file.equals("mp3")) {
                            ((Global) getActivity().getApplication()).add_audio_memory(mParam1);
                            file_open(path, "audio/*");
                        } else if (type_file.equals("mp4")) {
                            ((Global) getActivity().getApplication()).add_video_memory(mParam1);
                            file_open(path, "video/*");
                        } else if (type_file.equals("none")) {
                            file_open(path, "application/*");
                        } else {
                            ((Global) getActivity().getApplication()).add_forder_memory(mParam1);
                            MainActivity ma = (MainActivity) getActivity();
                            ma.myMetod(path);
                        }
                }else if (((Global) getActivity().getApplication()).getvadel_forder()&& (!((Global) getActivity().getApplication()).getCopy_view())){

                        if (vadel_prov){
                            ((Global) getActivity().getApplication()).vabor_forder_del(ts);
                            del_vabor();
                            vadel_prov = false;
                            MainActivity ma = (MainActivity) getActivity();
                            ma.vadel_forders();
                        }
                        else {
                            ImageView iv = view.findViewById(R.id.vadel);
                            iv.setImageResource(R.drawable.vabor_forder_open);
                            ((Global) getActivity().getApplication()).setvadel_forder(true);
                            ((Global) getActivity().getApplication()).vabor_forder_g(ts);
                            vadel_prov = true;
                            MainActivity ma = (MainActivity) getActivity();
                            ma.vadel_forders();
                        }
                    }
                    //if (((Global) getActivity().getApplication()).getCopy_view()){
                   //     MainActivity ma = (MainActivity) getActivity();
                  //      ma.myMetod(path);
//
               // }

            }
        });


        return view;
    }
}