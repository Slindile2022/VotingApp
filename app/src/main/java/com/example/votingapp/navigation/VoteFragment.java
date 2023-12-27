package com.example.votingapp.navigation;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chaos.view.PinView;
import com.example.votingapp.R;
import com.example.votingapp.databinding.FragmentProfileBinding;
import com.example.votingapp.databinding.FragmentVoteBinding;
import com.example.votingapp.voting.Vote;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VoteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VoteFragment extends Fragment {

    private FragmentVoteBinding binding;

    private FirebaseAuth firebaseAuth;


    //progress dialog

    private ProgressDialog progressDialog;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public VoteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VoteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VoteFragment newInstance(String param1, String param2) {
        VoteFragment fragment = new VoteFragment();
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

        // Inflate the layout for this fragment
        binding = FragmentVoteBinding.inflate(inflater, container, false);
        View view = binding.getRoot();


        //int firebase auth

        firebaseAuth = FirebaseAuth.getInstance();

        //changing the color


        String text = binding.idCode.getText().toString();
        SpannableString spannableString = new SpannableString(text);

        // Define the start and end indices of the substring you want to color
        int startIndex = text.indexOf("67891");
        int endIndex = startIndex + "67891".length();

        // Apply red color to the specified substring
        spannableString.setSpan(new ForegroundColorSpan(Color.RED), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        binding.idCode.setText(spannableString);



        //handling the verify button to check the value inserted

        binding.verifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //get user code and validate with the one entered and if they match allow the user to progress to the next stage

                //getting your information from realtime database


                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();


                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
                databaseReference.child(firebaseUser.getUid())
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                //get the user code
                                String code = "" + snapshot.child("userCode").getValue();
                                String enteredCode = binding.pinView.getText().toString().trim();
                                String voted = ""+snapshot.child("voted").getValue();


                                if (code.equals(enteredCode) && voted.equals("no")){
                                    startActivity(new Intent(requireContext(), Vote.class));
                                } else if (enteredCode.equals("")) {

                                    Toast.makeText(getActivity(), "insert code", Toast.LENGTH_SHORT).show();

                                }
                             else {
                                    Toast.makeText(getActivity(), "incorrect code or You cannot vote twice", Toast.LENGTH_SHORT).show();


                                    binding.pinView.setText("");        //clearing  the pin
                                }



                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });



            }
        });



        return view;
    }
}