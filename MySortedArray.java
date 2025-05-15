// TO DO: add your implementation and JavaDocs.
/**
 * This class creates a sorted dynamic array that can add, delete, and replace values in the array.
 * @param <T> that implements the Comparable Interface.
 */
public class MySortedArray<T extends Comparable<T>> {

	//default initial capacity / minimum capacity
	/**
	 * Default capacity variable for the dynamic array.
	 */
	private static final int DEFAULT_CAPACITY = 2;
	
	//underlying array for storage -- you MUST use this for credit!
	//Do NOT change the name or type
	/**
	 * Dynamic array for the sorted array.
	 */
	private T[] data;
	/**
	 * Keeps track of the amount of values in the index that aren't null.
	 */
	private int count;

	// ADD MORE PRIVATE MEMBERS HERE IF NEEDED!
	
	/**
	 * Default Constructor for the MySortedArray that sets the data's length to DEFAULT_CAPACITY.
	 */
	@SuppressWarnings("unchecked")
	public MySortedArray() {
		// Constructor
		
		// Initial capacity of the storage should be DEFAULT_CAPACITY
		// Hint: Can't remember how to make an array of generic Ts? It's in the textbook...
		this.data = (T[]) new Comparable[DEFAULT_CAPACITY];
		
	}
	
	/**
	 * Constructor for the MySortedArray that sets the capacity of the data to the param.
	 * @param initialCapacity sets the capacity of the array.
	 */
	@SuppressWarnings("unchecked")
	public MySortedArray(int initialCapacity){
		// Constructor

		// Initial capacity of the storage should be initialCapacity

		// Throw IllegalArgumentException if initialCapacity is smaller than 
		// 2 (which is the DEFAULT_CAPACITY). 
		// Use this _exact_ error message for the exception 
		//  (quotes are not part of the message):
		//    "Capacity must be at least 2!"
		if (initialCapacity < DEFAULT_CAPACITY) {
			throw new IllegalArgumentException("Capacity must be at least 2!");
		}
		this.data = (T[]) new Comparable[initialCapacity];
	}
	
	/**
	 * Returns the amount of elements present in the array.
	 * @return int of the array.
	 */
	public int size() {	
		// Report the current number of elements
		// O(1)
		
		return count; //default return, remove/change as needed
	}  
		
	/**
	 * Returns the data's length.
	 * @return int of the array.
	 */
	public int capacity() { 
		// Report max number of elements before the next expansion
		// O(1)
		
		return data.length; //default return, remove/change as needed
	}
	
	/**
	 * Method adds the value into the array while keeping it sorted in an ascending order.
	 * @param value that is added to the array
	 */
	public void add(T value){

		// Insert the given value into the array and keep the array _SORTED_ 
		// in ascending order. 

		// If the array already contains value(s) that are equal to the new value,
		// make sure that the new value is added at the end of the group. Check examples
		// in project spec and main() below.
		
		// Remember to use .compareTo() to perform order/equivalence checking.
				
		// Note: You _can_ append an item (and increase size) with this method.
		// - You must call doubleCapacity() if no space is available. 
		// - Check below for details of doubleCapacity().
		// - For the rare case that doubleCapacity() fails to increase the max 
		//   number of items allowed, throw IllegalStateException.
		// - Use this _exact_ error message for the exception 
		//  (quotes are not part of the message):
		//    "Cannot add: capacity upper-bound reached!"

		
		// Note: The value to be added cannot be null.
		// - Throw IllegalArgumentException if value is null. 
		// - Use this _exact_ error message for the exception 
		//  (quotes are not part of the message):
		//    "Cannot add: null value!"

		// O(N) where N is the number of elements in the storage
		if (value == null) {
			throw new IllegalArgumentException("Cannot add: null value!");
		}
		int index = 0;
		for (int i = 0; i < size(); i++) {
			if (data[0] == null) {
				data[0] = value;
				break;
			}
			else if (data[i].compareTo(value) == 0) {
				index = i+1;
				continue;
			}
			else if (data[i].compareTo(value) > 0) {
				index = i;
				break;
			}
			else if (data[count - 1].compareTo(value) < 0) {
				index = count;
				break;
			}
		}
		if ((size() + 1) > capacity()) {
			if (!doubleCapacity()) {
				throw new IllegalStateException("Cannot add: capacity upper-bound reached!");
			}
			halveCapacity();
			doubleCapacity();
		}
		if (index == data.length - 1) {
			data[index] = value;
		}
		else {
			for (int i = count; i > index; i--) {
				if (!(i >= capacity())) {
					data[i] = data[i-1];
				}
			}
			data[index] = value;
		}
		count++;
	}

