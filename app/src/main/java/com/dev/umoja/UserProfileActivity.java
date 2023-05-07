package com.dev.umoja;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserProfileActivity extends AppCompatActivity {
    //declare imageviews present in display xml
    ImageView about;
    ImageView chat;
    ImageView savings;
    ImageView statements;
    ImageView updates;
    ImageView calendar;
    ImageView logout;
    //create firebase authand user respectively
FirebaseAuth firebaseAuth;

FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        about=(ImageView) findViewById(R.id.about);
        chat=(ImageView) findViewById(R.id.chat);
        savings=(ImageView) findViewById(R.id.savings);
        statements=(ImageView) findViewById(R.id.statements);
        updates=(ImageView) findViewById(R.id.updates);
        calendar=(ImageView) findViewById(R.id.calendar);
        logout=(ImageView) findViewById(R.id.logout);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(UserProfileActivity.this,ScrollingActivity.class);
                startActivity(intent);
                Toast.makeText(UserProfileActivity.this,"Welcome to our savings group",Toast.LENGTH_LONG).show();
            }
        });
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(UserProfileActivity.this,ChatActivity.class);
                startActivity(intent);
                Toast.makeText(UserProfileActivity.this,"Express your opinions with others on app group chat",Toast.LENGTH_LONG).show();
            }
        });
        savings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(UserProfileActivity.this,UserProfileActivity.class);
                startActivity(intent);
            }
        });
        statements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(UserProfileActivity.this,MainActivity2.class);
                startActivity(intent);
            }
        });
        updates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(UserProfileActivity.this,DisplayActivity.class);
                startActivity(intent);
            }
        });
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(UserProfileActivity.this, LoanActivity.class);
                startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Destroying login season.
                firebaseAuth.getInstance().signOut();

                // Finishing current User Profile activity.
                finish();

                // Redirect to Login Activity after click on logout button.
                Intent intent = new Intent(UserProfileActivity.this, LoginActivity.class);
                startActivity(intent);

                // Showing toast message on logout.
                Toast.makeText(UserProfileActivity.this, "You've successfully Logged Out.", Toast.LENGTH_LONG).show();

            }
        });

    }
}
