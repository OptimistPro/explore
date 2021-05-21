package com.startgames.exploler;

import android.app.Application;

public class Global extends Application {
    private String someVariable = "byp";

    public String getSomeVariable() {
        return someVariable;
    }

    public void setSomeVariable(String someVariable) {
        this.someVariable = someVariable;
    }
}
