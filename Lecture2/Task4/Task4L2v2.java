import java.util.Scanner;
import java.util.*;

public class Task4L2v2 {

    public static void main(String[] args) {
        int[] times = {341 , 273 , 278 , 329 , 445 , 402 , 388 , 275 , 243 , 334 , 412 , 393 , 299 , 343 , 317 , 265};
        String[] names = {"Elena" , "Thomas" , "Hamilton" , "Suzie" , "Phil" , "Matt" , "Alex" , "Emma" , "John" , "James" , "Jane" , "Emily" , "Daniel" , "Neda" , "Aaron" , "Kate"};
        Map<Integer, String> results = new TreeMap<>();
        for (int i = 0; i < times.length; i++) {
            results.put(times[i], names[i]);
        }
        for (Map.Entry<Integer, String> eachResult : results.entrySet()) {
            System.out.println(eachResult.getValue() + " - " + eachResult.getKey());
        }
        System.out.println("\nThe marathon winner is: " + results.values().toArray()[0] + " - " + results.keySet().toArray()[0]);
        getPlace(results);
    }

    private static void getPlace(Map<Integer, String> results) {
        Scanner in = new Scanner(System.in);
        System.out.print("\nPlease enter a place (1-" + results.size() + "): ");
        int place;
        try {
            place = in.nextInt();
        }
        catch (Exception e) {
            System.out.println("Please check the entered value and try again.");
            return;
        }
        try {
            System.out.println("Place " + place + ": " + results.values().toArray()[place-1] + " - " + results.keySet().toArray()[place-1]);
        }
        catch (Exception e) {
            System.out.println("You have entered an invalid place. Please try enter a value from the 1-" + results.size() + " range.");
        }
    }
}