package com.startgames.exploler;

import android.app.Application;

import java.lang.reflect.Array;
import java.util.ArrayList;

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
    public void vabor_forder_del(forder_element obj){
        //obj.del_vabor();
        vabor_forder.remove(obj);
        //vabor_forder.add(obj);
    }
    public void del_vabors(){
        for (int i = 0;i<vabor_forder.size();i++){
            vabor_forder.get(i).del_vabor();
            vadel_forder=false;
        }
        vabor_forder.clear();
    }
    public int vabor_size(){
        return vabor_forder.size();
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
}
