package com.git.reny.wallpaper.ui.mvp;

import com.git.reny.wallpaper.core.IBaseView;
import com.git.reny.wallpaper.entity.response.HomeList;

/**
 * Created by reny
 */

public interface InfoOtherView extends IBaseView {

    void setData(boolean isRefresh, HomeList data);
}
