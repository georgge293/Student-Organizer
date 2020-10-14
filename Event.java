/**
 * Event class
 * December 12 2019
 * George Li
 */
public class Event implements Comparable<Event>{
  private String date;
  private String time;
  private String note;
  
  public Event(String date,String time,String note){
    this.date = date;
    this.time = time;
    this.note = note;
  }

  //converts String date and time to int minutes so I can compare multiple dates
  private  int convertDateToMin(){
      int [] daysInMonth ={0,31,59,90,120,151,181,212,242,273,303,334};
      String [] hourMin = this.time.split(":");
      String [] monthDayYear = this.date.split("/");
      int month = Integer.parseInt(monthDayYear[0]);
      int day = Integer.parseInt(monthDayYear[1]);
      int year = Integer.parseInt(monthDayYear[2]);
      int hour = Integer.parseInt(hourMin[0]);
      int minute = Integer.parseInt(hourMin[1]);
      return year*525600 + daysInMonth[month-1]*1440 + day*1440 + hour*60 + minute;
  }
  
 //Compare to method returns int value depending on which event has bigger date(more in the future)
  public int compareTo(Event other){
     return this.convertDateToMin() - other.convertDateToMin();
  }

  @Override
  public String toString(){
      return(this.date+", "+this.time+", Reminder: "+this.note);
  }
}