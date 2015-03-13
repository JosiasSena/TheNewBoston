package com.TheNewBoston.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.TheNewBoston.R;
import com.TheNewBoston.objects.YouTubeVideo;
import com.TheNewBoston.utils.ExpandableListAdapter;
import com.TheNewBoston.utils.Videos;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.TheNewBoston.utils.Constants.VIDEO_KEY;

public class VideosFragment extends Fragment {
    private ProgressDialog pDialog;
    private ExpandableListView expListView;
    private ExpandableListAdapter adapter;

    private static final String PLAYLIST_PASSED = "playlistPassed";
    private String[] playlistToUse;

    private static final String TAG_DATA = "data";
    private static final String TAG_ITEMS = "items";
    private static final String TAG_TITLE = "title";
    // private static final String TAG_CHAR_TITLE = "$t";
    private static final String TAG_SRC = "src";
    private static final String TAG_CONTENT = "content";
    private static final String TAG_ID = "id";
    private static final String TAG_POSITION_IN_PL = "position";
    // private static final String TAG_PLAYLIST_TOTAL = "openSearch$totalResults";
    private static final String TAG_VIDEO = "video";
    private static final String RETURN_TYPE = "jsonc";

    public static VideosFragment newInstance(String[] playlistToUse) {
        VideosFragment fragment = new VideosFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArray(PLAYLIST_PASSED, playlistToUse); // key, value
        fragment.setArguments(bundle);
        return fragment;
    }

    public VideosFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            playlistToUse = getArguments().getStringArray(PLAYLIST_PASSED);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.videos_list_view, container, false);

        expListView = (ExpandableListView) view.findViewById(R.id.lvExp);
        new runIt().execute();

        return view;
    }

    private class runIt extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // Showing progress dialog
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Getting videos, please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            final ArrayList<HashMap<String, String>> playlistVideos = new ArrayList<>();
            List<String> listDataHeader = new ArrayList<>();
            HashMap<String, List<String>> listDataChild = new HashMap<>();
            List<String> videoList; // arraylist to hold the video titles

            adapter = new ExpandableListAdapter(
                    getActivity().getApplicationContext(),
                    listDataHeader, // header for each category
                    listDataChild); // title for items in the categories

            for (int i = 0; i < playlistToUse.length; i++) {
                String playListId = playlistToUse[i];
                videoList = new ArrayList<>();

                try {
                    int total;
                    int START_INDEX = 1;
                    int cccc = 0;

                    do {
                        String url = "http://gdata.youtube.com/feeds/api/playlists/" + playListId +  // json url for playlist
                                "?v=2&alt=" + RETURN_TYPE +
                                "&start-index=" + START_INDEX +
                                "&max-results=10";

                        ObjectMapper mapper = new ObjectMapper();
                        JsonNode rootNode = mapper.readTree(new URL(url)); // The entire playlist/tree
                        JsonNode dataNode = rootNode.get(TAG_DATA); // Get all the data
                        JsonNode itemsArrayNode = dataNode.get(TAG_ITEMS); // Get the items array

                        if (cccc == 0) {
                            String playlistTitle = dataNode.get(TAG_TITLE).textValue(); // Get current playlist title
                            listDataHeader.add(playlistTitle); // Set ExpandableListView category headers
                            cccc = 1;
                        }

                        total = Integer.parseInt(dataNode.get("totalItems").toString());
                        // Log.d("Total", "" + total);

                        if (itemsArrayNode != null) {
                            int size = itemsArrayNode.size();

                            for (int j = 0; j < size; j++) {

                                /* Get single video information */
                                JsonNode videoItem = itemsArrayNode.get(j); // positions in the entry array
                                JsonNode videoNode = videoItem.get(TAG_VIDEO);
                                JsonNode contentNode = videoNode.get(TAG_CONTENT); // Content node

                                String videoTitle = videoNode.findValue(TAG_TITLE).textValue(); // Video title
                                String videoSrc = contentNode.findValue("5") + ""; // Video src/link
                                String vidPlayListId = dataNode.findValue(TAG_ID).textValue(); // Get the playlist ID
                                int positionInPL = videoItem.findValue(TAG_POSITION_IN_PL).intValue(); // Get the position in playlist

                                final HashMap<String, String> videos = new HashMap<>();
                                videos.put(TAG_TITLE, videoTitle); // add the video title
                                videos.put(TAG_SRC, videoSrc); // add the video url link/src
                                videos.put(TAG_ID, vidPlayListId); // the ID of the playlist the vid is in
                                videos.put(TAG_POSITION_IN_PL, "" + positionInPL); // video position in playlist

                                playlistVideos.add(videos); // add the videos to the list

                                videoList.add(videoTitle);
                                listDataChild.put(listDataHeader.get(i), videoList); // category title, videos in the category

                                adapter.notifyDataSetChanged();
                            }
                        }
                        START_INDEX += 10;
                    } while (START_INDEX < total);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    expListView.setAdapter(adapter);
                }
            });

            expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                @Override
                public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                    int position = 0;
                    for (int i = 0; i <= groupPosition; i++) {
                        if (groupPosition == 0) {
                            position = childPosition;
                        } else if (groupPosition == 1) {
                            position = adapter.getChildrenCount(0) + childPosition;
                        } else if (groupPosition > 1) {
                            position = (position + adapter.getChildrenCount(i));
                        }
                    }

                    if (groupPosition > 1) {
                        position -= adapter.getChildrenCount(groupPosition);
                        position += childPosition;
                    }

                    HashMap<String, String> video = playlistVideos.get(position);

                    String url = video.get(TAG_SRC).substring(25, 36);
                    String vidPlayListId = video.get(TAG_ID);
//                    String positionInPL = video.get(TAG_POSITION_IN_PL);

                    /** For debuging */
//                     Log.d("URL", url);
//                     Log.d("groupPosition", "" + groupPosition);
//                     Log.d("childPosition", "" + childPosition);
//                     Log.d("position", "" + position);
//                     Log.d("vidPlayListId", "" + vidPlayListId);

                    Intent openVideo = new Intent(getActivity().getApplicationContext(), Videos.class);
                    YouTubeVideo youTubeVideo = new YouTubeVideo();
                    youTubeVideo.setVideoURL(url);
                    youTubeVideo.setVidPlayListId(vidPlayListId);
                    youTubeVideo.setPositionInPL(position);

                    Bundle mBundle = new Bundle();
                    mBundle.putParcelable(VIDEO_KEY, youTubeVideo);

                    openVideo.putExtras(mBundle);
                    startActivity(openVideo);
                    return true;
                }
            });

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            // Dismiss the progress dialog
            if (pDialog.isShowing()) {
                pDialog.dismiss();
            }
        }
    }
}