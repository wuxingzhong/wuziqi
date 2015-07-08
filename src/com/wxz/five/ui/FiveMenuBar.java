package com.wxz.five.ui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import com.wxz.five.action.MenuAction;
@SuppressWarnings("serial")
public class FiveMenuBar extends JMenuBar{
	
	private JMenu[] menu={new JMenu("开始"),new JMenu("设置"),new JMenu("帮助")};
	private JMenuItem[] menuitem1={new JMenuItem("重新开始"),new JMenuItem("悔棋"),new JMenuItem("退出")};
	private JMenuItem[] menuitem2={new JMenuItem("人机博弈(人黑 机白)"),new JMenuItem("人机博弈(人白 机黑)"),new JMenuItem("人人对弈")};
	private JMenuItem[] menuitem3={new JMenuItem("规则"),new JMenuItem("关于")};
	public FiveMenuBar() {
		init();
	}
	private void init(){
		//添加到菜单中
		for(int i=0;i<3;i++)
			menu[0].add(menuitem1[i]);
		for(int i=0;i<3;i++)
			menu[1].add(menuitem2[i]);
		for(int i=0;i<2;i++)
			menu[2].add(menuitem3[i]);
		for(int i=0;i<3;i++)
			this.add(menu[i]);
		addMenuAction();
	
	}
	
	private void addMenuAction(){
		menuitem1[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("重新开始");
				MenuAction.RestartAction();
				Container p = FiveMenuBar.this.getParent();
				p.repaint();
			}
		});
		menuitem1[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("悔棋");
				MenuAction.RollbackAction();
				Container p = FiveMenuBar.this.getParent();
				p.repaint();
			}
		});
		menuitem1[2].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("退出！");
				System.exit(1);
			}
		});

		menuitem2[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("人机对弈0");
				MenuAction.RobotAction();
				Container p = FiveMenuBar.this.getParent();
				p.repaint();
			}
		});
		menuitem2[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("人机对弈1");
				MenuAction.RobotAction1();
				Container p = FiveMenuBar.this.getParent();
				p.repaint();
			}
		});
		menuitem2[2].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("人人对弈");
				MenuAction.HumenAction();
				Container p = FiveMenuBar.this.getParent();
				p.repaint();
			}
		});
		menuitem3[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("规则");
				MenuAction.RuleAction();
				Container p = FiveMenuBar.this.getParent();
				p.repaint();
			}
		});
		menuitem3[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("关于");
				MenuAction.AboutAction();
				Container p = FiveMenuBar.this.getParent();
				p.repaint();
			}
		});
		
	}
	
}
