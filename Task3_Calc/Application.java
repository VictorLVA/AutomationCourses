import java.io.*;
import java.util.Scanner;

public class Application {
    public static BufferedReader reader = new BufferedReader ( new InputStreamReader ( System.in ) );
    public static void main ( String[] args ) throws IOException {
        short mode = 0;
        Scanner mod = new Scanner ( System.in );
        System.out.print ( "Please select a mode (1-Common, 2-Star): " );
        mode = mod.nextShort ( );
        if (mode == 1) {
            while (true) {
                long n1;
                long n2;
                char o;
                System.out.println ( );
                while (true) {
                    System.out.print ( "Enter the 1st number: " );
                    String s = reader.readLine ( );
                    if ("quit".equals ( s )) {
                        return;
                    }
                    if (!s.matches ( "^[-+]?\\d+" )) {
                        System.out.print ( "Try to enter a proper value (INT). " );
                    }
                    if (s.matches ( "^[-+]?\\d+" )) {
                        n1 = Long.parseLong ( s );
                        break;
                    }
                }
                while (true) {
                    System.out.print ( "Enter the operation (+ or -): " );
                    String s2 = reader.readLine ( );
                    if ("quit".equals ( s2 )) {
                        return;
                    }
                    if ((!s2.matches ( "\\+" )) && (!s2.matches ( "\\-" ))) {
                        System.out.print ( "Try to enter a proper operation (+ or -). " );
                    }
                    if ((s2.matches ( "\\+" )) || (s2.matches ( "\\-" ))) {
                        o = s2.charAt ( 0 );
                        break;
                    }
                }
                while (true) {
                    System.out.print ( "Enter the 2nd number: " );
                    String s3 = reader.readLine ( );
                    if ("quit".equals ( s3 )) {
                        return;
                    }
                    if (!s3.matches ( "^[-+]?\\d+" )) {
                        System.out.print ( "Try to enter a proper value (INT). " );
                    }
                    if (s3.matches ( "^[-+]?\\d+" )) {
                        n2 = Long.parseLong ( s3 );
                        break;
                    }
                }
                if (o == '+') {
                    long sum = 0;
                    sum = n1 + n2;
                    System.out.println ( "The sum = " + sum );
                } else if (o == '-') {
                    long diff = 0;
                    diff = n1 - n2;
                    System.out.println ( "The diff = " + diff );
                }
            }
        }
        if (mode == 2) {
            while (true) {
                double n1;
                double n2;
                char o;
                System.out.println ( );
                while (true) {
                    System.out.print ( "Enter the 1st number: " );
                    String s = reader.readLine ( );
                    if ("quit".equals ( s )) {
                        return;
                    }
                    if (!s.matches ( "^[-+]?[0-9]*[.]?[0-9]+(?:[eE][-+]?[0-9]+)?$" )) {
                        System.out.print ( "Try to enter a proper value. " );
                    }
                    if (s.matches ( "^[-+]?[0-9]*[.]?[0-9]+(?:[eE][-+]?[0-9]+)?$" )) {
                        n1 = Double.parseDouble ( s );
                        break;
                    }
                }
                while (true) {
                    System.out.print ( "Enter the operation (+ or -, * or /): " );
                    String s2 = reader.readLine ( );
                    if ("quit".equals ( s2 )) {
                        return;
                    }
                    if ((!s2.matches ( "\\+" )) && (!s2.matches ( "\\-" )) && (!s2.matches ( "\\*" )) && (!s2.matches ( "\\/" ))) {
                        System.out.print ( "Try to enter a proper operation (+ or -, * or /). " );
                    }
                    if ((s2.matches ( "\\+" )) || (s2.matches ( "\\-" )) || (s2.matches ( "\\*" )) || (s2.matches ( "\\/" ))) {
                        o = s2.charAt ( 0 );
                        break;
                    }
                }
                while (true) {
                    System.out.print ( "Enter the 2nd number: " );
                    String s3 = reader.readLine ( );
                    if ("quit".equals ( s3 )) {
                        return;
                    }
                    if (!s3.matches ( "^[-+]?[0-9]*[.]?[0-9]+(?:[eE][-+]?[0-9]+)?$" )) {
                        System.out.print ( "Try to enter a proper value. " );
                    }
                    if (s3.matches ( "^[-+]?[0-9]*[.]?[0-9]+(?:[eE][-+]?[0-9]+)?$" )) {
                        n2 = Double.parseDouble ( s3 );
                        break;
                    }
                }
                if (o == '+') {
                    double sum = 0;
                    sum = n1 + n2;
                    System.out.println ( "The sum = " + sum );
                } else if (o == '-') {
                    double diff = 0;
                    diff = n1 - n2;
                    System.out.println ( "The diff = " + diff );
                }
                else if (o == '*') {
                    double der = 0;
                    der = n1 * n2;
                    System.out.println ("The der is " + der);
                }
                else if ( o == '/' && n2 == 0 ) {
                    System.out.println("You cannot divide by 0");
                }
                else if(o == '/') {
                    double div = 0;
                    div = n1 / n2;
                    System.out.println("The quo is " + div);
                }
            }
        }
    }
}