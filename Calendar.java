/**
 * Calendar class
 * December 12 2019
 * George Li
 */
import java.io.PrintWriter;    //import for file write
import java.io.File;
public class Calendar{    
    private BinaryTree<Event> calendarSearchTree; 
    
    //Constructor
    public Calendar(){
        this.calendarSearchTree = new BinaryTree<Event>(); 
    }
    
    //Add 
    public boolean add(Event event){
        return calendarSearchTree.add(event);
    }
    
    //Remove
    public  void remove(Event event){
        calendarSearchTree.remove(event);
    }
    
    //Output to text file
    public void output() throws Exception{
        File outputFile = new File ("load.txt");
        PrintWriter output = new PrintWriter(outputFile);
        output.println(calendarSearchTree);
        output.close();
    }
    
    //Prints for the user
    public void printAll(){
        System.out.println(calendarSearchTree);
    }
    
    //Clear
    public void clear(){
        calendarSearchTree.clear();
    }
    
    //Size
    public void size(){
        calendarSearchTree.size();
    }
    
    //Contains
    public void contains(Event event){
        if(calendarSearchTree.contains(event)){
            System.out.println("You have an event scheduled for that time");
        }
        else{
            System.out.println("Couldnt find event with this date");
        }
    }
    
}
  
  
 
  
