package com.wxz.five.bean;

import java.awt.Point;

import com.wxz.five.music.MusicList;
import com.wxz.five.util.WinUtil;

public class ComputerPlayer implements Runnable{
	private ChessTable table;
	private int PlayChessColor=0; //0:根据棋盘定  1：下棋黑 2，下白棋
	private static ComputerPlayer com=new ComputerPlayer();
	private  ComputerPlayer() {
		table=ChessTable.getInstance();
	}
	public static ComputerPlayer getInstance(){
		return com;
	}
	
	
	
	public int getPlayChessColor() {
		return PlayChessColor;
	}
	public void setPlayChessColor(int playChessColor) {
		PlayChessColor = playChessColor;
	}
	public boolean chessPlay(Chess chess) {
		//获取棋盘上应该下棋的颜色
		if(table.getPlayWayFlag()!=table.H_H&&!table.isGameOver()){
			int tc=Chess.WHITE_CHESS;
			if(table.getPlayColorFlag()){
				tc=Chess.BLACK_CHESS;
			}
			if(chess.getColor()==tc&&PlayChessColor==chess.getColor()){
				boolean b=table.playChessInTable(chess);
				return b;
			}
		}
		return false;
	}
	public Point getNestPoint(){
		int[][][] blacktable=table.getBlacktable();
		int[][][] whitetable=table.getWhitetable();
		int[][] tables=table.getTable();
		int minX=table.getMinChessX();
		int maxX=table.getMaxChessX();
		int minY=table.getMinChessY();
		int maxY=table.getMaxChessY();
		int x=0,y=0,maxScore=0;
		//自己先 下点 10,10
		if(table.getList().size()==0){
			return new Point(10,10);
		}
		int[] black=getTableMaxSc(blacktable);
		int[] white=getTableMaxSc(whitetable);
		
		//自己的棋子为四个 提权
		if(black[2]>=5000){
			if(PlayChessColor==Chess.BLACK_CHESS){
				black[2]=6000;
			}
		}
		if(white[2]>=5000){
			if(PlayChessColor==Chess.WHITE_CHESS){
				white[2]=6000;
			}
		}
		int[] temp=(black[2]>white[2]? black:white);
		
		System.out.println("maxScore:"+temp[2]);
		return  new Point(temp[0],temp[1]);
	}
	private int[] getTableMaxSc(int[][][] temptable){
		int minX=table.getMinChessX();
		int maxX=table.getMaxChessX();
		int minY=table.getMinChessY();
		int maxY=table.getMaxChessY();
		int[][] tables=table.getTable();
		int x=0,y=0,maxScore=0;
		for(int i=minX ;i<=maxX;i++){
			for(int j=minY;j<=maxY;j++){
				if(tables[i][j]==0){
					int tb=getSum(temptable[i][j]);
					if(maxScore<tb){
						maxScore=tb;
						x=i;
						y=j;
					}
				}
			}
		}
		int[] res={x,y,maxScore};
		return res;
	}
	
	private int getSum(int[] Sctable){
		int sum=0;
		for(int i=0;i<Sctable.length;i++){
			sum+=getSc(Sctable[i]);
		}
		
		return sum;
	}
	private int getSc(int count){
		int sc=0;
		switch(count){
			case 1:{sc=0;  break;}
			case 2:{sc=20; break;}
			case 3:{sc=100; break;}
			case 4:{sc=1000; break;}
			case 5:{sc=5000; break;}
			default: {sc=6000; break;}
		}
		return sc;
	}
	
	
	@Override
	public void run() {
		while(true){
			
			if(table.getPlayWayFlag()!=table.H_H&&!table.isGameOver()){
				//获取棋盘上应该下棋的颜色
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				int tc=Chess.WHITE_CHESS;
				if(table.getPlayColorFlag()){
					tc=Chess.BLACK_CHESS;
				}
				if(PlayChessColor==tc){
					Point p=getNestPoint();
					int x=(int)p.getX();
					int y=(int)p.getY();
					Chess chess=new Chess(x,y,PlayChessColor);
					boolean b=chessPlay(chess);
					
					if(b){
						
						MusicList.putVoice();
						//赢了就弹出窗口
						WinUtil.WinShow(table, chess);
					}
				}
			}else{
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
}
