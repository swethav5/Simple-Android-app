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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Signup extends AppCompatActivity {

    Button sregister;
    EditText susername,semail,spassword;
    TextView slogintext;
    private FirebaseAuth mAuth;
    FirebaseDatabase mydatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth=FirebaseAuth.getInstance();
        mydatabase=FirebaseDatabase.getInstance();
        susername=(EditText)findViewById(R.id.username);
        semail=(EditText)findViewById(R.id.email);
        spassword=(EditText)findViewById(R.id.password);
        sregister=(Button)findViewById(R.id.registerbutton);
        slogintext=(TextView)findViewById(R.id.logintextview);

        sregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=semail.getText().toString();
                String password=spassword.getText().toString();
                String user=susername.getText().toString();
                final UserFields object=new UserFields(email,password,user);
                if(email.isEmpty()){
                    Toast.makeText(Signup.this,"Enter email ID",Toast.LENGTH_SHORT).show();
                }else if(password.isEmpty()){
                    Toast.makeText(Signup.this,"Enter password",Toast.LENGTH_SHORT).show();
                }
                else  if(email.isEmpty() && password.isEmpty()){
                    Toast.makeText(Signup.this,"Fields are empty",Toast.LENGTH_SHORT).show();
                }
                else  if(!(email.isEmpty() && password.isEmpty())){
                    mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(Signup.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(Signup.this,"Error try again",Toast.LENGTH_SHORT).show();
                            }
                            else {
                                String user_id=mAuth.getCurrentUser().getUid();
                                DatabaseReference current_user=FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);
                                current_user.setValue(object);
                                Intent i=new Intent(Signup.this,Homeactivity.class);
                                startActivity(i);

                            }

                        }

                });

                }
                else {
                    Toast.makeText(Signup.this,"Error try again",Toast.LENGTH_SHORT).show();
                }


            }
        });
        slogintext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Signup.this,Login.class);
                startActivity(i);
            }
        });
    }
}
