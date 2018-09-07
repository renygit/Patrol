package com.git.reny.wallpaper.widget.divider.lib;

/**
 * Created by mac on 2017/5/17.
 */

public class RDivider {

    public RSideLine leftSideLine;
    public RSideLine topSideLine;
    public RSideLine rightSideLine;
    public RSideLine bottomSideLine;


    public RDivider(RSideLine leftSideLine, RSideLine topSideLine, RSideLine rightSideLine, RSideLine bottomSideLine) {
        this.leftSideLine = leftSideLine;
        this.topSideLine = topSideLine;
        this.rightSideLine = rightSideLine;
        this.bottomSideLine = bottomSideLine;
    }

    public RSideLine getLeftSideLine() {
        return leftSideLine;
    }

    public void setLeftSideLine(RSideLine leftSideLine) {
        this.leftSideLine = leftSideLine;
    }

    public RSideLine getTopSideLine() {
        return topSideLine;
    }

    public void setTopSideLine(RSideLine topSideLine) {
        this.topSideLine = topSideLine;
    }

    public RSideLine getRightSideLine() {
        return rightSideLine;
    }

    public void setRightSideLine(RSideLine rightSideLine) {
        this.rightSideLine = rightSideLine;
    }

    public RSideLine getBottomSideLine() {
        return bottomSideLine;
    }

    public void setBottomSideLine(RSideLine bottomSideLine) {
        this.bottomSideLine = bottomSideLine;
    }
}



