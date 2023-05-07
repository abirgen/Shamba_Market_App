package com.dev.umoja;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

public class InsertActivity extends AppCompatActivity {

    final static String DB_URL="https://fir-writeread-master.firebaseio.com/";

    EditText nameTxt;
    ListView lv;
    Button saveBtn;
    ArrayList<String> names=new ArrayList<>();
    Firebase fire;
    FloatingActionButton applicants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lv= (ListView) findViewById(R.id.lv);

        Firebase.setAndroidContext(this);

        fire=new Firebase(DB_URL);

applicants=(FloatingActionButton) findViewById(R.id.applicants);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

        applicants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(InsertActivity.this,LoanDisplay.class);
                startActivity(intent);
            }
        });



    }

    //DISPLAY INPUT DIALOG
    private void showDialog()
    {
        Dialog d=new Dialog(this);
        d.setTitle("Save Online");
        d.setContentView(R.layout.dialoglayout);

        nameTxt= (EditText) d.findViewById(R.id.nameEditText);
        saveBtn= (Button) d.findViewById(R.id.saveBtn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addData(nameTxt.getText().toString());

                nameTxt.setText("");
                Toast.makeText(InsertActivity.this,"Updates posted ",Toast.LENGTH_LONG).show();
            }
        });

        d.show();

    }

    //ADD DATA
    private void addData(String name)
    {
        Movie m=new Movie();
        m.setName(name);

        fire.child("Movie").push().setValue(m);
    }
    private void getUpdates(DataSnapshot ds)
    {
        names.clear();

        for(DataSnapshot data : ds.getChildren())
        {
            Movie m=new Movie();
            m.setName(data.getValue(Movie.class).getName());

            names.add(m.getName());
        }

        if(names.size()>0)
        {
            ArrayAdapter adapter=new ArrayAdapter(InsertActivity.this,android.R.layout.simple_list_item_1,names);
            lv.setAdapter(adapter);
        }else
        {
            Toast.makeText(InsertActivity.this,"No data",Toast.LENGTH_SHORT).show();
        }
    }
}