package com.example.votingapp.voting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.votingapp.R;
import com.example.votingapp.databinding.ActivityCastVoteBinding;
import com.example.votingapp.databinding.ActivityVoteBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CastVote extends AppCompatActivity {

    private ActivityCastVoteBinding binding;

    private FirebaseAuth firebaseAuth;



    private String politicalName, DA, ANC, EFF, IFP, NFP;


    private String firstChoice, secondChoice, thirdChoice, userCode, totalNumberOfVoters, waterIssues, roadIssues, electricityIssues, workIssues;


    private String  waterIssues1, roadIssues1, electricityIssues1, workIssues1,  waterIssues2, roadIssues2, electricityIssues2, workIssues2,  waterIssues3, roadIssues3, electricityIssues3, workIssues3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCastVoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //initialise the variables for the user choices
        firstChoice = "";
        secondChoice = "";
        thirdChoice = "";

        //int firebase auth
        firebaseAuth = FirebaseAuth.getInstance();


        //initialising the selected political party name
        Intent intent = getIntent();
        politicalName = intent.getStringExtra("politicalName");

        //get user id to get the code of the user
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();


        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        databaseReference.child(firebaseUser.getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //get user type


                        String userCode1 = "" + snapshot.child("userCode").getValue();

                        userCode = userCode1;


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        //total number of voters

        DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("AllVoters");
        databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //get user type


                        String totalNumberOfVoters1 = "" + snapshot.child("totalNumberOfVoters").getValue();

                        totalNumberOfVoters = totalNumberOfVoters1;


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


        //total number of issues

        DatabaseReference databaseReference2 = FirebaseDatabase.getInstance().getReference("Issues");
        databaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //get user type


                String waterIssues1 = "" + snapshot.child("Water").getValue();
                String electricityIssues1 = "" + snapshot.child("Electricity").getValue();
                String workIssues1 = "" + snapshot.child("Work").getValue();
                String roadIssues1 = "" + snapshot.child("Road").getValue();

                waterIssues = waterIssues1;
                electricityIssues = electricityIssues1;
                workIssues = workIssues1;
                roadIssues = roadIssues1;


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //first choice calculations

        DatabaseReference databaseReference3 = FirebaseDatabase.getInstance().getReference("FirstIssue");
        databaseReference3.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //get user type


                String waterIssues2 = "" + snapshot.child("Water").getValue();
                String electricityIssues2 = "" + snapshot.child("Electricity").getValue();
                String workIssues2 = "" + snapshot.child("Work").getValue();
                String roadIssues2 = "" + snapshot.child("Road").getValue();

                waterIssues1 = waterIssues2;
                electricityIssues1 = electricityIssues2;
                workIssues1 = workIssues2;
                roadIssues1 = roadIssues2;


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        //second issue calculations

        DatabaseReference databaseReference4 = FirebaseDatabase.getInstance().getReference("SecondIssue");
        databaseReference4.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //get user type


                String waterIssues1 = "" + snapshot.child("Water").getValue();
                String electricityIssues1 = "" + snapshot.child("Electricity").getValue();
                String workIssues1 = "" + snapshot.child("Work").getValue();
                String roadIssues1 = "" + snapshot.child("Road").getValue();

                waterIssues2 = waterIssues1;
                electricityIssues2 = electricityIssues1;
                workIssues2 = workIssues1;
                roadIssues2 = roadIssues1;


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        //third issue


        DatabaseReference databaseReference5 = FirebaseDatabase.getInstance().getReference("ThirdIssue");
        databaseReference5.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //get user type


                String waterIssues1 = "" + snapshot.child("Water").getValue();
                String electricityIssues1 = "" + snapshot.child("Electricity").getValue();
                String workIssues1 = "" + snapshot.child("Work").getValue();
                String roadIssues1 = "" + snapshot.child("Road").getValue();

                waterIssues3 = waterIssues1;
                electricityIssues3 = electricityIssues1;
                workIssues3 = workIssues1;
                roadIssues3 = roadIssues1;


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        //political name counts per vote


        DatabaseReference databaseReference6 = FirebaseDatabase.getInstance().getReference("LeadingOrganization");
        databaseReference6.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //get user type


                String DA1 = "" + snapshot.child("DA").getValue();
                String ANC1 = "" + snapshot.child("ANC").getValue();
                String EFF1 = "" + snapshot.child("EFF").getValue();
                String IFP1 = "" + snapshot.child("IFP").getValue();
                String NFP1 = "" + snapshot.child("NFP").getValue();

                DA = DA1;
                ANC = ANC1;
                EFF = EFF1;
                IFP = IFP1;
                NFP = NFP1;


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });






        //change layout if the user click on the option they want to choose

        binding.waterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //change the background color to light blue so that the user can see that it has been selected

                binding.waterBtn.setBackgroundColor(getResources().getColor(R.color.light_blue));

                //set the first choice to be the selected value


                   firstChoice = "water";


                //make sure that all the other buttons are set to green

                binding.electricBtn.setBackgroundColor(getResources().getColor(R.color.my_primary));
                binding.potholesBtn.setBackgroundColor(getResources().getColor(R.color.my_primary));
                binding.workBtn.setBackgroundColor(getResources().getColor(R.color.my_primary));

            }
        });


        binding.electricBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //change the background color to light blue so that the user can see that it has been selected

                binding.electricBtn.setBackgroundColor(getResources().getColor(R.color.light_blue));


                //set the first choice to be the selected value


                    firstChoice = "electricity";


                //make sure that all the other buttons are set to green

                binding.waterBtn.setBackgroundColor(getResources().getColor(R.color.my_primary));
                binding.potholesBtn.setBackgroundColor(getResources().getColor(R.color.my_primary));
                binding.workBtn.setBackgroundColor(getResources().getColor(R.color.my_primary));
            }
        });

        binding.potholesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //change the background color to light blue so that the user can see that it has been selected

                binding.potholesBtn.setBackgroundColor(getResources().getColor(R.color.light_blue));

                //set the first choice to be the selected value


                    firstChoice = "road";





                //make sure that all the other buttons are set to green

                binding.electricBtn.setBackgroundColor(getResources().getColor(R.color.my_primary));
                binding.waterBtn.setBackgroundColor(getResources().getColor(R.color.my_primary));
                binding.workBtn.setBackgroundColor(getResources().getColor(R.color.my_primary));
            }
        });

        binding.workBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //change the background color to light blue so that the user can see that it has been selected

                binding.workBtn.setBackgroundColor(getResources().getColor(R.color.light_blue));

                //set the first choice to be the selected value


                    firstChoice = "work";





                //make sure that all the other buttons are set to green

                binding.electricBtn.setBackgroundColor(getResources().getColor(R.color.my_primary));
                binding.potholesBtn.setBackgroundColor(getResources().getColor(R.color.my_primary));
                binding.waterBtn.setBackgroundColor(getResources().getColor(R.color.my_primary));
            }
        });


        //handle the first submit button
        binding.firstBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (!TextUtils.isEmpty(firstChoice)) {



                    //make the previous selected choice invisible

                    binding.firstChoice.setVisibility(View.GONE);
                    binding.firstBtn.setVisibility(View.GONE);

                    //set second choice to be visible
                    binding.secondChoice.setVisibility(View.VISIBLE);

                    binding.secondBtn.setVisibility(View.VISIBLE);


                    //change the option to show the user that he/she is on the next step of their vote
                    binding.question.setText("2/3");


                    binding.electricBtn.setBackgroundColor(getResources().getColor(R.color.my_primary));
                    binding.potholesBtn.setBackgroundColor(getResources().getColor(R.color.my_primary));
                    binding.waterBtn.setBackgroundColor(getResources().getColor(R.color.my_primary));
                    binding.workBtn.setBackgroundColor(getResources().getColor(R.color.my_primary));



                    //remove the previous selected option on the next list

                    if (firstChoice.equals("water")){

                        binding.waterBtn1.setVisibility(View.GONE);

                    } else if (firstChoice.equals("electricity")) {

                        binding.electricBtn1.setVisibility(View.GONE);

                    }else if (firstChoice.equals("road")) {

                        binding.potholesBtn1.setVisibility(View.GONE);

                    }
                    else {

                        binding.workBtn1.setVisibility(View.GONE);

                    }




                }
                else {
                    Toast.makeText(CastVote.this, "Please select", Toast.LENGTH_SHORT).show();
                }

            }
        });

        //change layout if the user click on the option they want to choose

        binding.waterBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //change the background color to light blue so that the user can see that it has been selected

                binding.waterBtn1.setBackgroundColor(getResources().getColor(R.color.light_blue));

                //set the first choice to be the selected value


                secondChoice  = "water";


                //make sure that all the other buttons are set to green

                binding.electricBtn1.setBackgroundColor(getResources().getColor(R.color.my_primary));
                binding.potholesBtn1.setBackgroundColor(getResources().getColor(R.color.my_primary));
                binding.workBtn1.setBackgroundColor(getResources().getColor(R.color.my_primary));

            }
        });


        binding.electricBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //change the background color to light blue so that the user can see that it has been selected

                binding.electricBtn1.setBackgroundColor(getResources().getColor(R.color.light_blue));


                //set the first choice to be the selected value


                secondChoice  = "electricity";


                //make sure that all the other buttons are set to green

                binding.waterBtn1.setBackgroundColor(getResources().getColor(R.color.my_primary));
                binding.potholesBtn1.setBackgroundColor(getResources().getColor(R.color.my_primary));
                binding.workBtn1.setBackgroundColor(getResources().getColor(R.color.my_primary));
            }
        });

        binding.potholesBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //change the background color to light blue so that the user can see that it has been selected

                binding.potholesBtn1.setBackgroundColor(getResources().getColor(R.color.light_blue));

                //set the first choice to be the selected value


                secondChoice  = "road";





                //make sure that all the other buttons are set to green

                binding.electricBtn1.setBackgroundColor(getResources().getColor(R.color.my_primary));
                binding.waterBtn1.setBackgroundColor(getResources().getColor(R.color.my_primary));
                binding.workBtn1.setBackgroundColor(getResources().getColor(R.color.my_primary));
            }
        });

        binding.workBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //change the background color to light blue so that the user can see that it has been selected

                binding.workBtn1.setBackgroundColor(getResources().getColor(R.color.light_blue));

                //set the first choice to be the selected value


                secondChoice = "work";





                //make sure that all the other buttons are set to green

                binding.electricBtn1.setBackgroundColor(getResources().getColor(R.color.my_primary));
                binding.potholesBtn1.setBackgroundColor(getResources().getColor(R.color.my_primary));
                binding.waterBtn1.setBackgroundColor(getResources().getColor(R.color.my_primary));
            }
        });

        //handle the second next button

        binding.secondBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!TextUtils.isEmpty(secondChoice)) {


                    //make the previous selected choice invisible

                    binding.secondChoice.setVisibility(View.GONE);
                    binding.secondBtn.setVisibility(View.GONE);


                    //allow the next options to be visible

                    binding.thirdChoice.setVisibility(View.VISIBLE);
                    binding.thirdBtn.setVisibility(View.VISIBLE);

                    //change the option to show the user that he/she is on the next step of their vote
                    binding.question.setText("3/3");

                    //remove the previous selected option on the next list


                    if (firstChoice.equals("water")){

                        binding.waterBtn2.setVisibility(View.GONE);

                    } else if (firstChoice.equals("electricity")) {

                        binding.electricBtn2.setVisibility(View.GONE);

                    }else if (firstChoice.equals("road")) {

                        binding.potholesBtn2.setVisibility(View.GONE);

                    }
                    else {

                        binding.workBtn2.setVisibility(View.GONE);

                    }


                    if (secondChoice.equals("water")){

                        binding.waterBtn2.setVisibility(View.GONE);

                    } else if (secondChoice.equals("electricity")) {

                        binding.electricBtn2.setVisibility(View.GONE);

                    }else if (secondChoice.equals("road")) {

                        binding.potholesBtn2.setVisibility(View.GONE);

                    }
                    else {

                        binding.workBtn2.setVisibility(View.GONE);

                    }


                }
                else {

                    //reset all the background color so that the user can progress to select the next choice
                    Toast.makeText(CastVote.this, "Choose choice", Toast.LENGTH_SHORT).show();


                }

            }
        });


        //change layout if the user click on the option they want to choose

        binding.waterBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //change the background color to light blue so that the user can see that it has been selected

                binding.waterBtn2.setBackgroundColor(getResources().getColor(R.color.light_blue));

                //set the first choice to be the selected value


                thirdChoice = "water";


                //make sure that all the other buttons are set to green

                binding.electricBtn2.setBackgroundColor(getResources().getColor(R.color.my_primary));
                binding.potholesBtn2.setBackgroundColor(getResources().getColor(R.color.my_primary));
                binding.workBtn2.setBackgroundColor(getResources().getColor(R.color.my_primary));

            }
        });


        binding.electricBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //change the background color to light blue so that the user can see that it has been selected

                binding.electricBtn2.setBackgroundColor(getResources().getColor(R.color.light_blue));


                //set the first choice to be the selected value


                thirdChoice = "electricity";


                //make sure that all the other buttons are set to green

                binding.waterBtn2.setBackgroundColor(getResources().getColor(R.color.my_primary));
                binding.potholesBtn2.setBackgroundColor(getResources().getColor(R.color.my_primary));
                binding.workBtn2.setBackgroundColor(getResources().getColor(R.color.my_primary));
            }
        });

        binding.potholesBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //change the background color to light blue so that the user can see that it has been selected

                binding.potholesBtn2.setBackgroundColor(getResources().getColor(R.color.light_blue));

                //set the first choice to be the selected value


                thirdChoice = "road";





                //make sure that all the other buttons are set to green

                binding.electricBtn2.setBackgroundColor(getResources().getColor(R.color.my_primary));
                binding.waterBtn2.setBackgroundColor(getResources().getColor(R.color.my_primary));
                binding.workBtn2.setBackgroundColor(getResources().getColor(R.color.my_primary));
            }
        });

        binding.workBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //change the background color to light blue so that the user can see that it has been selected

                binding.workBtn2.setBackgroundColor(getResources().getColor(R.color.light_blue));

                //set the first choice to be the selected value


                thirdChoice = "work";





                //make sure that all the other buttons are set to green

                binding.electricBtn2.setBackgroundColor(getResources().getColor(R.color.my_primary));
                binding.potholesBtn2.setBackgroundColor(getResources().getColor(R.color.my_primary));
                binding.waterBtn2.setBackgroundColor(getResources().getColor(R.color.my_primary));
            }
        });

        //handle the submit button
        binding.thirdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!TextUtils.isEmpty(thirdChoice)) {




                    //change the option to show the user that he/she is on the next step of their vote
                    binding.question.setText("3/3");

                    //here allow the user to store all the information on the database

                    addYourVote();


                    //open the activity to vote successfully
                    startActivity(new Intent(CastVote.this, DoneVoting.class));
                    finish();



                }

                else {

                    //reset all the background color so that the user can progress to select the next choice
                    Toast.makeText(CastVote.this, "Choose choice", Toast.LENGTH_SHORT).show();


                }





            }
        });



    }

    //vote for your own choice of organization
    private void addYourVote() {

        //allow the user to vote

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("AllVoters").child(userCode);
        ref.child(userCode).setValue("voted");

        //update under user profile to say that the user has voted

        DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference().child("Users").child(firebaseAuth.getUid());
        databaseReference1.child("voted").setValue("yes");

        //now the user must add the number of total votes

        int convertedNumberOfVoters = Integer.parseInt(totalNumberOfVoters);


        //add the total and store it to the database

        int finalTotalNumberOfVoters = convertedNumberOfVoters + 1;

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("AllVoters");
        databaseReference.child("totalNumberOfVoters").setValue(finalTotalNumberOfVoters);

        //calculate number of votes for all the organizations
        organizationVotes();


        //calculate all the votes and order them accordingly
        allVotesPerIssue();


    }

    private void organizationVotes() {
        //organization votes in all calculations

        int da = Integer.parseInt(DA);
        int anc = Integer.parseInt(ANC);
        int eff = Integer.parseInt(EFF);
        int ifp = Integer.parseInt(IFP);
        int nfp = Integer.parseInt(NFP);


        if (politicalName.equals("DA")){

            int finalCount = da + 1;

            //send the information to the database
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("LeadingOrganization");
            databaseReference.child("DA").setValue(finalCount);

        } else if (politicalName.equals("ANC")) {

            int finalCount = anc + 1;

            //send the information to the database
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("LeadingOrganization");
            databaseReference.child("ANC").setValue(finalCount);
        }
        else if (politicalName.equals("EFF")) {

            int finalCount = eff + 1;

            //send the information to the database
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("LeadingOrganization");
            databaseReference.child("EFF").setValue(finalCount);
        }

        else if (politicalName.equals("IFP")) {

            int finalCount = ifp + 1;

            //send the information to the database
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("LeadingOrganization");
            databaseReference.child("IFP").setValue(finalCount);
        }
        else {
            //automatically nfp organization

            int finalCount = nfp + 1;

            //send the information to the database
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("LeadingOrganization");
            databaseReference.child("NFP").setValue(finalCount);

        }



    }

    private void allVotesPerIssue() {

        //issues in all calculations

        int electricityCount = Integer.parseInt(electricityIssues);
        int waterCount = Integer.parseInt(waterIssues);
        int workCount = Integer.parseInt(workIssues);
        int roadCount = Integer.parseInt(roadIssues);

        //issues in all calculations

        int electricityCount1 = Integer.parseInt(electricityIssues1);
        int waterCount1 = Integer.parseInt(waterIssues1);
        int workCount1 = Integer.parseInt(workIssues1);
        int roadCount1 = Integer.parseInt(roadIssues1);

        //issues in all calculations

        int electricityCount2 = Integer.parseInt(electricityIssues2);
        int waterCount2 = Integer.parseInt(waterIssues2);
        int workCount2 = Integer.parseInt(workIssues2);
        int roadCount2 = Integer.parseInt(roadIssues2);

        //issues in all calculations

        int electricityCount3 = Integer.parseInt(electricityIssues3);
        int waterCount3 = Integer.parseInt(waterIssues3);
        int workCount3 = Integer.parseInt(workIssues3);
        int roadCount3 = Integer.parseInt(roadIssues3);


        //add the total number on the realtime database

        //working with the first choice

        if (firstChoice.equals("road")){

            int finalFirstChoice  = roadCount + 1;

            //count the choices

            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Issues");
            databaseReference.child("Road").setValue(finalFirstChoice);

            //update on the first choices
            int finalFirstChoice1  = roadCount1 + 1;

            //count the choices

            DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference().child("FirstIssue");
            databaseReference1.child("Road").setValue(finalFirstChoice1);


        }
        else if(firstChoice.equals("electricity")){

            int finalFirstChoice  = electricityCount + 1;

            //count the choices

            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Issues");
            databaseReference.child("Electricity").setValue(finalFirstChoice);

            //update on the first choices
            int finalFirstChoice1  = electricityCount1 + 1;

            //count the choices

            DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference().child("FirstIssue");
            databaseReference1.child("Electricity").setValue(finalFirstChoice1);

        }

        else if(firstChoice.equals("work")){

            int finalFirstChoice  = workCount + 1;

            //count the choices

            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Issues");
            databaseReference.child("Work").setValue(finalFirstChoice);


            int finalFirstChoice1  = workCount1 + 1;

            //count the choices

            DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference().child("FirstIssue");
            databaseReference1.child("Work").setValue(finalFirstChoice1);

        }

        else if(firstChoice.equals("water")){

            int finalFirstChoice  = waterCount + 1;

            //count the choices

            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Issues");
            databaseReference.child("Water").setValue(finalFirstChoice);


            int finalFirstChoice1  = waterCount1 + 1;

            //count the choices

            DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference().child("FirstIssue");
            databaseReference1.child("Water").setValue(finalFirstChoice1);


        }

        else {
            Toast.makeText(this, "Unknown error, please restart", Toast.LENGTH_SHORT).show();
        }


        //working with the second choice

        if (secondChoice.equals("road")){

            int finalFirstChoice  = roadCount + 1;

            //count the choices

            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Issues");
            databaseReference.child("Road").setValue(finalFirstChoice);


            int finalFirstChoice1  = roadCount2 + 1;

            //count the choices

            DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference().child("SecondIssue");
            databaseReference1.child("Road").setValue(finalFirstChoice1);



        }
        else if(secondChoice.equals("electricity")){

            int finalFirstChoice  = electricityCount + 1;

            //count the choices

            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Issues");
            databaseReference.child("Electricity").setValue(finalFirstChoice);


            int finalFirstChoice1  = electricityCount2 + 1;

            //count the choices

            DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference().child("SecondIssue");
            databaseReference1.child("Electricity").setValue(finalFirstChoice1);

        }

        else if(secondChoice.equals("work")){

            int finalFirstChoice  = workCount + 1;

            //count the choices

            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Issues");
            databaseReference.child("Work").setValue(finalFirstChoice);


            int finalFirstChoice1  = workCount2 + 1;

            //count the choices

            DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference().child("SecondIssue");
            databaseReference1.child("Work").setValue(finalFirstChoice1);

        }

        else if(secondChoice.equals("water")){

            int finalFirstChoice  = waterCount + 1;

            //count the choices

            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Issues");
            databaseReference.child("Water").setValue(finalFirstChoice);


            int finalFirstChoice1  = waterCount2 + 1;

            //count the choices

            DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference().child("SecondIssue");
            databaseReference1.child("Water").setValue(finalFirstChoice1);

        }

        else {
            Toast.makeText(this, "Unknown error, please restart", Toast.LENGTH_SHORT).show();
        }



        //working with the third choice

        if (thirdChoice.equals("road")){

            int finalFirstChoice  = roadCount + 1;

            //count the choices

            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Issues");
            databaseReference.child("Road").setValue(finalFirstChoice);


            int finalFirstChoice1  = roadCount3 + 1;

            //count the choices

            DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference().child("ThirdIssue");
            databaseReference1.child("Road").setValue(finalFirstChoice1);


        }
        else if(thirdChoice.equals("electricity")){

            int finalFirstChoice  = electricityCount + 1;

            //count the choices

            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Issues");
            databaseReference.child("Electricity").setValue(finalFirstChoice);

            int finalFirstChoice1  = electricityCount3 + 1;

            //count the choices

            DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference().child("ThirdIssue");
            databaseReference1.child("Electricity").setValue(finalFirstChoice1);

        }

        else if(thirdChoice.equals("work")){

            int finalFirstChoice  = workCount + 1;

            //count the choices

            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Issues");
            databaseReference.child("Work").setValue(finalFirstChoice);

            int finalFirstChoice1  = workCount3 + 1;

            //count the choices

            DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference().child("ThirdIssue");
            databaseReference1.child("Work").setValue(finalFirstChoice1);

        }

        else if(thirdChoice.equals("water")){

            int finalFirstChoice  = waterCount + 1;

            //count the choices

            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Issues");
            databaseReference.child("Water").setValue(finalFirstChoice);

            int finalFirstChoice1  = waterCount3 + 1;

            //count the choices

            DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference().child("ThirdIssue");
            databaseReference1.child("Water").setValue(finalFirstChoice1);

        }

        else {
            Toast.makeText(this, "Unknown error, please restart", Toast.LENGTH_SHORT).show();
        }




    }

}