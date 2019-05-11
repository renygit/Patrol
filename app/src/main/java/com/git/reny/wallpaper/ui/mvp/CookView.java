package com.git.reny.wallpaper.ui.mvp;

import com.git.reny.wallpaper.core.IBaseView;
import com.git.reny.wallpaper.entity.response.ReplyList;

/**
 * Created by reny
 */

public interface CookView extends IBaseView {

    void setData(boolean isRefresh, ReplyList data);

}
