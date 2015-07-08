package com.wxz.five.bean;
/**
 * 棋子类
 */

public class Chess {
	public final static int BLACK_CHESS=1;
	public final static int WHITE_CHESS=2;
	private int x;
	private int y;
	private int color; //1  BLACK_CHESS 黑色  2  WHITE_CHESS白色
	
	public Chess(int x, int y, int color) {
		super();
		this.x = x;
		this.y = y;
		this.color = color;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}

	public int getColor() {
		return color;
	}

	
}
