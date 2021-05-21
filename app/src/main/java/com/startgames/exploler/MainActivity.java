package com.startgames.exploler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public String path_back="/storage/emulated/0";

    public void path_back_new(String path){
        path_back=path;
    }


    public  void myMetod(String path){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        forder_manager catFragment = forder_manager.newInstance(path);
        ft.replace(R.id.block_layout, catFragment);
        ft.commit();
    }

    ////////////////////////////////////////
    private final int REQUEST_PERMISSION_PHONE_STATE=1;
    private void showPhoneStatePermission() {
        int permissionCheck = ContextCompat.checkSelfPermission(
                this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                showExplanation("Permission Needed", "Rationale", Manifest.permission.READ_EXTERNAL_STORAGE, REQUEST_PERMISSION_PHONE_STATE);
            } else {
                requestPermission(Manifest.permission.READ_EXTERNAL_STORAGE, REQUEST_PERMISSION_PHONE_STATE);
            }
        } else {
            //Toast.makeText(MainActivity.this, "Permission (already) Granted!", Toast.LENGTH_SHORT).show();
            myMetod("/storage/emulated/0");
        }
    }

    @Override
    public void onBackPressed(){
        myMetod(path_back);
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
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.block_layout, new time_layout());
                    ft.commit();
                    ImageButton but = findViewById(R.id.forder_buttom);
                    but.setImageResource(R.drawable.forder);
                    ImageButton but2 = findViewById(R.id.time_buttom);
                    but2.setImageResource(R.drawable.clock_pressed);
                }
                return true;
            }

        };
        button2.setOnTouchListener(st2);
    }
}