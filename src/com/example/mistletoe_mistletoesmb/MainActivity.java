package com.example.mistletoe_mistletoesmb;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends Activity {
	SQLiteMistletoeDatabaseHelper SQLMDHelper;
	
	private Oauth2AccessToken mAccessToken;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        //获取账号列表  
//        SQLMDHelper=new SQLiteMistletoeDatabaseHelper(this);  
//        //获取数据库用户列表
//        final List<UserInfo> userList= SQLMDHelper.GetUserList(true);  
//        //关闭数据库
//        SQLMDHelper.Close();
        
    //槲寄生：获取当前已保存过的 Token
     mAccessToken = AccessTokenKeeper.readAccessToken(this);
     // 为了看到载入界面的效果，我们延迟1s执行判断
      TimerTask task=new TimerTask(){
      	public void run()
      	{
      		  //如果为空说明第一次使用，引导用户进行授权
      		 if(mAccessToken == null)
      		 {
      			Intent intent = new Intent();  
				//跳到AuthorizeActivity页面进行OAuth认证  
		        intent.setClass(MainActivity.this, AuthorizeActivity.class);  
		        startActivity(intent); 
		        //关闭当前界面
		        MainActivity.this.finish();
      		 }
      		 else{
      			Intent it=new Intent();
			    //如果不为空，则跳转到登陆界面
			    it.setClass(MainActivity.this, LoginActivity.class);
			    startActivity(it);
			    //关闭本程序
			    MainActivity.this.finish();
      		 }		
      	}};
     Timer timer=new Timer();
	 timer.schedule(task,1000);
    }
}


