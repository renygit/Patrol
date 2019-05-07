package com.git.reny.wallpaper.ui.mvp;

import com.git.reny.wallpaper.core.IBaseView;
import com.git.reny.wallpaper.entity.response.HomeRecommend;
import com.git.reny.wallpaper.entity.response.HomeRecommendList;

/**
 * Created by reny
 */

public interface HomeTjView extends IBaseView {

    void setData(boolean isRefresh, HomeRecommend data);

    void setListData(boolean isRefresh, HomeRecommendList data);
}
