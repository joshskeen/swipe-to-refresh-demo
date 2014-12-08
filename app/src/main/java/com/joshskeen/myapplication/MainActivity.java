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
    private List<String> mCatNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.activity_main_swipe_refresh_layout);
        mListView = (ListView) findViewById(R.id.activity_main_listview);
        mCatNames = Arrays.asList(getResources().getStringArray(R.array.cat_names));
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mCatNames);
        mListView.setAdapter(mAdapter);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.orange, R.color.green, R.color.blue);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, getNewCatNames());
                        mListView.setAdapter(mAdapter);
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 2500);
            }
        });
    }

    private List<String> getNewCatNames() {
        List<String> newCatNames = new ArrayList<String>();
        for (int i = 0; i < mCatNames.size(); i++) {
            int randomCatNameIndex = new Random().nextInt(mCatNames.size() - 1);
            newCatNames.add(mCatNames.get(randomCatNameIndex));
        }
        return newCatNames;
    }

}