	/**
	 * Returns the T value at the given index.
	 * @param index of the array.
	 * @return T value of the array.
	 */
	public T get(int index) {
		// Return the item at the given index
		
		// O(1)
				
		// For an invalid index, throw an IndexOutOfBoundsException.
		// Use this code to produce the correct error message for
		// the exception (do not use a different message):
		//	  "Index " + index + " out of bounds!"
		if ((index >= capacity()) || (index < 0)) {
			throw new IndexOutOfBoundsException("Index " + index + " out of bounds!");
		}
		T value;
		value = data[index];
		return value;
		//default return, remove/change as needed

	}
	
	/**
	 * Method replaces the value at the given index with the param's value.
	 * @param index of the array.
	 * @param value to be replaced in the array.
	 * @return boolean if the replaced value at the specified index maintains the sorted array.
	 */
	public boolean replace(int index, T value) {
		// Change the item at the given index to be the given value.
		
		// For an invalid index, throw an IndexOutOfBoundsException. 
		// Use the same error message as get(index).
		// Note: You _cannot_ add new items with this method.
		
		// For a valid index, if value is null, throw IllegalArgumentException.
		// Use the exact same error message as add(value).
				
		// The array must keep to be sorted in ascending order after updating. 
		// For a valid index, return false if setting the value at index violates 
		// the required order hence can not be performed; no change should be made 
		// to the array.  Otherwise, change the item and return true. 
		
		// O(1)
		boolean valid = false;
		if ((index >= size()) || (index < 0)) {
			throw new IndexOutOfBoundsException("Index " + index + " out of bounds!");
		}
		if (data[index] == null) {
			throw new IllegalArgumentException("Cannot add: null value!");
		}
		if ((data[index].compareTo(value) == 0) || (data[index-1].compareTo(value) == 0) || (data[index+1].compareTo(value) == 0)) {
			data[index] = value;
			valid = true;
		}
		else if (index == 0) {
			if (data[index].compareTo(value) > 0) {
				data[0] = value;
				valid = true;
			}
		}
		else if (index == size() - 1) {
			if (data[index-1].compareTo(value) < 0) {
				data[index] = value;
				valid = true;
			}
		}
		else {
			if ((data[index-1].compareTo(value) < 0 ) && (data[index+1].compareTo(value) > 0)){
				data[index] = value;
				valid = true;
			}
			
		}
		return valid; //default return, remove/change as needed
	}
	
	/**
	 * Method adds the value at the given index in the array while keeping it sorted.
	 * @param index of the array.
	 * @param value to be added at the index in the array.
	 * @return boolean if the added value at the specified index maintains the sorted array.
	 */
	public boolean add(int index, T value) {
		// Insert the given value at the given index. Shift elements if needed.
		// Double capacity if no space available. 

		// For an invalid index, throw an IndexOutOfBoundsException. 
		// Use the same error message as get(index).
		// Note: You _can_ append items with this method, which is different from replace().
		
		// For a valid index, if value is null, throw IllegalArgumentException.
		// Use the exact same error message as add(value). See add(value) above.

		// The array must keep to be sorted in ascending order after updating. 
		// For a valid index, return false if inserting the value at index violates 
		// the required order hence can not be performed; no change should be made 
		// to the array.  Otherwise, insert the value and return true. 
		
		// You must call doubleCapacity() if no space is available. 
		// Throw IllegalStateException if doubleCapacity() fails.
		// Use the exact same error message as add(value). See add(value) above.

		// O(N) where N is the number of elements in the storage
		boolean add = false;
		if ((index > size()) || (index < 0)) {
			throw new IndexOutOfBoundsException("Index " + index + " out of bounds!");
		}
		if (value == null) {
			throw new IllegalArgumentException("Cannot add: null value!");
		}
		if (((data == null) || (index == 0)) || ((this.data[index] == null) && (index >= size()) && (index < capacity()))){
			data[index] = value;
			add = true;
			count++;
			return add;
		}
		if (index == size()){
			if ((data[index-1].compareTo(value) < 0) || (data[index-1].compareTo(value) == 0)) {
				if (size()+1 > data.length) {
					if (!doubleCapacity()) {
						throw new IllegalStateException("Cannot add: capacity upper-bound reached!");
					}
					halveCapacity();
					doubleCapacity();
				}
				T temp = data[index];
				data[index] = value;
				data[index+1] = temp;
				add = true;
				count++;
				return add;
			}
		}
		if (this.data[index] != null) {
			if ((data[index - 1].compareTo(value) == 0) || (data[index].compareTo(value) == 0)) {
				for (int i = count + 1; i > index; i--) {
					if (!(i >= capacity())) {
						data[i] = data[i-1];
					}
				}
				if (size()+1 > data.length) {
					if (!doubleCapacity()) {
						throw new IllegalStateException("Cannot add: capacity upper-bound reached!");
					}
					doubleCapacity();
				}
				data[index] = value;
				add = true;
				count++;
			}
		}
		else {
			if ((data[index-1].compareTo(value) < 0) && (value.compareTo(data[index]) < 0)) {
				add(value);
				add = true;
			}
		}
		return add; //default return, remove/change as needed
	}

