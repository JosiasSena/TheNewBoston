package com.TheNewBoston.utils;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.TheNewBoston.R;
import com.TheNewBoston.objects.YouTubeVideo;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;

import static com.TheNewBoston.utils.Constants.*;

public class Videos extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    static private final String DEVELOPER_KEY = "AIzaSyDAGwYcL18OpHNAmayXYTnT23_hZCUe5aY";
    private YouTubeVideo youTubeVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.videos);

        YouTubePlayerView youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        youTubeView.initialize(DEVELOPER_KEY, this);

        youTubeVideo = getIntent().getParcelableExtra(VIDEO_KEY);
    }

    @Override
    public void onInitializationFailure(Provider provider, YouTubeInitializationResult error) {
        Toast.makeText(this, getString(R.string.uhoh) + error.toString(), Toast.LENGTH_LONG).show();
        Log.e("Video URL", youTubeVideo.getVideoURL());
        Log.e("Video playlistId", youTubeVideo.getVidPlayListId());
        Log.e("Video positionInPL", "" + youTubeVideo.getPositionInPL());
    }

    @Override
    public void onInitializationSuccess(Provider provider, YouTubePlayer player, boolean wasRestored) {
        new PlayVideo(player).execute();
    }

    private class PlayVideo extends AsyncTask<Void, Void, Void>{
        private final YouTubePlayer player;

        public PlayVideo(YouTubePlayer player) {
            this.player = player;
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                player.setFullscreen(true);
                player.setShowFullscreenButton(false);
//                player.loadVideo(youTubeVideo.getVideoURL());
                // playlist id, position in playlist, where to start playing from (in ms)
                player.loadPlaylist(youTubeVideo.getVidPlayListId(), youTubeVideo.getPositionInPL(), 0);
            } catch (IllegalStateException | NullPointerException ise) {
                ise.printStackTrace();
            }
            return null;
        }
    }
}
