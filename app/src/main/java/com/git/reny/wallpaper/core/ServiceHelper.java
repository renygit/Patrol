package com.git.reny.wallpaper.core;

import com.git.reny.wallpaper.api.ApiService;

/**
 * Created by reny on 2017/2/9.
 */

public class ServiceHelper {

    public static ApiService getApi(){
        return (ApiService) ServiceFactory.getInstance().getService(ApiService.class);
    }



    public static ApiService _getApi() {
        return (ApiService) CommonServiceFactory.getInstance().getService(ApiService.class);
    }

}
