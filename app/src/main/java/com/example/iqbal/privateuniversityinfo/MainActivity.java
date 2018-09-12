package com.example.iqbal.privateuniversityinfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private CardView Dhk,Ctg,Raj,Khu,Bar,Syl,Rang,Mym;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Dhk = findViewById(R.id.dhkCardView);
        Ctg = findViewById(R.id.ctgCardView);
        Raj = findViewById(R.id.rajCardView);
        Khu = findViewById(R.id.khuCardView);
        Bar = findViewById(R.id.barCardView);
        Syl = findViewById(R.id.sylCardView);
        Rang = findViewById(R.id.rangCardView);
        Mym = findViewById(R.id.mymCardView);

        Dhk.setOnClickListener(this);
        Ctg.setOnClickListener(this);
        Raj.setOnClickListener(this);
        Khu.setOnClickListener(this);
        Bar.setOnClickListener(this);
        Syl.setOnClickListener(this);
        Rang.setOnClickListener(this);
        Mym.setOnClickListener(this);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.activity_back_in,R.anim.activity_back_out);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.dhkCardView:
                startActivity(new Intent(this,UniversityList.class).putExtra("div","dhk"));
                overridePendingTransition(R.anim.activity_in,R.anim.activity_out);
                break;

            case R.id.ctgCardView:
                startActivity(new Intent(this,UniversityList.class).putExtra("div","ctg"));
                overridePendingTransition(R.anim.activity_in,R.anim.activity_out);
                break;

            case R.id.rajCardView:
                startActivity(new Intent(this,UniversityList.class).putExtra("div","raj"));
                overridePendingTransition(R.anim.activity_in,R.anim.activity_out);
                break;

            case R.id.khuCardView:
                startActivity(new Intent(this,UniversityList.class).putExtra("div","khu"));
                overridePendingTransition(R.anim.activity_in,R.anim.activity_out);
                break;

            case R.id.barCardView:
                startActivity(new Intent(this,UniversityList.class).putExtra("div","bar"));
                overridePendingTransition(R.anim.activity_in,R.anim.activity_out);
                break;

            case R.id.sylCardView:
                startActivity(new Intent(this,UniversityList.class).putExtra("div","syl"));
                overridePendingTransition(R.anim.activity_in,R.anim.activity_out);
                break;

            case R.id.rangCardView:
                startActivity(new Intent(this,UniversityList.class).putExtra("div","rang"));
                overridePendingTransition(R.anim.activity_in,R.anim.activity_out);
                break;

            case R.id.mymCardView:
                Toast.makeText(this, "No University found in Mymensingh division", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
