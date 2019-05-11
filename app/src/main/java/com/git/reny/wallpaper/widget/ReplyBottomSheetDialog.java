package com.git.reny.wallpaper.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.git.reny.wallpaper.R;
import com.git.reny.wallpaper.utils.CommonUtils;
import com.zyctd.mvplib.utils.ResUtils;
import com.zyctd.mvplib.utils.ToastUtils;

/**
 * Created by reny on 2018/8/14.
 */

public class ReplyBottomSheetDialog extends BottomSheetDialog {

    private ReplyCallBack callBack;
    private String replyId;

    public void setCallBack(ReplyCallBack callBack) {
        this.callBack = callBack;
    }

    public ReplyBottomSheetDialog(@NonNull Context context, String replyId) {
        super(context, R.style.BottomSheetEdit);
        this.replyId = replyId;
        init(context);
    }

    private void init(Context context){
        View replyView = LayoutInflater.from(context).inflate(R.layout.dialog_layout_reply, null);
        EditText et_reply = replyView.findViewById(R.id.et_reply);
        et_reply.setHint("请输入您的精彩评论");
        TextView tv_publish_reply = replyView.findViewById(R.id.tv_publish_reply);
        setContentView(replyView);


        try {
            /***
             * 解决bsd显示不全的情况
             */
            View parent = (View) replyView.getParent();
            BottomSheetBehavior behavior = BottomSheetBehavior.from(parent);
            replyView.measure(0,0);
            behavior.setPeekHeight(replyView.getMeasuredHeight());

            /***
             * 禁用下拉关闭
             */
            /*Field mBehaviorField = getClass().getDeclaredField("mBehavior");
            final BottomSheetBehavior behavior = (BottomSheetBehavior) mBehaviorField.get(bottomSheetDialog);*/
            behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
                @Override
                public void onStateChanged(@NonNull View bottomSheet, int newState) {
                    if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                        behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    }
                }
                @Override
                public void onSlide(@NonNull View bottomSheet, float slideOffset) {}
            });
        } catch (Exception e) {}

        tv_publish_reply.setOnClickListener(view -> {
            String content = et_reply.getText().toString().trim();
            if(!TextUtils.isEmpty(content)){
                dismiss();
                et_reply.setText("");
                if(null != callBack){
                    callBack.reply(content, replyId);
                }
            }else {
                ToastUtils.showLong("评论内容不能为空");
            }
        });
        et_reply.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!TextUtils.isEmpty(charSequence) && charSequence.length() > 0){
                    tv_publish_reply.setTextColor(ResUtils.getColor(R.color.theme));
                }else {
                    tv_publish_reply.setTextColor(Color.parseColor("#999999"));
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });

        setOnDismissListener(dialog -> {
            CommonUtils.hideSoftInput(et_reply);
        });
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }

    public interface ReplyCallBack{
        void reply(String content, String replyId);
    }

}
