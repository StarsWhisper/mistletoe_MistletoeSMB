package com.example.mistletoe_mistletoesmb;

public class Certificate {   //认证授权 类
	public static final String CONSUMER_KEY = "832864193";// 替换为开发者的appkey
	public static final String CONSUMER_SECRET = "839c0ab2c9fbcc0501204d077e39e91e";// 替换为开发者的app_secret
	public static final String CALLBACK_URL = "http://baidu.com";//回调地址
	public static String ACCESS_TOKEN;
	public static final String GET_USERINFO = "https://api.weibo.com/2/users/show.json?access_token="+ACCESS_TOKEN+"&uid="; //api地址
}
