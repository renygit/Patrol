package com.git.reny.wallpaper.api;

import com.git.reny.wallpaper.entity.response.ImgListData;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by reny on 2017/1/5.
 */

public interface ApiService {

    //BaseServiceFactory->createService() 获取属性为BASE_URL的值
    String BASE_URL = APIConfig.BASE_URL_DEFAULT;


    //http://192.168.2.29:8080/img/list?category=明星&page_size=20&page=1
    @GET("img/list")
    Observable<ImgListData> getImgs(@Query("category")String category, @Query("page_size")int page_size, @Query("page")int page);

}
