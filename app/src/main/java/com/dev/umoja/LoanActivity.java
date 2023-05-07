package com.dev.umoja;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;

import java.util.ArrayList;

public class LoanActivity extends AppCompatActivity {

    final static String DB_URL="https://tork-master-39e7b.firebaseio.com/";
    EditText txtName,txtMobile,txtEmail,txtId,txtAmount,txtPeriod;
    Button apply;
    TextView status;
    ArrayList<String>loans=new ArrayList<>();
    Firebase firebase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan);
        txtName=(EditText) findViewById(R.id.txtName);
        txtMobile=(EditText) findViewById(R.id.txtMobile);
        txtEmail=(EditText) findViewById(R.id.txtEmail);
        txtId=(EditText) findViewById(R.id.txtId);
        txtAmount=(EditText)findViewById(R.id.txtAmount);
        txtPeriod=(EditText)findViewById(R.id.txtPeriod);
        apply=(Button)findViewById(R.id.apply);
        status=(TextView)findViewById(R.id.status);
         Firebase.setAndroidContext(this);
        firebase=new Firebase(DB_URL);
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addData(txtName.getText().toString(),txtMobile.getText().toString(),txtEmail.getText().toString(),txtId.getText().toString(),txtAmount.getText().toString(),txtPeriod.getText().toString());



                txtName.setText("");
                txtMobile.setText("");
                txtEmail.setText("");
                txtId.setText("");
                txtAmount.setText("");
                txtPeriod.setText("");

if (!txtName.equals("")&& !txtMobile.equals("")&& !txtEmail.equals("")&& !txtId.equals("")&& !txtAmount.equals("")&& !txtPeriod.equals(""))
    Toast.makeText(LoanActivity.this,"Successful .AWaiting :Review $ Response soon ",Toast.LENGTH_LONG).show();
else

                Toast.makeText(LoanActivity.this,"Successful .AWaiting :Review $ Response soon",Toast.LENGTH_LONG).show();
            }
        });

    }

    //ADD DATA
    private void addData(String txtName, String txtMobile, String txtEmail, String txtId, String txtAmount, String txtPeriod)
    {
        Loans l=new Loans();
        l.setName(txtName);
        l.setMobile_Number(txtMobile);
        l.setEmail(txtEmail);
        l.setId(txtId);
        l.setAmount(txtAmount);
        l.setPeriod(txtPeriod);

        firebase.child("Loans").push().setValue(l);
    }
}