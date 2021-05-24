package com.startgames.exploler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    public String path_back="/storage/emulated/0";

    public void path_back_new(String path){
        path_back=path;
    }
    public String path_obj="/storage/emulated/0";

    private imega_view_smotr listener;
    private control_forder_interfeis listener_contr;

    public  void myMetod(String path){
        path_obj=path;
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        forder_manager catFragment = forder_manager.newInstance(path);
        ft.replace(R.id.block_layout, catFragment);
        ft.commit();
    }

    ////////////////////////////////////////
    private final int REQUEST_PERMISSION_PHONE_STATE=1;
    private void showPhoneStatePermission() {
        int permissionCheck = ContextCompat.checkSelfPermission(
                this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                showExplanation("Необходимо разрешение", "Использование файловой системы", Manifest.permission.WRITE_EXTERNAL_STORAGE, REQUEST_PERMISSION_PHONE_STATE);
            } else {
                requestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, REQUEST_PERMISSION_PHONE_STATE);
            }
        } else {
            //Toast.makeText(MainActivity.this, "Permission (already) Granted!", Toast.LENGTH_SHORT).show();
            myMetod("/storage/emulated/0");
        }
    }

    public int size_windos(){
        Display display =getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return  size.x/200;
    }


    public void tool_bar_no(){
        findViewById(R.id.panel_buttom).startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.toolbarclose));
        findViewById(R.id.fon_panel_fon).startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fon_alfa_close));
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                findViewById(R.id.toolbar_l).setVisibility(View.INVISIBLE);
                ((Global) getApplication()).setMenu_close(false);
            }
        }, 300);
    }

    public void setting_pressed(){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Setting catFragment = Setting.newInstance("","");
        ft.add(R.id.setting_block, catFragment);
       // FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        //ft.replace(R.id.setting_block, new Setting());
        ft.commit();
        findViewById(R.id.setting_block).setLeft(0);
        findViewById(R.id.setting_block).startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.open_setting));
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                findViewById(R.id.liner).setLeft(2000);
            }
        }, 300);
    }

    public void setting_close(){
        findViewById(R.id.setting_block).startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.close_setting));
        findViewById(R.id.liner).setLeft(0);
        findViewById(R.id.liner).startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.toolbaropen));
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                //findViewById(R.id.setting_block).setVisibility(View.INVISIBLE);
                //((Global) getApplication()).setMenu_close(false);
                findViewById(R.id.setting_block).setLeft(2000);
            }
        }, 300);
    }

    public void image_pressed(String path,String name){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        foto_smotr catFragment = foto_smotr.newInstance(path,name);
        ft.add(R.id.images_smotr, catFragment);
        this.listener=catFragment;
        ft.commit();
        findViewById(R.id.images_smotr).setLeft(0);
        findViewById(R.id.images_smotr).startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.open_setting));
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                findViewById(R.id.liner).setLeft(2000);
                ((Global) getApplication()).setimages_close(true);
            }
        }, 300);
    }

    public void image_close(){
        findViewById(R.id.images_smotr).startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.close_setting));
        findViewById(R.id.liner).setLeft(0);
        findViewById(R.id.liner).startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.toolbaropen));
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                findViewById(R.id.images_smotr).setLeft(2000);
                ((Global) getApplication()).setimages_close(false);
                listener.del();
            }
        }, 300);
    }



    public void new_forder_rel(String names){
        File forder = new File(path_obj+File.separator+names);
        if (!forder.exists()){
            forder.mkdir();
            myMetod(path_obj+"/"+names);
        }else{
            Toast.makeText(MainActivity.this, "Папка уже существует", Toast.LENGTH_SHORT).show();
        }

    }


    public void window_new_forder(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = (ConstraintLayout) getLayoutInflater().inflate(R.layout.new_forder_l,null);
        final EditText namekey= (EditText) view.findViewById(R.id.text_new_forder);
        builder.setMessage("Введите имя папки")
                .setTitle("Новая папка")
                .setView(view)
                .setPositiveButton("ок", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new_forder_rel(String.valueOf(namekey.getText()));
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

    public void new_file_rel(String names){
        File forder = new File(path_obj,names);
        if (!forder.exists()){
            try{
            forder.createNewFile();
            myMetod(path_obj);}
            catch (IOException e){
                Toast.makeText(MainActivity.this, "Ошибка создания файла", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(MainActivity.this, "Файл уже существует", Toast.LENGTH_SHORT).show();
        }

    }

    public void window_new_file(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = (ConstraintLayout) getLayoutInflater().inflate(R.layout.new_file,null);
        final EditText namekey= (EditText) view.findViewById(R.id.text_new_forder);
        builder.setMessage("Введите имя файла")
                .setTitle("Новый файл")
                .setView(view)
                .setPositiveButton("ок", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new_file_rel(String.valueOf(namekey.getText()));
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

    public void vadel_forders(){
        if (((Global) getApplication()).vabor_size()<=0){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        control_forder_file catFragment = control_forder_file.newInstance("","");
        ft.add(R.id.control_forder, catFragment);
        this.listener_contr=catFragment;
        ft.commit();
        }else{
            listener_contr.update();
        }

    }

    @Override
    public void onBackPressed(){
        if (((Global) this.getApplication()).getMenu_close()) {
            tool_bar_no();
        }
        else if(((Global) this.getApplication()).getimages_close()){
            image_close();
        }
        else{
            myMetod(path_back);
        }
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            String permissions[],
            int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSION_PHONE_STATE:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //Toast.makeText(MainActivity.this, "Permission Granted!", Toast.LENGTH_SHORT).show();
                    myMetod("/storage/emulated/0");
                } else {
                    System.exit(0);
                    //Toast.makeText(MainActivity.this, "Permission FALSE!", Toast.LENGTH_SHORT).show();
                }
        }
    }

    private void showExplanation(String title,
                                 String message,
                                 final String permission,
                                 final int permissionRequestCode) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        requestPermission(permission, permissionRequestCode);
                    }
                });
        builder.create().show();
    }

    private void requestPermission(String permissionName, int permissionRequestCode) {
        ActivityCompat.requestPermissions(this,
                new String[]{permissionName}, permissionRequestCode);
    }
    //////////////////////////////////////////





    private Animation tool_b;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showPhoneStatePermission();



        //((Global) this.getApplication()).setSomeVariable("foo"); // записать
        //String s = ((Global) this.getApplication()).getSomeVariable(); //запросить
        ImageButton button1 = findViewById(R.id.forder_buttom);
        View.OnTouchListener st = new View.OnTouchListener(){
            public boolean onTouch(View view, MotionEvent event)
            {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    if (!((Global) getApplication()).getMenu_close()) {

                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        //time_layout catFragment = time_layout.newInstance("Банкомат","Банкомат");
                        //ft.add(R.id.liner, catFragment);
                        //ft.add(R.id.fragment, new time_layout());

                        ft.replace(R.id.block_layout, new forder_manager());
                        ft.commit();
                        ImageButton but = findViewById(R.id.forder_buttom);
                        but.setImageResource(R.drawable.forder_pressed);
                        ImageButton but2 = findViewById(R.id.time_buttom);
                        but2.setImageResource(R.drawable.clock);
                        //ImageButton but3 = findViewById(R.id.forder_buttom);
                        // but3.setImageResource(R.drawable.forder_pressed);
                        ImageButton but3 = findViewById(R.id.info_panel_fragment);
                        but3.setImageResource(R.drawable.kist);
                    }
                }
                return true;
            }

        };
        button1.setOnTouchListener(st);

        ImageButton button2 = findViewById(R.id.time_buttom);
        View.OnTouchListener st2 = new View.OnTouchListener(){
            public boolean onTouch(View view, MotionEvent event)
            {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    if (!((Global) getApplication()).getMenu_close()) {
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.block_layout, new time_layout());
                        ft.commit();
                        ImageButton but = findViewById(R.id.forder_buttom);
                        but.setImageResource(R.drawable.forder);
                        ImageButton but2 = findViewById(R.id.time_buttom);
                        but2.setImageResource(R.drawable.clock_pressed);
                        ImageButton but3 = findViewById(R.id.info_panel_fragment);
                        but3.setImageResource(R.drawable.kist);
                    }
                }
                return true;
            }

        };
        button2.setOnTouchListener(st2);

        ImageButton button3 = findViewById(R.id.menu_buttom);
        View.OnTouchListener st3 = new View.OnTouchListener(){
            public boolean onTouch(View view, MotionEvent event)
            {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    if (!((Global) getApplication()).getMenu_close()) {
                        findViewById(R.id.toolbar_l).setVisibility(View.VISIBLE);
                        findViewById(R.id.panel_buttom).startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.toolbaropen));
                        findViewById(R.id.fon_panel_fon).startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fon_alfa_open));
                        ((Global) getApplication()).setMenu_close(true);
                    }
                }
                return true;
            }

        };
        button3.setOnTouchListener(st3);


        ImageButton button4 = findViewById(R.id.info_panel_fragment);
        View.OnTouchListener st4 = new View.OnTouchListener(){
            public boolean onTouch(View view, MotionEvent event)
            {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    if (!((Global) getApplication()).getMenu_close()) {
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.block_layout, new info_forder());
                        ft.commit();
                        ImageButton but = findViewById(R.id.forder_buttom);
                        but.setImageResource(R.drawable.forder);
                        ImageButton but2 = findViewById(R.id.time_buttom);
                        but2.setImageResource(R.drawable.clock);
                        ImageButton but3 = findViewById(R.id.info_panel_fragment);
                        but3.setImageResource(R.drawable.kist_pressed);
                    }
                }
                return true;
            }

        };
        button4.setOnTouchListener(st4);
    }
}