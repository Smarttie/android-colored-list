/**
 * The MIT License (MIT)
 * <p/>
 * Copyright (c) 2016 Smarttie Software, Inc.
 * <p/>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p/>
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * <p/>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.smarttie.coloredlist.lib.manager;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.StateListDrawable;

import com.smarttie.coloredlist.lib.utils.ColorUtils;

import java.util.ArrayList;


/**
 * ColoredListManager is a manager used to calculate a list of colors departing of
 * the same base color. Each color in the list has a small degradation that differentiates
 * it from the previous and next color. When used to color a list of views, it generates
 * a nice looking effect on the list.
 * <p/>
 * This manager is a good choice to be used along with {@code RecycleView}, {@code ListView}
 * or any similar visual component.
 */
public class ColoredListManager
{
    /**
     * if the list count is less or equals to this number, the colors will be
     * generated based on a pre-populated color list. If the length grows, the
     * colors will start to be generated dynamically
     */
    private static final int MAX_STATIC_LENGTH = 15;

    private static final float DEFAULT_MIN_HSV_VALUE = 0.2f;
    private static final float DEFAULT_MAX_HSV_VALUE = 0f;

    private Context mContext;
    private ColorTheme mTheme;

    private float mMinHSVValue = DEFAULT_MIN_HSV_VALUE;
    private float mMaxHSVValue = DEFAULT_MAX_HSV_VALUE;
    private float mTotalHsvValue;
    private float mIncrementsHSVValue;

    private int mListLength = 0;
    private final ArrayList<Integer> mStaticColorList = new ArrayList<>(MAX_STATIC_LENGTH);


    /**
     * Returns the minimum HSV value to be used to calculate the bottom limit color
     *
     * @return the minimum HSV value
     */
    public float getMinHSVValue()
    {
        return mMinHSVValue;
    }

    /**
     * Specifies the minimum HSV value to be used to calculate the bottom limit color
     *
     * @param mMinHSVValue the minimum HSV value
     */
    public void setMinHSVValue(float mMinHSVValue)
    {
        this.mMinHSVValue = mMinHSVValue;
        updateManagerValues();
    }

    /**
     * Returns the maximum HSV value to be used to calculate the top limit color
     *
     * @return the maximum HSV value
     */
    public float getMaxHSVValue()
    {
        return mMaxHSVValue;
    }

    /**
     * Specifies the maximum HSV value to be used to calculate the top limit color
     *
     * @param mMaxHSVValue the maximum HSV value
     */
    public void setMaxHSVValue(float mMaxHSVValue)
    {
        this.mMaxHSVValue = mMaxHSVValue;
        updateManagerValues();
    }

    /**
     * Specifies the base color theme used to color a list
     *
     * @param theme the base color theme
     */
    public void setColorTheme(ColorTheme theme)
    {
        mTheme = theme;
        updateManagerValues();
    }


    /**
     * Constructs a new instance of {@code ColoredListManager} with the specified
     * color theme.
     *
     * @param context the context the list is running in, through which it can access
     *                resources, etc.
     * @param theme   the color theme used to color the list
     */
    public ColoredListManager(Context context, ColorTheme theme)
    {
        mContext = context;
        mTheme = theme;

        updateBaseValues();
    }

    /**
     * Constructs a new instance of {@code ColoredListManager} with the specified
     * color theme.
     *
     * @param context    the context the list is running in, through which it can access
     *                   resources, etc.
     * @param theme      the color theme used to color the list
     * @param listLength the length of the list to be colored
     */
    public ColoredListManager(Context context, ColorTheme theme, int listLength)
    {
        this(context, theme);
        updateListLength(listLength);
    }

    private void updateBaseValues()
    {
        float[] hsv = ColorUtils.colorToHSV(mTheme.getColor(mContext));
        mMaxHSVValue = hsv[2];
        mTotalHsvValue = mMaxHSVValue - mMinHSVValue;
    }

    /**
     * Updates the length of the list that is being colored with this manager.
     * This is needed to enable the manager to calculate the different degradation levels
     * to be used on the colored list.
     * <p/>
     * Ensure to call this method every time the colored list changes of size.
     *
     * @param listLength the length of the list to be colored
     */
    public void updateListLength(int listLength)
    {
        mListLength = listLength;

        //updating hsv increments
        if (mListLength == 0)
        {
            mIncrementsHSVValue = 0;
        }
        else
        {
            mIncrementsHSVValue = mTotalHsvValue / mListLength;
        }
        generateStaticColorList();
    }

    private void updateManagerValues()
    {
        updateBaseValues();
        updateListLength(mListLength);
    }

    private void generateStaticColorList()
    {
        float staticIncrementsHSVValue = mTotalHsvValue / MAX_STATIC_LENGTH;

        mStaticColorList.clear();
        for (int a = 0; a < MAX_STATIC_LENGTH; a++)
        {
            mStaticColorList.add(getColor(staticIncrementsHSVValue, a));
        }
    }

    /**
     * Returns the base theme color used to color the list
     *
     * @return the base theme color
     */
    public int getBaseColor()
    {
        return mTheme.getColor(mContext);
    }

    /**
     * Returns a selector with the color for the specified position based on the color
     * degradation
     *
     * @param position the position of the view in the list to be colored
     * @return a selector with the color for the specified row
     */
    public StateListDrawable getColorSelector(int position)
    {
        int rowColor = getColor(position);
        int baseColor = mTheme.getColor(mContext);

        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{android.R.attr.state_pressed},
                new ColorDrawable(baseColor));
        stateListDrawable.addState(new int[]{android.R.attr.state_focused},
                new ColorDrawable(baseColor));
        stateListDrawable.addState(new int[0],
                new ColorDrawable(rowColor));

        return stateListDrawable;
    }

    /**
     * Returns the color for the specified position based on the color degradation
     *
     * @param position the position of the view in the list to be colored
     * @return the color for the specified position
     */
    public int getColor(int position)
    {
        if (mListLength <= MAX_STATIC_LENGTH)
        {
            return mStaticColorList.get(position);
        }

        return getColor(mIncrementsHSVValue, position);
    }

    private int getColor(float incrementsHSVValue, int rowPosition)
    {
        float[] hsv = ColorUtils.colorToHSV(mTheme.getColor(mContext));
        hsv[2] = mMaxHSVValue - (incrementsHSVValue * rowPosition);
        return Color.HSVToColor(hsv);
    }
}