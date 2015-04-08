package com.example.mistletoe_mistletoesmb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.sina.weibo.sdk.R;
import com.sina.weibo.sdk.utils.LogUtil;

/**
 * 该类是整个 DEMO 程序的入口。
 * 
 * @author SINA
 * @since 2013-09-29
 */
public class WBDemoMainActivity extends Activity {

    /**
     * @see {@link Activity#onCreate}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LogUtil.sIsLogEnable = true;
        
        // 微博授权功能
        this.findViewById(R.id.feature_oauth).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(WBDemoMainActivity.this, WBAuthActivity.class));
            }
        });

        // 分享到微博功能
        this.findViewById(R.id.feature_share).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(WBDemoMainActivity.this, WBShareMainActivity.class));
            }
        });
        
        // 社会化组件
        this.findViewById(R.id.social_component).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WBDemoMainActivity.this, WBSocialActivity.class));
            }
        });
        
        // 登录注销按钮功能
        this.findViewById(R.id.feature_login_logout).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(WBDemoMainActivity.this, WBLoginLogoutActivity.class));
            }
        });
        
        // 开放接口（Open API）功能
        this.findViewById(R.id.feature_open_api).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(WBDemoMainActivity.this, WBOpenAPIActivity.class));
            }
        });
    }
}
