package com.tools;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foscare.model.FosterService;
import com.foscare.model.FosterVO;
import com.gat.model.GatService;


@WebServlet("/ScheduleServlet3")
public class ScheduleServlet_gat extends HttpServlet {
	//將時間到的寄養單修改狀態成F7
	Timer timer = null;
	TimerTask task = null;
	public void destroy() {
		timer.cancel();
	}
	public void init() {
		timer = new Timer();
		Calendar cal = Calendar.getInstance();
		int y = cal.get(Calendar.YEAR);
		int m = cal.get(Calendar.MONTH);
		int d = cal.get(Calendar.DATE);
		Calendar cal2 = new GregorianCalendar(y, m, d, 11, 0, 0);
		
		task = new MyTimerTask();
		timer.scheduleAtFixedRate(task, cal2.getTime(), 1*60*1000);	 
	}
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
private class MyTimerTask extends TimerTask{

	int count = 0;
	@Override
	public void run() {
		GatService gatSvc = new GatService();
		System.out.println("This is Task_GAT" + count);
		gatSvc.autoStatus();
		count++;
	}
	}
}
