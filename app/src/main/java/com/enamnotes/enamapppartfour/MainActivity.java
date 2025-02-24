package com.enamnotes.enamapppartfour;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {


    private EditText participantNameEdit;
    private Button addParticipantBtn;
    private DBHandler dbHandler;

    private Button readParticipantBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        participantNameEdit = findViewById(R.id.idParticipantName);
        addParticipantBtn= findViewById(R.id.idParticipantBtn);
        readParticipantBtn = findViewById(R.id.idBtnReadParticipant);

        dbHandler  = new DBHandler(MainActivity.this);

        addParticipantBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String participantName= participantNameEdit.getText().toString();

                // dbHandler.addNewParticipant(participantName);
                if(participantName.isEmpty()){
                    Toast.makeText(MainActivity.this,"Please enter data", Toast.LENGTH_SHORT).show();
                    return;
                }
                dbHandler.addNewParticipant(participantName);
                Toast.makeText(MainActivity.this, "Participant has been added", Toast.LENGTH_SHORT).show();

                participantNameEdit.setText("");


            }
        });

        // add on click listener for our read participants button.
        readParticipantBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // opening a new activity via an intent.
                Intent i = new Intent(MainActivity.this, ViewParticipant.class);
                startActivity(i);
            }
        });



    }
}