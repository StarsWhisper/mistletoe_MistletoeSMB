package com.example.mistletoe_mistletoesmb;

import com.example.mistletoe_mistletoesmb.R.string;

public class MistletoeSMBContentsKeeper {
	private String weiboID;
	private String userID;
	private String createdTime;
	private String contents;
	private String userName; 
	private String userIcon;	  
	private Boolean haveImage=false;
	
	public String getweiboID(){  
		return weiboID;  
	}  

	public void setweiboID(String weiboID){  
		this.weiboID=weiboID;  
	}  	  

	public String getUserId(){  
		return userID;  
	}  

	public void setUserId(String userID){  
		this.userID=userID;  
	}	

	public String getUserName(){  
		return userName;  
	}  

	public void setUserName(String userName){  
		this.userName=userName;  
	}  	  

	public String getUserIcon(){  
		return userIcon;  
	} 

	public void setUserIcon(String userIcon){  
		this.userIcon=userIcon;  
	} 

	public String getTime(){  
		return createdTime;  
	}  

	public void setTime(String createdTime)  
	{  
		this.createdTime=createdTime;  
	}  	  	
	
	public Boolean getHaveImage(){  
		return haveImage;  
	}  
	
	public void setHaveImage(Boolean haveImage){  
		this.haveImage=haveImage;  
	}  	  

	public String getContents(){  
		return contents;  
	}  

	public void setContents(String contents){  
		this.contents=contents;  
	}  	
}
