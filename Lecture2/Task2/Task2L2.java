import java.util.Scanner;

public class Task2L2 {

    private static final String MESSAGE_INVALID = "You have entered an invalid day's index";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] days = {"MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"};
        System.out.print("Please enter an index of a day within a week (1-7): ");
        byte day;
        try {
            day = in.nextByte();
        }
        catch (Exception e) {
            System.out.println(MESSAGE_INVALID);
            return;
        }
        try {
            System.out.println("You have selected a " + days[day - 1]);
        }
        catch (Exception e) {
            System.out.println(MESSAGE_INVALID);
        }
    }
}