package com.startgames.exploler;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link control_forder_file#newInstance} factory method to
 * create an instance of this fragment.
 */
public class control_forder_file extends Fragment implements control_forder_interfeis {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public control_forder_file() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment control_forder_file.
     */
    // TODO: Rename and change types and number of parameters
    public static control_forder_file newInstance(String param1, String param2) {
        control_forder_file fragment = new control_forder_file();
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
        MainActivity ma = (MainActivity) getActivity();
        ma.del_control_forder();
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }

    public void update(){
        View view =getView();
        TextView t = view.findViewById(R.id.col_vabor);
        int g = ((Global) getActivity().getApplication()).vabor_size();
        t.setText("Выбрано:"+String.valueOf(g));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_control_forder_file, container, false);

        ImageButton button4 = view.findViewById(R.id.close_control_panel);
        View.OnTouchListener st4 = new View.OnTouchListener(){
            public boolean onTouch(View view, MotionEvent event)
            {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    ((Global) getActivity().getApplication()).del_vabors();
                    ((Global) getActivity().getApplication()).del_vse(false);
                    del();
                }
                return true;
            }

        };
        button4.setOnTouchListener(st4);

        ImageButton button = view.findViewById(R.id.delete_buttom);
        View.OnTouchListener st = new View.OnTouchListener(){
            public boolean onTouch(View view, MotionEvent event)
            {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    if (((Global) getActivity().getApplication()).vabor_size()>=1) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        //View views = (ConstraintLayout) getLayoutInflater().inflate(R.layout.new_file,null);
                        //final EditText namekey= (EditText) view.findViewById(R.id.text_new_forder);
                        builder.setMessage("Вы действительно хотите удалить файлы")
                                //.setTitle("Новый файл")
                                //.setView(view)
                                .setPositiveButton("ок", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        ((Global) getActivity().getApplication()).del_vabors_object();
                                        MainActivity ma = (MainActivity) getActivity();
                                        ma.update_forder();
                                        del();
                                    }
                                })
                                .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                        builder.show();
                    }
                }
                return true;
            }

        };
        button.setOnTouchListener(st);

        ImageButton button2 = view.findViewById(R.id.rename_buttom);
        View.OnTouchListener st2 = new View.OnTouchListener(){
            public boolean onTouch(View view, MotionEvent event)
            {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    if (((Global) getActivity().getApplication()).vabor_size()==1) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        View views = (ConstraintLayout) getLayoutInflater().inflate(R.layout.new_file, null);
                        final EditText namekey = (EditText) views.findViewById(R.id.text_new_forder);
                        namekey.setText(((Global) getActivity().getApplication()).name_object_vadel());
                        builder.setMessage("Введите новое название файла")
                                .setTitle("Переименование файла")
                                .setView(views)
                                .setPositiveButton("ок", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        ((Global) getActivity().getApplication()).rename_vabors_object(namekey.getText().toString());
                                        MainActivity ma = (MainActivity) getActivity();
                                        ma.update_forder();
                                        del();
                                    }
                                })
                                .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                        builder.show();
                    }
                }
                return true;
            }

        };
        button2.setOnTouchListener(st2);
        return view;
    }
}