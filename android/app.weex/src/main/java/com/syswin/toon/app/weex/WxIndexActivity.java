package com.syswin.toon.app.weex;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.syswin.toon.lib.weex.page.WXBaseActivity;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.WXRenderStrategy;

import net.wequick.small.Small;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author : walid
 * @Data : 2017-02-20  15:27
 * @Describe : Weex 入口
 */

public class WxIndexActivity extends WXBaseActivity {

    private WXSDKInstance mWXSDKInstance;
    private FrameLayout mContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_wxindex);
        mContainer = (FrameLayout) findViewById(R.id.container);
        mWXSDKInstance = new WXSDKInstance(this);
        mWXSDKInstance.registerRenderListener(this);
        Map<String, Object> options = new HashMap<>();
        Uri uri = Small.getUri(this);
        if (uri == null) {
            throw new NullPointerException("weex page uri isn't empty !!!");
        }
        String url = uri.getQueryParameter("url");
        if (TextUtils.isEmpty(url)) {
            throw new NullPointerException("weex page url isn't empty !!!");
        }
        Uri weexUri = Uri.parse(url);
        setTitle(weexUri.getQueryParameter("title"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        options.put(WXSDKInstance.BUNDLE_URL, url);
        mWXSDKInstance.renderByUrl("WxIndex", url, options, null, WXRenderStrategy.APPEND_ASYNC);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mWXSDKInstance != null) {
            mWXSDKInstance.onActivityStart();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mWXSDKInstance != null) {
            mWXSDKInstance.onActivityResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mWXSDKInstance != null) {
            mWXSDKInstance.onActivityPause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mWXSDKInstance != null) {
            mWXSDKInstance.onActivityStop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWXSDKInstance != null) {
            mWXSDKInstance.onActivityDestroy();
        }
    }

    @Override
    public void onViewCreated(WXSDKInstance instance, View view) {
        if (view.getParent() != null) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
        mContainer.addView(view);
    }

    @Override
    public void onRenderSuccess(WXSDKInstance instance, int width, int height) {

    }

    @Override
    public void onRefreshSuccess(WXSDKInstance instance, int width, int height) {

    }

    @Override
    public void onException(WXSDKInstance instance, String errCode, String msg) {

    }
}
