package com.example.votingapp.account;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.votingapp.R;
import com.example.votingapp.databinding.ActivityForgotPasswordBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {



    //view binding
    private ActivityForgotPasswordBinding binding;

    //firebase auth
    private FirebaseAuth firebaseAuth;

    //progressbar dialog
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForgotPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //initialize firebase auth

        firebaseAuth = FirebaseAuth.getInstance();

        //set up progress dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("please wait...");
        progressDialog.setCanceledOnTouchOutside(false);



        //handle click , begin recovery password

        binding.submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
            }
        });




    }



    private String email="";

    private void validateData() {

        //get data

        email = binding.emailAddress.getText().toString().trim();

        //validate data

        if (email.isEmpty()){
            Toast.makeText(ForgotPassword.this, "Enter email", Toast.LENGTH_SHORT).show();
        }

        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(ForgotPassword.this, "invalid email", Toast.LENGTH_SHORT).show();
        }
        else {
            recoverPassword();


        }
    }

    private void recoverPassword() {

        //show progress bar
        progressDialog.setMessage("sending password recovery instructions to "+email);
        progressDialog.show();

        //begin sending reset information

        firebaseAuth.sendPasswordResetEmail(email)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        progressDialog.dismiss();
                        Toast.makeText(ForgotPassword.this, "check your email", Toast.LENGTH_SHORT).show();



                        startActivity(new Intent(ForgotPassword.this, SignIn.class));
                        finish();


                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //failed to send

                        progressDialog.dismiss();
                        Toast.makeText(ForgotPassword.this, "failed to send due to "+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


    }
}