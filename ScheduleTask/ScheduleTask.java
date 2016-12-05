package com.cooltrickshome;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class ScheduleTask {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("Program started at "+new Date());
		Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE,1);
        
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
            	System.out.println("Task started at "+new Date());
            	System.out.println("Hello !!!");
            }
          };
          
          runTask(calendar,task);
	}

	public static void runTask(Calendar calendar, TimerTask task)
	{
		Timer time = new Timer(); 
        time.schedule(task, calendar.getTime(), TimeUnit.SECONDS.toMillis(15));
	}
	
}
