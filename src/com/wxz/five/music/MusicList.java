package com.wxz.five.music;

import java.applet.AudioClip;
import java.net.URL;

public class MusicList {
	//-------声音                               
	private static AudioClip soundPut;
	private static AudioClip soundWin;
	private static AudioClip soundLost;
	static{
		//Class clazz=MusicList.class; 
		String[] choics = { "put.wav", "win.wav","lost.wav" }; //声音文件名数组
		 URL file1 =MusicList.class.getResource(choics[0]); //落子声音文件
	 	 URL file2 =MusicList.class.getResource(choics[1]); //获胜声音文件
	 	 URL file3 = MusicList.class.getResource(choics[2]); //失败声音文件
		soundPut = java.applet.Applet.newAudioClip(file1); //落子声音剪辑对象
		soundWin = java.applet.Applet.newAudioClip(file2); //获胜声音剪辑对象
		soundLost = java.applet.Applet.newAudioClip(file3); //失败声音剪辑对象
	}
	
	  
	  //落子声音
	  public static void putVoice(){
			soundPut.play();     
	  }
	  //获胜声音
	  public static void winVoice(){
		   soundWin.play();
	  }
	  //失败声音
	  public static void lostVoice(){
		  soundLost.play();
	  }
	  
}
