package com.git.reny.wallpaper.ui.mvp;

import com.git.reny.wallpaper.core.IBaseView;
import com.git.reny.wallpaper.entity.response.ImgListData;

/**
 * Created by maly on 18-1-29.
 */

public interface HomeView extends IBaseView {

    void setData(boolean isRefresh, ImgListData data);

}
