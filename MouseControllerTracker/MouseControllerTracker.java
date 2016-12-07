package com.cooltrickshome;

import java.awt.AWTException;
import java.awt.FlowLayout;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.event.InputEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class MouseControllerTracker{

	JLabel coordinate=null;
	final static int frameWidth=500;
	final static int frameHeight=500;
	static int frameStartPosX=0;
	static int frameStartPosY=0;
	static int frameEndPosX=0;
	static int frameEndPosY=0;
	static int mouseRecenterPosX=0;
	static int mouseRecenterPosY=0;
	
	public static void main(String[] args) {
		
		try {
		Robot r=new Robot();
		MouseControllerTracker mt=new MouseControllerTracker();
		JFrame f=mt.showFrame(frameWidth,frameHeight);
		mt.trackFrame(f);
		mt.moveFrameUsingMouse(r);
		while(true)
		{
			mt.trackMouse(r);
		}
		} catch (AWTException e) {
			System.out.println("There was an issue while instantiating Robot class "+e.getMessage());
		}
	}

	public JFrame showFrame(int frameWidth, int frameHeight)
	{
		JFrame f=new JFrame();
		coordinate=new JLabel();
		f.add(coordinate);
		f.setSize(frameWidth, frameHeight);
		f.setLayout(new FlowLayout(0));
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		return f;
	}
	
	public void trackFrame(JFrame f)
	{
		Point p=f.getLocationOnScreen();
		frameStartPosX=p.x;
		frameStartPosY=p.y;
		frameEndPosX=frameStartPosX+frameWidth;
		frameEndPosY=frameStartPosY+frameHeight;
		
		mouseRecenterPosX=(frameStartPosX+frameEndPosX)/2;
		mouseRecenterPosY=(frameStartPosY+frameEndPosY)/2;
	}

	public void moveFrameUsingMouse(Robot robot)
	{
		robot.mouseMove(mouseRecenterPosX, frameStartPosY+10);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		for(int i=0;i<200;i++){
			robot.mouseMove(mouseRecenterPosX+i, mouseRecenterPosY+i);
			robot.delay(10);
		}
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}
	
	public void trackMouse(Robot r)
	{
		PointerInfo inf = MouseInfo.getPointerInfo();
		Point p = inf.getLocation();
		coordinate.setText("Mouse moved to ("+p.x+","+p.y+")");
	}
	
}
