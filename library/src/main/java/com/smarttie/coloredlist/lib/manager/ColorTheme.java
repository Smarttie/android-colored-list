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

import com.smarttie.coloredlist.lib.R;
import com.smarttie.coloredlist.lib.utils.ColorUtils;

/**
 * ColorTheme defines a set of predefined colors based on Google Material Design colors.
 *
 * @see <a href="https://www.google.com/design/spec/style/color.html</a>
 */
public enum ColorTheme
{
    RED(R.color.gmd_color_red),
    PINK(R.color.gmd_color_pink),
    FUCHSIA(R.color.gmd_color_fuchsia),
    PURPLE(R.color.gmd_color_purple),
    DEEP_PURPLE(R.color.gmd_color_deep_purple),
    INDIGO(R.color.gmd_color_indigo),
    BLUE(R.color.gmd_color_blue),
    LIGHT_BLUE(R.color.gmd_color_light_blue),
    CYAN(R.color.gmd_color_cyan),
    TEAL(R.color.gmd_color_teal),
    GREEN(R.color.gmd_color_green),
    LIGHT_GREEN(R.color.gmd_color_light_green),
    LIME(R.color.gmd_color_lime),
    YELLOW(R.color.gmd_color_yellow),
    AMBER(R.color.gmd_color_amber),
    ORANGE(R.color.gmd_color_orange),
    DEEP_ORANGE(R.color.gmd_color_deep_orange),
    BROWN(R.color.gmd_color_brown),
    GREY(R.color.gmd_color_grey),
    BLUE_GREY(R.color.gmd_color_blue_grey),
    BLACK(R.color.gmd_color_black),
    WHITE(R.color.gmd_color_white);

    private int mColor;

    /**
     * Returns the resource identifier for the color
     *
     * @return the resource identifier for the color
     */
    public int getColorResId()
    {
        return mColor;
    }

    /**
     * Returns the integer representation of the ARGB color
     *
     * @param context the context to retrieve resources
     * @return the integer representation of the ARGB color
     */
    public int getColor(Context context)
    {
        return context.getResources().getColor(mColor);
    }

    /**
     * Returns the hexadecimal representation of the ARGB color
     *
     * @param context the context to retrieve resources
     * @return the hexadecimal representation of the ARGB color
     */
    public String getHexColor(Context context)
    {
        return ColorUtils.colorToHex(getColor(context));
    }

    ColorTheme(int color)
    {
        mColor = color;
    }

    /**
     * Returns the {@link ColorTheme} for the specified color resource identifier
     *
     * @param colorResId the color resource identifier
     * @return the {@link ColorTheme} for the specified color resource identifier or null if
     * no ColorTheme is found to match the specified color
     */
    public static ColorTheme fromColor(int colorResId)
    {
        for (ColorTheme theme : ColorTheme.values())
        {
            if (theme.getColorResId() == colorResId) return theme;
        }

        return null;
    }
}