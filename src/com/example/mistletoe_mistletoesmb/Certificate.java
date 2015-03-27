package com.example.mistletoe_mistletoesmb;

public class Certificate {   //认证授权 类
	public static final String CONSUMER_KEY = "832864193";// 替换为开发者的appkey
	public static final String CONSUMER_SECRET = "839c0ab2c9fbcc0501204d077e39e91e";// 替换为开发者的app_secret
	public static final String CALLBACK_URL = "http://baidu.com";//回调地址
	public static String ACCESS_TOKEN;
//	public static final String GET_USERINFO = "https://api.weibo.com/2/users/show.json?access_token="+ACCESS_TOKEN+"&uid="; //api地址
	public static final String SCOPE = // 应用申请的高级权限
			"email,direct_messages_read,direct_messages_write,"
			+ "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
			+ "follow_app_official_microblog," + "invitation_write";
}
