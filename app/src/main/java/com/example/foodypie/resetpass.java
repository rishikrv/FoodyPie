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
import com.google.firebase.auth.FirebaseAuth;

public class resetpass extends AppCompatActivity {

    Button resett;
    EditText restmail;
    TextView signup, signin;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpass);

        firebaseAuth = FirebaseAuth.getInstance();

        resett = (Button)findViewById(R.id.resetb) ;
        restmail = (EditText)findViewById(R.id.remail);

        resett.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailid = restmail.getText().toString();

                if (TextUtils.isEmpty(emailid)){
                    Toast.makeText(resetpass.this, "Enter Email", Toast.LENGTH_SHORT).show();

                }
                else{
                    firebaseAuth.sendPasswordResetEmail(emailid).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(resetpass.this, "Password reset link send, Please check your E-mail", Toast.LENGTH_SHORT).show();

                                startActivity(new Intent(resetpass.this,login.class));
                            }
                            else{
                                String message = task.getException().getMessage();
                                Toast.makeText(resetpass.this, "Network Failed / Invalid User / " + message, Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }
            }
        });

        signup =  (TextView)findViewById(R.id.signup111) ;
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(resetpass.this,register.class);
                startActivity(intent1);
            }
        });

        signin = (TextView)findViewById(R.id.signin111);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(resetpass.this,login.class);
                startActivity(intent);
            }
        });

    }
}
