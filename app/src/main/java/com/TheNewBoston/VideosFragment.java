package com.TheNewBoston;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class VideosFragment extends Fragment {
    private ProgressDialog pDialog;
    private ExpandableListView expListView;
    private ExpandableListAdapter adapter;

    private static final String PLAYLIST_PASSED = "playlistPassed";
    private String[] playlistToUse;

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
                String url = "http://gdata.youtube.com/feeds/api/playlists/" + playListId + "?v=2&alt=json"; // json url for playlist
                videoList = new ArrayList<>();
//                playlistVideos.clear();

                try {
                    ObjectMapper mapper = new ObjectMapper();
                    JsonNode rootNode = mapper.readTree(new URL(url)); // The entire playlist/tree
                    String TAG_FEED = "feed";
                    JsonNode feedNode = rootNode.get(TAG_FEED); // Get all the feed information
                    String TAG_ENTRY = "entry";
                    JsonNode entryNode = feedNode.get(TAG_ENTRY); // Get the entry

                    String TAG_TITLE = "title";
                    String TAG_CHAR_TITLE = "$t";
                    String playlistTitle = feedNode.get(TAG_TITLE).findValue(TAG_CHAR_TITLE).textValue(); // Get current playlist title
                    listDataHeader.add(playlistTitle); // Set ExpandableListView category headers

                    String TAG_SRC = "src";
                    for (int j = 0; j < entryNode.size(); j++) {
                    /* Get single video information */
                        JsonNode vid = entryNode.get(j); // positions in the entry array

                        JsonNode videoTitleNode = vid.get(TAG_TITLE); // Video title node
                        String TAG_CONTENT = "content";
                        JsonNode contentNode = vid.get(TAG_CONTENT); // Content node

                        String videoTitle = videoTitleNode.findValue(TAG_CHAR_TITLE).textValue(); // Video title
                        String videoSrc = contentNode.findValue(TAG_SRC).textValue(); // Video src/link

                        final HashMap<String, String> videos = new HashMap<>();
                        videos.put(TAG_TITLE, videoTitle); // add the video title
                        videos.put(TAG_SRC, videoSrc); // add the video url link/src

                        playlistVideos.add(videos); // add the videos to the list

                        videoList.add(videoTitle);
                        listDataChild.put(listDataHeader.get(i), videoList); // category title, videos in the category

                        adapter.notifyDataSetChanged();
                    }
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

                    String url = playlistVideos.get(position).get("src").substring(25, 36);

                    /** For debuging
                     Log.e("URL", url);
                     Log.d("groupPosition", "" + groupPosition);
                     Log.d("childPosition", "" + childPosition);
                     Log.d("position", "" + position);
                     */

                    Intent openVideo = new Intent(getActivity().getApplicationContext(), Videos.class);
                    openVideo.putExtra("URL", url);
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

// 10 yrs ago friends from hs started the company
// Vice