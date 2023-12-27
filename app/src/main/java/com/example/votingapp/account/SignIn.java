package com.example.votingapp.account;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.votingapp.MainActivity;
import com.example.votingapp.SplashActivity;
import com.example.votingapp.databinding.ActivitySignInBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class SignIn extends AppCompatActivity {

    //view binding
    private ActivitySignInBinding binding;

    //firebase auth
    private FirebaseAuth firebaseAuth;

    //progress dialog

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        //int firebase auth
        firebaseAuth = FirebaseAuth.getInstance();



        //setup progress dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait");
        progressDialog.setCanceledOnTouchOutside(false);




        //handle create account password

        binding.createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignIn.this, SignUp.class));
            }
        });

        //handle forgot password click

        binding.forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignIn.this, ForgotPassword.class));
            }
        });

        //handle, begin login

        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //validate user input if it is correct or not before signing them in
                validateData();



            }
        });
    }



    //validate user input first before logging in

    private String email="", password="";
    private void validateData() {

        //validate user data before login

        email = binding.emailAddress.getText().toString().trim();
        password = binding.password.getText().toString().trim();


        if (email.isEmpty()){
            Toast.makeText(this, "Enter email", Toast.LENGTH_SHORT).show();
        }

        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(this, "invalid email", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "enter password", Toast.LENGTH_SHORT).show();
        }
        else{

            //begin logging in user
            loginUser();
        }
    }


    private void loginUser() {

        //show progress

        progressDialog.setMessage("logging in...");
        progressDialog.show();

        //login user

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {

                        //check if the email is verified
                        checkUser();



                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        //Login failure
                        progressDialog.dismiss();
                        Toast.makeText(SignIn.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });


    }


    private void checkUser() {


        //checking if the user is verified or not

        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser.isEmailVerified() || !firebaseUser.isEmailVerified()){ //allow users for now to log in even if they are not verified



            //login the user and direct them to the dashboard

            progressDialog.dismiss();
            startActivity(new Intent(SignIn.this, SplashActivity.class));
            finish();
            finish();
            finish();
            finish();





        }

        else {

            //show progress bar
            progressDialog.setMessage("sending account verification instructions");
            progressDialog.show();


            //send verification email
            FirebaseUser user = firebaseAuth.getCurrentUser();
            user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {

                    progressDialog.dismiss();

                    //send verification to the user's email
                    Toast.makeText(SignIn.this, "email verification sent", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressDialog.dismiss();

                    Toast.makeText(SignIn.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });




        }
    }
}