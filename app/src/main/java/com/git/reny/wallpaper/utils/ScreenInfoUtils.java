package com.git.reny.wallpaper.utils;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

public class ScreenInfoUtils {

	public static int screenHeight = 0;
	public static int screenWidth = 0;

	public static void init(Context context) {
		if (screenWidth == 0 || screenHeight == 0) {
			WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
			Display display = windowManager.getDefaultDisplay();
			Point size = new Point();
			display.getSize(size);
			screenWidth = size.x;
			screenHeight = size.y;
		}
	}

}
