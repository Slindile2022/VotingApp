package com.example.votingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.os.Bundle;

import com.example.votingapp.databinding.ActivityMainBinding;
import com.example.votingapp.navigation.NewsFragment;
import com.example.votingapp.navigation.ProfileFragment;
import com.example.votingapp.navigation.VoteFragment;

public class MainActivity extends AppCompatActivity {

    //view binding
    private ActivityMainBinding binding;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        replaceFragment(new NewsFragment());





        //ensuring that the fragments always change when selected
        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.news) {
                replaceFragment(new NewsFragment());
            }
            else if (item.getItemId() == R.id.vote) {
                replaceFragment(new VoteFragment());
            }
            else if (item.getItemId() == R.id.profile) {
                replaceFragment(new ProfileFragment());
            }



            return true;
        });



    }

    //ensuring that the fragments always change when selected
    private void replaceFragment(Fragment fragment){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =  fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();

    }
}