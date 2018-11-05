import java.util.Scanner;

public class Task1L2 {
    public static void main ( String[] args ) {
        Scanner in = new Scanner ( System.in );
        System.out.print ( "Please enter a sentence: " );
        String sentence = in.nextLine ();
        String reverse = new StringBuffer(sentence).reverse().toString();
        System.out.println ("Lets reverse: \""+reverse+"\"");
        String[] words = sentence.split(" ");
        System.out.println ("Lets split by words:");
        for(String eachWord : words) {
            System.out.println("\""+eachWord+"\"");
        }
        String stars = sentence.replace ( ' ', '*' );
        System.out.println ("Lets replace with *: \""+stars+"\"");
        String upperCase = sentence.toUpperCase ();
        System.out.println ("Lets rewrite it with uppercase: \""+upperCase+"\"");
        String symbols =
                (sentence.length () < 10)
                ? sentence.substring ( 4 )
                : sentence.substring ( 4, 10 );
        System.out.println ("Lets get the 5-10 symbols: \""+symbols+"\"");
    }
}