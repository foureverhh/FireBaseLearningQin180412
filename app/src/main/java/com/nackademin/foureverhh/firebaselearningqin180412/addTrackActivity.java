package com.nackademin.foureverhh.firebaselearningqin180412;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addTrackActivity extends AppCompatActivity {

    TextView textViewArtistName;
    EditText editTextTrackName;
    SeekBar seekBarRating;
    ListView listViewTracks;
    DatabaseReference databaseTracks;

    Button buttonAddTrack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_track);

        textViewArtistName = findViewById(R.id.textViewArtistName);
        editTextTrackName = findViewById(R.id.editTextTrackName);
        seekBarRating = findViewById(R.id.seekBarRating);
        listViewTracks = findViewById(R.id.listViewTracks);
        buttonAddTrack = findViewById(R.id.buttonAddTrack);

        Intent intent = getIntent();
        String id = intent.getStringExtra(MainActivity.ARTIST_ID);
        String name = intent.getStringExtra(MainActivity.ARTIST_NAME);

        textViewArtistName.setText(name);

        databaseTracks = FirebaseDatabase.getInstance().getReference("tracks").child(id);

        buttonAddTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTrack();
            }
        });

    }

    public void saveTrack(){
       int trackRating = seekBarRating.getProgress();
       String trackName = editTextTrackName.getText().toString().trim();
       if(!TextUtils.isEmpty(trackName)){
           String trackID = databaseTracks.push().getKey();
           TrackInfo trackInfo = new TrackInfo(trackID,trackName,trackRating);
           databaseTracks.child(trackID).setValue(trackInfo);
           Toast.makeText(this,"Track saved ok",Toast.LENGTH_SHORT).show();
       }else{
           Toast.makeText(this,"Track name should not empty",Toast.LENGTH_SHORT).show();
       }

    }
}
