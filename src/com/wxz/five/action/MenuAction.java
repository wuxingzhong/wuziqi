package com.wxz.five.action;

import javax.swing.JOptionPane;

import com.wxz.five.bean.Chess;
import com.wxz.five.bean.ChessTable;
import com.wxz.five.bean.ComputerPlayer;
import com.wxz.five.bean.PersonPlayer;

public class MenuAction {
	private static ChessTable table;
	static{
		table=ChessTable.getInstance();
	}
	public static void RestartAction(){
		//重新开始
		table.resetTable();
	}
	public static void RollbackAction(){
		//悔棋
		table.robackTable();
		if(table.getPlayWayFlag()!=table.H_H){
			table.robackTable();
		}
	}
	public static void RobotAction(){
		//人机对弈
		table.setPlayWayFlag(0);
		//重新开始
		table.resetTable();
		//开启
		ComputerPlayer com = ComputerPlayer.getInstance();
		com.setPlayChessColor(Chess.WHITE_CHESS);
		PersonPlayer.getInstance().setPlayChessColor(Chess.BLACK_CHESS);
		
	}
	public static void RobotAction1() {
		//人机对弈
		table.setPlayWayFlag(1);
		//重新开始
		table.resetTable();
		//开启
		ComputerPlayer com = ComputerPlayer.getInstance();
		com.setPlayChessColor(Chess.BLACK_CHESS);
		PersonPlayer.getInstance().setPlayChessColor(Chess.WHITE_CHESS);
	}                                                                                                                                                                                                                           
	public static void HumenAction(){
		//人人对弈
		table.setPlayWayFlag(2);
		//重新开始
		table.resetTable();
		PersonPlayer.getInstance().setPlayChessColor(0);
	}
	
	public static void RuleAction(){
		//规  则
		JOptionPane.showConfirmDialog(null,
      			"1、无禁手：" +"\n"+
				"   黑白双方依次落子，任一方先在棋盘上形成连续的五个(含五个以上)棋子的一方为胜。" +"\n"+
				"2、有禁手：（走禁手就输，禁手不能落子）" +"\n"+
				"   鉴于无禁手规则黑棋必胜，人们不断采用一些方法限制黑棋先行的优势，以平衡黑白双方的形式。" +"\n"+
				"   于是针对黑棋的各种禁手逐渐形成。" +"\n"+
				"   禁手主要分为以下几类：" +"\n"+
				"   (1)黑长连禁手：连成六个以上连续相同的棋子。" +"\n"+
				"   (2)黑三三禁手：两个以上的活三。" + "\n"+
				"   (3)黑四四禁手：两个以上的四。" + "\n"+
				"   禁手是针对黑棋而言的，白棋没有任何禁手。" ,"规则",
				JOptionPane.CLOSED_OPTION,
				JOptionPane.INFORMATION_MESSAGE);
	}
	public static void AboutAction(){
		//关  于
		JOptionPane.showConfirmDialog(null, 
				"作   者：\n" +
				"     吴兴中  \n" +
				"联系电话：\n" +
				"     13599840407\n", 
				"关  于", JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
	}
	
}
