package com.git.reny.wallpaper.api;

import com.git.reny.wallpaper.entity.response.CollectData;
import com.git.reny.wallpaper.entity.response.CookList;
import com.git.reny.wallpaper.entity.response.HomeList;
import com.git.reny.wallpaper.entity.response.HomeRecommend;
import com.git.reny.wallpaper.entity.response.HomeRecommendList;
import com.git.reny.wallpaper.entity.response.ListResults;
import com.git.reny.wallpaper.entity.response.UserData;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by reny on 2017/1/5.
 */

public interface ApiService {

    //BaseServiceFactory->createService() 获取属性为BASE_URL的值
    String BASE_URL = APIConfig.BASE_URL_DEFAULT;


    @GET("register")
    Observable<UserData> register(@Query("name")String name, @Query("pwd")String pwd);

    @GET("login")
    Observable<UserData> login(@Query("name")String name, @Query("pwd")String pwd);



    @GET("is_collect")
    Observable<CollectData> isCollect(@Query("userId")String userId, @Query("cookId")String cookId);

    @GET("collect")
    Observable<CollectData> collect(@Query("userId")String userId, @Query("cookId")String cookId);

    //获取收藏的List数据(http://http://192.168.3.14:8080/collect/getlist?userId=5cd031f55460de20686b416a&page_size=10&page=1)
    @GET("collect/getlist")
    Observable<HomeList> getCollectList(@Query("userId")String userId, @Query("page_size")int page_size, @Query("page")int page);



    @GET("upload")
    Observable<Object> upload(@Query("userId")String userId, @Query("title")String title, @Query("content")String content, @Query("imgs[]")String[] imgs);

    @GET("cook/getlist")
    Observable<CookList> getCookList(@Query("page_size")int page_size, @Query("page")int page);


    //首页tab项
    @GET("home/categorys")
    Observable<ListResults> getHomeCategorys();

    //首页 推荐页相关
    @GET("home/recommend")
    Observable<HomeRecommend> getHomeRecommend();

    @GET("home/recommend/list")
    Observable<HomeRecommendList> getHomeRecommendList(@Query("lastid")String lastid);


    //食文tab项
    @GET("info/categorys")
    Observable<ListResults> getInfoCategorys();

    //首页其它分类List数据(http://192.168.1.10:8080/home/list?category=xx&page_size=20&page=1)
    @GET("home/list")
    Observable<HomeList> getHomeList(@Query("category")String category, @Query("page_size")int page_size, @Query("page")int page);

    //食文分类List数据(http://192.168.1.9:8080/info/list?category=xx&page_size=20&page=1)
    @GET("info/list")
    Observable<HomeList> getInfoList(@Query("category")String category, @Query("page_size")int page_size, @Query("page")int page);


}
