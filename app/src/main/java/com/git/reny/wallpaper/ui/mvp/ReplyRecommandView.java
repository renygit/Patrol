package com.git.reny.wallpaper.ui.mvp;

import com.git.reny.wallpaper.core.IBaseView;
import com.git.reny.wallpaper.entity.response.CookBean;

import java.util.List;

/**
 * Created by reny
 */

public interface ReplyRecommandView extends IBaseView {

    void setData(boolean isRefresh, List<CookBean> datas);

}
