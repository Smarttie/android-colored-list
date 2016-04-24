package com.smarttie.coloredlist.sample;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.smarttie.coloredlist.lib.manager.ColorTheme;

public class MainActivity extends AppCompatActivity
{

    private RecyclerView mRecyclerView;
    private SimpleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String[] mItems;
    private int mCurrentThemePosition = 0;

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new SimpleAdapter(this, ColorTheme.RED);

        mRecyclerView = (RecyclerView) findViewById(R.id.list_recycle_view);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mItems = getResources().getStringArray(R.array.item_list);
        mHandler.post(mUpdateListRunnable);
    }

    private final Runnable mUpdateListRunnable = new Runnable()
    {
        @Override
        public void run()
        {
            int position = mAdapter.getItemCount();
            if (position < mItems.length)
            {
                mAdapter.add(mItems[position]);
                mHandler.postDelayed(mUpdateListRunnable, 600);
            }
            else if ((mCurrentThemePosition + 1) < ColorTheme.values().length)
            {
                mCurrentThemePosition++;
                mAdapter.setColorTheme(ColorTheme.values()[mCurrentThemePosition]);
                mHandler.postDelayed(mUpdateListRunnable, 3000);
            }
        }
    };
}