package com.example.foodypie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Mobilelogin extends AppCompatActivity {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobilelogin);

        editText = findViewById(R.id.mobent);

        findViewById(R.id.sedd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = editText.getText().toString().trim();

                if (number.isEmpty()|| number.length()<10){
                    editText.setError("Enter Valid Mobile No.");
                    editText.requestFocus();

                    return;
                }

                String phoneNumber = "+91"+number;
                Intent intent = new Intent(Mobilelogin.this,Mobverify.class);
                intent.putExtra("phonenumber",phoneNumber);
                startActivity(intent);






            }
        });

    }
}
