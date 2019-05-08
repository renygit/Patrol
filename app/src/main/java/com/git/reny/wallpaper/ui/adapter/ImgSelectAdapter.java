package com.git.reny.wallpaper.ui.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.git.reny.wallpaper.R;
import com.git.reny.wallpaper.entity.other.MultImgBean;
import com.git.reny.wallpaper.utils.PicUtils;
import com.git.reny.wallpaper.utils.glide.GlideHelper;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.List;

/**
 * Created by reny on 2019/5/8.
 */

public class ImgSelectAdapter extends BaseMultiItemQuickAdapter<MultImgBean, BaseViewHolder> {

    public interface ImgManageCallBack{
        void deleteImg(int pos);
        void clickAddImgs();
    }

    private ImgManageCallBack callBack;
    private int imgMaxNum;

    public ImgSelectAdapter(List<MultImgBean> imgs, int imgMaxNum) {
        super(imgs);
        addItemType(MultImgBean.ADD, R.layout.item_img_add);
        addItemType(MultImgBean.IMG, R.layout.item_img);
        this.imgMaxNum = imgMaxNum;
    }

    public void setCallBack(ImgManageCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    protected void convert(BaseViewHolder helper, MultImgBean item) {
        switch (helper.getItemViewType()) {
            case MultImgBean.ADD:
                helper.setText(R.id.tv_img_add_tip, getItemCount() > 1 ? (getItemCount()-1)+"/"+imgMaxNum : "传图片");
                if(null != callBack) {
                    helper.itemView.setOnClickListener(v -> {
                        callBack.clickAddImgs();
                    });
                }
                break;
            case MultImgBean.IMG:
                if(item.getImg() instanceof LocalMedia) {
                    GlideHelper.display(helper.getView(R.id.iv_img), PicUtils.getPath((LocalMedia)item.getImg()));
                }else {
                    GlideHelper.display(helper.getView(R.id.iv_img), item.getImg());
                }
                if (null != callBack) {
                    helper.getView(R.id.iv_delete_img).setOnClickListener(v -> {
                        callBack.deleteImg(helper.getAdapterPosition());
                    });
                }
                break;
        }
    }
}
