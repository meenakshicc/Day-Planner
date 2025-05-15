// TO DO: add your implementation and JavaDocs.
/**
 * MyTime implements the Comparable interface that has the minute and hour for a time.
 */
public class MyTime implements Comparable<MyTime> {

	// Hour and minute of a time.
	/**
	 * Hour int.
	 */
	private int hour;
	/**
	 * Min int.
	 */
	private int min;
	
	/**
	 * Default Constructor for the MyTime class that sets the time to 00 hour and 00 min.
	 */
	public MyTime(){
		// Constructor
		// initialize time to be 00:00
		this.hour = 00;
		this.min = 00;
		
	}
	
	/**
	 * Constructor for the MyTime class that sets the hour to the param's value.
	 * @param hour for the hour variable.
	 */
	public MyTime(int hour){
		// Constructor with hour specified
		// initialize time to be hour:00
		
		// A valid hour can only be within [0, 23].
		// For an invalid hour, throw IllegalArgumentException.
		// Use this _exact_ error message for the exception 
		//  (quotes are not part of the message):
		// "Hour must be within [0, 23]!"
		
		if ((hour >= 0) && (hour <= 23)) {
			this.hour = hour;
			this.min = 00;
		}
		else {
			throw new IllegalArgumentException("Hour must be within [0, 23]!");
		}
	}
	
	/**
	 * Constructor for the MyTime class that sets the hour and min to the param's values.
	 * @param hour for the hour variable.
	 * @param min for the min variable.
	 */
	public MyTime(int hour, int min){
		// Constructor with hour and minutes specified
		// initialize time to be hour:minute

		// A valid hour can only be within [0, 23].
		// A valid minute can only be within [0, 59].

		// For an invalid hour / minute, throw IllegalArgumentException.
		// Use this _exact_ error message for the exception 
		//  (quotes are not part of the message):
		// "Hour must be within [0, 23]; Minute must be within [0, 59]!");
		
		if (((hour >= 0) && (hour <= 23)) && ((min >= 0) && (min <= 59))) {
			this.hour = hour;
			this.min = min;
		}
		else {
			throw new IllegalArgumentException("Hour must be within [0, 23]; Minute must be within [0, 59]!");
		}
	}
	
	/**
	 * Getter method for the hour variable.
	 * @return int of the hour.
	 */
	public int getHour(){
		// report hour
		
		return this.hour; //default return, remove/change as needed
	}

	/**
	 * Getter method for the min variable.
	 * @return int of the min.
	 */
	public int getMin(){
		// report minute
		
		return this.min; //default return, remove/change as needed
	}
	
	/**
	 * Method that compares the current MyTime to the param's MyTime and returns the number based on the ordering. 
	 * @param otherTime to be compared to the this.MyTime.
	 */
	@Override 
	public int compareTo(MyTime otherTime){
		// compare two times for ordering
		// return the value 0 if the argument Time has the same hour and minute of this Time;
		// return a value less than 0 if this Time is before the otherTime argument; 
		// return a value greater than 0 if this Time is after the otherTime argument.
		
		// Throw IllegalArgumentException if otherTime is null. 
		// - Use this _exact_ error message for the exception 
		//  (quotes are not part of the message):
		//    "Null Time object!"
		if (otherTime == null) {
			throw new IllegalArgumentException("Null Time object!");
		}
		
		int value = 0;
		if ((this.hour == otherTime.getHour()) && (this.min == otherTime.getMin())) {
			value = 0;
		}
		else if ((this.hour < otherTime.getHour()) || ((this.hour == otherTime.getHour()) && (this.min < otherTime.getMin()))) {
			value = -1;
		}
		else if ((this.hour > otherTime.getHour()) || ((this.hour == otherTime.getHour()) && (this.min > otherTime.getMin()))) {
			value = 1;
		}
		
		return value; //default return, remove/change as needed
	}
	
	/**
	 * Method gets the duration from this.MyTime and the endTime.
	 * @param endTime to get the duration.
	 * @return int of the duration.
	 */
	public int getDuration(MyTime endTime){
		// return the number of minutes starting from this Time and ending at endTime
		// return -1 if endTime is before this Time
	
		// Throw IllegalArgumentException if endTime is null. 
		// - Use this _exact_ error message for the exception 
		//  (quotes are not part of the message):
		//    "Null Time object!"
		int duration = 0;
		if (endTime == null) {
			throw new IllegalArgumentException("Null Time object!");
		}
		else if ((this.hour > endTime.getHour()) || ((this.hour == endTime.getHour()) && (this.min > endTime.getMin()))) {
			duration = -1;
		}
		else if (this.hour == endTime.getHour()) {
			duration += endTime.getMin() - this.min;
		}
		else if (this.hour < endTime.getHour()) {
			int hours = endTime.getHour() - this.hour;
			duration += (hours * 60) + (endTime.getMin() - this.min);
		}
		return duration; //default return, remove/change as needed		
	}
	
	/**
	 * Method returns the EndTime based on the addition of param's value and this.MyTime.
	 * @param duration for the activity.
	 * @return MyTime, which is the EndTime.
	 */
	public MyTime getEndTime(int duration){
		// return a Time object that is duration minute from this Time
		
		// Throw IllegalArgumentException if duration is negative. 
		// Use this _exact_ error message for the exception 
		//  (quotes are not part of the message):
		// "Duration must be non-negative!"			
		
		// return null if endTime passes 23:59 given this Time and duration argument
		MyTime newtime;
		if (duration < 0) {
			throw new IllegalArgumentException("Duration must be non-negative!");
		}
		int mins = duration % 60;
		int hours = (duration - mins)/60;
		int newhours = this.getHour() + hours;
		int newmins = this.getMin() + mins;
		if ((newhours > 23) || ((newhours == 23) && (newmins > 59))) {
			return null;
		}
		else {
			if (newmins >= 60) {
				int mins2 = newmins % 60;
				int hours2 = (newmins - mins2)/60;
				if (newhours + hours2 <= 23) {
					newhours += hours2;
					newmins = mins2;
				}
			}
			newtime = new MyTime(newhours, newmins);
		}
		return newtime; //default return, remove/change as needed	
	}

	/**
	 * Method returns the string version of the array and prints the contents and variables.
	 * @return String of the array.
	 */
	public String toString() {
		// return a String representation of this object in the form of hh:mm
		// hh is the hour of the time (00 through 23), as two decimal digits
		// mm is the minute of the time (00 through 59), as two decimal digits
		
		// Hint: String.format() can be helpful here...
		String hourr;
		String minn;
		if (this.hour < 10) {
			hourr = "0"+this.hour; 
		}
		else {
			hourr = ""+this.hour;
		}
		if (this.min < 10) {
			minn = "0"+this.min;
		}
		else {
			minn = ""+this.min;
		}
		String result = "" + hourr + ":" + minn;
		
		return result; //default return, remove/change as needed		
	}
}