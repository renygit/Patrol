package com.git.reny.wallpaper.ui.mvp;

import com.git.reny.wallpaper.core.IBaseView;
import com.git.reny.wallpaper.entity.response.ListResults;

/**
 * Created by reny
 */

public interface InfoView extends IBaseView {

    void setData(boolean isRefresh, ListResults data);

}
