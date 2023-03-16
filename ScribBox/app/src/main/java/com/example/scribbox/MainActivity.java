package com.example.scribbox;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private Button logIn,SignUp;
    private EditText logInMail, loginPassword;
    private TextView ForgotPassword;

    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getSupportActionBar().hide();
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        /**
         * If user is already logged In then no need to login again and again
          */


        if(firebaseUser!=null){
            finish();
            startActivity(new Intent(MainActivity.this,NotesActivity.class));
        }

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
                String retrieve_LogInPassword = loginPassword.getText().toString().trim();

                if(retrieve_LogInMail.isEmpty() || retrieve_LogInPassword.isEmpty()){

                    Toast.makeText(MainActivity.this, "Provide Credentials", Toast.LENGTH_SHORT).show();

                }
                else{

                    /**
                     *  User login and OnComplete Listener
                     */

                     firebaseAuth.signInWithEmailAndPassword(retrieve_LogInMail,retrieve_LogInPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                         @Override
                         public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    checkEmailVerification();
                                }
                                else {
                                    Toast.makeText(MainActivity.this, "Account Doesn't Exist", Toast.LENGTH_SHORT).show();
                                    firebaseAuth.signOut();
                                }
                         }
                     });

                }

            }
        });


    }

    private void checkEmailVerification() {

        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser.isEmailVerified() == true){
            Toast.makeText(this, "Successfully Logged In", Toast.LENGTH_SHORT).show();
            finish();
            startActivity(new Intent(MainActivity.this,NotesActivity.class));
        }else{
            Toast.makeText(this, "You haven't verified Email", Toast.LENGTH_SHORT).show();
        }

    }


}