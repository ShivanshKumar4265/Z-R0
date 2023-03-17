package com.example.scribbox;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CreateNotesActivity extends AppCompatActivity {


    private EditText CreateTitleForNotes,CreateContentOfNotes;
    private FloatingActionButton SaveNotes;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseFirestore firebaseFirestore;

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
         * Seting the action bar as tool bar
         */
        Toolbar toolbar = findViewById(R.id.toolbarofCreateNotes);
        setSupportActionBar(toolbar);
        // implement back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /**
         * geting instance of of firebase
         */


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();

        SaveNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String get_noteTitle = CreateTitleForNotes.getText().toString();
                String get_noteContent = CreateContentOfNotes.getText().toString();

                if(get_noteTitle.isEmpty() || get_noteContent.isEmpty()){

                    Toast.makeText(CreateNotesActivity.this, "Required both field", Toast.LENGTH_SHORT).show();

                }else{


                    DocumentReference documentReference = firebaseFirestore.collection("notes").document(firebaseUser.getUid()).collection("myNotes").document();
                    Map<String, Object> note = new HashMap<>();
                    note.put("title",get_noteTitle);
                    note.put("content",get_noteContent);


                    documentReference.set(note).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Log.d(TAG, "Requesting connection..");
                            Toast.makeText(CreateNotesActivity.this, "Notes Uploaded", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(CreateNotesActivity.this,NotesActivity.class));

                        }
                    }).addOnFailureListener(new OnFailureListener() {

                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(CreateNotesActivity.this, "Failed to Create note", Toast.LENGTH_SHORT).show();

                        }
                    });


                }
            }
        });

    }



    //If we press back
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}

