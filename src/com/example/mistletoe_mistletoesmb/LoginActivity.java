package com.example.mistletoe_mistletoesmb;

import java.text.SimpleDateFormat;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.openapi.LogoutAPI;
import com.sina.weibo.sdk.widget.LoginButton;
import com.sina.weibo.sdk.widget.LoginoutButton;

public class LoginActivity extends Activity{
	private TextView mTokenView;
	private LoginoutButton mLoginoutBtnSilver;
	
	private AuthListener mLoginListener = new AuthListener();
	private LogOutRequestListener mLogoutListener = new LogOutRequestListener();
	private AuthInfo mAuthInfo;
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_logout);
        mTokenView = (TextView) findViewById(R.id.result);

        // 创建授权认证信息
        mAuthInfo = new AuthInfo(this, Certificate.CONSUMER_KEY, Certificate.CALLBACK_URL, Certificate.SCOPE);

        mLoginoutBtnSilver = (LoginoutButton) findViewById(R.id.login_out_button_silver);
        mLoginoutBtnSilver.setWeiboAuthInfo(mAuthInfo, mLoginListener);
        mLoginoutBtnSilver.setLogoutListener(mLogoutListener);
        /**
         * 注销按钮：该按钮未做任何封装，直接调用对应 API 接口
         */
//        final Button logoutButton = (Button) findViewById(R.id.logout_button);
//        logoutButton.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new LogoutAPI(LoginActivity.this, Certificate.CONSUMER_KEY, 
//                        AccessTokenKeeper.readAccessToken(LoginActivity.this)).logout(mLogoutListener);
//            }
//        });
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        
        if (mLoginoutBtnSilver != null) {
            mLoginoutBtnSilver.onActivityResult(requestCode, resultCode, data);
        }
        
    }
	
	/**
     * 登入按钮的监听器，接收授权结果。
     */
    private class AuthListener implements WeiboAuthListener {
        @Override
        public void onComplete(Bundle values) {
            Oauth2AccessToken accessToken = Oauth2AccessToken.parseAccessToken(values);
            if (accessToken != null && accessToken.isSessionValid()) {
                String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(
                        new java.util.Date(accessToken.getExpiresTime()));
                String format = getString(R.string.weibosdk_demo_token_to_string_format_1);
                mTokenView.setText(String.format(format, accessToken.getToken(), date));

                AccessTokenKeeper.writeAccessToken(getApplicationContext(), accessToken);
            }
        }

        @Override
        public void onWeiboException(WeiboException e) {
            Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel() {
            Toast.makeText(LoginActivity.this, 
                    R.string.weibosdk_demo_toast_auth_canceled, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 登出按钮的监听器，接收登出处理结果。（API 请求结果的监听器）
     */
    private class LogOutRequestListener implements RequestListener {
        @Override
        public void onComplete(String response) {
            if (!TextUtils.isEmpty(response)) {
                try {
                    JSONObject obj = new JSONObject(response);
                    String value = obj.getString("result");

                    if ("true".equalsIgnoreCase(value)) {
                        AccessTokenKeeper.clear(LoginActivity.this);
                        mTokenView.setText(R.string.weibosdk_demo_logout_success);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }     

        @Override
        public void onWeiboException(WeiboException e) {
            mTokenView.setText(R.string.weibosdk_demo_logout_failed);
        }
    }
}
