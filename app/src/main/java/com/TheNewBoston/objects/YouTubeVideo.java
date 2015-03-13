package com.TheNewBoston.objects;

import android.os.Parcel;
import android.os.Parcelable;

public class YouTubeVideo implements Parcelable {
    private String videoURL;
    private String vidPlayListId;
    private int positionInPL;

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public String getVidPlayListId() {
        return vidPlayListId;
    }

    public void setVidPlayListId(String vidPlayListId) {
        this.vidPlayListId = vidPlayListId;
    }

    public int getPositionInPL() {
        return positionInPL;
    }

    public void setPositionInPL(int positionInPL) {
        this.positionInPL = positionInPL;
    }

    public static final Parcelable.Creator<YouTubeVideo> CREATOR = new Creator<YouTubeVideo>() {
        public YouTubeVideo createFromParcel(Parcel source) {
            YouTubeVideo youTubeVideo = new YouTubeVideo();
            youTubeVideo.videoURL = source.readString();
            youTubeVideo.vidPlayListId = source.readString();
            youTubeVideo.positionInPL = source.readInt();
            return youTubeVideo;
        }

        @Override
        public YouTubeVideo[] newArray(int size) {
            return new YouTubeVideo[0];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(videoURL);
        parcel.writeString(vidPlayListId);
        parcel.writeInt(positionInPL);
    }
}
