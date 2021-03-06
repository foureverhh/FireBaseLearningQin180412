package com.nackademin.foureverhh.firebaselearningqin180412;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button button;
    Spinner spinner;
    FirebaseDatabase database;
    DatabaseReference databaseArtists;
    ListView listViewArtists;

    List<Artist> artistList;

    public static final String ARTIST_NAME = "artistname";
    public static final String ARTIST_ID = "artistid";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);
        spinner = findViewById(R.id.spinner);

        listViewArtists = findViewById(R.id.listViewArtists);
        artistList = new ArrayList<>();

        database = FirebaseDatabase.getInstance();
        databaseArtists = database.getReference("artists");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addArtist();
            }
        });

        listViewArtists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Artist artist = artistList.get(position);
                Intent intent = new Intent(getApplicationContext(),addTrackActivity.class);
                intent.putExtra(ARTIST_ID,artist.getArtistId());
                intent.putExtra(ARTIST_NAME, artist.getArtistName());
                startActivity(intent);

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseArtists.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                artistList.clear(); //So that the list will contain new objects each time
                for(DataSnapshot artistSnapshot : dataSnapshot.getChildren()){
                    Artist artist = artistSnapshot.getValue(Artist.class);
                    artistList.add(artist);
                }
                ArtistList adapter = new ArtistList(MainActivity.this,artistList);
                listViewArtists.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void addArtist(){
        String name = editText.getText().toString().trim();
        String genre = spinner.getSelectedItem().toString();

        if(!TextUtils.isEmpty(name)){
           String id = databaseArtists.push().getKey();
           Artist artist = new Artist(id,name,genre);
           databaseArtists.child(id).setValue(artist);
           Toast.makeText(this,"Artists added",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"You should add a name",Toast.LENGTH_SHORT).show();
        }

    }
}
