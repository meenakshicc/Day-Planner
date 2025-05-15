# Day Planner
Implementing dynamic array list operations from scratch. And using this data structure to implement a larger problem. 
# About
This planner keeps track of events in an ascending order with the use of a dynamicaly sorted array list. This would allow for maintaining a single-day planner, including adding, removing, changing, or moving an event. 
# Implemented Classes
  * MySortedArray- A generic class to practice the sorted dynamic array list. It's used as a storage container for the events of the day.
  * MyTime- Uses the Comparable interface to compare MyTime objects for ordering the events in the day planner.
  * Event- Also implements the Comparable interface and the ordering of two events is based on their starting time.
  * Planner- Stores a collection of events in ascending order of their starting times and has all the operations offered by the Day Planner.
