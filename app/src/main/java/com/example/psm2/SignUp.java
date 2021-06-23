package com.example.psm2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;

    private EditText mFName, mLName, mEmail, mPhone, mPassword;
    private Button mGo, mLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        mFName = findViewById(R.id.fName);
        mLName = findViewById(R.id.lastName);
        mEmail = findViewById(R.id.email);
        mPhone = findViewById(R.id.phone);
        mPassword = findViewById(R.id.password);

        mGo = findViewById(R.id.btnSignup);
        mGo.setOnClickListener(this);

        mLogin = findViewById(R.id.btnLogin);
        mLogin.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnSignup:
                btnSignUp();
                break;
            case R.id.btnLogin:
                startActivity(new Intent(this, Login.class));
                break;
        }
    }

    private void btnSignUp() {
        String fName = mFName.getText().toString().trim();
        String lName = mLName.getText().toString().trim();
        String email = mEmail.getText().toString().trim();
        String phone = mPhone.getText().toString().trim();
        String pwd = mPassword.getText().toString().trim();

        if(fName.isEmpty()){
            mFName.setError("Full name is required!");
            mFName.requestFocus();
            return;
        }

        if (lName.isEmpty()){
            mLName.setError("Last name is required!");
            mLName.requestFocus();
            return;
        }

        if (email.isEmpty()){
            mEmail.setError("Email is required!");
            mEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            mEmail.setError("Please provide valid email!");
            mEmail.requestFocus();
            return;
        }
        if (phone.isEmpty()){
            mPhone.setError("Phone number is required!");
            mPhone.requestFocus();
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

        mAuth.createUserWithEmailAndPassword(email, pwd)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()) {

                            User user = new User(fName,lName,phone,email);

                            HashMap<Object,String> hashMap = new HashMap<>();
                            hashMap.put("first name", fName);
                            hashMap.put("last name", lName);
                            hashMap.put("phone", phone);
                            hashMap.put("email", email);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(mAuth.getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull @NotNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(SignUp.this, "User has been registered successfully!", Toast.LENGTH_LONG).show();
                                    }else{
                                        Toast.makeText(SignUp.this, "Failed to register (not in real-time)! Try again", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });

                        }else{
                            Toast.makeText(SignUp.this, "Failed to register (create new instance)! Try again", Toast.LENGTH_LONG).show();
                        }

                    }
                });
    }
}