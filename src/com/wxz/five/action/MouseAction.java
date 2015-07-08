package com.wxz.five.action;
import java.awt.Point;
import com.wxz.five.bean.Chess;
import com.wxz.five.bean.ChessTable;
import com.wxz.five.bean.PersonPlayer;
import com.wxz.five.music.MusicList;
import com.wxz.five.util.WinUtil;

public class MouseAction {
	
	public static void MouseClickedAction(Point point) {
		ChessTable table=ChessTable.getInstance();
		PersonPlayer play=PersonPlayer.getInstance();
		if(!table.isGameOver()){
			int tc=Chess.WHITE_CHESS;
			if(table.getPlayColorFlag()){
				tc=Chess.BLACK_CHESS;
			}
			int x=(int)point.getX();
			int y=(int)point.getY();
			Chess chess=new Chess(x,y,tc);
			boolean b=play.chessPlay(chess);
			if(b){
				MusicList.putVoice();
				WinUtil.WinShow(table, chess);
			}
		}
			
	}
	
}
