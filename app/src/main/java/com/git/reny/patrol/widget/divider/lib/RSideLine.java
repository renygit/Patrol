package com.git.reny.patrol.widget.divider.lib;

import android.support.annotation.ColorInt;

/**
 * Created by mac on 2017/5/17.
 */

public class RSideLine {

    public boolean isHave = false;
    /**
     * A single color value in the form 0xAARRGGBB.
     **/
    public int color;
    /**
     * 单位px
     */
    public float widthPx;
    /**
     * startPaddingPx,分割线起始的padding，水平方向左为start，垂直方向上为start
     * endPaddingPx,分割线尾部的padding，水平方向右为end，垂直方向下为end
     */
    public float startPaddingPx;
    public float endPaddingPx;

    public RSideLine(boolean isHave, @ColorInt int color, float widthPx, float startPaddingPx, float endPaddingPx) {
        this.isHave = isHave;
        this.color = color;
        this.widthPx = widthPx;
        this.startPaddingPx = startPaddingPx;
        this.endPaddingPx = endPaddingPx;
    }

    public float getStartPaddingPx() {
        return startPaddingPx;
    }

    public void setStartPaddingPx(float startPaddingPx) {
        this.startPaddingPx = startPaddingPx;
    }

    public float getEndPaddingPx() {
        return endPaddingPx;
    }

    public void setEndPaddingPx(float endPaddingDp) {
        this.endPaddingPx = endPaddingDp;
    }

    public boolean isHave() {
        return isHave;
    }

    public void setHave(boolean have) {
        isHave = have;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public float getWidthPx() {
        return widthPx;
    }

    public void setWidthPx(float widthPx) {
        this.widthPx = widthPx;
    }
}
