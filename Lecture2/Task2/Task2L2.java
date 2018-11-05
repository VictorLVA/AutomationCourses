import java.util.Scanner;

public class Task2L2 {
    public static void main ( String[] args ) throws Exception {
        Scanner in = new Scanner ( System.in );
        System.out.print ( "Please enter an index of a day within a week (1-7): " );
        byte day;
        try {
            day = in.nextByte ( );
        }
        catch (Exception e) {
            System.out.println ("You have entered an invalid day's index");
            return;
        }
        switch (day) {
            case 1:
                System.out.println ( "You have selected a MONDAY" );
                break;
            case 2:
                System.out.println ( "You have selected a TUESDAY" );
                break;
            case 3:
                System.out.println ( "You have selected a WEDNESDAY" );
                break;
            case 4:
                System.out.println ( "You have selected a THURSDAY" );
                break;
            case 5:
                System.out.println ( "You have selected a FRIDAY" );
                break;
            case 6:
                System.out.println ( "You have selected a SATURDAY" );
                break;
            case 7:
                System.out.println ( "You have selected a SUNDAY" );
                break;
            default:
                System.out.println ( "You have entered an invalid day's index" );
        }
    }
}