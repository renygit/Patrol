package com.git.reny.patrol.api;

import com.git.reny.patrol.entity.response.Test;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by reny on 2017/1/5.
 */

public interface ApiService {

    //BaseServiceFactory->createService() 获取属性为BASE_URL的值
    String BASE_URL = APIConfig.BASE_URL_DEFAULT;


    @GET("books")
    Observable<Test> getBooks();

}
