/**
 * Test Class
 * December 12 2019
 * George Li
 */
public class Test extends Event{
  private String subject;
  private int roomNum;
  
  public Test(String date,String time,String note,String subject, int roomNum){
    super(date,time,note);
    this.subject = subject;
    this.roomNum = roomNum;
  }

  @Override
  public String toString(){
      return (super.toString()+", Test: "+this.subject+", Room Number: "+this.roomNum);
  } 
}