package sait.mms.problemdomain;

public class Movie {

	public int duration;
	public String title;
	public int year;
	
	public Movie(int duration, String title, int year) {
		this.duration = duration;
		this.title = title;
		this.year = year;
	}
	
	public int getYear() {
		return year;
	}
	
	public int getDuration() {
		return duration;
	}
	
	public String getTitle() {
		return title;
	}

	public String toString(){
		return(this.duration + "," + this.title + "," + this.year);
	}

}
