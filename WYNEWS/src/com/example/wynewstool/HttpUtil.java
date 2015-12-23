package com.example.wynewstool;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * 网络连接工具类
 *
 */
public class HttpUtil {

	private static byte[] getByteByURL(String url) {
		try {
			URL u = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) u.openConnection();
			conn.setReadTimeout(5000);
			conn.setRequestMethod("GET");
			InputStream in = conn.getInputStream();
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int len = 0;
			byte[] buffer = new byte[1024 * 2];
			while ((len = in.read(buffer)) != -1) {
				out.write(buffer, 0, len);
			}
			return out.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据URL获得JSON字符串
	 * 
	 */
	public static JSONObject getJSON(String url) {
		byte[] buffer = getByteByURL(url);
		if (buffer != null) {
			try {
				String data = new String(buffer, "utf-8");
				JSONObject json = new JSONObject(data);
				return json;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 根据URL获得图片对象
	 * 
	 */
	public static Bitmap getBitmap(String url) {
		byte[] buffer = getByteByURL(url);
		Bitmap bitmap = null;
		if (buffer != null) {
			bitmap = BitmapFactory.decodeByteArray(buffer, 0, buffer.length);
		}
		return bitmap;
	}
}
