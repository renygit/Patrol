package com.git.reny.wallpaper.ui.mvp;

import com.git.reny.wallpaper.core.IBaseView;
import com.git.reny.wallpaper.entity.response.HomeRecommendDetails;

/**
 * Created by reny
 */

public interface DetailsView extends IBaseView {

    void setData(boolean isRefresh, HomeRecommendDetails data);
}
