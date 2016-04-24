package com.smarttie.coloredlist.sample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smarttie.coloredlist.lib.manager.ColorTheme;
import com.smarttie.coloredlist.lib.widget.ColoredListAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class SimpleAdapter extends ColoredListAdapter<SimpleAdapter.ViewHolder>
{
    private static final String TAG = "SimpleAdapter";

    private ArrayList<String> mDataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView mTitleTextView;

        public ViewHolder(View v)
        {
            super(v);
            mTitleTextView = (TextView) v.findViewById(R.id.title_text_view);
        }
    }

    public SimpleAdapter(Context contexts, ColorTheme colorTheme)
    {
        super(contexts, colorTheme);
        mDataSet = new ArrayList<>();
    }

    @Override
    public int getItemCount()
    {
        return mDataSet.size();
    }

    public void add(String item)
    {
        mDataSet.add(item);
        notifyDataSetChanged();
    }

    public void addAll(String[] items)
    {
        mDataSet.addAll(Arrays.asList(items));
        notifyDataSetChanged();
    }

    @Override
    public SimpleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType)
    {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SimpleAdapter.ViewHolder holder, int position)
    {
        Log.i(TAG, "onBindViewHolder: " + position);
        holder.mTitleTextView.setText(mDataSet.get(position));
        holder.itemView.setBackgroundColor(getColor(position));
    }
}