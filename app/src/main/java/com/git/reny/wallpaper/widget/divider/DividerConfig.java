package com.git.reny.wallpaper.widget.divider;

import android.support.annotation.ColorInt;

import com.git.reny.wallpaper.R;
import com.git.reny.wallpaper.utils.ResUtils;

/**
 * Created by admin on 2017/6/30.
 */

public class DividerConfig {

    private int color;
    private float lineWidth;
    private float startPadding;
    private float endPadding;
    private float bothPadding;

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public float getLineWidth() {
        return lineWidth;
    }

    public void setLineWidth(float lineWidth) {
        this.lineWidth = lineWidth;
    }

    public float getStartPadding() {
        return startPadding;
    }

    public void setStartPadding(float startPadding) {
        this.startPadding = startPadding;
    }

    public float getEndPadding() {
        return endPadding;
    }

    public void setEndPadding(float endPadding) {
        this.endPadding = endPadding;
    }

    public float getBothPadding() {
        return bothPadding;
    }

    public void setBothPadding(float bothPadding) {
        this.bothPadding = bothPadding;
    }

    private DividerConfig(){}

    public DividerConfig(Build build) {
        this.color = build.color;
        this.lineWidth = build.lineWidth;
        this.startPadding = build.startPadding;
        this.endPadding = build.endPadding;
        this.bothPadding = build.bothPadding;
    }

    public static class Build{
        private int color = ResUtils.getColor(R.color.line);
        private float lineWidth = ResUtils.getDimenPx(R.dimen.line);
        private float startPadding = 0;
        private float endPadding = 0;
        private float bothPadding = 0;

        public Build setColor(@ColorInt int color) {
            this.color = color;
            return this;
        }

        public Build setLineWidth(float lineWidth) {
            this.lineWidth = lineWidth;
            return this;
        }

        public Build setStartPadding(float startPadding) {
            this.startPadding = startPadding;
            return this;
        }

        public Build setEndPadding(float endPadding) {
            this.endPadding = endPadding;
            return this;
        }

        public Build setBothPadding(float bothPadding) {
            this.bothPadding = bothPadding;
            return this;
        }

        public DividerConfig build(){
            return new DividerConfig(this);
        }

    }
}
