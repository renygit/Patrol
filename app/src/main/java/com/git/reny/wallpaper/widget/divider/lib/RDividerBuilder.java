package com.git.reny.wallpaper.widget.divider.lib;

import android.support.annotation.ColorInt;

/**
 * Created by mac on 2017/5/17.
 */

public class RDividerBuilder {

    private RSideLine leftSideLine;
    private RSideLine topSideLine;
    private RSideLine rightSideLine;
    private RSideLine bottomSideLine;


    public RDividerBuilder setLeftSideLine(boolean isHave, @ColorInt int color, float widthPx, float startPaddingPx, float endPaddingPx) {
        this.leftSideLine = new RSideLine(isHave, color, widthPx, startPaddingPx, endPaddingPx);
        return this;
    }

    public RDividerBuilder setTopSideLine(boolean isHave, @ColorInt int color, float widthPx, float startPaddingPx, float endPaddingPx) {
        this.topSideLine = new RSideLine(isHave, color, widthPx, startPaddingPx, endPaddingPx);
        return this;
    }

    public RDividerBuilder setRightSideLine(boolean isHave, @ColorInt int color, float widthPx, float startPaddingPx, float endPaddingPx) {
        this.rightSideLine = new RSideLine(isHave, color, widthPx, startPaddingPx, endPaddingPx);
        return this;
    }

    public RDividerBuilder setBottomSideLine(boolean isHave, @ColorInt int color, float widthPx, float startPaddingPx, float endPaddingPx) {
        this.bottomSideLine = new RSideLine(isHave, color, widthPx, startPaddingPx, endPaddingPx);
        return this;
    }

    public RDivider create() {
        //提供一个默认不显示的sideline，防止空指针
        RSideLine defaultSideLine = new RSideLine(false, 0xffe6e6e6, 0, 0, 0);

        leftSideLine = (leftSideLine != null ? leftSideLine : defaultSideLine);
        topSideLine = (topSideLine != null ? topSideLine : defaultSideLine);
        rightSideLine = (rightSideLine != null ? rightSideLine : defaultSideLine);
        bottomSideLine = (bottomSideLine != null ? bottomSideLine : defaultSideLine);

        return new RDivider(leftSideLine, topSideLine, rightSideLine, bottomSideLine);
    }


}



