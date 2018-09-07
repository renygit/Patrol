package com.git.reny.wallpaper.widget.divider;


import com.git.reny.wallpaper.widget.divider.lib.RDivider;
import com.git.reny.wallpaper.widget.divider.lib.RDividerBuilder;
import com.git.reny.wallpaper.widget.divider.lib.RDividerItemDecoration;

/**
 * Created by admin on 2017/6/30.
 */

public class HDivider extends RDividerItemDecoration {

    private RDivider divider;
    private DividerConfig config;

    public HDivider(DividerConfig config) {
        super();
        if(null == config){
            config = new DividerConfig.Build().build();
        }
        this.config = config;
    }

    @Override
    public RDivider getDivider(int itemPosition) {
        if(null == divider) {
            if(config.getBothPadding() > 0){
                config.setStartPadding(config.getBothPadding());
                config.setEndPadding(config.getBothPadding());
            }
            divider = new RDividerBuilder()
                    .setBottomSideLine(true, config.getColor(), config.getLineWidth(), config.getStartPadding(), config.getEndPadding())
                    .create();
        }
        return divider;
        /*RDivider divider = null;
        switch (itemPosition % 2) {
            case 0:
                //每一行第一个显示rignt和bottom
                divider = new RDividerBuilder()
                        .setRightSideLine(true, 0xff666666, 10, 0, 0)
                        .setBottomSideLine(true, 0xff666666, 20, 0, 0)
                        .create();
                break;
            case 1:
                //第二个显示Left和bottom
                divider = new RDividerBuilder()
                        .setLeftSideLine(true, 0xff666666, 10, 0, 0)
                        .setBottomSideLine(true, 0xff666666, 20, 0, 0)
                        .create();
                break;
            default:
                break;
        }
        return divider;*/
    }
}
