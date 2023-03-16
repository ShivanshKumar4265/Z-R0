package com.example.scribbox;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

public class NotesActivity extends AppCompatActivity {

    private FloatingActionButton BtnAddNotes;
    private RecyclerView NotesRecyclerView;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        firebaseAuth = FirebaseAuth.getInstance();

        getSupportActionBar().setTitle("Your Notes");
        
        BtnAddNotes = findViewById(R.id.btnAddNotes);
        NotesRecyclerView  = findViewById(R.id.notesRecyclerView);

        BtnAddNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NotesActivity.this,CreateNotesActivity.class));
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){
            case R.id.btnLogOut:
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(NotesActivity.this,MainActivity.class));
        }
        return super.onOptionsItemSelected(item);

    }
}