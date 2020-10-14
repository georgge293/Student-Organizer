/**
 * Data structures assignment
 * December 12 2019
 * George Li
 */
import java.io.File;  //for file io
import java.util.Scanner;//import for scanner for user input
public class StudentOrganizer implements Planner{//implement for constants
    public static void main(String[] args) throws Exception { 
        //scanner
        Scanner keyboard = new Scanner (System.in);
        
        //input file to read previously saved events
        File load = new File("load.txt"); 
        Scanner input = new Scanner(load);
        
        boolean isRunning = true;
        boolean addSuccess = false;
        
        
        Calendar calendar = new Calendar();
        load(input,calendar);
        while(isRunning){
            System.out.println("1. Add \n2. Edit \n3. Delete \n4. Clear schedule \n5. Print \n6. Calendar size \n7. Contains \n8. QUIT");
            int calendarOption = keyboard.nextInt();
            keyboard.nextLine();
            if(calendarOption == EDIT){
                System.out.println("Enter date of what you're editing");
                String date = getDate(keyboard);
                System.out.println("Enter time of what you're editing");
                String time = getTime(keyboard);
                calendar.remove(createEvent(date,time,""));
                System.out.println("Enter info for your newly edited event");
            }
            if(calendarOption  == ADD || calendarOption == DELETE || calendarOption == EDIT){
                String date = getDate(keyboard);
                String time = getTime(keyboard);
                if(calendarOption == ADD || calendarOption == EDIT){
                    String note = getNote(keyboard);
                    System.out.println("1. Test \n2. Assignment \n3. Club \n4. Other");
                    int eventType = keyboard.nextInt();
                    keyboard.nextLine();
                    if(eventType==TEST){
                        addSuccess = calendar.add(createTest(date,time,note,keyboard));
                    }
                    else if(eventType == ASSIGNMENT){
                        addSuccess = calendar.add(createAssignment(date,time,note,keyboard));
                    }
                    else if(eventType == CLUB){
                        addSuccess = calendar.add(createClub(date,time,note,keyboard));
                    }
                    else if (eventType == OTHER){
                        addSuccess = calendar.add(createEvent(date,time,note));
                    }
                    if(addSuccess){
                        System.out.println("Added!");
                    }
                    else{
                        System.out.println("You already have a event scheduled with that exact time and date");
                    }
                }
                else if(calendarOption == DELETE){
                    //removes event with the same date as the temporary event just created
                    calendar.remove(createEvent(date,time,""));
                }
                calendar.printAll();
            }
            else if(calendarOption == CLEAR){
                calendar.clear();
            }
            else if(calendarOption == PRINT){
                calendar.printAll(); 
            }
            else if(calendarOption == CALENDAR_SIZE){
                calendar.size();
            }
            else if(calendarOption== CONTAINS){
                System.out.println("Enter the following information to see if you have something scheduled on that specific date and time");
                String date = getDate(keyboard);
                String time = getTime(keyboard);
                calendar.contains(createEvent(date,time,""));
            }
            else if(calendarOption== QUIT){
                isRunning = false;
            }
            else{
                System.out.println("Enter something valid");        
            }
            
            try{
                calendar.output();
            }
            catch (Exception e) //catch exception
            { 
                System.out.println("error");
            }
        }
        keyboard.close();//close scanner
    }
    
    //Methods to help create events
    public static Test createTest(String date, String time, String note,Scanner keyboard){
        System.out.println("Subject for test ");
        String subject = keyboard.nextLine();
        System.out.println("Room number for test room");
        int roomNum = keyboard.nextInt();
        Test event = new Test(date,time,note,subject,roomNum);
        return event;
    }
    public static Assignment createAssignment(String date,String time,String note ,Scanner keyboard){
        System.out.println("Subject for assignment ");
        String subject = keyboard.nextLine();
        Assignment assignment = new Assignment(date,time,note,subject);
        return assignment;
    }
    public static Club createClub(String date,String time,String note, Scanner keyboard){
        System.out.println("Name of club");
        String clubName = keyboard.nextLine();
        Club club = new Club(date,time,note,clubName);
        return club;
    }
    public static Event createEvent(String date,String time,String note){
        Event event = new Event(date,time,note);
        return event;
    }
    
    public static String getDate(Scanner keyboard){
        System.out.println("Date MM/DD/YYYY format: ");
        return  keyboard.nextLine();
    }
    public static String getTime(Scanner keyboard){
        System.out.println("Time H:M 24 hr format");
        return keyboard.nextLine();
    }
    public static String getNote(Scanner keyboard){
        System.out.println("Note");
        return keyboard.nextLine();
    }
    
    //Load methods
    public static void load(Scanner input,Calendar calendar){
        while(input.hasNext()){
            String load = input.nextLine();
            if(!load.trim().equals("You're calendar is empty" )){//trim load to remove leading and trailing spaces
                String [] paramsList = load.trim().split(", ");
                String [] note = paramsList[2].split(": ");
                paramsList[2] = note[1];
                if(paramsList.length == 3){
                    loadEvent(input,calendar,paramsList);
                }
                else if(paramsList.length == 4){
                    String [] clubOrAssignment = paramsList[3].split(": ");
                    paramsList[3] = clubOrAssignment[1];
                    if(clubOrAssignment[0].equals("Assignment")){
                        loadAssignment(input,calendar,paramsList);
                    }
                    else{
                        loadClub(input,calendar,paramsList);
                    }
                }
                else{
                    String [] test = paramsList[3].split(": ");
                    paramsList[3] = test[1];
                    String [] roomNum = paramsList[4].split(": ");
                    paramsList[4] = roomNum[1];
                    loadTest(input,calendar,paramsList);
                }
            }
        }
    }
    
    //load specific event methods
    public static void loadEvent(Scanner input, Calendar calendar,String []  paramsList){
        Event event = new Event(paramsList[0],paramsList[1],paramsList[2]);
        calendar.add(event);
    }
    public static void loadClub(Scanner input, Calendar calendar,String []  paramsList){
        Club club = new Club(paramsList[0],paramsList[1],paramsList[2],paramsList[3]);
        calendar.add(club);
    }
    public static void loadAssignment(Scanner input, Calendar calendar, String [] paramsList){
        Assignment assignment = new Assignment(paramsList[0],paramsList[1],paramsList[2],paramsList[3]);
        calendar.add(assignment);
    }   
    public static void loadTest(Scanner input, Calendar calendar, String [] paramsList){
        Test test = new Test(paramsList[0],paramsList[1],paramsList[2],paramsList[3],(Integer.parseInt(paramsList[4])));
        calendar.add(test);
    }
}
