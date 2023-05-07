package com.dev.umoja;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import java.util.ArrayList;



public class DisplayActivity extends AppCompatActivity {

    final static String DB_URL="https://fir-writeread-master.firebaseio.com/";
    ProgressDialog progressDialog;
    ListView lv;
    ArrayList<String> names=new ArrayList<>();
    Firebase fire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        progressDialog=new ProgressDialog(DisplayActivity.this);

        lv= (ListView) findViewById(R.id.lv);

        Firebase.setAndroidContext(this);

        fire=new Firebase(DB_URL);

        this.retrieveData();





    }





    //RETRIEVE
    private void retrieveData()
    {
        fire.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                getUpdates(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                getUpdates(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


        progressDialog.setMessage("Fetching updates from server");
        progressDialog.show();



        progressDialog.dismiss();

    }


    private void getUpdates(DataSnapshot ds) {
        names.clear();

        for (DataSnapshot data : ds.getChildren()) {
            Movie m = new Movie();
            m.setName(data.getValue(Movie.class).getName());

            names.add(m.getName());
        }

        if (names.size() > 0) {
            ArrayAdapter adapter = new ArrayAdapter(DisplayActivity.this, android.R.layout.simple_list_item_1, names);
            lv.setAdapter(adapter);
        } else {
            Toast.makeText(DisplayActivity.this, "No data", Toast.LENGTH_SHORT).show();

        }
    }}