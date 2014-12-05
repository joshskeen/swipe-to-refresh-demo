package com.joshskeen.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class MainActivity extends Activity {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ListView mListView;
    private ArrayAdapter<String> mAdapter;
    private List<String> mFakeTweets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.activity_main_swipe_refresh_layout);
        mSwipeRefreshLayout.setColorSchemeColors();
        mListView = (ListView) findViewById(R.id.activity_main_listview);
        mFakeTweets = Arrays.asList(getResources().getStringArray(R.array.fake_tweets));

        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mFakeTweets);
        mListView.setAdapter(mAdapter);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.orange, R.color.green, R.color.blue);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, getNewTweets());
                        mListView.setAdapter(mAdapter);
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 2500);
            }
        });
    }

    private List<String> getNewTweets() {
        List<String> newTweets = new ArrayList<String>();
        for (int i = 0; i < mFakeTweets.size(); i++) {
            int randomTweetIndex = new Random().nextInt(mFakeTweets.size() - 1);
            newTweets.add(mFakeTweets.get(randomTweetIndex));
        }
        return newTweets;
    }

}
