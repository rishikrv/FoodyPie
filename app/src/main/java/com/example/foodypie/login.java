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
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {

    EditText txt1mail, txt2pass ;
    TextView signup1, reset,mobsign;
    Button botto;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txt1mail = (EditText)findViewById(R.id.name1);
        txt2pass = (EditText)findViewById(R.id.pass1);
        botto = (Button)findViewById(R.id.Login);

        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user!=null)
        {
            finish();
            startActivity(new Intent(login.this,Home.class));

        }




        botto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = txt1mail.getText().toString().trim();
                String password = txt2pass.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(login.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)){
                    Toast.makeText(login.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }


                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    startActivity(new Intent(getApplicationContext(),Home.class));

                                } else {
                                    Toast.makeText(login.this, "Network Failed / Invalid User", Toast.LENGTH_SHORT).show();


                                }
                            }
                        });



            }
        });
        signup1 =  (TextView)findViewById(R.id.sign12) ;
        signup1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(login.this,register.class);
                startActivity(intent1);
            }
        });

        reset = (TextView)findViewById(R.id.reset12);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this,resetpass.class);
                startActivity(intent);
            }
        });

        mobsign = (TextView)findViewById(R.id.mobotp);
        mobsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this,Mobilelogin.class);
                startActivity(intent);
            }
        });


    }
}
