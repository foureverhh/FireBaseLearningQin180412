package com.nackademin.foureverhh.firebaselearningqin180412;

public class TrackInfo {

    private String trackID;
    private String trackName;
    private int trackRating;

    public TrackInfo(){

    }

    public TrackInfo(String trackID, String trackName, int trackRating){
        this.trackID = trackID;
        this.trackName = trackName;
        this.trackRating = trackRating;
    }

    public String getTrackID() {
        return trackID;
    }

    public String getTrackName() {
        return trackName;
    }

    public int getTrackRating() {
        return trackRating;
    }
}
