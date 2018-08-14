package com.git.reny.patrol.api;

import com.git.reny.patrol.R;
import com.git.reny.patrol.core.ResultException;
import com.git.reny.patrol.entity.response.GlobleResponse;
import com.git.reny.patrol.utils.GZIPUtils;
import com.git.reny.patrol.utils.ResUtils;
import com.zyctd.mvplib.utils.LogUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.Util;

/**
 * Created by reny on 2017/6/7.
 * 在这里可以对请求和响应做统一处理
 */

public class MyNetInterceptor implements Interceptor {

    private static final int TIMEOUT_CONNECT = 5; //5秒
    private String cache;

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder requestBuilder = chain.request().newBuilder();
        requestBuilder.removeHeader("Accept-Encoding");
        Request request = requestBuilder.build();
        //LogUtils.e(request.url().url().toString());//打印要访问的地址 可注释
        Response response = chain.proceed(request);
        cache = chain.request().header("cache");
        if (cache == null || "".equals(cache)) {
            cache = TIMEOUT_CONNECT + "";
        }

        if (response.code() == 200) {
            byte[] decodeBytes;
            try {
                byte[] bytes = response.body().bytes();
                decodeBytes = GZIPUtils.decompress(bytes);
            } catch (Exception e) {
                e.printStackTrace();
                //LogUtils.e("responseBody解析出错："+response.code()+"--"+response.message());
                throw new ResultException(ResultException.UNKNOWNERROR, "数据解压出错");
            }

            if (null != decodeBytes) {
                String result = new String(decodeBytes, Util.UTF_8);
//                LogUtils.e("处理前：" + result);

                try {
                    response = handlerResponse(response, new JSONObject(result));
                } catch (JSONException e) {
                    e.printStackTrace();
                    throw new ResultException(ResultException.UNKNOWNERROR, "json解析出错");
                }
//                LogUtils.e("再次打印（处理后）："+response.body().string());
                return response;
            } else {
                throw new ResultException(ResultException.UNKNOWNERROR, "返回数据为空");
            }

        } else {
            //LogUtils.e("访问出错："+response.code()+"--"+response.message());
            throw new ResultException(response.code(), ResUtils.getString(R.string.request_error));
        }
    }

    private Response handlerResponse(Response response, JSONObject jsonObject) {
        if (null != jsonObject) {
            GlobleResponse globleResponse = new GlobleResponse();
            try {
                //globleResponse.setCommandName(jsonObject.getString("CommandName"));
                globleResponse.setErrorCode(jsonObject.getInt("ErrorCode"));
                globleResponse.setErrorMessage(jsonObject.getString("ErrorMessage"));

                if (globleResponse.getErrorCode() == ResultException.SUCCESSCODE) {

                    if (jsonObject.has("Data") && !jsonObject.isNull("Data")) {
                        JSONObject Data;
                        try {
                            Data = jsonObject.getJSONObject("Data");
                        } catch (JSONException e) {
                            JSONArray jsonArray = jsonObject.getJSONArray("Data");
                            Data = new JSONObject();
                            Data.put("Data", jsonArray);
                        }

                        String cacheControl = response.header("Cache-Control");
                        //如果cacheControl为空，就让他TIMEOUT_CONNECT秒的缓存，本例是5秒，方便观察。注意这里的cacheControl是服务器返回的
                        if (cacheControl == null) {
                            //如果cache没值，缓存时间为TIMEOUT_CONNECT，有的话就为cache的值
                            response = response.newBuilder()
                                    .header("Cache-Control", "public, max-age=" + cache)
                                    .body(ResponseBody.create(null, Data.toString()))
                                    .build();
                        } else {
                            response = response.newBuilder()
                                    .body(ResponseBody.create(null, Data.toString()))
                                    .build();
                        }
                    }

                } else {
                    switch (globleResponse.getErrorCode()) {
                        case ResultException.LOGIN_OVERDUE:
                            //访问某个接口时报 登录超时
                            LogUtils.e("登录过期,请重新登录" + globleResponse.getCommandName());
                            /*LoginData.self().clear();

                            Intent intent = new Intent(MyApp.getContext(), LoginActivity.class);
                            MyApp.getContext().startActivity(intent);*/
                            break;
                        case ResultException.SERVICE_ERROR:
                            LogUtils.e(globleResponse.getErrorCode() + "  服务器出错");
                            break;
                        case ResultException.COMMANDNAME_ERROR:
                            LogUtils.e(globleResponse.getErrorCode() + "  请求命令参数错误");
                            break;
                    }

                    throw new ResultException(globleResponse.getErrorCode(), globleResponse.getErrorMessage());
                }
            } catch (JSONException e) {
                e.printStackTrace();
                throw new ResultException(ResultException.UNKNOWNERROR, "数据解析出错");
            }
        }
        return response;
    }

}
