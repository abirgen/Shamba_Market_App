package com.dev.umoja;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.graphics.Color;
import android.widget.TextView;

import static android.graphics.Color.RED;


public class Admin extends AppCompatActivity {

        Button login;
        EditText email,password;

        TextView textView;
        int counter = 4;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_admin);

            login = (Button) findViewById(R.id.login);
            email = (EditText) findViewById(R.id.email);
            password = (EditText) findViewById(R.id.password);


            textView = (TextView) findViewById(R.id.textView);
            textView.setVisibility(View.VISIBLE);

            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (email.getText().toString().equals("admin") &&
                            password.getText().toString().equals("admin")) {
                        Intent intent=new Intent(Admin.this,InsertActivity.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(),"welcome admin",Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Wrong Credentials", Toast.LENGTH_SHORT).show();

                        textView.setVisibility(View.VISIBLE);
                        textView.setBackgroundColor(RED);
                        counter--;
                        textView.setText(Integer.toString(counter));

                        if (counter == 0) {
                            login.setEnabled(false);
                            login.setBackgroundColor(RED);
                            login.setText("Blocked!");
                            Toast.makeText(getApplicationContext(),"Blocked!",Toast.LENGTH_LONG);
                        }
                    }
                }
            });

        }
    }