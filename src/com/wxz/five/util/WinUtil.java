package com.wxz.five.util;

import javax.swing.JOptionPane;

import com.wxz.five.bean.Chess;
import com.wxz.five.bean.ChessTable;
import com.wxz.five.music.MusicList;

public class WinUtil {

	public static void WinShow(ChessTable table,Chess chess){
		if(table.isVictory(chess)){
			MusicList.winVoice();
			if(chess.getColor()==Chess.BLACK_CHESS){
				JOptionPane.showConfirmDialog(null, 
					"黑棋赢了！！。。白方继续努力。 ", "结     果", JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
			
			}else{
				JOptionPane.showConfirmDialog(null, 
				 "白棋赢了！！。。黑方继续努力。 ", "结     果", JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
			}
			
		}
	}
}
