package com.git.reny.wallpaper.api;

import com.git.reny.wallpaper.BuildConfig;

/**
 * Created by reny on 2018/1/17.
 */

public class APIConfig {

    //public static final String BASE_URL_DEFAULT = "http://192.168.2.37:8010/";
    public static final String BASE_URL_DEFAULT = BuildConfig.DEBUG ? "http://192.168.2.29:8080/":"http://gyandroid.zyctd.com/";

}
