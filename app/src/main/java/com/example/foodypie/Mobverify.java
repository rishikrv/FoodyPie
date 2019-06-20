package com.example.foodypie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.executor.TaskExecutor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Mobverify extends AppCompatActivity {

    private String VerificationId;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobverify);

        mAuth  =FirebaseAuth.getInstance();

        progressBar = findViewById(R.id.progressBar);
        editText = findViewById(R.id.mobv);

        String phonenumber = getIntent().getStringExtra("phonenumber");
        senndVerificationCode(phonenumber);

        Toast.makeText(Mobverify.this, "OTP send to your Phone No.", Toast.LENGTH_SHORT).show();

        findViewById(R.id.senddd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = editText.getText().toString().trim();
                if (code.isEmpty()  || code.length()<6){

                    editText.setError("Enter Code");
                    editText.requestFocus();
                    return;
                }


                progressBar.setVisibility(View.VISIBLE);
                verifyCode(code);

            }
        });


    }

    private  void verifyCode(String code){
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(VerificationId, code);
        signInWithCredential(credential);

    }

    private void signInWithCredential(PhoneAuthCredential credential) {

        mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Intent intent = new Intent(Mobverify.this,Home.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);

                }
                else {
                    Toast.makeText(Mobverify.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();

                }

            }
        });


    }

    private void senndVerificationCode(String number){

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallBack
        );
    }
    private  PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            VerificationId = s;
        }

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            if(code!=null){
                progressBar.setVisibility(View.VISIBLE);
                verifyCode(code);
            }

        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(Mobverify.this,e.getMessage(),Toast.LENGTH_LONG).show();

        }
    };

}
