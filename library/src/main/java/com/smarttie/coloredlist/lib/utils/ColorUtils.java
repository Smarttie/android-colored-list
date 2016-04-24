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

package com.smarttie.coloredlist.lib.utils;


import android.graphics.Color;

/**
 * Utility class that holds methods related to color processing
 */
public class ColorUtils
{
    /**
     * Convert an ARGB color to its hexadecimal representation
     *
     * @param color an ARGB color
     * @return the resulting hexadecimal representation for the received color
     */
    public static String colorToHex(int color)
    {
        return String.format("#%06X", (0xFFFFFF & color));
    }

    /**
     * Convert an ARGB color to HSV components
     *
     * @param color an ARGB color
     * @return the HSV components for the specified color
     */
    public static float[] colorToHSV(int color)
    {
        int r = Color.red(color);
        int g = Color.green(color);
        int b = Color.blue(color);

        float[] hsv = new float[3];
        Color.RGBToHSV(r, g, b, hsv);
        return hsv;
    }
}