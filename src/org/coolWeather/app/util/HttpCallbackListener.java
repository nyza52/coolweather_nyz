package org.coolWeather.app.util;

//HttpUtil类中使用到此接口，来回调服务返回的结果
public interface HttpCallbackListener {
	
	void onFinish(String response);
    
	void onError(Exception e);
}
