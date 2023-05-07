package com.dev.umoja;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.collection.LLRBNode;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class ChatActivity extends AppCompatActivity {

    final static int SIGN_IN_REQUEST_CODE = 2;

    private FloatingActionButton fab;
    private ListView Messages;
    private FirebaseListAdapter<ChatMessage> adapter;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
         progressDialog =new ProgressDialog(ChatActivity.this);
        if(FirebaseAuth.getInstance().getCurrentUser() == null) {
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .build(),
                    SIGN_IN_REQUEST_CODE
            );
        } else {
            Toast.makeText(this,"Welcome " + FirebaseAuth.getInstance().getCurrentUser().getEmail(), Toast.LENGTH_LONG).show();
            Messages=findViewById(R.id.messages);
            // Load chat room contents
            displayChatMessages();

            fab = findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.v("FAB ", "Onclick called");
                    EditText input = (EditText)findViewById(R.id.input);
                    String message = input.getText().toString();
                    String user = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                    FirebaseDatabase.getInstance()
                            .getReference()
                            .push()
                            .setValue(new ChatMessage(message, user));
                    input.setText("");
                }
            });
        }
        Messages = findViewById(R.id.messages);


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SIGN_IN_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this,
                        "Successfully signed in!",
                        Toast.LENGTH_LONG).show();
                displayChatMessages();
            } else {
                Toast.makeText(this,"We couldn't sign you in. Please try again later.",Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }
    void displayChatMessages(){
        adapter = new FirebaseListAdapter<ChatMessage>(this, ChatMessage.class,
                R.layout.message, FirebaseDatabase.getInstance().getReference()) {
            @Override
            protected void populateView(View v, ChatMessage model, int position) {
                TextView user = (v.findViewById(R.id.message_user));
                TextView message = v.findViewById(R.id.message_text);
                TextView date = v.findViewById(R.id.message_time);

                user.setText(model.getMessageUser());
                message.setText(model.getMessageText());
                date.setText(DateFormat.format("yyyy.MMM.dd (hh:mm. aaa)",
                        model.getMessageTime()));

            }
        };
        Messages.setAdapter(adapter);
    }
}