package com.git.reny.wallpaper.api;

import com.git.reny.wallpaper.R;
import com.git.reny.wallpaper.core.ResultException;
import com.git.reny.wallpaper.entity.response.GlobleResponse;
import com.zyctd.mvplib.utils.LogUtils;
import com.zyctd.mvplib.utils.ResUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.Util;

/**
 * Created by admin on 2017/6/7.
 */

public class MyNetInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder requestBuilder = chain.request().newBuilder();

        /*String _UUID = SPUtils.init(R.string.Token).getString(R.string.Token);
        if(TextUtils.isEmpty(_UUID)){
            _UUID = UUID.randomUUID().toString();
            SPUtils.init(R.string.Token).putString(R.string.Token, _UUID);
        }
        requestBuilder.addHeader("token", _UUID);*/
        Request request = requestBuilder.build();

        //LogUtils.e("token:" + request.header("token"));

        LogUtils.e(request.url().url().toString());//打印要访问的地址 可注释
        Response response = chain.proceed(request);

        if (response.code() == 200) {
            byte[] bytes = response.body().bytes();

            if (null != bytes) {
                String result = new String(bytes, Util.UTF_8);
                //LogUtils.e("处理前："+result);
                try {
                    response = handlerResponse(response, new JSONObject(result));
                } catch (JSONException e) {
                    e.printStackTrace();
                    throw new ResultException(ResultException.UNKNOWNERROR, "json解析出错");
                }
                //LogUtils.e("再次打印（处理后）："+response.body().string());
                return response;
            } else {
                throw new ResultException(ResultException.UNKNOWNERROR, "返回数据为空");
            }

        } else {
            LogUtils.e("访问出错："+response.code()+"--"+response.message());
            throw new ResultException(response.code(), ResUtils.getString(R.string.request_error));
        }
    }


    private Response handlerResponse(Response response, JSONObject jsonObject) {
        if (null != jsonObject) {
            GlobleResponse globleResponse = new GlobleResponse();
            try {
                globleResponse.setCode(jsonObject.getInt("code"));
                globleResponse.setMsg(jsonObject.getString("msg"));

                if (globleResponse.getCode() == ResultException.SUCCESSCODE) {
                    if (jsonObject.has("data") && !jsonObject.isNull("data")) {
                        JSONObject data = null;
                        Object listArray = new JSONTokener(jsonObject.getString("data")).nextValue();
                        if (listArray instanceof JSONArray){
                            JSONArray jsonArray = (JSONArray)listArray;
                            data = new JSONObject();
                            data.put("listData", jsonArray);
                        }else if (listArray instanceof JSONObject) {
                            data = (JSONObject)listArray;
                        }
                        if(null == data){
                            data = new JSONObject();
                        }
                        response = response.newBuilder()
                                .body(ResponseBody.create(null, data.toString()))
                                .build();
                    }

                } else {
                    switch (globleResponse.getCode()){
                        /*case ResultNewException.LOGIN_OVERDUE:
                            //访问某个接口时报 登录超时
                            LogUtils.e("登录过期,请重新登录" + globleResponse.getMessage());

                            EventBus.getDefault().post(EventServiceEnum.LoginOut);

                            *//*Intent intent = new Intent(MyApplication.getContext(), LoginActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            MyApplication.getContext().startActivity(intent);*//*
                            break;*/
                        /*case ResultNewException.SERVICE_ERROR:
                            LogUtils.e(globleResponse.getErrorCode() + "  服务器出错");
                            break;
                        case ResultNewException.COMMANDNAME_ERROR:
                            LogUtils.e(globleResponse.getErrorCode() + "  请求命令参数错误");
                            break;*/
                    }

                    throw new ResultException(globleResponse.getCode(), globleResponse.getMsg());
                }
            } catch (JSONException e) {
                e.printStackTrace();
                throw new ResultException(ResultException.UNKNOWNERROR, "数据解析出错");
            }
        }
        return response;
    }


}
