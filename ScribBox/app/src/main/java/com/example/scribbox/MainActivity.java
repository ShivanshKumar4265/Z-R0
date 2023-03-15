package com.example.scribbox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button logIn,SignUp;
    private EditText logInMail, loginPassword;
    private TextView ForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Finding all the views
         */

        logIn = findViewById(R.id.btnlogIn);
        SignUp = findViewById(R.id.btnSignUp);
        logInMail = findViewById(R.id.LoginMail);
        loginPassword = findViewById(R.id.LoginPassword);
        ForgotPassword = findViewById(R.id.forgotPasswordBtn);

        /**
         * On clicking signUp Button, then it will intent_toSignUpActivity"
         */

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_toSignUpActivity = new Intent(MainActivity.this, SignUp_Activity.class);
                startActivity(intent_toSignUpActivity);

            }
        });

        /**
         * On clicking Forgot Password Button, then it will intent_toForgetPasswordActivity"
         */

        ForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_toForgetPasswordActivity = new Intent(MainActivity.this,ForgotPasswordActivity.class);
                startActivity(intent_toForgetPasswordActivity);
            }
        });

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String retrieve_LogInMail = logInMail.getText().toString().trim();
                String retrieve_LogInPassword = logInMail.getText().toString().trim();

                if(retrieve_LogInMail.isEmpty() || retrieve_LogInPassword.isEmpty()){

                    Toast.makeText(MainActivity.this, "Provide Credentials", Toast.LENGTH_SHORT).show();

                }
                else{

                    //Login the user
                }

            }
        });


    }
}