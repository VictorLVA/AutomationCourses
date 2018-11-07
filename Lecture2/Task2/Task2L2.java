import java.util.Scanner;

public class Task2L2 {

    private static final String MESSAGE_SELECTED = "You have selected a ";
    private static final String MESSAGE_INVALID = "You have entered an invalid day's index";

    public static void main ( String[] args ) {
        Scanner in = new Scanner ( System.in );
        System.out.print ( "Please enter an index of a day within a week (1-7): " );
        byte day;
        try {
            day = in.nextByte ( );
        }
        catch (Exception e) {
            System.out.println ( MESSAGE_INVALID );
            return;
        }
        if (day == 1)
            System.out.println ( MESSAGE_SELECTED + "MONDAY" );
        else if (day == 2)
            System.out.println ( MESSAGE_SELECTED + "TUESDAY" );
        else if (day == 3)
            System.out.println ( MESSAGE_SELECTED + "WEDNESDAY" );
        else if (day == 4)
            System.out.println ( MESSAGE_SELECTED + "THURSDAY" );
        else if (day == 5)
            System.out.println ( MESSAGE_SELECTED + "FRIDAY" );
        else if (day == 6)
            System.out.println ( MESSAGE_SELECTED + "SATURDAY" );
        else if (day == 7)
            System.out.println ( MESSAGE_SELECTED + "SUNDAY" );
        else System.out.println ( MESSAGE_INVALID );
    }
}