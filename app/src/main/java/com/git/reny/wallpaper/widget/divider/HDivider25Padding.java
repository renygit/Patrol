package com.git.reny.wallpaper.widget.divider;


import com.git.reny.wallpaper.R;
import com.zyctd.mvplib.utils.ResUtils;

/**
 * Created by admin on 2019/4/21.
 * 水平分割线（横线） 左右 "25px" Padding
 */

public class HDivider25Padding extends HDivider {
    public HDivider25Padding() {
        super(new DividerConfig.Build().setBothPadding(ResUtils.getDimenPx(R.dimen.x25)).build());
    }
}
