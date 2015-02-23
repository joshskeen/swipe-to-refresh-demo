package com.joshskeen.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CatNamesRecyclerViewAdapter extends RecyclerView.Adapter<CatNamesRecyclerViewAdapter.CatNamesViewHolder> {

    private Context mContext;
    List<String> mCatNames;

    public CatNamesRecyclerViewAdapter(Context context) {
        mContext = context;
        randomizeCatNames();
    }

    public void randomizeCatNames() {
        mCatNames = Arrays.asList(getCatNamesResource());
        Collections.shuffle(mCatNames);
    }

    public class CatNamesViewHolder extends RecyclerView.ViewHolder {
        TextView mCatNameTextView;

        public CatNamesViewHolder(View itemView) {
            super(itemView);
            mCatNameTextView = (TextView) itemView.findViewById(R.id.cat_name_textview);
        }
    }

    private String[] getCatNamesResource() {
        return mContext.getResources().getStringArray(R.array.cat_names);
    }

    @Override
    public CatNamesViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflatedView = LayoutInflater.from(mContext).inflate(R.layout.cat_name_view, viewGroup, false);
        return new CatNamesViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(CatNamesViewHolder viewHolder, int i) {
        String catName = getItem(i);
        viewHolder.mCatNameTextView.setText(catName);
    }

    public String getItem(int position) {
        return mCatNames.get(position);
    }

    @Override
    public int getItemCount() {
        return mCatNames.size();
    }


}
