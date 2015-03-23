package com.example.mistletoe_mistletoesmb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

//SQLiteOpenHelper提供后面两个重要方法，一个用于初次创建数据库，一个用于更新数据库

//??????????????????????????updateColumn
public class SQLiteMistletoeDatabase extends SQLiteOpenHelper{
//用来保存   UserID、Access Token、Access Secret 的表名 	 
	public static final String TB_NAME="users";  
	public SQLiteMistletoeDatabase(Context context, String name, CursorFactory factory, int version) {  
	super(context, name, factory, version);  
	}  
	//创建表  
	@Override  
	public void onCreate(SQLiteDatabase db) {  
		db.execSQL("CREATE TABLE IF NOT EXISTS "+  
		TB_NAME+"("+  
		UserInfo.MDID+" integer primary key,"+  
		UserInfo.USERMBID+" varchar,"+  
		UserInfo.TOKEN+" varchar,"+  
		UserInfo.TOKENSECRET+" varchar,"+
		UserInfo.USERMBNAME+" varchar,"+  		
		UserInfo.USERMBICON+" blob"+  
		")"  
		);  
		Log.e("Database","onCreate");  
	}  
	//更新表  
	@Override  
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {  
		db.execSQL("DROP TABLE IF EXISTS " + TB_NAME);  
		onCreate(db);  
		Log.e("Database","onUpgrade");  
	}  
	//更新列  
	public void updateColumn(SQLiteDatabase db, String oldColumn, String newColumn, String typeColumn){  
		try{  
		db.execSQL("ALTER TABLE " +  
		TB_NAME + " CHANGE " +  
		oldColumn + " "+ newColumn +  
		" " + typeColumn  
		);  
		}catch(Exception e){  
		e.printStackTrace();  
		}  
	}  
}
