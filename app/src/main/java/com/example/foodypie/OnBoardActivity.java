package com.example.foodypie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.foodypie.fragments.PageFragment1;
import com.example.foodypie.fragments.PageFragment2;
import com.example.foodypie.fragments.PageFragment3;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class OnBoardActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    Button button;
    ViewPager myPager;
    List<Fragment> fragmentList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_board);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user!=null)
        {
            finish();
            startActivity(new Intent(OnBoardActivity.this,Home.class));


        }

        button = (Button) findViewById(R.id.button21);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openlogin();
            }
        });



        myPager = findViewById(R.id.my_pager);
        fragmentList = new ArrayList<>();
        initList();

        SlidePagerAdapter adapter = new SlidePagerAdapter(getSupportFragmentManager(),fragmentList);
        myPager.setAdapter(adapter);

    }

    public void openlogin(){
        Intent intent = new Intent(this,login.class);
        startActivity(intent);
    }

    private void initList(){
        fragmentList.add(new PageFragment1());
        fragmentList.add(new PageFragment2());
        fragmentList.add(new PageFragment3());
    }
}
