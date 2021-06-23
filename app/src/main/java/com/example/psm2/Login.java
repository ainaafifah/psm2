package com.example.psm2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class Login extends AppCompatActivity implements View.OnClickListener{

    private EditText mEmail, mPassword;
    private Button mRegister, mMainLogin;
    private TextView mForgot;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mRegister = (Button) findViewById(R.id.btnRegister);
        mRegister.setOnClickListener(this);

        mMainLogin = (Button) findViewById(R.id.btnMainLogin);
        mMainLogin.setOnClickListener(this);

        mForgot = (TextView) findViewById(R.id.btnForgot);
        mForgot.setOnClickListener(this);

        mEmail = (EditText) findViewById(R.id.Email);
        mPassword = (EditText) findViewById(R.id.password);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnRegister:
                    startActivity(new Intent(this, SignUp.class));
                    break;

            case R.id.btnMainLogin:
                userLogin();
                break;
        }
    }

    private void userLogin() {
        String email = mEmail.getText().toString().trim();
        String pwd = mPassword.getText().toString().trim();

        if (email.isEmpty()){
            mEmail.setError("Email is required!");
            mEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            mEmail.setError("Please provide valid email!");
            mEmail.requestFocus();
            return;
        }

        if (pwd.isEmpty()){
            mPassword.setError("Password is required!");
            mPassword.requestFocus();
            return;
        }

        if (pwd.length() < 6){
            mPassword.setError("Min password should be 6 characters!");
            mPassword.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
               if(task.isSuccessful()){
                   //redirect to user profile
                   startActivity(new Intent(Login.this, MainActivity.class));

               }else{
                   Toast.makeText(Login.this, "Failed to login! Please check your credentials", Toast.LENGTH_LONG).show();
               }
            }
        });
    }
}