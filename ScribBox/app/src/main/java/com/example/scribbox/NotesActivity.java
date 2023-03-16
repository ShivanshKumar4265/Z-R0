package com.example.scribbox;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class NotesActivity extends AppCompatActivity {

    private FloatingActionButton BtnAddNotes;
    private RecyclerView NotesRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        BtnAddNotes = findViewById(R.id.btnAddNotes);
        NotesRecyclerView = findViewById(R.id.notesRecyclerView);

        getSupportActionBar().setTitle("ScribBox");

        BtnAddNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NotesActivity.this,CreateNotesActivity.class));
            }
        });
    }
}