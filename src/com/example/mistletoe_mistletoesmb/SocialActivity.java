package com.example.mistletoe_mistletoesmb;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.component.view.AttentionComponentView;
import com.sina.weibo.sdk.component.view.CommentComponentView;
import com.sina.weibo.sdk.exception.WeiboException;

public class SocialActivity extends Activity{
	    
	    private Oauth2AccessToken mAccessToken;
	    
	    private AttentionComponentView mAttentionView;
	    private CommentComponentView mCommentView;

	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_social);
	        
	        // 从 SharedPreferences 中读取上次已保存好 AccessToken 等信息，
	        // 第一次启动本应用，AccessToken 不可用
	        mAccessToken = AccessTokenKeeper.readAccessToken(this);
	        
	        mAttentionView = (AttentionComponentView) findViewById(R.id.attentionView);
	        mAttentionView.setAttentionParam(AttentionComponentView.RequestParam.createRequestParam(
	                Certificate.CONSUMER_KEY, mAccessToken.getToken(), "2016183205", "", new WeiboAuthListener() {
	                    @Override
	                    public void onWeiboException( WeiboException arg0 ) {
	                    }
	                    @Override
	                    public void onComplete( Bundle arg0 ) {
	                        Toast.makeText(SocialActivity.this, "auth acess_token:"+Oauth2AccessToken.parseAccessToken(arg0).getToken(), 
	                                0).show();
	                    }
	                    @Override
	                    public void onCancel() {
	                    }
	                }));
	        
	        mCommentView = (CommentComponentView) findViewById(R.id.commentView);
	        mCommentView.setCommentParam(CommentComponentView.RequestParam.createRequestParam(
	        		 Certificate.CONSUMER_KEY, mAccessToken.getToken(), 
	                "后会无期", "测试评论", CommentComponentView.Category.MOVIE, new WeiboAuthListener() {
	                    @Override
	                    public void onWeiboException( WeiboException arg0 ) {
	                    }
	                    @Override
	                    public void onComplete( Bundle arg0 ) {
	                        Toast.makeText(SocialActivity.this, "auth acess_token:"+Oauth2AccessToken.parseAccessToken(arg0).getToken(), 
	                                0).show();
	                    }
	                    @Override
	                    public void onCancel() {
	                    }
	                }));
	        
	    }	
}
