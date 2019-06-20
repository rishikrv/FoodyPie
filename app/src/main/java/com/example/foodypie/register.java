package com.example.foodypie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class register extends AppCompatActivity {

    EditText txtname, txtemail, txtmobile, txtpass;
    Button btton;
    TextView signin;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txtname = (EditText)findViewById(R.id.name);
        txtmobile = (EditText)findViewById(R.id.mobile);
        txtpass = (EditText)findViewById(R.id.pass);
        txtemail = (EditText)findViewById(R.id.email);
        btton = (Button)findViewById(R.id.register);


        firebaseAuth = FirebaseAuth.getInstance();


        btton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = txtname.getText().toString().trim();
                String mobile = txtmobile.getText().toString().trim();
                String email = txtemail.getText().toString().trim();
                String password = txtpass.getText().toString().trim();



                if (TextUtils.isEmpty(name)){
                    Toast.makeText(register.this, "Enter Full Name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(mobile)){
                    Toast.makeText(register.this, "Enter Mobile No.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(register.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)){
                    Toast.makeText(register.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length()<6){
                    Toast.makeText(register.this, "Enter more than 6 character", Toast.LENGTH_SHORT).show();
                    return;
                }




                firebaseAuth.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener(register.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {



                                if (task.isSuccessful()) {
                                    startActivity(new Intent(register.this,login.class));
                                    Toast.makeText(register.this, "Registration Successful", Toast.LENGTH_SHORT).show();


                                } else {
                                    Toast.makeText(register.this, "Registration Failed / Already have an Account", Toast.LENGTH_SHORT).show();


                                }


                            }
                        });


            }
        });

        signin = (TextView)findViewById(R.id.textView5);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(register.this,login.class);
                startActivity(intent);
            }
        });
    }
}
