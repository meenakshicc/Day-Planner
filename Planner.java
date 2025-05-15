// TO DO: add your implementation and JavaDocs.
/**
 * Planner class that has a collection of Events in ascending order of their starting times.
 */
public class Planner{

	// DO NOT MODIFY INSTANCE VARIABLES PROVIDED BELOW
	
	//underlying array of events  -- you MUST use this for credit!
	//Do NOT change the name or type

	/**
	 * events in a MySortedArray array.
	 */
	private MySortedArray<Event> events;
	
	// ADD MORE PRIVATE MEMBERS HERE IF NEEDED!
	
	/**
	 * Default Constructor for the Planner class that creates the array of events with the initial capacity to be default capacity.
	 */
	public Planner(){
		// Constructor with no arguments.
		
		// A list of events should be created.  The initial capacity should be 
		// DEFAULT_CAPACITY defined in our MySortfgrdsedArray class. 
		// The list should be empty (with no events).
		this.events = new MySortedArray<Event>();
	}

	/**
	 * Returns the size of the events' array.
	 * @return int of the size of the array.
	 */
	public int size(){
		// return the number of events in the list.
		// O(1)
		
		return events.size(); //default return, remove/change as needed
	}
	
	/**
	 * Method returns the string representation of the Planner class.
	 * @return String of the events' array.
	 */
	public String toString(){
		// return the string representation of the planner with this format:
		// - include all events in the list in ascending order of the indexes;
		// - each event goes into a separate line;
		// - each line except for the last uses this format (quotes excluded): "[index]event\n"
		// - the last line does not end with a new line and uses this format: "[index]event"

		// The format of an event is the same as .toString() of Event class

		// Hint: String.format() can be helpful here...
		
		// Note: use StringBuilder instead of String concatenation for a better efficiency 

		String result = "";
		
		for (int i = 0; i < events.size(); i++) {
			if (i == size()-1) {
				result += String.format("[%d]%s", i, events.get(i).toString());
			}
			else {
				result += String.format("[%d]%s\n",i , events.get(i).toString());
			}
		}
		return result; //default return, remove/change as needed
	}
	
	/**
	 * Method adds the param's event into the current list of events.
	 * @param event to be added to the array.
	 */
	public void addEvent(Event event){
		
		// Add a new event into the list
		//	- make sure events are sorted after addition

		// Throw IllegalArgumentException if event is null. 
		// - Use this _exact_ error message for the exception 
		//  (quotes are not part of the message):
		//    "Null Event object!"
		
		// O(N) where N is the number of events in the list
		if (event == null) {
			throw new IllegalArgumentException("Null Event object!");
		}
		
		events.add(event);
	}
	
	/**
	 * Method moves the startTime of the event to the param's MyTime at the param's index.
	 * @param index of the events' array.
	 * @param newStart to be used to change the current startTime.
	 * @return boolean whether the newStart at the array's index's value can properly be moved while keeping it sorted.
	 */
	public boolean moveEvent(int index, MyTime newStart){
		// Move the event at index to be start at newStart.
		// Hint: we will keep the same duration but the end time may need to be changed.
		
		// Return true if event can be updated; return false otherwise.
		// - return false for an invalid index
		// - return false if event cannot be moved to newStart
		// - return false if newStart is null
		
		// If with the updated starting time, the events are still sorted in ascending 
		// order of their starting times, do not change the index of the event.
		// Otherwise, fix the ordering by first removing the updated event, 
		// then adding it back.

		// O(N) where N is the number of events currently in list
		boolean moved = false;
		boolean changei = false;
		if ((index >= events.capacity()) || (newStart == null) || (size() == 0) || (index < 0)) {
			return false;
		}
		
		boolean changed = events.get(index).moveStart(newStart);
		if (changed) {
			if (index == 0) {
				if (events.get(index+1) != null) {
					if ((events.get(index+1).compareTo(events.get(index)) > 0) || (events.get(index+1).compareTo(events.get(index)) == 0)) {
						changei = true;
						moved = true;
					}
				}
			}
			if (index - 1 == size()) {
				if ((events.get(index-1).compareTo(events.get(index)) < 0) || (events.get(index+1).compareTo(events.get(index)) == 0)) {
					changei = true;
					moved = true;
				}
			}
			if ((index+1 < size()) && (index-1 < size())) {
				if ((events.get(index+1) != null) && (events.get(index-1) != null)) {
					if (((events.get(index).compareTo(events.get(index-1)) > 0) && (events.get(index).compareTo(events.get(index+1)) < 0)) || (events.get(index+1).compareTo(events.get(index)) == 0)){
						changei = true;
						moved = true;
					}		
				}
			}
			if (!changei) {
				Event event = events.get(index);
				events.delete(index);
				events.add(event);
				moved = true;
			}
		}
		return moved; //default return, remove/change as needed
	}

	/**
	 * Method changes the duration of the Event at the param's index.
	 * @param index of the events' array.
	 * @param minute of the duration.
	 * @return boolean whether the duration is changed properly.
	 */
	public boolean changeDuration (int index, int minute){
		// Change the duration of event at index to be the given number of minutes.
		
		// Return true if the duration can be changed.
		// Return false if:
		// - index is invalid; or
		// - minute is negative; or
		// - the duration of event at index can not be updated with the specified minute

		// O(1)
		boolean changed = false;
		if ((index >= events.capacity()) || (minute < 0) || (size() == 0) || (index < 0)) {
			return false;
		}
		boolean changeDuration = events.get(index).changeDuration(minute);
		if (changeDuration) {
			changed = true;
		}
		return changed; //default return, remove/change as needed
	
	}

	/**
	 * Method changes the description of the event at the param's index to the param's description.
	 * @param index of the events' array.
	 * @param description to be changed.
	 * @return boolean whether the description is changed properly.
	 */
	public boolean changeDescription(int index, String description){
		// Change the description of event at index.
		
		// Return true if the event can be changed.
		// Return false for an invalid index.

		// If description argument is null, 
		// set description of the event to be empty string ""
		
		// O(1)
		if ((index >= events.capacity()) || (size() == 0) || (index < 0)) {
			return false;
		}
		boolean changed = false;
		if (description == null) {
			events.get(index).setDescription("");
			changed = true;
		}
		else {
			events.get(index).setDescription(description);
			changed = true;
		}
		return changed; //default return, remove/change as needed
	}
	
	/**
	 * Method removes the event at the param's index.
	 * @param index of the events' array.
	 * @return boolean whether the Event has been removed properly.
	 */
	public boolean removeEvent(int index){
		// Remove the event at index.
		
		// Return true if the event can be removed
		// Return false for an invalid index.
		
		// O(N) where N is the number of elements currently in the storage
		
		if ((index >= events.capacity()) || (size() == 0) || (index < 0)) {
			return false;
		}
		boolean removed = false;
		Event removedEvent = events.delete(index);
		if (removedEvent != null) {
			removed = true;
		}
		return removed; //default return, remove/change as needed
	}
	
	/**
	 * Getter method for the event at param's index.
	 * @param index of the events' array.
	 * @return Event is returned.
	 */
	public Event getEvent(int index){
		// Return the event at index
		
		// Return null for an invalid index.
		
		//O(1)
		if (index >= events.capacity()) {
			return null;
		}
		return events.get(index); //default return, remove/change as needed
	}
}