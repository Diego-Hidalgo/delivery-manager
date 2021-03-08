package model;

public class Date {
	private int day;
	private int month;
	private int year;
	private int hour;
	private int minute;
	
	public Date(){
		day = 0;
		month = 0;
		year = 0;
		hour = 0;
		minute = 0;
	}//End constructor1
	public Date(int day, int month, int year, int hour, int minute){
		this.day = day;
		this.month = month;
		this.year = year;
		this.hour = hour;
		this.minute = minute;
	}//End constructor1
	public void setDay(int day){
		this.day = day;
	}//End setDay
	public void setMonth(int month){
		this.month = month;
	}//End setMonth
	public void setYear(int year){
		this.year = year;
	}//End setYear
	public void setHour(int hour){
		this.hour = hour;
	}//End setHour
	public void setMinute(int minute){
		this.minute = minute;
	}//End setMinute
	public int getDay(){
		return day;
	}//End getDay
	public int getMonth(){
		return month;
	}//End getDay
	public int getYear(){
		return year;
	}//End getDay
	public int getHour(){
		return hour;
	}//End getDay
	public int getMinute(){
		return minute;
	}//End getDay
	public String getDate(){
		String date = day + "/"+month+"/"+year;
		return date;
	}//End getDate
	public String getTime(){
		String time = hour+":"+minute;
		return time;
	}//End getDate
}//End date
