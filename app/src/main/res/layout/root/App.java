package com.ashgen.groupchatapp.root;

import android.app.Application;

import com.ashgen.groupchatapp.chatactivity.ChatActivity;

/**
 * Created by malavan on 13/01/18.
 */

public  class App extends Application {
    static UserDetails users;

    public static void setUserObject(UserDetails user){
        users=user;
    }

    public static UserDetails getUserObject(){
        return users;
    }


    @Override
    public void onCreate() {
        super.onCreate();


    }
}
