package com.example.campusassistant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class digilockerMain extends AppCompatActivity {
    Button upload,retri,test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digilocker_main);
        upload=findViewById(R.id.up);
        retri=findViewById(R.id.ret);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(digilockerMain.this,UploadFile.class));
            }
        });
        retri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(digilockerMain.this, Retrieve_File.class));
            }
        });
    }
}