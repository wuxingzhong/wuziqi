package com.wxz.five.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.wxz.five.action.MouseAction;
import com.wxz.five.bean.Chess;

@SuppressWarnings("serial")
public class ChessPanel extends JPanel{
  	private ImageIcon blackchess;			//黑子位图
  	private ImageIcon whitechess;			//白子位图
  	private List<Chess> list;              //存放棋子
  	private final static int SUMLINE_MAP=19; //棋盘总边数  
  	private final static int MAP_SIZE=540;
  	private int chess_size; //棋子大小
  	private final static int BOUND_SIZE=30; //边界宽度；
 	public ChessPanel(final List<Chess> list){
 		this.list=list;
 		blackchess=new ImageIcon("src\\img\\blackchess.GIF");
 		whitechess=new ImageIcon("src\\img\\whitechess.GIF");
 		chess_size=blackchess.getIconHeight();
 		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				super.mouseClicked(arg0);
				Point point=getMouseClickedPoint();
				//获取到的点不为空，则触发点击处理事件，并重新绘制
				if(point!=null){
					MouseAction.MouseClickedAction(point);
					ChessPanel.this.repaint();
				}
			}
		});
 	}
 	
 	@Override
 	public void paint(Graphics g) {
 		super.paint(g);
 		setBackground(new Color(90, 90, 40));
 		paintChessMap(g);
 		paintChess(g);
 	}
 	//画棋盘
 	private void paintChessMap(Graphics g){
 		
 		g.setColor(Color.black);
 		for(int i=0;i<SUMLINE_MAP;i++){
 			g.drawLine(BOUND_SIZE+i*chess_size, BOUND_SIZE, BOUND_SIZE+i*chess_size, MAP_SIZE+BOUND_SIZE);
 			g.drawLine(BOUND_SIZE,BOUND_SIZE+i*chess_size, MAP_SIZE+BOUND_SIZE, BOUND_SIZE+i*chess_size);
 		}
 		//画五个矩形 9,9  4,4 4,13 13,4 13,13 
 		g.fill3DRect(BOUND_SIZE-5+9*chess_size, BOUND_SIZE-5+9*chess_size, 10, 10,true);
 		g.fill3DRect(BOUND_SIZE-5+4*chess_size, BOUND_SIZE-5+4*chess_size, 10, 10,true);
 		g.fill3DRect(BOUND_SIZE-5+14*chess_size, BOUND_SIZE-5+4*chess_size, 10, 10,true);
 		g.fill3DRect(BOUND_SIZE-5+4*chess_size, BOUND_SIZE-5+14*chess_size, 10, 10,true);
 		g.fill3DRect(BOUND_SIZE-5+14*chess_size, BOUND_SIZE-5+14*chess_size, 10, 10,true);
 	}
 	//画棋子
 	private void paintChess(Graphics g){
 		int x=0;
 		int y=0;
 		int color=0;
 		if(list!=null&&list.size()!=0){
 			//解决java.util.ConcurrentModificationException异常
 			synchronized (list) {
	 			for(Chess chess:list){
	 				x=chess.getX();
	 				y=chess.getY();
	 				color=chess.getColor();
	 				if(color==Chess.BLACK_CHESS){
	 					blackchess.paintIcon(this,g, (int)(30+chess_size*(x-0.5)),(int)(30+chess_size*(y-0.5)));
	 				}else if(color==Chess.WHITE_CHESS){
	 					whitechess.paintIcon(this,g, (int)(30+chess_size*(x-0.5)),(int)(30+chess_size*(y-0.5)));
	 				}
	 			}
	 		}
 		}
 	}
 	
 	//获取鼠标点击的点,并转化为棋子在棋盘上的点 返回
 	private Point getMouseClickedPoint(){
 		
 				//获取鼠标点击的点
 				Point point=this.getMousePosition();
 				if(point!=null)
 				{
	 				int xx=(int)point.getX();
	 				int yy=(int)point.getY();
	 				int x=-1;
	 				int y=-1;
	 				for(int i=0;i<19;i++){
	 					if(30+i*30-6<=xx&&xx<=30+i*30+6){
	 						x=i;
	 					}
	 					if(30+i*30-6<=yy&&yy<=30+i*30+6){
	 						y=i;
	 					}
	 				}
	 				if(x!=-1&&y!=-1)
	 				{
	 					return new Point(x,y);
	 				}
 			  }
 			return null;	
 	}
 }

