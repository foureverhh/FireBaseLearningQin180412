package com.nackademin.foureverhh.firebaselearningqin180412;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ArtistList extends ArrayAdapter<Artist> {

    private Activity context;

    private List<Artist> artistList;

    public ArtistList(Activity context, List<Artist> artistList){
        super(context,R.layout.list_layout,artistList);
        this.context = context;
        this.artistList = artistList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_layout,null,true);
        TextView textName = listViewItem.findViewById(R.id.textName);
        TextView textGenre = listViewItem.findViewById(R.id.textGenre);

        Artist artist = artistList.get(position);
        textName.setText(artist.getArtistName());
        textGenre.setText(artist.getArtistGenre());
        return listViewItem;
    }
}
