// TO DO: add your implementation and JavaDocs.
/**
 * Event implements the Comparable interface and creates an event with startTime and endTime.
 */
public class Event implements Comparable<Event> {

	//starting and ending time of the event
	/**
	 * MyTime startTime of an Event.
	 */
	private MyTime startTime;	
	/**
	 * MyTime endTime of an Event.
	 */
	private MyTime endTime;
		
	//description of the event
	/**
	 * String description of an Event.
	 */
	private String description;
	
	/**
	 * Constructor for the Event class that sets the startTime and endTime to the param's values. 
	 * @param startTime of an Event.
	 * @param endTime of an Event.
	 */
	public Event(MyTime startTime, MyTime endTime){
		// constructor with start and end times
		// set description to be empty string ""
		
		// Throw IllegalArgumentException if endTime comes before startTime
		// - Use this _exact_ error message for the exception 
		//  (quotes are not part of the message):
		//        "End Time cannot come before Start Time!"
		// - Assume that the start time can be the same as the end time 
		//   (0-duration event allowed)

		// Throw IllegalArgumentException if either time is null. 
		// - Use this _exact_ error message for the exception 
		//  (quotes are not part of the message):
		//    "Null Time object!"
		
		if (endTime.compareTo(startTime) < 0) {
			throw new IllegalArgumentException("End Time cannot come before Start Time!");
		}
		if ((startTime == null) || (endTime == null)) {
			throw new IllegalArgumentException("Null Time object!");
		}
		this.startTime = startTime;
		this.endTime = endTime;
		this.description = "";
	}
	
	/**
	 * Constructor for the Event class that sets the startTime, endTime, and description to the param's values. 
	 * @param startTime of an Event.
	 * @param endTime of an Event.
	 * @param description of an Event.
	 */
	public Event(MyTime startTime, MyTime endTime, String description){
		// constructor with start time, end time, and description
		
		// perform the same checking of start/end times and 
		// throw the same exception as the constructor above
		
		// if description argument is null, 
		// set description of the event to be empty string ""
		if (endTime.compareTo(startTime) < 0) {
			throw new IllegalArgumentException("End Time cannot come before Start Time!");
		}
		if ((startTime == null) || (endTime == null)) {
			throw new IllegalArgumentException("Null Time object!");
		}
		this.startTime = startTime;
		this.endTime = endTime;
		if (description != null) {
			this.description = description;
		}
		else {
			this.description = "";
		}
	}
	
	/**
	 * Getter method for the startTime.
	 * @return MyTime startTime.
	 */
	public MyTime getStart(){
		// report starting time
		
		return this.startTime; //default return, remove/change as needed
	}
	
	/**
	 * Getter method for the endTime.
	 * @return MyTime endTime.
	 */
	public MyTime getEnd(){
		// report ending time

		return this.endTime; //default return, remove/change as needed
	}

	/**
	 * Getter method for the Description.
	 * @return String description.
	 */
	public String getDescription(){
		// report description
		
		return this.description; //default return, remove/change as needed
	}
	
	/**
	 * Method that compares the current Event to the param's Event and returns the number based on the ordering. 
	 * @param otherEvent to be compared.
	 * @return int for the ordering.
	 */
	@Override 
	public int compareTo(Event otherEvent){
		// compare two times for ordering
		
		// The ordering of two events is the same as the ordering of their start times
	
		// Throw IllegalArgumentException if otherEvent is null. 
		// - Use this _exact_ error message for the exception 
		//  (quotes are not part of the message):
		//    "Null Event object!"
		if (otherEvent == null) {
			throw new IllegalArgumentException("Null Event object!");
		}
		int value = 0;
		if (this.startTime.compareTo(otherEvent.startTime) < 0) {
			value = -1;
		}
		else if (this.startTime.compareTo(otherEvent.startTime) > 0) {
			value = 1;
		}
		else if (this.startTime.compareTo(otherEvent.startTime) == 0) {
			value = 0;
		}
		return value; //default return, remove/change as needed

	}

	/**
	 * Method moves/changes the startTime to the param's MyTime and changing the endTime accordingly with the same duration. 
	 * @param newStart to be moved
	 * @return boolean if the startTime is properly moved
	 */
	public boolean moveStart(MyTime newStart){
		// Move the start time of this Event to be newStart but keep the same duration. 
		// - Remember to update the end time to ensure duration unchanged.
		
		// The start time can be moved forward or backward but the end time cannot 
		// go beyond 23:59 of the same day.  Do not update the event if this condition
		// cannot be satisfied and return false.  Return false if newStart is null. 
		
		// Return true if the start time can be moved to newStart successfully.
		
		// Note: a false return value means the specified newStart can not be used 
		//       for the current event.  Hence if newSart is the same as the current 
		//       start, we will still return true.
		if (newStart == null) {
			return false;
		}
		if (this.startTime.compareTo(newStart) == 0) {
			return true;
		}
		boolean moved = false;
		MyTime newTime = new MyTime(23,59);
		int duration = this.startTime.getDuration(endTime);
		
		if ((newStart.getEndTime(duration) == null) || (newStart.getEndTime(duration).compareTo(newTime) > 0) || (newStart.compareTo(newTime) == 0)) {
			moved = false;
		}
		else {
			this.startTime = newStart;
			this.endTime = this.startTime.getEndTime(duration);
			moved = true;
		}
		return moved; //default return, remove/change as needed
	}
	
	/**
	 * Method changes the duration of the Event and changes the endTime accordingly.
	 * @param minute for the duration
	 * @return boolean whether the new duration causes the endTime to exceed 23:59. 
	 */
	public boolean changeDuration(int minute){
		// Change the duration of event to be the given number of minutes.
		// Update the end time of event based on the updated duration.	
			
		// The given minute cannot be negative; and the updated end time cannot go 
		// beyond 23:59 of the same day.  Do not update the event if these conditions
		// cannot be satisfied and return false.  
		// Return true if the duration can be changed.
		
		// Note: a false return value means the specified duration is invalid for some 
		// 		 reason.  Hence if minute argument is the same as the current duration, 
		//       we will still return true.
		if ((minute == 0) || (minute < 0)) {
			return false;
		}
		if (minute == this.startTime.getDuration(endTime)) {
			return true;
		}
		boolean changed = false;
		MyTime newend = this.startTime.getEndTime(minute);
		MyTime newTime = new MyTime(23,59);
		if ((newend == null) || (newend.compareTo(newTime) > 0)) {
			changed = false;
		}
		else {
			this.endTime = newend;
			changed = true;
		}
		return changed; //default return, remove/change as needed
	
	}
	
	/**
	 * Method sets the description of the Event to the param's value.
	 * @param newDescription for the Event's description.
	 */
	public void setDescription(String newDescription){
		// set the description of this event

		// if newDescription argument is null, 
		// set description of the event to be empty string ""
		if (newDescription == null) {
			this.description = "";
		}
		else {
			this.description = newDescription;
		}
	
	}
	
	/**
	 * Method returns the string representation of the Event.
	 * @return String of the Event.
	 */
	public String toString(){
		// return a string representation of the event in the form of
		// startTime-endTime/description
		// example: "06:30-07:00/breakfast"

		// Hint: String.format() can be helpful here...
		
		// The format of start/end times is the same as .toString() of MyTime
		
		String result = String.format("%s-%s/%s", this.startTime, this.endTime, this.description);

		return result; //default return, remove/change as needed
	
	}
}