package com.cooltrickshome;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class SetBackgroundJFrame extends JFrame{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		JFrame frame=new JFrame("My frame");
		JLabel background=setBackground(frame, "background.png");
		
		/**
		 * Your usual work on the frame
		 */
		JLabel l1=new JLabel("Enter your name");
		JTextField t1=new JTextField(10);
		JButton b1=new JButton("Submit");
		
		background.add(l1);
		background.add(t1);
		background.add(b1);
		
		frame.setTitle("My Application");
		frame.setSize(640,480);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public static JLabel setBackground(JFrame frame, String backgroundFilePath)
	{
		frame.setLayout(new BorderLayout());
		JLabel background=new JLabel(new ImageIcon(backgroundFilePath));
		frame.add(background);
		background.setLayout(new FlowLayout(0));
		return background;
	}
	
}
