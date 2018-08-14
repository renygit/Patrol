package com.git.reny.patrol.widget.divider;


import com.git.reny.patrol.R;
import com.git.reny.patrol.utils.ResUtils;

/**
 * Created by admin on 2017/6/30.
 * 水平分割线（横线） 左右 "20px" Padding
 */

public class HDivider40Padding extends HDivider {
    public HDivider40Padding() {
        super(new DividerConfig.Build().setBothPadding(ResUtils.getDimenPx(R.dimen.x40)).build());
    }
}
