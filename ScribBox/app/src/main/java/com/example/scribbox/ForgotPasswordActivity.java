package com.example.scribbox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ForgotPasswordActivity extends AppCompatActivity {
    /**
     * Declaring all the views in this activity
     */

    private EditText enterRegistersEmail;
    private Button BtnRecover;
    private TextView wantToLoginClickHere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

       // getSupportActionBar().hide(); // use to hid the ActionBar in this activity

/**
* Finding all the views used in this Layout
*/
        enterRegistersEmail = findViewById(R.id.EnterRegisteredEmail);
        BtnRecover = findViewById(R.id.btnRecover);
        wantToLoginClickHere = findViewById(R.id.WantToLogInClickHere);

 /**
* Providing functionality to "TextView wantToLoginClickHere" to intent to MainActivity
*/
        wantToLoginClickHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_to_LogInActivity = new Intent(ForgotPasswordActivity.this,MainActivity.class);
                startActivity(intent_to_LogInActivity);
            }
        });

/**
 *
*/

        BtnRecover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail_toRecoverPassword = enterRegistersEmail.getText().toString().trim();

                if(mail_toRecoverPassword.isEmpty()){

                    Toast.makeText(getApplicationContext(),"Provide Email",Toast.LENGTH_SHORT).show();
                }
                else{
                    // We have to send password recover email.
                }
            }
        });

    }
}