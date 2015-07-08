package com.wxz.five.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChessTable {
	private final static int TABLE_SIZE=19; 
	private int[][] table=new int[TABLE_SIZE][TABLE_SIZE]; //0:空   1：黑  2：白
	public final static int H_H=2;    //人人对弈
	public final static int Hb_Rw=0;  //人机对弈 人黑机白
	public final static int Hw_Rb=1;  //人机对弈  人白机黑
	private int  playWayFlag=0;        //下棋方式(人机或者人人) 
	private int[][][] blacktable=new int [TABLE_SIZE][TABLE_SIZE][4];  //0：空  1：有
	private int[][][] whitetable=new int [TABLE_SIZE][TABLE_SIZE][4];   //[4] 为四个方向   0:左上   1上   2右上   3右 
	private List<Chess> list;
	private boolean playColorFlag=true; //下棋颜色true:黑色     false:白色 
	private boolean gameOver=false;    //是否结束游戏
	private int maxChessX=12; 		//已下棋子的最大x和y值
	private int maxChessY=12;
	private int minChessX=6;
	private int minChessY=6;
	private static ChessTable chtable=new ChessTable();
	private ChessTable() {
		list=new ArrayList<Chess>();
	}
	

	public int getMaxChessX() {
		return maxChessX;
	}

	public int getMaxChessY() {
		return maxChessY;
	}

	public int getMinChessX() {
		return minChessX;
	}


	public int getMinChessY() {
		return minChessY;
	}

	public int getPlayWayFlag() {
		return playWayFlag;
	}

	public void setPlayWayFlag(int playWayFlag) {
		this.playWayFlag = playWayFlag;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public void ChangcePlayColorFlag() {
		this.playColorFlag=playColorFlag^true;
	}

	public void setPlayColorFlag(boolean playColorFlag) {
		this.playColorFlag = playColorFlag;
	}

	public boolean getPlayColorFlag() {
		return playColorFlag;
	}

	public int[][] getTable() {
		return table;
	}

	public List<Chess> getList() {
		return list;
	}

	public int[][][] getBlacktable() {
		return blacktable;
	}


	public int[][][] getWhitetable() {
		return whitetable;
	}

	//单例设计 获取棋盘信息
	public static ChessTable getInstance(){
		return chtable;
	}
	
	//往棋盘中下棋
	public boolean playChessInTable(Chess chess){
		int x=chess.getX();
		int y=chess.getY();
		int color=chess.getColor();
		//该地方没有棋子 可以下
		if(table[x][y]==0){
			//棋子加到棋子的列表中;
			//解决java.util.ConcurrentModificationException异常
 			synchronized (list) {
 				list.add(chess);
			}
			//修改棋盘信息
			table[x][y]=color;
			//修改所下颜色棋的标志
			ChangcePlayColorFlag();
			//更新最大XY值
			updateXY(chess);
			//更新得分表
			updateScore();
			return true;
		}
		return false;
	}
	//更新最大的x和y值
	private void updateXY(Chess chess){
		if(maxChessX<=chess.getX()){
			if(chess.getX()+2<=18){
				maxChessX=chess.getX()+1;
			}else if(chess.getX()+1<=18){
					maxChessX=chess.getX()+1;
					}else{
						maxChessX=chess.getX();
					}
		}
		if(maxChessY<=chess.getY()){
			if(chess.getY()+2<=18){
				maxChessY=chess.getY()+2;
			}else if(chess.getY()+1<=18){
					maxChessY=chess.getY()+1;
					}else{
						maxChessY=chess.getY();
						}
		}
		if(minChessX>=chess.getX()){
			if(chess.getX()-2>=0){
				minChessX=chess.getX()-2;
			}else if(chess.getX()-1>=0){
					minChessX=chess.getX()-1;
				}else{
					minChessX=chess.getX();
				}
		}
		if(minChessY>=chess.getY()){
			if(chess.getY()-2>=0){
				minChessY=chess.getY()-2;
			}else if(chess.getY()-1>=0){
					minChessY=chess.getY()-1;
				}else{
					minChessY=chess.getY();
				}
		}
	}
	//计算最大x+1，y+1范围内的所有没放棋子的边界值
	private void updateScore(){
		int[] countb=new int[4];
		int[] countw=new int[4];
		for(int i=minChessX ;i<=maxChessX;i++){
			for(int j=minChessY;j<=maxChessY;j++){
				if(table[i][j]==0){
					countb=CalcMaxValue(new Chess(i,j,Chess.BLACK_CHESS));
					countw=CalcMaxValue(new Chess(i,j,Chess.WHITE_CHESS));
					for(int k=0;k<4;k++){
						blacktable[i][j][k]=countb[k];
						whitetable[i][j][k]=countw[k];
					}
				}
			}
		}
	}
	//重置棋盘
	public void resetTable(){
		list.clear();
		for(int i=0;i<19;i++){
			for(int j=0;j<19;j++){
				table[i][j]=0;
				for(int k=0;k<4;k++){
					blacktable[i][j][k]=0;
					whitetable[i][j][k]=0;
				}
			}
		}
		setPlayColorFlag(true);
		setGameOver(false);
	}
	//悔棋 撤销上一步
	public void robackTable(){
		if(list.size()!=0){
			Chess chess=list.remove(list.size()-1);
			//可以撤销
			if(chess!=null)
			{	
				int x=chess.getX();
				int y=chess.getY();
				int color=chess.getColor();
				//修改棋盘信息
				table[x][y]=0;
				ChangcePlayColorFlag();
				setGameOver(false);
			}
		}
	}
	//判断胜负 ，下完一个棋子，判断一下胜负
	public boolean isVictory(Chess chess){
		int[] count=CalcMaxValue(chess);
		//返回最大值
		Arrays.sort(count);
		int max=count[3];
		if(max>=5){
			setGameOver(true);
			return true;
		}
		return false;
	}
	//下完一个棋子后获得该棋子的四个方向的棋子值
	private int[] CalcMaxValue(Chess chess){
		int x=chess.getX();
		int y=chess.getY();
		int color=chess.getColor();
		int[] count={1,1,1,1};
		//左上
		int tx=x-1;
		int ty=y-1;
		while(tx>=0&&ty>=0&&table[tx][ty]==color){
			count[0]++;
			tx--;
			ty--;
		}
		//右下
		tx=x+1;
		ty=y+1;
		while(tx<=18&&ty<=18&&table[tx][ty]==color){
			count[0]++;
			tx++;
			ty++;
		}
		//上
		tx=x;
		ty=y-1;
		while(ty>=0&&table[tx][ty]==color){
			count[1]++;
			ty--;
		}
		//下
		tx=x;
		ty=y+1;
		while(ty<=18&&table[tx][ty]==color){
			count[1]++;
			ty++;
		}	
		//右上
		tx=x+1;
		ty=y-1;
		while(tx<=18&&ty>=0&&table[tx][ty]==color){
			count[2]++;
			tx++;
			ty--;
		}
		//左下
		tx=x-1;
		ty=y+1;
		while(tx>=0&&ty<=18&&table[tx][ty]==color){
			count[2]++;
			tx--;
			ty++;
		}
		//右
		tx=x+1;
		ty=y;
		while(tx<=18&&table[tx][ty]==color){
			count[3]++;
			tx++;
		}
		//左
		tx=x-1;
		ty=y;
		while(tx>=0&&table[tx][ty]==color){
			count[3]++;
			tx--;
		}
		return count;
	}
}
