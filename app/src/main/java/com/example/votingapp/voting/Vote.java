package com.example.votingapp.voting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.votingapp.R;
import com.example.votingapp.databinding.ActivityMainBinding;
import com.example.votingapp.databinding.ActivityVoteBinding;
import com.google.firebase.auth.FirebaseAuth;

public class Vote extends AppCompatActivity {

    private ActivityVoteBinding binding;



    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityVoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());




        //handle all the buttons to direct you to one activity where you can send your vote

        binding.ancBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //open the activity to vote successfully
                startActivity(new Intent(Vote.this, CastVote.class));

                //pass the organization name to the next activity

                Intent intent = new Intent(Vote.this, CastVote.class);
                intent.putExtra("politicalName", "ANC");
                startActivity(intent);

            }
        });

        binding.daBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //open the activity to vote successfully
                startActivity(new Intent(Vote.this, CastVote.class));

                //pass the organization name to the next activity

                Intent intent = new Intent(Vote.this, CastVote.class);
                intent.putExtra("politicalName", "DA");
                startActivity(intent);

            }
        });


        binding.effBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //open the activity to vote successfully
                startActivity(new Intent(Vote.this, CastVote.class));

                //pass the organization name to the next activity

                Intent intent = new Intent(Vote.this, CastVote.class);
                intent.putExtra("politicalName", "EFF");
                startActivity(intent);

            }
        });


        binding.ifpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //open the activity to vote successfully
                startActivity(new Intent(Vote.this, CastVote.class));

                //pass the organization name to the next activity

                Intent intent = new Intent(Vote.this, CastVote.class);
                intent.putExtra("politicalName", "IFP");
                startActivity(intent);
            }
        });

        binding.nfpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //open the activity to vote successfully
                startActivity(new Intent(Vote.this, CastVote.class));

                //pass the organization name to the next activity

                Intent intent = new Intent(Vote.this, CastVote.class);
                intent.putExtra("politicalName", "NFP");
                startActivity(intent);

            }
        });


        //handle back button

        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });





    }
}