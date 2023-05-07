package com.dev.umoja;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.app.ProgressDialog;
import android.content.Intent;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
public class LoginActivity extends AppCompatActivity {
        // Creating EditText.
        EditText email, password ;
        TextView admin;

        // Creating string to hold values.
        String  PasswordHolder;
        String EmailHolder;
        // Creating buttons.
        Button Login;
        TextView Register ;

        // Creating Boolean to hold EditText empty true false value.
        Boolean EditTextEmptyCheck;

        // Creating progress dialog.
        ProgressDialog progressDialog;


        // Creating FirebaseAuth object.
        FirebaseAuth firebaseAuth;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);

            // Assign ID's to EditText.
            email = (EditText)findViewById(R.id.email);
            password = (EditText)findViewById(R.id.password);
            Register = (TextView)findViewById(R.id.register);
            // Assign ID's to button.
            Login = (Button)findViewById(R.id.login);
            admin=(TextView) findViewById(R.id.admin);
            admin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(LoginActivity.this, Admin.class);
                    startActivity(intent);
                }
            });

            progressDialog =  new ProgressDialog(LoginActivity.this);

            // Assign FirebaseAuth instance to FirebaseAuth object.
            firebaseAuth = FirebaseAuth.getInstance();


            // Checking if user already logged in before and not logged out properly.
            if(firebaseAuth.getCurrentUser() != null){

                // Finishing current Login Activity.
                finish();

                // Opening UserProfileActivity .
                Intent intent = new Intent(LoginActivity.this, UserProfileActivity.class);
                startActivity(intent);
            }


            // Adding click listener to login button.
            Login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // Calling method CheckEditTextIsEmptyOrNot().
                    CheckEditTextIsEmptyOrNot();

                    // If  EditTextEmptyCheck == true
                    if(EditTextEmptyCheck)
                    {

                        // If  EditTextEmptyCheck == true then login function called.
                        LoginFunction();

                    }
                    else {

                        // If  EditTextEmptyCheck == false then toast display on screen.
                        Toast.makeText(LoginActivity.this, "Please Fill All the Required Fields", Toast.LENGTH_LONG).show();
                    }


                }
            });

            // Adding click listener to Sign up button.
            Register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // Closing current activity.
                    finish();

                    // Opening the Main Activity .
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);

                }
            });
        }

        // Creating method to check EditText is empty or not.
        public void CheckEditTextIsEmptyOrNot(){

            // Getting value form Email's EditText and fill into EmailHolder string variable.
            EmailHolder = email.getText().toString().trim();

            // Getting value form Password's EditText and fill into PasswordHolder string variable.
            PasswordHolder = password.getText().toString().trim();

            // Checking Both EditText is empty or not.
            if(TextUtils.isEmpty(EmailHolder) || TextUtils.isEmpty(PasswordHolder))
            {

                // If any of EditText is empty then set value as false.
                EditTextEmptyCheck = false;

            }
            else {

                // If any of EditText is empty then set value as true.
                EditTextEmptyCheck = true ;

            }

        }

        // Creating login function.
        public void LoginFunction(){

            // Setting up message in progressDialog.
            progressDialog.setMessage("Loging In Please Wait");

            // Showing progressDialog.
            progressDialog.show();

            // Calling  signInWithEmailAndPassword function with firebase object and passing EmailHolder and PasswordHolder inside it.
            firebaseAuth.signInWithEmailAndPassword(EmailHolder, PasswordHolder)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            // If task done Successful.
                            if(task.isSuccessful()){

                                // Hiding the progress dialog.
                                progressDialog.dismiss();

                                // Closing the current Login Activity.
                                finish();


                                // Opening the UserProfileActivity.
                                Intent intent = new Intent(LoginActivity.this,UserProfileActivity.class);
                                startActivity(intent);
                                Toast.makeText(LoginActivity.this,"Welcome to Umoja Group ",Toast.LENGTH_LONG).show();
                            }
                            else {

                                // Hiding the progress dialog.
                                progressDialog.dismiss();

                                // Showing toast message when email or password not found in Firebase Online database.
                                Toast.makeText(LoginActivity.this, "Try Again! Email or Password Not Found", Toast.LENGTH_LONG).show();
                            }
                        }
                    });

        }

    }