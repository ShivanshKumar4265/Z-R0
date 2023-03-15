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

public class SignUp_Activity extends AppCompatActivity {

    /**
     * Declaring all the views in this activity
     */

    private EditText  SignUpMail;
    private EditText  SignUpPassword;
    private Button BtnSignUp;
    private TextView WantToLogIn;

    /**
     * creating the instance of FirebaseAuth
     */
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        firebaseAuth = FirebaseAuth.getInstance(); // Getting the instance of the firebase Auth


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


                    firebaseAuth.createUserWithEmailAndPassword(mail_enteredToRegister,password_enterToRegister).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(SignUp_Activity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();


                                /**
                                 * A function to verify the email whether it exist or not
                                 */

                                sendEmailVerification();
                            }else{
                                Toast.makeText(SignUp_Activity.this, "Something went wrong...", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                }
            }
        });
    }

    private void sendEmailVerification() {

        // getting current user
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser!=null){
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(SignUp_Activity.this, "Verification Email Has Been Sent.please verify it", Toast.LENGTH_SHORT).show();
                    firebaseAuth.signOut();
                    finish();
                    startActivity(new Intent(SignUp_Activity.this,MainActivity.class));
                }
            });
        }else {
            Toast.makeText(this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
        }
    }
}