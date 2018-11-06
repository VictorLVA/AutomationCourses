import java.util.Scanner;
import java.util.*;

public class Task4L2 {

    static class Pair implements Comparable {
        private int time;
        private String name;

        private Pair(int time, String name) {
            this.time = time;
            this.name = name;
        }

        private int getTime() {
            return time;
        }

        public int compareTo (Object o)  {
            Pair right = (Pair)o;
            return Integer.compare(time, right.getTime ());
        }

        public String toString() {return name + " - " + time;}
    }

    public static void main(String[] args) {
        int[] times = {341 , 273 , 278 , 329 , 445 , 402 , 388 , 275 , 243 , 334 , 412 , 393 , 299 , 343 , 317 , 265};
        String[] names = {"Elena" , "Thomas" , "Hamilton" , "Suzie" , "Phil" , "Matt" , "Alex" , "Emma" , "John" , "James" ,
                "Jane" , "Emily" , "Daniel" , "Neda" , "Aaron" , "Kate"};
        Pair[] pairs = new Pair[times.length];
        for (int i = 0; i < pairs.length; i++) {
            pairs[ i ] = new Pair ( times[i], names[i] );
        }
        Arrays.sort(pairs);
        for(Pair p : pairs) {
            System.out.println(p);
        }
        System.out.println ( );
        System.out.println("The marathon winner is: "+pairs[0]);
        if (getPlace ( pairs ));
    }

    private static boolean getPlace ( Pair[] pairs ) {
        System.out.println ( );
        Scanner in = new Scanner ( System.in );
        System.out.print ( "Please enter a place (1-"+pairs.length+"): " );
        int place;
        try {
            place = in.nextInt ( );
        } catch (Exception e) {
            System.out.println ( "Please check the entered value and try again." );
            return true;
        }
        try {
            System.out.println ( "Place " + place + ": " + (pairs[place-1]) );
        }
        catch (Exception e) {
            System.out.println ( "You have entered an invalid place. Please try enter a value from the 1-" + pairs.length + " range. " );
            return true;
        }
        return false;
    }
}