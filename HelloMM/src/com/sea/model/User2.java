package com.sea.model;

import java.io.Serializable;

import android.os.Parcel;
import android.os.Parcelable;

public class User2 implements Parcelable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String UserName;
	private String PassWord;
	
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getPassWord() {
		return PassWord;
	}
	public void setPassWord(String passWord) {
		PassWord = passWord;
	}
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(UserName);
		dest.writeString(PassWord);
	}
	
	public static Parcelable.Creator<User2> CREATOR = new Creator<User2>() {

		@Override
		public User2 createFromParcel(Parcel source) {
			User2 user = new User2();
			user.setUserName(source.readString());
			user.setPassWord(source.readString());
			return user;
		}

		@Override
		public User2[] newArray(int size) {
			// TODO Auto-generated method stub
			return null;
		}
		
	};
	
}
