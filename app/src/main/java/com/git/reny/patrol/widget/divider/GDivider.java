package com.git.reny.patrol.widget.divider;


import com.git.reny.patrol.R;
import com.git.reny.patrol.utils.ResUtils;
import com.git.reny.patrol.widget.divider.lib.RDivider;
import com.git.reny.patrol.widget.divider.lib.RDividerBuilder;
import com.git.reny.patrol.widget.divider.lib.RDividerItemDecoration;

/**
 * Created by admin on 2017/6/30.
 * GridLayoutManager类型
 */

public class GDivider extends RDividerItemDecoration {

    private int spanCount = 3;//列
    private int listSize = 6;//列表数
    private int color = ResUtils.getColor(R.color.line);
    private float lineWidth = ResUtils.getDimenPx(R.dimen.line);

    public GDivider(int spanCount, int listSize){
        super();
        this.spanCount = spanCount;
        this.listSize = listSize;
    }

    @Override
    public RDivider getDivider(int itemPosition) {
        RDivider divider;

        int rowCount = listSize / spanCount;//总行数
        if(listSize % spanCount > 0){
            rowCount += 1;
        }


        int curRow = (itemPosition + 1) / spanCount;//当前行数
        if((itemPosition + 1) % spanCount > 0){
            curRow += 1;
        }

        if (itemPosition % spanCount < spanCount - 1) {//每一排最后一个的前面item 非最后一个
            divider = new RDividerBuilder()
                    .setRightSideLine(true, color, lineWidth, 0, 0)
                    .setBottomSideLine(curRow < rowCount, color, lineWidth, 0, 0)//不是最后一行 需要画下边线
                    .create();
        }else {//每一排最后一个
            divider = new RDividerBuilder()
                    .setBottomSideLine(curRow < rowCount, color, lineWidth, 0, 0)
                    .create();
        }
        return divider;
    }
}
