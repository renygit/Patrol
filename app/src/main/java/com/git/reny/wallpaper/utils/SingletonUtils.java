package com.git.reny.wallpaper.utils;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.zyctd.mvplib.utils.AppUtils;

import java.io.File;

/**
 * Created by admin on 2017/6/6.
 */

public class SingletonUtils {

    public static ClearableCookieJar cookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(AppUtils.self().getContext()));
    public static File cacheDir = AppUtils.self().getContext().getCacheDir();

}
