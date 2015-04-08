package com.example.mistletoe_mistletoesmb;

import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sina.weibo.sdk.R;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.openapi.UsersAPI;
import com.sina.weibo.sdk.openapi.models.ErrorInfo;
import com.sina.weibo.sdk.openapi.models.User;
import com.sina.weibo.sdk.utils.LogUtil;


public class MistletoeSMBMainActivity extends Activity{
	private static final String TAG = WBUserAPIActivity.class.getName();
	private DataHelper dbHelper;
	/** 当前 Token 信息 */
    private Oauth2AccessToken mAccessToken;
    /** 用户信息接口 */
    private UsersAPI mUsersAPI;
    private UserInfo userInfo;
    
    //槲寄生
    private String saveName;
    private String saveUserID;
    private String saveImgURL;
    private TextView mytext;
    private ImageView myHeadSculpture;
    private TextView mySMBName;
    
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mistletoe_activity_main);	
		// 获取当前已保存过的 Token
        mAccessToken = AccessTokenKeeper.readAccessToken(this);
        // 获取用户信息接口
        mUsersAPI = new UsersAPI(this, Constants.APP_KEY, mAccessToken);     
   //     String uid = mAccessToken.getUid();
        Log.d("mytag", "xx: " + mAccessToken.getUid());
        long uid = Long.parseLong(mAccessToken.getUid());
        mUsersAPI.show(uid, mListener);
        
        
        dbHelper = new DataHelper(MistletoeSMBMainActivity.this);
        List<UserInfo> userList = dbHelper.GetUserList(true);
		
        /*        
				if (userList.isEmpty()) {
					Toast.makeText(MistletoeSMBMainActivity.this, 
                            "用户列表还是空的", 
                            Toast.LENGTH_LONG).show();                                      */
//					// 如果为空说明第一次使用跳到AuthorizeActivity页面进行OAuth认证
//					Intent intent = new Intent();
//					intent.setClass(MistletoeSMBMainActivity.this, WBAuthActivity.class);
//					startActivity(intent);
        /*				} else {
					Toast.makeText(MistletoeSMBMainActivity.this, 
                            "用户列表有东西啦~~~~~", 
                            Toast.LENGTH_LONG).show();										*/
//					// 然后根据这3个值调用新浪的api接口获取这些记录对应的用户昵称和用户头像图标等信息。
////					UpdateUserInfo(this, userList);
//					Intent intent = new Intent();
//					intent.setClass(MistletoeSMBMainActivity.this, WBLoginLogoutActivity.class);
//					startActivity(intent);
				}
/*	}   */
//	public void UpdateUserInfo(Context context, List<UserInfo> userList) {
//		DataHelper dbHelper = new DataHelper(context);
//		OAuth auth = new OAuth();
//		String url = "http://api.t.sina.com.cn/users/show.json";
//		Log.e("userCount", userList.size() + "");
//		for (UserInfo user : userList) {
//			if (user != null) {
//				List params = new ArrayList();
//				params.add(new BasicNameValuePair("source", auth.CONSUMER_KEY));
//				params.add(new BasicNameValuePair("user_id", user.getUserid()));
//				HttpResponse response = auth.SignRequest(user.getToken(), user
//						.getTokensecret(), url, params);
//				if (200 == response.getStatusLine().getStatusCode()) {
//					try {
//						InputStream is = response.getEntity().getContent();
//						Reader reader = new BufferedReader(
//								new InputStreamReader(is), 4000);
//						StringBuilder buffer = new StringBuilder((int) response
//								.getEntity().getContentLength());
//						try {
//							char[] tmp = new char[1024];
//							int l;
//							while ((l = reader.read(tmp)) != -1) {
//								buffer.append(tmp, 0, l);
//							}
//						} finally {
//							reader.close();
//						}
//						String string = buffer.toString();
//						response.getEntity().consumeContent();
//						JSONObject data = new JSONObject(string);
//						String ImgPath = data.getString("profile_image_url");
//						Bitmap userIcon = DownloadImg(ImgPath);
//
//						String userName = data.getString("screen_name");
//						

//						Log.e("ImgPath", ImgPath);
//
//					} catch (IllegalStateException e) {
//						e.printStackTrace();
//					} catch (IOException e) {
//						e.printStackTrace();
//					} catch (JSONException e) {
//						e.printStackTrace();
//					}
//				}
//			}
//		}
//		dbHelper.Close();
//	}

	public Bitmap DownloadImg(String url) {
		URL uri;
		Bitmap bm = null;
		try {
			uri = new URL(url);
			// 获取图片流数据
			InputStream is = uri.openStream();
			// 生成图片
			bm = BitmapFactory.decodeStream(is);
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bm;
	}
	
	public void myClick_EnterWeibo(View v) {
    	//进入微博界面按钮
		Intent intent = new Intent(MistletoeSMBMainActivity.this, MistletoeSMBWeiboActivity.class);
		startActivity(intent);
    }
	
	/**
     * 微博 OpenAPI 回调接口。
     */
    private RequestListener mListener = new RequestListener() {
        @Override
        public void onComplete(String response) {               //异步，可能最后才执行
            if (!TextUtils.isEmpty(response)) {
                LogUtil.i(TAG, response);
                // 调用 User#parse 将JSON串解析成User对象
                User user = User.parse(response);
                Bundle bundle = new Bundle();
                if (user != null) {
                    Toast.makeText(MistletoeSMBMainActivity.this, 
                            "获取User信息成功，用户昵称：" + user.screen_name + " userid:" + user.id +" user头像地址：" +user.profile_image_url, 
                            Toast.LENGTH_LONG).show(); 
                    //槲寄生
                    saveUserID = user.id;
                    saveName = user.screen_name;
                    saveImgURL = user.profile_image_url;
                    
                    myHeadSculpture = (ImageView)findViewById(R.id.headSculpture);
                    myHeadSculpture.setImageBitmap(DownloadImg(saveImgURL));
                    mySMBName = (TextView)findViewById(R.id.mySMBName);
                    mySMBName.setText(saveName);
                    
//                    userInfo = new UserInfo();
//                    userInfo.setUserid(saveUserID);
//                    userInfo.setToken(mAccessToken.getToken());
//                    dbHelper.UpdateUserInfo(userInfo);
//                    Bitmap userIcon = DownloadImg(saveImgURL);
//                    dbHelper.UpdateUserInfo(saveName, userIcon, saveUserID);
//                    dbHelper.Close();
                    
//                    bundle.putCharSequence("userName",user.screen_name);
//                    bundle.putCharSequence("userID",user.id);
//                    bundle.putCharSequence("userName",user.screen_name);
                } else {
                    Toast.makeText(MistletoeSMBMainActivity.this, response, Toast.LENGTH_LONG).show();
                }
            }
        }

        @Override
        public void onWeiboException(WeiboException e) {
            LogUtil.e(TAG, e.getMessage());
            ErrorInfo info = ErrorInfo.parse(e.getMessage());
            Toast.makeText(MistletoeSMBMainActivity.this, info.toString(), Toast.LENGTH_LONG).show();
        }
    };
}
