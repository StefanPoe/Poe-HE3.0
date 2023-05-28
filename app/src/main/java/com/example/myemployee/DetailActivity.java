package com.example.myemployee;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.util.Linkify;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;

public class DetailActivity extends AppCompatActivity {


    TextView detailName , detailAge , detailCity  ,detailSkill,detailSalary,detailEducation, detailType , detailCV , detailPhone, detailEmail;
    ImageView detailImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        detailAge = findViewById(R.id.detailAge);
        detailName = findViewById(R.id.detailName);
        detailCity = findViewById(R.id.detailCity);
        detailEducation = findViewById(R.id.detailEducation);
        detailType = findViewById(R.id.detailType);
        detailSalary = findViewById(R.id.detailSalary);
        detailSkill = findViewById(R.id.detailSkill);
        detailImage = findViewById(R.id.detailImage);
        detailCV = findViewById(R.id.detailCV);
        detailEmail = findViewById(R.id.detailEmail);
        detailPhone = findViewById(R.id.detailPhone);
        detailEmail.setAutoLinkMask(Linkify.ALL);
        detailPhone.setAutoLinkMask(Linkify.ALL);
        detailCV.setAutoLinkMask(Linkify.ALL);


        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            detailName.setText(bundle.getString("Name"));
            detailAge.setText(bundle.getString("Age"));
            detailCity.setText(bundle.getString("City"));
            detailEducation.setText(bundle.getString("Education"));
            detailSkill.setText(bundle.getString("Skill"));
            detailSalary.setText(bundle.getString("Salary"));
            detailType.setText(bundle.getString("Type"));
            detailCV.setText(bundle.getString("CV"));
            detailEmail.setText(bundle.getString("Email"));
            detailPhone.setText(bundle.getString("Phone"));








            Glide.with(this).load(bundle.getString("Image")).into(detailImage);
        }

    }
}