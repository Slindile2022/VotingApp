package com.example.votingapp.voting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.votingapp.MainActivity;
import com.example.votingapp.R;
import com.example.votingapp.account.SignIn;
import com.example.votingapp.account.SignUp;
import com.example.votingapp.databinding.ActivityDoneVotingBinding;
import com.example.votingapp.databinding.FragmentVoteBinding;
import com.example.votingapp.navigation.VoteFragment;

public class DoneVoting extends AppCompatActivity {

    private ActivityDoneVotingBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDoneVotingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //handle the back button
        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(DoneVoting.this, MainActivity.class));
            }
        });
    }
}