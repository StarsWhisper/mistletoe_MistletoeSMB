package com.example.mistletoe_mistletoesmb;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;

public class SQLiteMistletoeDatabaseHelper {
		//数据库名称  
		private static String DB_NAME = "sinaweibo.db";  
		//数据库版本  
		private static int DB_VERSION = 2;  
		private SQLiteDatabase db;  
		private SQLiteMistletoeDatabaseBuilder SQLMDB;  
		  
		public SQLiteMistletoeDatabaseHelper(Context context){  
			SQLMDB=new SQLiteMistletoeDatabaseBuilder(context,DB_NAME, null, DB_VERSION);  
			db= SQLMDB.getWritableDatabase();  
		}  
		//关闭数据库 
		public void Close()  
		{  
			db.close();  
			SQLMDB.close();  
		}  
		//获取users表中的UserID、Access Token、Access Secret的记录  
		public List<UserInfo> GetUserList(Boolean isSimple)  
		{  
			List<UserInfo> userList = new ArrayList<UserInfo>();  
			//获得数据表中的所有数据
			Cursor cursor=db.query(SQLiteMistletoeDatabaseBuilder.TB_NAME, null, null, null, null, null, UserInfo.MDID+" DESC");  
			//将游标移动到开始
			cursor.moveToFirst();  
			//循环遍历整个数据库
			while(!cursor.isAfterLast()&& (cursor.getString(1)!=null)){  
			UserInfo user=new UserInfo();  
			user.setMDId(cursor.getString(0));  
			user.setUserMBId(cursor.getString(1));  
			user.setToken(cursor.getString(2));  
			user.setTokenSecret(cursor.getString(3));  
		if(!isSimple){
			if(cursor.getString(4) != null && cursor.getBlob(5) != null)
			{
				user.setUserMBName(cursor.getString(4));  
				ByteArrayInputStream stream = new ByteArrayInputStream(cursor.getBlob(5));   
				Drawable icon= Drawable.createFromStream(stream, "image");  
				user.setUserMBIcon(icon);  
			}
		}  
			userList.add(user);  
			cursor.moveToNext();  
		}  
			cursor.close();  
			return userList;  
		}  
		  
		//判断users表中的是否包含某个UserID的记录  
		public Boolean HaveUserInfo(String UserId)  
		{  
			Boolean b=false;  
			Cursor cursor=db.query(SQLiteMistletoeDatabaseBuilder.TB_NAME, null, UserInfo.USERMBID + "=" + UserId, null, null, null,null);  
			b=cursor.moveToFirst();  
			Log.e("HaveUserInfo",b.toString());  
			cursor.close();  
			return b;  
		}
		//判断users表中的是否包含某个UserID的记录  
		public UserInfo GetUserByName(String userName)  
		{  
			Boolean b=false;  
			//注意汉字为查询条件时需要加''
			Cursor cursor=db.query(SQLiteMistletoeDatabaseBuilder.TB_NAME, null, UserInfo.USERMBNAME + "='" + userName+"'", null, null, null,null);  
			b=cursor.moveToFirst();  
			Log.e("GetUserByName",b.toString());  
			if(b != false){  
				UserInfo user=new UserInfo();  
				user.setMDId(cursor.getString(0));  
				user.setUserMBId(cursor.getString(1));  
				user.setToken(cursor.getString(2));  
				user.setTokenSecret(cursor.getString(3));  
				user.setUserMBName(cursor.getString(4));  
				ByteArrayInputStream stream = new ByteArrayInputStream(cursor.getBlob(5));   
				Drawable icon= Drawable.createFromStream(stream, "image");  
				user.setUserMBIcon(icon);  
				cursor.close();
				return user;
			}
			return null;  
		}	  
		//更新users表的记录，根据UserId更新用户昵称和用户图标  
		public int UpdateUserInfo(String userName,Bitmap userIcon,String UserId)  
		{  
			ContentValues values = new ContentValues();  
			values.put(UserInfo.USERMBNAME, userName);  
			// BLOB类型   
			final ByteArrayOutputStream os = new ByteArrayOutputStream();   
			// 将Bitmap压缩成PNG编码，质量为100%存储   
			userIcon.compress(Bitmap.CompressFormat.PNG, 100, os);   
			// 构造SQLite的Content对象，这里也可以使用raw   
			values.put(UserInfo.USERMBICON, os.toByteArray());  
			int id= db.update(SQLiteMistletoeDatabaseBuilder.TB_NAME, values, UserInfo.USERMBID + "=" + UserId, null);  
			Log.e("UpdateUserInfo2",id+"");  
			return id;  
		}  
		  
		//更新users表的记录  
		public int UpdateUserInfo(UserInfo user)  
		{  
			ContentValues values = new ContentValues();  
			values.put(UserInfo.USERMBID, user.getUserMBId());  
			values.put(UserInfo.TOKEN, user.getToken());  
			values.put(UserInfo.TOKENSECRET, user.getTokenSecret());  
			int id= db.update(SQLiteMistletoeDatabaseBuilder.TB_NAME, values, UserInfo.USERMBID + "=" + user.getUserMBId(), null);  
			Log.e("UpdateUserInfo",id+"");  
			return id;  
		}  
		  
		//添加users表的记录  
		public Long SaveUserInfo(UserInfo user)  
		{  
			ContentValues values = new ContentValues();  
			values.put(UserInfo.USERMBID, user.getUserMBId());  
			values.put(UserInfo.TOKEN, user.getToken());  
			values.put(UserInfo.TOKENSECRET, user.getTokenSecret());  
			Long uid = db.insert(SQLiteMistletoeDatabaseBuilder.TB_NAME, UserInfo.MDID, values);  
			Log.e("SaveUserInfo",uid+"");  
			return uid;  
		}  
		  
		//删除users表的记录  
		public int DelUserInfo(String UserId){  
			int id= db.delete(SQLiteMistletoeDatabaseBuilder.TB_NAME, UserInfo.USERMBID +"="+UserId, null);  
			Log.e("DelUserInfo",id+"");  
			return id;  
		}  
}  

