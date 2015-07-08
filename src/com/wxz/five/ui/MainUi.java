package com.wxz.five.ui;


import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import com.wxz.five.bean.ChessTable;
import com.wxz.five.bean.ComputerPlayer;

public class MainUi {
	public static void main(String[] args) {
		JFrame  f=new JFrame("五子棋人机对战(算法设计by吴兴中)");
		JMenuBar menubar=new FiveMenuBar();
		//从棋盘中获取棋子 并设置棋子
		JPanel pannel=new ChessPanel(ChessTable.getInstance().getList());
		f.setJMenuBar(menubar);
		f.add(pannel);
		f.setLocation(300, 40);
		f.setSize(620, 660);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//开启电脑线程
		Thread th=new Thread(ComputerPlayer.getInstance());
		th.start();
		//主线程刷新界面
		while(true){
			f.repaint();
		}
	
	}
}
