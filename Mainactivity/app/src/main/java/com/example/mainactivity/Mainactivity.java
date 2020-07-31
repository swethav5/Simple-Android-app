package com.example.mainactivity;

import android.app.Application;
import com.firebase.client.Firebase;

public class Mainactivity extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Firebase.setAndroidContext(this);
    }
}
