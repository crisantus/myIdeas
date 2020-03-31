package com.example.myideas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myideas.model.Idea;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class IdealCreateActivity extends AppCompatActivity {

    //widget
    EditText name;
    EditText description;
    EditText owner;
    Button btnCreate;
    ProgressBar mProgressBar;

    //Firebase
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mDatabaseReference;
    private FirebaseAuth mAuth;

    Idea mIdea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ideal_create);

        name=(EditText)findViewById(R.id.idea_name);
        description=(EditText)findViewById(R.id.idea_description);
        owner=(EditText)findViewById(R.id.idea_owner);
        btnCreate=(Button)findViewById(R.id.idea_create);
        mProgressBar=(ProgressBar)findViewById(R.id.progressBar);

        mFirebaseDatabase=FirebaseDatabase.getInstance();
        mDatabaseReference=mFirebaseDatabase.getReference("Idea");

        setTitle("create idea");



        mIdea=new Idea();

        btnCreate.setOnClickListener(new View.OnClickListener() {
            String Cname=name.getText().toString();
            String Cdesc=description.getText().toString();
            String Cowne=owner.getText().toString();
            @Override
            public void onClick(View v) {
               mProgressBar.setVisibility(View.VISIBLE);
                    mDatabaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            String userId=user.getUid();
                            //insering to firebase database
                            getValues();
                            mDatabaseReference
                                    .child(userId)
                                    .setValue(mIdea);
                            Intent intent=new Intent(IdealCreateActivity.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
            }
        });
        mProgressBar.setVisibility(View.GONE);
    }

    private void getValues(){
        mIdea.setName(name.getText().toString());
        mIdea.setDescription(description.getText().toString());
        mIdea.setOwner(owner.getText().toString());
    }

    private boolean isEmpty(String string){
        return string.equals("");
    }
}
