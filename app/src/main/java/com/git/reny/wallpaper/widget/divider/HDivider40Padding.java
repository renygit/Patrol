package com.git.reny.wallpaper.widget.divider;


import com.git.reny.wallpaper.R;
import com.git.reny.wallpaper.utils.ResUtils;

/**
 * Created by admin on 2017/6/30.
 * 水平分割线（横线） 左右 "20px" Padding
 */

public class HDivider40Padding extends HDivider {
    public HDivider40Padding() {
        super(new DividerConfig.Build().setBothPadding(ResUtils.getDimenPx(R.dimen.x40)).build());
    }
}
