package com.startgames.exploler;

import android.app.Application;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Global extends Application {

    private String someVariable = "byp";
    public String getSomeVariable() {
        return someVariable;
    }
    public void setSomeVariable(String someVariable) {
        this.someVariable = someVariable;
    }

    private boolean menu_close=false;
    public boolean getMenu_close() {
        return menu_close;
    }
    public void setMenu_close(boolean menu_closeVariable) {
        this.menu_close = menu_closeVariable;
    }


    private boolean images_close=false;
    public boolean getimages_close() {
        return images_close;
    }
    public void setimages_close(boolean menu_closeVariable) {
        this.images_close = menu_closeVariable;
    }

    private boolean vadel_forder=false;
    public boolean getvadel_forder() {
        return vadel_forder;
    }
    public void setvadel_forder(boolean menu_closeVariable) {
        this.vadel_forder = menu_closeVariable;
    }
    public ArrayList<forder_element> vabor_forder=new ArrayList<>();
    public void vabor_forder_g(forder_element obj){
        //obj.del_vabor();
        vabor_forder.add(obj);
    }
    public String name_object_vadel(){
        return vabor_forder.get(0).name_file;
    }
    public void vabor_forder_del(forder_element obj){
        //obj.del_vabor();
        vabor_forder.remove(obj);
        //vabor_forder.add(obj);
    }
    public void del_vabors(){
        for (int i = 0;i<vabor_forder.size();i++){
            vabor_forder.get(i).del_vabor();
        }
        vadel_forder=false;
        vabor_forder.clear();
    }
    public int vabor_size(){
        return vabor_forder.size();
    }
    public void del_vabors_object(){
        for (int i = 0;i<vabor_forder.size();i++){
            vabor_forder.get(i).del_object();
        }
        del_vabors();
        del_vse(false);
    }

    public void rename_vabors_object(String name){
        for (int i = 0;i<vabor_forder.size();i++){
            vabor_forder.get(i).rename_object(name);
        }
        del_vabors();
        del_vse(false);
    }

    public ArrayList<forder_element> vse_forder=new ArrayList<>();
    public void vse_forder_g(forder_element obj){
        vse_forder.add(obj);
    }
    public void del_vse(boolean provs){
        for (int i = 0;i<vse_forder.size();i++){
            vse_forder.get(i).kryg(provs);
        }

    }
    public void vse_clear(){
        vse_forder.clear();
    }

    public String path_reals = "/storage/emulated/0";
    public String getPath_reals(){
        return path_reals;
    }
    public void setPath_reals(String menu_closeVariable) {
        this.path_reals = menu_closeVariable;
    }


    public String path_cope = "/storage/emulated/0";
    public String getPath_cope(){
        return path_cope;
    }
    public void setPath_cope(String menu_closeVariable) {
        this.path_cope = menu_closeVariable;
    }



    private boolean Copy_view=false;
    public boolean getCopy_view() {
        return Copy_view;
    }
    public void setCopy_view(boolean menu_closeVariable) {
        this.Copy_view = menu_closeVariable;
    }

    public void copy_files(){
        for (int i = 0;i<vabor_forder.size();i++){
            vabor_forder.get(i).copy_filse();
        }
        del_vabors();
        del_vse(false);
    }


    private ArrayList<String> images_memory=new ArrayList<>();
    public void add_images_memory(String path){
        boolean path_seach = true;
        for (int i = 0;i<images_memory.size();i++){
            if (images_memory.get(i).equals(path)){
                path_seach=false;
                break;
            }
        }
        if (path_seach) {
            if (images_memory.size() < 20) {
                images_memory.add(path);
            } else {
                for (int i = 1; i < images_memory.size() - 1; i++) {
                    images_memory.set(i - 1, images_memory.get(i));
                }
                images_memory.set(19, path);
            }
        }
    }
    public ArrayList<String> getImages_memory(){
        return images_memory;
    }



    private ArrayList<String> doc_memory=new ArrayList<>();
    public void add_doc_memory(String path){
        boolean path_seach = true;
        for (int i = 0;i<doc_memory.size();i++){
            if (doc_memory.get(i).equals(path)){
                path_seach=false;
                break;
            }
        }
        if (path_seach) {
            if (doc_memory.size() < 20) {
                doc_memory.add(path);
            } else {
                for (int i = 1; i < doc_memory.size() - 1; i++) {
                    doc_memory.set(i - 1, doc_memory.get(i));
                }
                doc_memory.set(19, path);
            }
        }
    }
    public ArrayList<String> getDoc_memory(){
        return doc_memory;
    }


    private ArrayList<String> audio_memory=new ArrayList<>();
    public void add_audio_memory(String path){
        boolean path_seach = true;
        for (int i = 0;i<audio_memory.size();i++){
            if (audio_memory.get(i).equals(path)){
                path_seach=false;
                break;
            }
        }
        if (path_seach) {
            if (audio_memory.size() < 20) {
                audio_memory.add(path);
            } else {
                for (int i = 1; i < audio_memory.size() - 1; i++) {
                    audio_memory.set(i - 1, audio_memory.get(i));
                }
                audio_memory.set(19, path);
            }
        }
    }
    public ArrayList<String> getAudio_memory(){
        return audio_memory;
    }


    private ArrayList<String> video_memory=new ArrayList<>();
    public void add_video_memory(String path){
        boolean path_seach = true;
        for (int i = 0;i<video_memory.size();i++){
            if (video_memory.get(i).equals(path)){
                path_seach=false;
                break;
            }
        }
        if (path_seach) {
            if (video_memory.size() < 20) {
                video_memory.add(path);
            } else {
                for (int i = 1; i < video_memory.size() - 1; i++) {
                    video_memory.set(i - 1, video_memory.get(i));
                }
                video_memory.set(19, path);
            }
        }
    }
    public ArrayList<String> getVideo_memory(){
        return video_memory;
    }



    private ArrayList<String> apk_memory=new ArrayList<>();
    public void add_apk_memory(String path){
        boolean path_seach = true;
        for (int i = 0;i<apk_memory.size();i++){
            if (apk_memory.get(i).equals(path)){
                path_seach=false;
                break;
            }
        }
        if (path_seach) {
            if (apk_memory.size() < 20) {
                apk_memory.add(path);
            } else {
                for (int i = 1; i < apk_memory.size() - 1; i++) {
                    apk_memory.set(i - 1, apk_memory.get(i));
                }
                apk_memory.set(19, path);
            }
        }
    }
    public ArrayList<String> getApk_memory(){
        return apk_memory;
    }


    private ArrayList<String> forder_memory=new ArrayList<>();
    public void add_forder_memory(String path){
        boolean path_seach = true;
        for (int i = 0;i<forder_memory.size();i++){
            if (forder_memory.get(i).equals(path)){
                path_seach=false;
                break;
            }
        }
        if (path_seach) {
            if (forder_memory.size() < 20) {
                forder_memory.add(path);
            } else {
                for (int i = 1; i < forder_memory.size() - 1; i++) {
                    forder_memory.set(i - 1, forder_memory.get(i));
                }
                forder_memory.set(19, path);
            }
        }
    }
    public ArrayList<String> getForder_memory(){
        return forder_memory;
    }
}
