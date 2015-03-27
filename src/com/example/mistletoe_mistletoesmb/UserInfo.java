package com.example.mistletoe_mistletoesmb;

import android.graphics.drawable.Drawable;

public class UserInfo {  //用户信息的存取方法
	final static String MDID = "MistletoeDatabaseID";
	final static String USERMBID = "UserMicroBlogID";
	final static String USERMBNAME = "UserMicroBlogName";
	final static String TOKEN = "TOKEN";
	final static String TOKENSECRET = "TOKENSECRET";
	final static String USERMBICON = "USERMicroBlogICON";
	
	private String mDId;
	private String userMBId;
	private String userMBName;
	private String token;
	private String tokenSecret;
	private Drawable userMBIcon;
	
	public void setMDId(String mDId){
		this.mDId = mDId;
	}
	public void setUserMBId(String userMBId){
		this.userMBId = userMBId;
	}
	public void setUserMBName(String userMBName){
		this.userMBName = userMBName;
	}
	public void setToken(String token){
		this.token = token;
	}
	public void setTokenSecret(String tokenSecret){
		this.tokenSecret = tokenSecret;
	}
	public void setUserMBIcon(Drawable icon){
		this.userMBIcon = icon;
	}
	
	public String getMDId(){
		return mDId;
	}
	public String getUserMBId(){
		return userMBId;
	}
	public String getUserMBName(){
		return userMBName;
	}
	public String getToken(){
		return token;
	}
	public String getTokenSecret(){
		return tokenSecret;
	}
	public Drawable getUserMBIcon(){
		return userMBIcon;
	}
}
