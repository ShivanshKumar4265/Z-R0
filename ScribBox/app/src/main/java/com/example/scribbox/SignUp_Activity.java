package com.example.scribbox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUp_Activity extends AppCompatActivity {

    /**
     * Declaring all the views in this activity
     */

    private EditText  SignUpMail;
    private EditText  SignUpPassword;
    private Button BtnSignUp;
    private TextView WantToLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


/**
 * Finding all the views used in this Layout
 */

        SignUpMail = findViewById(R.id.signUpMail);
        SignUpPassword = findViewById(R.id.signUpPassword);
        BtnSignUp = findViewById(R.id.btnSignUp);
        WantToLogIn = findViewById(R.id.wantToLogIn);


/**
* Providing functionality to "TextView WantToLogIn" to intent to MainActivity
*/
        WantToLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_toLogInActivity = new Intent(SignUp_Activity.this,MainActivity.class);
                startActivity(intent_toLogInActivity);
            }
        });




        BtnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /**
                 * Getting input from user from "EditText  SignUpPassword" , "EditText  SignUpPassword"
                 */
                String mail_enteredToRegister  = SignUpMail.getText().toString().trim();
                String password_enterToRegister = SignUpPassword.getText().toString().trim();

                if(mail_enteredToRegister.isEmpty()){

                     Toast.makeText(getApplicationContext(),"Enter Email!",Toast.LENGTH_SHORT).show();

                } else if (password_enterToRegister.isEmpty()) {

                    Toast.makeText(getApplicationContext(),"Enter Password!",Toast.LENGTH_SHORT).show();

                }else if(password_enterToRegister.length()<=7){

                    Toast.makeText(SignUp_Activity.this, "Password should be greater than 7 characters", Toast.LENGTH_SHORT).show();

                }else {
                    //Registeer to fire base
                }
            }
        });
    }
}