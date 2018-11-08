import java.util.Scanner;

public class Task3L2 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Please enter a rhombus size: ");
        int size;
        try {
            size = in.nextInt();
        }
        catch (Exception e) {
            System.out.println("Please check the entered value and try again");
            return;
        }
        if (size < 3) {
            System.out.println("You have entered an invalid rhombus size (should be 3 at least). Please try again");
            return;
        }
        drawRhombus(size);
    }

    private static void drawRhombus(int size) {
        String space = " ";
        int inSpaceCount = 1;
        for (int i = 1; i < size*2; i++) {
            if (i == 1) {
                System.out.println(space.repeat(size - i) + "*");
            }
            if (i > 1 && i < size) {
                System.out.println(space.repeat(size - i) + "*" + space.repeat(inSpaceCount) + "*");
                inSpaceCount += 2;
            }
            if (i >= size && i < size*2-1) {
                System.out.println(space.repeat(i - size) + "*" + space.repeat(inSpaceCount) + "*");
                inSpaceCount -= 2;
            }
            if (i == size*2-1) {
                System.out.println(space.repeat(i - size) + "*");
            }
        }
    }
}