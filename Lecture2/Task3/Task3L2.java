import java.util.Scanner;

public class Task3L2 {

    private static final String SYMBOL_BLANK_SPACE = " ";
    private static final String SYMBOL_CONTOUR = "*";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Please enter a rhombus size: ");
        int size;
        try {
            size = in.nextInt();
        } catch (Exception e) {
            System.out.println("Please check the entered value and try again");
            return;
        }
        if (size < 2) {
            System.out.println("You have entered an invalid rhombus size (should be 2 at least). Please try again");
            return;
        }
        drawRhombus(size);
    }

    private static void drawRhombus(int size) {
        int innerSpaceCount = 1;
        for (int i = 1; i < size * 2; i++) {
            if (i == 1) {
                for (int j = 1; j < size; j++) {
                    System.out.print(SYMBOL_BLANK_SPACE);
                }
                System.out.println(SYMBOL_CONTOUR);
            }
            if (i > 1 && i < size) {
                for (int j = 0; j < size - i; j++) {
                    System.out.print(SYMBOL_BLANK_SPACE);
                }
                System.out.print(SYMBOL_CONTOUR);
                for (int j = 0; j < innerSpaceCount; j++) {
                    System.out.print(SYMBOL_BLANK_SPACE);
                }
                System.out.println(SYMBOL_CONTOUR);
                innerSpaceCount += 2;
            }
            if (i >= size && i < size * 2 - 1) {
                for (int j = 0; j < i - size; j++) {
                    System.out.print(SYMBOL_BLANK_SPACE);
                }
                System.out.print(SYMBOL_CONTOUR);
                for (int j = 0; j < innerSpaceCount; j++) {
                    System.out.print(SYMBOL_BLANK_SPACE);
                }
                System.out.println(SYMBOL_CONTOUR);
                innerSpaceCount -= 2;
            }
            if (i == size * 2 - 1) {
                for (int j = 0; j < i - size; j++) {
                    System.out.print(SYMBOL_BLANK_SPACE);
                }
                System.out.print(SYMBOL_CONTOUR);
            }
        }
    }
}