import java.util.Scanner;

public class Task3 {
    public static void main ( String[] args ) throws Exception {
        Scanner in = new Scanner ( System.in );
        System.out.print ( "Please enter a rhombus size: " );
        int size;
        try {
            size = in.nextInt ( );
        } catch (Exception e) {
            System.out.println ( "Please check the entered value and try again" );
            return;
        }
        if (size < 3) {
            System.out.println ( "You have entered an invalid rhombus size (should be 3 at least). Please try again" );
            return;
        }
        String innerblank = " ";
        String outerblank = "";
        for (int i = 1; i < size ; i++) {
            outerblank = outerblank.concat (" ");
        }
        System.out.println (outerblank + "*");
        for ( int i = 1 ; i < size ; i++ ) {
            outerblank = outerblank.replaceFirst ( " " , "" );
            System.out.println ( outerblank + "*" + innerblank + "*");
            innerblank = innerblank.concat ("  ");
        }
        innerblank = innerblank.replaceFirst ( "  " , "" );
        for ( int i = 2 ; i < size ; i++ ) {
            outerblank = outerblank.concat (" ");
            innerblank = innerblank.replaceFirst ( "  " , "" );
            System.out.println ( outerblank + "*" + innerblank + "*");
        }
        outerblank = outerblank.concat (" ");
        System.out.print (outerblank + "*");
    }
}