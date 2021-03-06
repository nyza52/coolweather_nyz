package org.coolWeather.app.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
//和服务器交互
public class HttpUtil {
	
	public static void sendHttpRequest(final String address,final HttpCallbackListener listener){
		
		new Thread(new Runnable(){

			@Override
			public void run() {
			    HttpURLConnection connection = null;
			    try {
					URL url = new URL(address);
					connection=(HttpURLConnection) url.openConnection();
					connection.setRequestMethod("GET");
					connection.setReadTimeout(8000);
					InputStream in = connection.getInputStream();
					BufferedReader reader = new BufferedReader(new InputStreamReader(in));
					StringBuilder response = new StringBuilder();
					String line;
					while((line = reader.readLine()) != null){
						response.append(line);
					}
					if (listener != null) {
						//回调onFinish()方法
						listener.onFinish(response.toString());
					}
				} catch (Exception e) {
					if (listener != null) {
						//回调onError()方法
						listener.onError(e);
					}
					e.printStackTrace();
				}finally {
					if (connection != null) {
						connection.disconnect();
					}
				}
				
			}
			
		}).start();
	}

}
