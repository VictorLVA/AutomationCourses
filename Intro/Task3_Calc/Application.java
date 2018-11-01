import java.io.*;

public class Application {
    public static BufferedReader reader = new BufferedReader ( new InputStreamReader ( System.in ) );
    public static void main ( String[] args ) throws Exception {
        short mode = 0;
        System.out.print ( "Please select a mode (1-Common, 2-Star, 3-withTryCatchExceptions): " );
        while (mode == 0) {
            String mod = reader.readLine ( );
            if ("quit".equals ( mod )) {
                return;
            }
            if (mod.matches ("[1-3]")) {
                mode = Short.parseShort ( mod );
            }
            else {
                System.out.print ( "Please select a proper mode! 1-Common, 2-Star, 3-withTryCatchExceptions: " );
            }
        }
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
                    if (s.matches ( "^[-+]?\\d+" )) {
                        n1 = Long.parseLong ( s );
                        break;
                    }
                    else {
                        System.out.print ( "Try to enter a proper value (INT). " );
                    }
                }
                while (true) {
                    System.out.print ( "Enter the operation (+ or -): " );
                    String s2 = reader.readLine ( );
                    if ("quit".equals ( s2 )) {
                        return;
                    }
                    if ((s2.matches ( "\\+" )) || (s2.matches ( "\\-" ))) {
                        o = s2.charAt ( 0 );
                        break;
                    }
                    else {
                        System.out.print ( "Try to enter a proper operation (+ or -). " );
                    }
                }
                while (true) {
                    System.out.print ( "Enter the 2nd number: " );
                    String s3 = reader.readLine ( );
                    if ("quit".equals ( s3 )) {
                        return;
                    }
                    if (s3.matches ( "^[-+]?\\d+" )) {
                        n2 = Long.parseLong ( s3 );
                        break;
                    }
                    else {
                        System.out.print ( "Try to enter a proper value (INT). " );
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
                    if (s.matches ( "^[-+]?[0-9]*[.]?[0-9]+(?:[eE][-+]?[0-9]+)?$" )) {
                        n1 = Double.parseDouble ( s );
                        break;
                    }
                    else {
                        System.out.print ( "Try to enter a proper value. " );
                    }
                }
                while (true) {
                    System.out.print ( "Enter the operation (+ or -, * or /): " );
                    String s2 = reader.readLine ( );
                    if ("quit".equals ( s2 )) {
                        return;
                    }
                    if ((s2.matches ( "\\+" )) || (s2.matches ( "\\-" )) || (s2.matches ( "\\*" )) || (s2.matches ( "\\/" ))) {
                        o = s2.charAt ( 0 );
                        break;
                    }
                    else {
                        System.out.print ( "Try to enter a proper operation. " );
                    }
                }
                while (true) {
                    System.out.print ( "Enter the 2nd number: " );
                    String s3 = reader.readLine ( );
                    if ("quit".equals ( s3 )) {
                        return;
                    }
                    if (s3.matches ( "^[-+]?[0-9]*[.]?[0-9]+(?:[eE][-+]?[0-9]+)?$" )) {
                        n2 = Double.parseDouble ( s3 );
                        break;
                    }
                    else {
                        System.out.print ( "Try to enter a proper value. " );
                    }
                }
                if (o == '+') {
                    double sum = 0;
                    sum = n1 + n2;
                    System.out.println ( "The sum = " + sum );
                }
                else if (o == '-') {
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
        if (mode == 3) {
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
                    else {
                        try {
                            n1 = Double.parseDouble ( s );
                            break;
                        }
                        catch ( Exception e) {
                            System.out.print ( "Try to enter a proper value. " );
                        }

                    }
                }
                while (true) {
                    System.out.print ( "Enter the operation (+ or -, * or /): " );
                    String s2 = reader.readLine ( );
                    if ("quit".equals ( s2 )) {
                        return;
                    }
                    if ((s2.matches ( "\\+" )) || (s2.matches ( "\\-" )) || (s2.matches ( "\\*" )) || (s2.matches ( "\\/" ))) {
                        o = s2.charAt ( 0 );
                        break;
                    }
                    else {
                        System.out.print ( "Try to enter a proper operation. " );
                    }
                }
                while (true) {
                    System.out.print ( "Enter the 2nd number: " );
                    String s3 = reader.readLine ( );
                    if ("quit".equals ( s3 )) {
                        return;
                    }
                    else {
                        try {
                            n2 = Double.parseDouble ( s3 );
                            break;
                        }
                        catch ( Exception e) {
                            System.out.print ( "Try to enter a proper value. " );
                        }
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
                else if (o == '/') {
                    double div = 0;
                    div = n1 / n2;
                    System.out.println("The quo is " + div);
                }
            }
        }
    }
}