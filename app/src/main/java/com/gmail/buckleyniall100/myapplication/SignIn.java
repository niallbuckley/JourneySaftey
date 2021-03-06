package com.gmail.buckleyniall100.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignIn extends AppCompatActivity implements View.OnClickListener {
    Button signInButton, signUpButton;
    EditText email, password;
    FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
    ProgressBar progressBar;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mAuth = FirebaseAuth.getInstance();
        //findViewById(R.id.textViewSignUp).setOnClickListener(this);

        //Intialization Button
        signInButton = (Button) findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(SignIn.this);
        //signUpButton = (Button) findViewById(R.id.sign_up_button);
        //signUpButton.setOnClickListener(SignIn.this);
        findViewById(R.id.textSignUp).setOnClickListener(this);

        progressBar = (ProgressBar) findViewById(R.id.login_progress);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.sign_in_button:
                userSignIn();
                break;
            case R.id.textSignUp:
                startActivity(new Intent(this, SignUp.class));
                break;
        }
    }

    private void userSignIn() {
        String username = email.getText().toString().trim();
        String pswrd = password.getText().toString().trim();

        if(pswrd.isEmpty()){
          password.setError("Password is required");
          password.requestFocus();
          return;
        }
        if(username.isEmpty()){
            email.setError("Email sign in name is required");
            email.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(username).matches()){
            email.setError("Please enter a valid email");
            email.requestFocus();
            return;
        }
        if(pswrd.length()<4){
            password.setError("Minimum length of password is 5");
            password.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(username, pswrd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()){
                     Intent intent = new Intent(SignIn.this, MainActivity.class);
                     intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                     startActivity(intent);
                     finish();
                }else {
                    password.setError("E-mail or password incorrect");
                    email.setError("E-mail or password incorrect");
                }
            }
        });

    }
}
