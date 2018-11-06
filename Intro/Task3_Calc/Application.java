import java.io.*;

public class Application {
    private static BufferedReader reader = new BufferedReader ( new InputStreamReader ( System.in ) );
    public static void main ( String[] args ) throws Exception {
        short mode = 0;
        System.out.print ( "Please select a mode (1-Common, 2-Star, 3-withTryCatchExceptions): " );
        while (mode == 0) {
            String mod = reader.readLine ( );
            if (checkQuit(mod)) return;
            mode = checkSelectedMode(mode, mod);
        }
        if (mode == 1) {
            while (true) {
                long n1;
                long n2;
                char o;
                System.out.print("\n");
                while (true) {
                    System.out.print ( "Enter the 1st number: " );
                    String s = reader.readLine ( );
                    if (checkQuit(s)) return;
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
                    if (checkQuit(s2)) return;
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
                    if (checkQuit(s3)) return;
                    if (s3.matches ( "^[-+]?\\d+" )) {
                        n2 = Long.parseLong ( s3 );
                        break;
                    }
                    else {
                        System.out.print ( "Try to enter a proper value (INT). " );
                    }
                }
                tryCalcM1(n1, n2, o);
            }
        }
        if (mode == 2) {
            while (true) {
                double n1;
                double n2;
                char o;
                System.out.print ("\n");
                while (true) {
                    System.out.print ( "Enter the 1st number: " );
                    String s = reader.readLine ( );
                    if (checkQuit(s)) return;
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
                    if (checkQuit(s2)) return;
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
                    if (checkQuit(s3)) return;
                    if (s3.matches ( "^[-+]?[0-9]*[.]?[0-9]+(?:[eE][-+]?[0-9]+)?$" )) {
                        n2 = Double.parseDouble ( s3 );
                        break;
                    }
                    else {
                        System.out.print ( "Try to enter a proper value. " );
                    }
                }
                tryCalcM23(n1, n2, o);
            }
        }
        if (mode == 3) {
            while (true) {
                double n1;
                double n2;
                char o;
                System.out.print ("\n");
                while (true) {
                    System.out.print ( "Enter the 1st number: " );
                    String s = reader.readLine ( );
                    if (checkQuit(s)) return;
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
                    if (checkQuit(s2)) return;
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
                    if (checkQuit(s3)) return;
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
                tryCalcM23(n1, n2, o);
            }
        }
    }

    private static void tryCalcM23(double n1, double n2, char o) {
        trySumM23(n1, n2, o);
        tryDiffM23(n1, n2, o);
        tryDerM23(n1, n2, o);
        if (checkDiv0(n2, o)) return;
        tryDivM23(n1, n2, o);
    }

    private static void tryCalcM1(long n1, long n2, char o) {
        trySumM1(n1, n2, o);
        tryDiffM1(n1, n2, o);
    }

    private static void tryDivM23(double n1, double n2, char o) {
        if (o == '/') {
            System.out.println("The quo is " + (n1 / n2));
        }
    }

    private static boolean checkDiv0(double n2, char o) {
        if (o == '/' && n2 == 0) {
            System.out.println("You cannot divide by 0");
            return true;
        }
        return false;
    }

    private static void tryDerM23(double n1, double n2, char o) {
        if (o == '*') {
            System.out.println ("The der is " + (n1 * n2));
        }
    }

    private static void tryDiffM23(double n1, double n2, char o) {
        if (o == '-') {
            System.out.println ( "The diff = " + (n1 - n2) );
        }
    }

    private static void trySumM23(double n1, double n2, char o) {
        if (o == '+') {
            System.out.println ( "The sum = " + (n1 + n2) );
        }
    }

    private static void tryDiffM1(long n1, long n2, char o) {
        if (o == '-') {
            System.out.println ( "The diff = " + (n1 - n2) );
        }
    }

    private static void trySumM1(long n1, long n2, char o) {
        if (o == '+') {
            System.out.println ( "The sum = " + (n1 + n2) );
        }
    }

    private static short checkSelectedMode(short mode, String mod) {
        if (mod.matches ("[1-3]")) {
            mode = Short.parseShort ( mod );
        }
        else {
            System.out.print ( "Please select a proper mode! 1-Common, 2-Star, 3-withTryCatchExceptions: " );
        }
        return mode;
    }

    private static boolean checkQuit(String s) {
        return "quit".equals(s);
    }
}