	/**
	 * Deletes the value at the index in the array.
	 * @param index of the array.
	 * @return T the value that is deleted at the array.
	 */
	public T delete(int index) {
		// Remove and return the element at the given index. Shift elements
		// to remove the gap. Throw an exception when there is an invalid
		// index (see replace(), get(), etc. above).
		
		// After deletion, if the number of elements falls below 1/3 
		// of the capacity, you must call halveCapacity() to shrink the storage.
		// - Check halveCapacity() below for details.
		// - If the capacity would fall below the DEFAULT_CAPACITY, 
		//   shrink to the DEFAULT_CAPACITY. This should be implemented by
		//   halveCapacity().
		
		// O(N) where N is the number of elements currently in the storage
		if ((index > capacity()) || (index < 0) || (size() == 0)) {
			throw new IndexOutOfBoundsException("Index " + index + " out of bounds!");
		}
		
		T value = null;
		
		if (data[index] == null) {
			return null;
		}
		else {
			value = data[index];
			data[index] = null;
			for (int i = index + 1; i < data.length; i++) {
				if (data[i] != null) {
					data[i - 1] = data[i];
					data[i] = null;
				}
			}
			count--;
			double x = (double) capacity()/3;
			if (size() < x) {
				halveCapacity();
			}
		}
		return value; //default return, remove/change as needed
	}  

	/**
	 * Method doubles the capacity of the array if the elements in the array to be added exceed the capacity.
	 * @return boolean if the capacity is properly doubled.
	 */
	@SuppressWarnings("unchecked")
	public boolean doubleCapacity(){
		// Double the max number of items allowed in data storage.
		// Remember to copy existing items to the new storage after expansion.

		// - Out of abundance of caution, we will use (Integer.MAX_VALUE - 50)
		//   as the upper-bound of our capacity.
		// - If double the current capacity would go beyond this upper-bound,
		//   use this upper-bound value as the new capacity.
		// - If the current capacity is already this upper-bound (Integer.MAX_VALUE - 50), 
		//   do not expand and return false.
		
		// Return true for a successful expansion.

		// O(N) where N is the number of elements in the array
		
		T[] tempdata = this.data;
		boolean doubled = false;
		if (data.length == Integer.MAX_VALUE - 50) {
			return doubled;
		}
		T[] doubledata =(T[]) new Comparable[data.length * 2];
		if (doubledata.length > (Integer.MAX_VALUE - 50)) {
			doubledata = (T[]) new Comparable[Integer.MAX_VALUE - 50];
		}
		doubled = true;
		this.data = doubledata;
		for (int i = 0; i < tempdata.length; i++) {
			this.data[i] = tempdata[i];
		}
		return doubled; //default return, remove/change as needed
	}

	/**
	 * Method halves the capacity of the array.
	 * @return boolean if the capacity is properly halved
	 */
	@SuppressWarnings("unchecked")
	public boolean halveCapacity(){
		// Reduce the max number of items allowed in data storage by half.
		// - If the current capacity is an odd number, _round down_ to get the 
		//   new capacity;
		// - If the new capacity would fall below the DEFAULT_CAPACITY, 
		//   shrink to the DEFAULT_CAPACITY;
		// - If the new capacity (after necessary adjustment to DEFAULT_CAPACITY) 
		//   cannot hold all existing items, do not shrink and return false;
		// - Return true for a successful shrinking.

		// Remember to copy existing items to the new storage after shrinking.
		
		// O(N) where N is the number of elements in the array
		T[] tempdata = this.data;
		boolean halved = false;
		int count = 0;
		if (capacity() % 2 != 0) {
			count = (capacity()-1)/2;
		}
		else {
			count = capacity()/2;
		}
		T[] halvedata = (T[]) new Comparable[count];
		halved = true;
		if (halvedata.length < DEFAULT_CAPACITY) {
			halvedata = (T[]) new Comparable[DEFAULT_CAPACITY];
		}
		if (halvedata.length < size()) {
			return false;
		}
		else {
			this.data = halvedata;
			for (int i = 0; i < data.length; i++) {
				this.data[i] = tempdata[i];
			}
		}
		return halved; //default return, remove/change as needed
		
	}
}