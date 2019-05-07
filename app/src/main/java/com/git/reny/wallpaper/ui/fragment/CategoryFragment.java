/*
package com.git.reny.wallpaper.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dingmouren.layoutmanagergroup.skidright.SkidRightLayoutManager;
import com.git.reny.wallpaper.R;
import com.git.reny.wallpaper.core.BaseFragment;
import com.git.reny.wallpaper.entity.other.CategoryBean;
import com.git.reny.wallpaper.ui.adapter.CategoryCardAdapter;
import com.git.reny.wallpaper.ui.adapter.CategoryGridAdapter;
import com.git.reny.wallpaper.ui.adapter.CategoryListAdapter;
import com.git.reny.wallpaper.utils.CommonUtils;
import com.git.reny.wallpaper.utils.SPUtils;
import com.zyctd.mvplib.base.RBasePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class CategoryFragment extends BaseFragment {

    @BindView(R.id.rv)
    RecyclerView rv;

    private String[] categoryList = {"明星", "节日", "美女", "风景", "汽车", "可爱", "唯美", "苹果",
            "动漫", "爱情", "动态", "卡通", "搞笑", "非主流", "创意", "游戏", "影视", "动物", "植物"};

    private int[] mipmapList = {R.mipmap.img_mx,R.mipmap.img_jr,R.mipmap.img_mn,R.mipmap.img_fj,R.mipmap.img_qc,R.mipmap.img_ka,R.mipmap.img_wm,R.mipmap.img_pg,
            R.mipmap.img_dm,R.mipmap.img_aq,R.mipmap.img_dt,R.mipmap.img_kt,R.mipmap.img_gx,R.mipmap.img_fzl,R.mipmap.img_cy,R.mipmap.img_yx,R.mipmap.img_ys,R.mipmap.img_dw,R.mipmap.img_zw};


    private SkidRightLayoutManager cardLayoutManager;
    private GridLayoutManager gridLayoutManager;
    private LinearLayoutManager listLayoutManager;

    private CategoryCardAdapter cardAdapter;
    private CategoryListAdapter listAdapter;
    private CategoryGridAdapter gridAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_category;
    }

    @Override
    protected RBasePresenter obtainPresenter() {
        return null;
    }

    @Override
    protected void init(Bundle bundle) {
        cardLayoutManager = new SkidRightLayoutManager(1.5f, 0.85f);
        listLayoutManager = new LinearLayoutManager(getActivity());
        gridLayoutManager = new GridLayoutManager(getActivity(), 3);

        List<CategoryBean> dataList = new ArrayList<>(categoryList.length);
        for (int i = 0; i < categoryList.length; i++) {
            dataList.add(new CategoryBean(mipmapList[i], categoryList[i]));
        }

        cardAdapter = new CategoryCardAdapter(dataList);
        listAdapter = new CategoryListAdapter(dataList);
        gridAdapter = new CategoryGridAdapter(dataList);

        int style = SPUtils.init(R.string.CategoryStyle).getInt(R.string.CategoryStyle);
        if(style == 0 || style == 1) {
            CommonUtils.initRecyclerView(rv, cardLayoutManager);
            rv.setAdapter(cardAdapter);
        }else if(style == 2) {
            CommonUtils.initRecyclerView(rv, listLayoutManager);
            rv.setAdapter(listAdapter);
        }else {
            CommonUtils.initRecyclerView(rv, gridLayoutManager);
            rv.setAdapter(gridAdapter);
        }
    }

    public void setLayoutManager(int menuId){
        switch (menuId) {
            case R.id.menu_card:
                rv.setLayoutManager(cardLayoutManager);
                rv.setAdapter(cardAdapter);
                SPUtils.init(R.string.CategoryStyle).putInt(R.string.CategoryStyle, 1);
                break;
            case R.id.menu_list:
                rv.setLayoutManager(listLayoutManager);
                rv.setAdapter(listAdapter);
                SPUtils.init(R.string.CategoryStyle).putInt(R.string.CategoryStyle, 2);
                break;
            case R.id.menu_grid:
                rv.setLayoutManager(gridLayoutManager);
                rv.setAdapter(gridAdapter);
                SPUtils.init(R.string.CategoryStyle).putInt(R.string.CategoryStyle, 3);
                break;
        }
    }

}
*/
