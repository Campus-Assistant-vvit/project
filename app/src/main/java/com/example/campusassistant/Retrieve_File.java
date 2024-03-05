package com.example.campusassistant;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Retrieve_File extends AppCompatActivity {
    ListView lv;
    ProgressDialog pd;
    List<Filename> filter;
    ImageView ig;
    int posi;
    String key;

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if (item.getItemId() == R.id.del) {
            // Delete operation
            // Removed Firebase Storage and Database code
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
        ig.setVisibility(View.INVISIBLE);
        viewAll();
        registerForContextMenu(lv);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Open file operation
                // Removed Firebase Storage code
            }
        });
    }

    private void viewAll() {
        // Assume filter is the list you are populating in your ListView

        if (filter.isEmpty()) {
            // If the list is empty, show the ImageView and hide the ListView
            lv.setVisibility(View.GONE);
            ig.setVisibility(View.VISIBLE);
        } else {
            // If the list is not empty, show the ListView and hide the ImageView
            lv.setVisibility(View.VISIBLE);
            ig.setVisibility(View.GONE);
        }
    }

}
