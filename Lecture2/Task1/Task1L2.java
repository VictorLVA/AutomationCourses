import java.util.Scanner;

public class Task1L2 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Please enter a sentence: ");
        String sentence = in.nextLine();
        System.out.print("Lets reverse: \"");
        for (int i = sentence.length() - 1; i >= 0; i--) {
            System.out.print(sentence.charAt(i));
        }
        System.out.println("\"");
        String[] words = sentence.split(" ");
        System.out.println("Lets split by words:");
        for(String eachWord : words) {
            System.out.println("\"" + eachWord + "\"");
        }
        System.out.println("Lets replace spaces with *: \"" + sentence.replace(' ', '*') + "\"");
        System.out.println("Lets rewrite it with uppercase: \"" + sentence.toUpperCase() + "\"");
        if (sentence.length() < 10) {
            System.out.println("Lets get the 5-10 symbols: \"" + sentence.substring(4) + "\"");
        }
        else {
            System.out.println("Lets get the 5-10 symbols: \"" + sentence.substring(4, 10) + "\"");
        }
    }
}