package com.example.votingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.votingapp.account.SignIn;
import com.example.votingapp.databinding.ActivitySplashBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SplashActivity extends AppCompatActivity {


    private ActivitySplashBinding binding;

    //firebase auth
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =  ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //int auth

        firebaseAuth = FirebaseAuth.getInstance();



        //start main activity after 2 seconds
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                checkUser();


            }
        },2000);
    }

    private void checkUser() {


        //get current use if is logged in
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser == null){

            //user not logged in, go to log in screen

            startActivity(new Intent(SplashActivity.this, SignIn.class));
            finish();

        }

        else
        {
            //check if the app is accessible or not before a user can start using it


            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Availability");
            databaseReference
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            //get user type

                            String availability =""+snapshot.child("Availability").getValue();


                            //check user type

                            if(availability.equals("no")){
                                //you are an admin


                                startActivity(new Intent(SplashActivity.this, Maintenance.class));
                                finish();
                                finish();
                                finish();
                                finish();



                            }

                            else {

                                //user is logged in, direct them to the main activity since the application is available

                                startActivity(new Intent(SplashActivity.this,  MainActivity.class));
                                finish();

                            }



                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });




        }

    }
}