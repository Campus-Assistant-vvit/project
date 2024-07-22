package com.example.campusassistant;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class Retrieve_File extends AppCompatActivity {
    ListView lv;
    ProgressDialog pd;
    List<Filename> filter;
    ImageView ig;
    int posi;
    String key;
    String rollNumber;
    ProgressBar progressBar;

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        posi = info.position;
        key = filter.get(posi).getKey();

        if (item.getItemId() == R.id.del) {
            // Delete operation
            deleteFile(key);
            return true;
        } else if (item.getItemId() == R.id.ig) {
            // View operation
            String fileUrl = filter.get(posi).getUrl();
            if (fileUrl != null) {
                openFile(fileUrl); // Use openFile function to open the file
            } else {
                Toast.makeText(Retrieve_File.this, "File URL not available", Toast.LENGTH_SHORT).show();
            }
            return true;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId() == R.id.listv) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.option_menu, menu);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_file);
        lv = findViewById(R.id.listv);
        filter = new ArrayList<>();
        ig = findViewById(R.id.ig);
        progressBar = findViewById(R.id.progressBar);
        ig.setVisibility(View.INVISIBLE);

        // Retrieve roll number from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("CMS", MODE_PRIVATE);
        rollNumber = sharedPreferences.getString("rollnumber", "");

        retrieveFiles();

        registerForContextMenu(lv);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Open file operation
                String fileUrl = filter.get(i).getUrl();
                if (fileUrl != null) {
                    openFile(fileUrl);
                } else {
                    Toast.makeText(Retrieve_File.this, "File URL not available", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void retrieveFiles() {
        pd = new ProgressDialog(this);
        pd.setTitle("Retrieving Files");
        pd.show();

        // Get a reference to the user's files in the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference userRef = database.getReference("users").child(rollNumber).child("files");

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                filter.clear();
                for (DataSnapshot fileSnapshot : dataSnapshot.getChildren()) {
                    String fileUrl = fileSnapshot.getValue(String.class);
                    String fileName = fileSnapshot.getKey();

                    Filename filename = new Filename(fileName, fileUrl, fileSnapshot.getKey());
                    filter.add(filename);
                }

                viewAll();
                pd.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                pd.dismiss();
                Toast.makeText(Retrieve_File.this, "Error retrieving files", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void viewAll() {
        // Create a custom adapter to display the files in the ListView
        FileAdapter adapter = new FileAdapter(Retrieve_File.this, filter);
        lv.setAdapter(adapter);

        if (filter.isEmpty()) {
            lv.setVisibility(View.GONE);
            ig.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        } else {
            lv.setVisibility(View.VISIBLE);
            ig.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
        }
    }

    public boolean deleteFile(String key) {
        // Delete file from Firebase Storage
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        StorageReference fileRef = storageRef.child("uploads/" + rollNumber + "/" + key);

        // Delete from Firebase Storage
        fileRef.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    // Delete from Firebase Realtime Database
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference userRef = database.getReference("users").child(rollNumber).child("files").child(key);
                    userRef.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                filter.remove(posi);
                                viewAll();
                                Toast.makeText(Retrieve_File.this, "File deleted successfully", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Retrieve_File.this, "Error deleting file", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(Retrieve_File.this, "Error deleting file", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return false;
    }

    private void openFile(String fileUrl) {
        if (fileUrl != null) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(fileUrl));  // Just set the data (URL)
            startActivity(intent);
        } else {
            Toast.makeText(Retrieve_File.this, "File URL not available", Toast.LENGTH_SHORT).show();
        }
    }

    // FileAdapter Class
    public class FileAdapter extends ArrayAdapter<Filename> {

        public FileAdapter(Context context, List<Filename> filenames) {
            super(context, 0, filenames);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            Filename filename = getItem(position);

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.file_item, parent, false); // Use your new layout here
            }

            TextView fileNameTextView = convertView.findViewById(R.id.file_name_text_view); // Assuming you have a TextView with this ID in your new layout
            fileNameTextView.setText(filename.getFilename());

            return convertView;
        }
    }

    // Helper method to get MIME type from file extension
    private String getMimeType(String fileExtension) {
        switch (fileExtension.toLowerCase()) {
            case "pdf":
                return "application/pdf";
            case "jpg":
            case "jpeg":
            case "png":
                return "image/jpeg"; // Use "image/jpeg" for images
            case "gif":
                return "image/gif";
            // Add more file extensions and MIME types as needed
            default:
                return null; // Return null for unknown file types
        }
    }
}