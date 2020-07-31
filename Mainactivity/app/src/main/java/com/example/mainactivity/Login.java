package com.example.mainactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Login extends AppCompatActivity {

    Button loginnow;
    EditText lemail, lpassword;
    TextView llogin,lregister;
    FirebaseAuth mFirebaseAuth;
    FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFirebaseAuth=FirebaseAuth.getInstance();

        lemail=(EditText)findViewById(R.id.loginemail);
        lpassword=(EditText)findViewById(R.id.loginpassword);
        llogin=(TextView)findViewById(R.id.logintext);
        loginnow=(Button)findViewById(R.id.loginnowbutton);
        lregister=(TextView)findViewById(R.id.registertextview);
        mAuthStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser muser= mFirebaseAuth.getCurrentUser();
                if(muser!=null){
                    Toast.makeText(Login.this,"You are logged in",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Login.this,Homeactivity.class));
                }else{
                    Toast.makeText(Login.this,"Please Login",Toast.LENGTH_SHORT).show();
                }
            }
        };


        loginnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email1=lemail.getText().toString();
                String password1=lpassword.getText().toString();
                if(email1.isEmpty()){
                    Toast.makeText(Login.this,"Enter email ID",Toast.LENGTH_SHORT).show();
                }else if(password1.isEmpty()){
                    Toast.makeText(Login.this,"Enter password",Toast.LENGTH_SHORT).show();
                }
                else  if(email1.isEmpty() && password1.isEmpty()){
                    Toast.makeText(Login.this,"Fields are empty",Toast.LENGTH_SHORT).show();
                }
                else  if(!(email1.isEmpty() && password1.isEmpty())){
                    mFirebaseAuth.signInWithEmailAndPassword(email1,password1).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(Login.this,"Login error! Try again later",Toast.LENGTH_SHORT).show();
                            }
                            else {
                                startActivity(new Intent(Login.this,Homeactivity.class));
                            }
                        }
                    });

                }
                else {
                    Toast.makeText(Login.this,"Login error Try again later",Toast.LENGTH_SHORT).show();
                }
            }
        });
        lregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Login.this,Signup.class);
                startActivity(i);
            }
        });
    }

}