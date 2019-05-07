package com.git.reny.wallpaper.entity.response;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.git.reny.wallpaper.R;
import com.git.reny.wallpaper.ui.activity.LoginActivity;
import com.git.reny.wallpaper.utils.SPUtils;
import com.zyctd.mvplib.utils.GsonSingleton;
import com.zyctd.mvplib.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;

public class UserData {

    public static UserData self;

    private IdBean _id;
    private String name;
    private String pwd;

    public IdBean get_id() {
        return _id;
    }

    public void set_id(IdBean _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }


    public static void saveData(UserData userData){
        self = userData;
        String saveDataStr = GsonSingleton.gson.toJson(userData);
        SPUtils.init(R.string.LoginData).put(R.string.LoginData, saveDataStr);
        EventBus.getDefault().post(userData);
    }

    public static void readData() {
        String saveDataStr = SPUtils.init(R.string.LoginData).getString(R.string.LoginData);
        if(!TextUtils.isEmpty(saveDataStr)) {
            self = GsonSingleton.gson.fromJson(saveDataStr, UserData.class);
        }
    }

    public static void clear() {
        self = null;
        SPUtils.init(R.string.LoginData).clear();
    }

    //是否登录
    public static boolean isLogin(){
        return null != self && null != self.get_id() && !TextUtils.isEmpty(self.get_id().get$oid());
    }

    public static boolean isLogin(Context context){
        if(isLogin()){
            return true;
        }else {
            ToastUtils.showShort("您还未登录，请先登录");
            context.startActivity(new Intent(context, LoginActivity.class));
            return false;
        }
    }

    public static String getUserId(){
        return isLogin() ? self.get_id().get$oid() : null;
    }

}
