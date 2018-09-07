package com.git.reny.wallpaper.utils.glide;

import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;

/**
 * Created by reny on 2017/8/9.
 */

@GlideModule
public class MyAppGlideModule extends AppGlideModule {

    //主要针对V3升级到v4的用户，可以提升初始化速度，避免一些潜在错误。
    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }

}
