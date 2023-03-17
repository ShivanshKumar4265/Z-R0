package com.example.scribbox;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CreateNotesActivity extends AppCompatActivity {


    private EditText CreateTitleForNotes,CreateContentOfNotes;
    private FloatingActionButton SaveNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_notes);
        /**
         * Finding all the views
         */
        CreateTitleForNotes = findViewById(R.id.createTitleForNotes);
        CreateContentOfNotes = findViewById(R.id.createcontentofnote);
        SaveNotes = findViewById(R.id.saveNotes);
        /**
         * Hiding  the action bar
         */
        getSupportActionBar().hide();


    }
}