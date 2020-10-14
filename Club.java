/**
 * Club class
 * December 12 2019
 * George Li
 */
public class Club extends Event{
  private String clubName;
  
  public Club(String date,String time,String note,String clubName){
    super(date,time,note);
    this.clubName = clubName;
  }
  
  @Override
  public String toString(){
      return (super.toString()+", Club name: "+this.clubName);
  } 
  
}