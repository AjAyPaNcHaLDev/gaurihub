package com.vilees.app;


import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import com.onesignal.OSNotificationAction;
import com.onesignal.OSNotificationOpenedResult;
import com.onesignal.OneSignal;

import org.json.JSONException;
import org.json.JSONObject;

public class Notify extends Application {
    private  static Notify mInstance;
    private static final String ONESIGNAL_APP_ID ="1c0bbc1b-a9f4-4995-a5d6-fc5e54119dad";
    public  static String Link="";
    public Notify(){
        mInstance=this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);
        OneSignal.setNotificationOpenedHandler(
                new OneSignal.OSNotificationOpenedHandler() {
                    @Override
                    public void notificationOpened(OSNotificationOpenedResult result) {
                       JSONObject data = result.getNotification().getAdditionalData();
                        if (data != null) {

                            if (data.toString() != ""){
    try {

                                    JSONObject getData = new JSONObject(data.toString());
                                    Link = getData.getString("link");
        Log.e("AJAY",Link);
                                    Intent i=new Intent(getApplicationContext(),MainActivity.class);
                                    i.setFlags(FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(i);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                        }


                    }
                });
    }


}