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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

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
    String rollNumber;

    @Deprecated
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCompat.requestPermissions(UploadFile.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.MANAGE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
        setContentView(R.layout.activity_upload_file);

        uf = findViewById(R.id.upf);
        ucf = findViewById(R.id.choof);

        // Retrieve roll number from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("CMS", MODE_PRIVATE);

        rollNumber = sharedPreferences.getString("rollnumber", "");

        ucf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent f = new Intent(Intent.ACTION_GET_CONTENT);
                f.setType("*/*");
                startActivityForResult(f, 100);
            }
        });


        uf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (img == null) {
                    Toast.makeText(UploadFile.this, "Select any image", Toast.LENGTH_LONG).show();
                } else {
                    pd = new ProgressDialog(UploadFile.this);
                    pd.setTitle("Uploading");
                    pd.show();
                    SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss", Locale.CANADA);
                    Date now = new Date();
                    String fn = getName(img, getApplicationContext());

                    path = "/" + rollNumber + "/" + fn;
                    System.out.println("uri:" + img);

                    // Upload the file to Firebase Storage
                    uploadFileToStorage(rollNumber, img);
                }
            }
        });
    }

    private void uploadFileToStorage(String rollNumber, Uri fileUri) {
        // Get a reference to Firebase Storage
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();

        // Create a reference to store the file under the user's roll number
        StorageReference userFileRef = storageRef.child("uploads/" + rollNumber + "/" + System.currentTimeMillis() + "_" + fileUri.getLastPathSegment());

        // Upload the file to Firebase Storage
        userFileRef.putFile(fileUri)
                .addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        if (task.isSuccessful()) {
                            // File uploaded successfully, get the download URL
                            userFileRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    if (task.isSuccessful()) {
                                        Uri fileUrl = task.getResult();
                                        saveFileUrlToDatabase(rollNumber, fileUrl);
                                    } else {
                                        // Handle the file URL retrieval error
                                        pd.dismiss();
                                        Toast.makeText(UploadFile.this, "File URL retrieval failed", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        } else {
                            // Handle the file upload error
                            pd.dismiss();
                            Toast.makeText(UploadFile.this, "File upload failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void saveFileUrlToDatabase(String rollNumber, Uri fileUrl) {
        // Get a reference to the Realtime Database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference userRef = database.getReference("users").child(rollNumber);

        // Save the file URL in the user's data in the database
        userRef.child("files").push().setValue(fileUrl.toString())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(Task<Void> task) {
                        pd.dismiss();
                        if (task.isSuccessful()) {
                            Toast.makeText(UploadFile.this, "File uploaded successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            // Handle the file URL saving error
                            Toast.makeText(UploadFile.this, "Failed to save file URL", Toast.LENGTH_SHORT).show();
                        }
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
