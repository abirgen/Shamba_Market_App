package com.ashgen.groupchatapp.startactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ashgen.groupchatapp.R;
import com.ashgen.groupchatapp.chatactivity.ChatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StartActivity extends AppCompatActivity implements StartActivityMVP.View {

    @BindView(R.id.button_getstarted)
    Button buttonGetstarted;
    @BindView(R.id.textinputlayout_username)
    TextInputLayout textinputlayoutUsername;
    @BindView(R.id.progressbar)
    ProgressBar progressBar;

    StartActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);

        if (new SharedPreferenceHelper(this).isUserLoggedIn())
        {
            initializeChat(new SharedPreferenceHelper(this).getUserDetails().getName());
        }

        presenter = new StartActivityPresenter();





    }

    @Override
    public String getUsername() {
        return textinputlayoutUsername.getEditText().getText().toString();
    }

    @Override
    public void initializeChat(String username) {



        Intent intent = new Intent(StartActivity.this,ChatActivity.class);
        intent.putExtra("username",username);
        startActivity(intent);
        finish();
    }

    @Override
    public void CreateUser(UserDetails userDetails) {
        SharedPreferenceHelper sharedPreferenceHelper = new SharedPreferenceHelper(StartActivity.this);
        sharedPreferenceHelper.putUserDetails(userDetails);
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.button_getstarted)
    public void onViewClicked() {

        presenter.getStartedButtonClick();
    }


    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
    }
}

