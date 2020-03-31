package com.example.myideas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    //widget
    EditText emailEditText;
    EditText passwordEdittext ;
    Button loginButton;
    ProgressBar mProgressBar;
    TextView txtRegister;

    //Firebase variable
    private FirebaseAuth mAuthListener;
    FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText=(EditText)findViewById(R.id.email_id);
        passwordEdittext=(EditText)findViewById(R.id.password_id);
        loginButton=(Button)findViewById(R.id.btnLogin);
        mProgressBar=(ProgressBar)findViewById(R.id.progressBar);
        txtRegister=(TextView)findViewById(R.id.register);


        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,registerActivity.class);
                startActivity(intent);
                finish();
            }
        });

        init();
        CheckIfUserLoggin();

    }

    public void init(){
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignInExistingUser();
            }
        });

    }

    public void SignInExistingUser(){
        final String email = emailEditText.getText().toString().trim();
        final String password=passwordEdittext.getText().toString().trim();

        //check if the fields are filled out
        if(!isEmpty(email) && !isEmpty(password)){

        mProgressBar.setVisibility(View.VISIBLE);
            mAuthListener.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                           // FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                            Toast.makeText(LoginActivity.this,"welcome",Toast.LENGTH_SHORT).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                        mProgressBar.setVisibility(View.GONE);
                    }
                });
        }else{
            Toast.makeText(LoginActivity.this, "You didn't fill in all the fields.", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isEmpty(String string){
        return string.equals("");
    }

    public void CheckIfUserLoggin(){
        mAuthListener=FirebaseAuth.getInstance();
        mAuthStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user =FirebaseAuth.getInstance().getCurrentUser();
                if (user!=null){
                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuthListener.addAuthStateListener(mAuthStateListener);
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        mAuthListener.addAuthStateListener(mAuthStateListener);
//    }

        @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            FirebaseAuth.getInstance().removeAuthStateListener(mAuthStateListener);
        }
    }

}
