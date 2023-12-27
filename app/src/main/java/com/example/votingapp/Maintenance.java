package com.example.votingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.votingapp.databinding.ActivityDoneVotingBinding;
import com.example.votingapp.databinding.ActivityMaintenanceBinding;
import com.example.votingapp.voting.DoneVoting;

public class Maintenance extends AppCompatActivity {

    private ActivityMaintenanceBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMaintenanceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }
}