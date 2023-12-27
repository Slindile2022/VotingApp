package com.example.votingapp.navigation;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.votingapp.R;
import com.example.votingapp.databinding.ActivityVoteBinding;
import com.example.votingapp.databinding.FragmentNewsBinding;
import com.example.votingapp.databinding.FragmentVoteBinding;
import com.example.votingapp.voting.Vote;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsFragment extends Fragment {

    private FragmentNewsBinding binding;

    private FirebaseAuth firebaseAuth;

    private String totalNumberOfVoters;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NewsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewsFragment newInstance(String param1, String param2) {
        NewsFragment fragment = new NewsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        binding = FragmentNewsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();


        //int firebase auth

        firebaseAuth = FirebaseAuth.getInstance();

        //load the total number of people who voted

        DatabaseReference databaseReference7 = FirebaseDatabase.getInstance().getReference("AllVoters");
        databaseReference7
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {


                        String totalVoters = "" + snapshot.child("totalNumberOfVoters").getValue();



                        //assign the values into the layout
                        totalNumberOfVoters = totalVoters;

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });






        //load all the information about the priorities in terms of their order

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("FirstIssue");
        databaseReference
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {


                        String water1 = "" + snapshot.child("Water").getValue();
                        String work1 = "" + snapshot.child("Work").getValue();
                        String road1 = "" + snapshot.child("Road").getValue();
                        String electricity1 = "" + snapshot.child("Electricity").getValue();



                        //convert the string into int

                        int water = Integer.parseInt(water1);
                        int work = Integer.parseInt(work1);
                        int road = Integer.parseInt(road1);
                        int electricity = Integer.parseInt(electricity1);


                        //create an array to facilitate our sorting

                        int[] sortedIssues = new int[4];

                        //another array that will not change


                        int[] issues = new int[4];

                        issues[0] = water;
                        issues[1] = work;
                        issues[2] = road;
                        issues[3] = electricity;

                        //storing the information on the array

                        sortedIssues[0] = water;
                        sortedIssues[1] = work;
                        sortedIssues[2] = road;
                        sortedIssues[3] = electricity;


                        //sort the list
                        Arrays.sort(sortedIssues);

                        //assign the values into the layout

                        String first = "";



                        //finding the first issue

                        for (int i = 0; i < 4; i++) {

                            if (sortedIssues[3] == issues[i]){
                                //the first is found

                                if (i == 0){

                                    first = "Water";

                                } else if (i == 1) {

                                    first = "Unemployment";

                                }

                                else if (i == 2) {

                                    first = "Potholes";

                                }
                                else {

                                    first = "Electricity";

                                }

                            }
                        }







                        //assigning the values into the front end for statistics

                        binding.oneText.setText(first+ " ("+sortedIssues[3]+" of "+totalNumberOfVoters+")");



                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });



        //load all the information about the priorities in terms of their order

        DatabaseReference databaseReference4 = FirebaseDatabase.getInstance().getReference("SecondIssue");
        databaseReference4
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {


                        String water1 = "" + snapshot.child("Water").getValue();
                        String work1 = "" + snapshot.child("Work").getValue();
                        String road1 = "" + snapshot.child("Road").getValue();
                        String electricity1 = "" + snapshot.child("Electricity").getValue();



                        //convert the string into int

                        int water = Integer.parseInt(water1);
                        int work = Integer.parseInt(work1);
                        int road = Integer.parseInt(road1);
                        int electricity = Integer.parseInt(electricity1);


                        //create an array to facilitate our sorting

                        int[] sortedIssues = new int[4];

                        //another array that will not change


                        int[] issues = new int[4];

                        issues[0] = water;
                        issues[1] = work;
                        issues[2] = road;
                        issues[3] = electricity;

                        //storing the information on the array

                        sortedIssues[0] = water;
                        sortedIssues[1] = work;
                        sortedIssues[2] = road;
                        sortedIssues[3] = electricity;


                        //sort the list
                        Arrays.sort(sortedIssues);

                        //assign the values into the layout

                        String first = "";



                        //finding the first issue

                        for (int i = 0; i < 4; i++) {

                            if (sortedIssues[3] == issues[i]){
                                //the first is found

                                if (i == 0){

                                    first = "Water";



                                } else if (i == 1) {

                                    first = "Unemployment";



                                }

                                else if (i == 2) {

                                    first = "Potholes";


                                }
                                else {

                                    first = "Electricity";

                                }

                            }
                        }





                        //assigning the values into the front end for statistics

                        //binding the information on the design
                        binding.twoText.setText(first+ " ("+sortedIssues[3]+" of "+totalNumberOfVoters+")");


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });



        //load all the information about the priorities in terms of their order

        DatabaseReference databaseReference6 = FirebaseDatabase.getInstance().getReference("ThirdIssue");
        databaseReference6
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {


                        String water1 = "" + snapshot.child("Water").getValue();
                        String work1 = "" + snapshot.child("Work").getValue();
                        String road1 = "" + snapshot.child("Road").getValue();
                        String electricity1 = "" + snapshot.child("Electricity").getValue();



                        //convert the string into int

                        int water = Integer.parseInt(water1);
                        int work = Integer.parseInt(work1);
                        int road = Integer.parseInt(road1);
                        int electricity = Integer.parseInt(electricity1);


                        //create an array to facilitate our sorting

                        int[] sortedIssues = new int[4];

                        //another array that will not change


                        int[] issues = new int[4];

                        issues[0] = water;
                        issues[1] = work;
                        issues[2] = road;
                        issues[3] = electricity;

                        //storing the information on the array

                        sortedIssues[0] = water;
                        sortedIssues[1] = work;
                        sortedIssues[2] = road;
                        sortedIssues[3] = electricity;


                        //sort the list
                        Arrays.sort(sortedIssues);

                        //assign the values into the layout

                        String first = "";




                        //finding the first issue

                        for (int i = 0; i < 4; i++) {

                            if (sortedIssues[3] == issues[i]){
                                //the first is found

                                if (i == 0){

                                    first = "Water";

                                } else if (i == 1) {

                                    first = "Unemployment";

                                }

                                else if (i == 2) {

                                    first = "Potholes";

                                }
                                else {

                                    first = "Electricity";

                                }

                            }
                        }



                        //assigning the values into the front end for statistics

                        binding.threeText.setText(first+ " ("+sortedIssues[3]+" of "+totalNumberOfVoters+")");


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });




        //all the issues that have been  reported



        DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("Issues");
        databaseReference1
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {


                        String water = "" + snapshot.child("Water").getValue();
                        String work = "" + snapshot.child("Work").getValue();
                        String road = "" + snapshot.child("Road").getValue();
                        String electricity = "" + snapshot.child("Electricity").getValue();


                        //set the information

                        binding.water.setText("Water ("+water+")");
                        binding.work.setText("Unemployment ("+work+")");
                        binding.road.setText("Potholes ("+road+")");
                        binding.electricity.setText("Electricity ("+electricity+")");

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });



        //return the view
        return view;

    }
}