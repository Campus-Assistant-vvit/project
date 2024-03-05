package com.example.campusassistant;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class UploadFile extends AppCompatActivity {
    Button uf, ucf;
    ProgressDialog pd;
    Uri img;
    ImageButton backb;
    String path;

    @Deprecated
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCompat.requestPermissions(UploadFile.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.MANAGE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
        setContentView(R.layout.activity_upload_file);
        uf = findViewById(R.id.upf);
        ucf = findViewById(R.id.choof);
        ucf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent f = new Intent(Intent.ACTION_GET_CONTENT);
                f.setType("*/*");
                startActivityForResult(f, 100);
            }
        });
//        backb = findViewById(R.id.ba);
//        backb.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(UploadFile.this, digilockerMain.class));
//            }
//        });
        uf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (img == null) {
                    Toast.makeText(UploadFile.this, "Upload any image", Toast.LENGTH_LONG).show();
                }
                pd = new ProgressDialog(UploadFile.this);
                pd.setTitle("Uploading");
                pd.show();
                SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss", Locale.CANADA);
                Date now = new Date();
                String fn = getName(img, getApplicationContext());
                String user;
                SharedPreferences pre = getSharedPreferences("CMS", MODE_PRIVATE);
                SharedPreferences.Editor edi = pre.edit();
                user = pre.getString("na", "");
                path = "/" + user + " " + fn;
                System.out.println("uri:" + img);
                // Firebase Storage code removed
            }
        });

    }

    @SuppressLint("Range")
    String getName(Uri uri, Context context) {
        String res = null;
        if (Objects.equals(uri.getScheme(), "content")) {
            try (Cursor cursor = context.getContentResolver().query(uri, null, null, null, null)) {
                if (cursor != null && cursor.moveToFirst()) {
                    res = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            }
            if (res == null) {
                res = uri.getPath();
                assert res != null;
                int cutt = res.lastIndexOf('/');
                if (cutt != -1) {
                    res = res.substring(cutt + 1);
                }
            }
        }
        return res;
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 100) {
                try {
                    assert data != null;
                    img = data.getData();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
