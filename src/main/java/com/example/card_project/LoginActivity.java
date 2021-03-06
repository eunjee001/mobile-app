package com.example.card_project;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.loginButton).setOnClickListener(onClickListener);
        findViewById(R.id.validate).setOnClickListener(onClickListener);
        findViewById(R.id.gotoPassword).setOnClickListener(onClickListener);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @SuppressLint("NonConstantResourceId")
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.loginButton:
                    login();
                    break;
                case R.id.validate:
                    myStartActivity(SignUpActivity.class);
                    break;
                case R.id.gotoPassword:
                    myStartActivity(PasswordResetActivity.class);
                    break;
            }
        }
    };



    private void login() { //????????? ?????? ??????
        String email = ((EditText) findViewById(R.id.user_id)).getText().toString(); //????????? ???????????? ???????????? ??????
        String password = ((EditText) findViewById(R.id.user_password)).getText().toString();

        if (email.length() > 0 && password.length() > 0) {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                if(mAuth.getCurrentUser().isEmailVerified()){
                                    // ???????????? ???????????? "????????? ??????" ???????????? ?????????
                                    Toast.makeText(LoginActivity.this, "???????????? ??????????????????.", Toast.LENGTH_SHORT).show();
                                    FirebaseUser user = mAuth.getCurrentUser();

                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                    finish();

                            } else {
                                    Toast.makeText(LoginActivity.this, "????????? ????????? ??????????????????.", Toast.LENGTH_SHORT).show();
                                }
                                }else {
                                    // ???????????? ???????????? "????????? ??????" ???????????? ?????????
                                    Toast.makeText(LoginActivity.this, "???????????? ??????????????????.", Toast.LENGTH_SHORT).show();
                                }
                                }

                    });

                    }
        }


    private void startToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void myStartActivity(Class c) {
        Intent intent = new Intent(LoginActivity.this, c);
        startActivity(intent);
    }

}
