package com.git.reny.patrol.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.git.reny.patrol.R;
import com.git.reny.patrol.core.BaseActivity;
import com.git.reny.patrol.entity.other.MainGridBean;
import com.git.reny.patrol.ui.adapter.MainGridAdapter;
import com.git.reny.patrol.utils.CommonUtils;
import com.zyctd.mvplib.base.RBasePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindArray;
import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.rv)
    RecyclerView rv;

    @BindArray(R.array.homeOptionTitles)
    String[] homeOptionTitles;

    int[] mipmapIds = {
            R.mipmap.ic_clock,
            R.mipmap.ic_upload,
            R.mipmap.ic_record,
            R.mipmap.ic_feedback,
            R.mipmap.ic_clear,
            R.mipmap.ic_refresh,
            R.mipmap.ic_calendar,
            R.mipmap.ic_pwd,
            R.mipmap.ic_logout
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected RBasePresenter obtainPresenter() {
        return null;
    }

    @Override
    protected void init(Bundle bundle) {
        CommonUtils.initRecyclerView(rv, new GridLayoutManager(getActivity(), 3));

        List<MainGridBean> list = new ArrayList<>(homeOptionTitles.length);
        for (int i = 0; i < homeOptionTitles.length; i++) {
            list.add(new MainGridBean(mipmapIds[i], homeOptionTitles[i]));
        }
        MainGridAdapter adapter = new MainGridAdapter(R.layout.item_main_grid, list);
        adapter.setOnItemChildClickListener((adapter1, view, position) -> {
            switch (position){
                case 0:
                    break;
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
            }
        });
        rv.setAdapter(adapter);
    }

    @Override
    protected boolean isEnableSwipeBack() {
        return false;
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);//false 必需是根Activity才有效 true任意Activity都有效
    }
}
