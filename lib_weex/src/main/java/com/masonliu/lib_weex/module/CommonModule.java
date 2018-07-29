package com.masonliu.lib_weex.module;

import com.masonliu.lib_weex.manager.WXCommonModuleManager;
import com.masonliu.lib_weex.manager.WXLoadAndCacheManager;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liumeng02 on 2018/3/26.
 */

public class CommonModule extends WXModule {

    @JSMethod(uiThread = true)
    public void handle(String url) {
        //处理weex发出的操作信息url
        WXCommonModuleManager.INSTANCE.handle(url, this);
    }

    @JSMethod(uiThread = true)
    public void handleWithResult(String url, JSCallback callback) {
        //处理weex发出的操作信息url,带返回值
        Map<String, Object> map = new HashMap<>();
        try {
            map = WXCommonModuleManager.INSTANCE.handle(url, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        callback.invoke(map);
    }

    @JSMethod(uiThread = false)
    public void handleWithResultInThread(String url, JSCallback callback) {
        //处理weex发出的操作信息url,带返回值，新开子线程
        Map<String, Object> map = new HashMap<>();
        try {
            map = WXCommonModuleManager.INSTANCE.handle(url, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        callback.invoke(map);
    }

    /** 内置桥接功能 **/
    @JSMethod(uiThread = false)
    public void nativeHttpGet(String url, JSCallback callback) {
        Map<String, Object> map = new HashMap<>();
        try {
            Request request = new Request.Builder().url(url).build();
            Response response = WXLoadAndCacheManager.INSTANCE.getOkHttpClient().newCall(request).execute();
            map.put("status", response.code());
            map.put("data", response.body().string());
            if (response.isSuccessful()) {
                map.put("ok", true);
            } else {
                map.put("ok", false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", 500);
            map.put("data", e.toString());
            map.put("ok", false);
        }
        callback.invoke(map);
    }

    @JSMethod(uiThread = true)
    public void openBrowser(String url) {

    }

    @JSMethod(uiThread = true)
    public void getSysInfo(String url) {
//        result.put("DEBUG", "" + BuildConfig.DEBUG);
//        result.put("APPLICATION_ID", BuildConfig.APPLICATION_ID);
//        result.put("VERSION_CODE", "" + BuildConfig.VERSION_CODE);
//        result.put("VERSION_NAME", BuildConfig.VERSION_NAME);
//        osversion
//                osname
//        sdkversion
//                sdkname
//                网络类型
//                        厂商
//                        型号
//                                rom名称
//                                rom版本

    }
}