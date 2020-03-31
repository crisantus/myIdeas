package com.example.myideas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class registerActivity extends AppCompatActivity {

    //widget
    EditText emailEditText;
    EditText passwordEdittext;
    Button btnSignup;
    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailEditText = (EditText) findViewById(R.id.email_id);
        passwordEdittext = (EditText) findViewById(R.id.password_id);
        btnSignup = (Button) findViewById(R.id.signUp);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        init();
    }

    public void init(){
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emailEditText.getText().toString().trim();
                String password = passwordEdittext.getText().toString().trim();
                // String confirmPassWord=mConfirmPassword.getText().toString().trim();

                //check if the fields are filled out
                if(!isEmpty(email) && !isEmpty(password)){
                mProgressBar.setVisibility(View.VISIBLE);
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseAuth.getInstance().signOut();
                                    //redirect the user to the login screen
                                    Intent intent = new Intent(registerActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                                if (!task.isSuccessful()) {
                                    Toast.makeText(registerActivity.this, task.getException().getMessage(),
                                            Toast.LENGTH_SHORT).show();
                                }
                                mProgressBar.setVisibility(View.GONE);
                            }
                        });

                }else{
                    Toast.makeText(registerActivity.this, "You didn't fill in all the fields.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isEmpty(String string){
        return string.equals("");
    }
}
