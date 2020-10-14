/**
 * Assignment class
 * December 12 2019
 * George Li
 */
public class Assignment extends Event{
    private String subject;
    
    //constructor
    public Assignment(String date,String time,String note,String subject){
        super(date,time,note);//call super constructor
        this.subject = subject;
    }
    
    @Override
    public String toString(){
        return (super.toString()+", Assignment: "+this.subject);
    } 
}