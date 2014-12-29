package com.TheNewBoston;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;

public class Videos extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private String video;
    static private final String DEVELOPER_KEY = "AIzaSyDAGwYcL18OpHNAmayXYTnT23_hZCUe5aY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.videos);

        YouTubePlayerView youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        youTubeView.initialize(DEVELOPER_KEY, this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            video = extras.getString("URL");
            Log.i("Video url", video);
        }
    }

    @Override
    public void onInitializationFailure(Provider provider, YouTubeInitializationResult error) {
        Toast.makeText(this, "Uh oh,  " + error.toString(), Toast.LENGTH_LONG).show();
        Log.e("URL", video);
    }

    @Override
    public void onInitializationSuccess(Provider provider, YouTubePlayer player, boolean wasRestored) {
        new init(player).execute();
    }

    private class init extends AsyncTask<Void, Void, Void>{
        private final YouTubePlayer player;

        public init(YouTubePlayer player) {
            this.player = player;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                player.setFullscreen(true);
                player.setShowFullscreenButton(false);
                player.loadVideo(video);
            } catch (IllegalStateException | NullPointerException ise) {
                ise.printStackTrace();
            }
            return null;
        }

    }
}
