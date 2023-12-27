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

import com.example.votingapp.R;
import com.example.votingapp.databinding.ActivitySignInBinding;
import com.example.votingapp.databinding.ActivitySignUpBinding;
import com.example.votingapp.datacollection.UserInformation;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class SignUp extends AppCompatActivity {

    private ActivitySignUpBinding binding;


    //firebase auth

    private FirebaseAuth firebaseAuth;




    //progress dialog

    private ProgressDialog progressDialog;

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        //initialise firebase auth

        firebaseAuth = FirebaseAuth.getInstance();


        //setup progress dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("please wait");
        progressDialog.setCanceledOnTouchOutside(false);


        //handle create account password

        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp.this, SignIn.class));
            }
        });

        // handle register button
        binding.signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validateData();

            }
        });



    }

    //validating user information before creating account for them

    private String name="", idNumber="", email="", userType="", password="", confirmPassword="";
    private void validateData() {

        //validating user information


        name = binding.fullNames.getText().toString().trim();
        idNumber = binding.idNumber.getText().toString().trim();
        email = binding.emailAddress.getText().toString().trim();
        password = binding.password.getText().toString().trim();
        confirmPassword = binding.confirmPassword.getText().toString().trim();




        //validate data

        if(TextUtils.isEmpty(name)){

            Toast.makeText(this, "enter your name", Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(password)){

            Toast.makeText(this, "enter password", Toast.LENGTH_SHORT).show();
        }
        else if(password.length() < 8){

            Toast.makeText(this, "password too short", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(confirmPassword)){

            Toast.makeText(this, "confirm password", Toast.LENGTH_SHORT).show();
        }
        else if(!password.equals(confirmPassword)){

            Toast.makeText(this, "password do not match", Toast.LENGTH_SHORT).show();
        }

        else if(TextUtils.isEmpty(idNumber)){

            Toast.makeText(this, "enter your id number", Toast.LENGTH_SHORT).show();
        }
        else if (idNumber.length() != 13){

            Toast.makeText(this, "invalid id number", Toast.LENGTH_SHORT).show();

        }

        else if(TextUtils.isEmpty(email)){

            Toast.makeText(this, "enter your email", Toast.LENGTH_SHORT).show();
        }

        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(this, "invalid email", Toast.LENGTH_SHORT).show();
        }


        else {
            //create account for the user
            createUserAccount();
        }






    }

    private void createUserAccount() {


        //show progress

        progressDialog.setMessage("Creating account..");
        progressDialog.show();

        //create user in firebase auth

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {

                        //account creation success


                        String uid = authResult.getUser().getUid();



                        //getting the unique code from the id document
                        String extractedString = idNumber.substring(5, 10);





                        UserInformation userInformation=new UserInformation(name,idNumber,email,uid,"", userType,extractedString,"no");




                        firebaseDatabase.getReference("Users").child(uid).setValue(userInformation);

                        //send verification email right after registering

                        //send verification email
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                                progressDialog.dismiss();

                                //send verification to the user's email to the user registered
                                Toast.makeText(SignUp.this, "Account created!!", Toast.LENGTH_SHORT).show();

                                //direct the user to log in screen


                                startActivity(new Intent(SignUp.this, SignIn.class));


                                // Clear EditText fields to allow another user to be registered
                                binding.fullNames.setText("");
                                binding.idNumber.setText("");
                                binding.emailAddress.setText("");
                                binding.password.setText("");
                                binding.confirmPassword.setText("");









                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                progressDialog.dismiss();

                                Toast.makeText(SignUp.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });







                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        //account creation failure
                        progressDialog.dismiss();
                        Toast.makeText(SignUp.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
    }
}