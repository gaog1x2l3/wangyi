package com.example.wynewstool;

import android.os.Handler;

public class DownUtil {

	public static final int TYPE_JSON = 0;// JOSN 字符串
	public static final int TYPE_IAMGE = 1;// image图片
	private static Handler handler = new Handler();

	public static void down(final int type, final String url, final OnDownCompelet onDownCompelet) {
		new Thread() {
			public void run() {
				Object obj = null;
				switch (type) {
				case DownUtil.TYPE_JSON:
					obj = HttpUtil.getJSON(url);
					break;
				case DownUtil.TYPE_IAMGE:
					obj = HttpUtil.getBitmap(url);
					break;
				}
				final Object result = obj;
				handler.post(new Runnable() {
					@Override
					public void run() {
						// 在主线程中执行的方法
						// 下载完以后得到一个obj
						onDownCompelet.downCompelet(url, result);
					}
				});
			};
		}.start();
	}

	public interface OnDownCompelet {
		void downCompelet(String url, Object obj);
	}
}
