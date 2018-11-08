import java.io.*;
import java.util.*;

public class Task5L2 {

    private static final String REGEX_NAME_LASTNAME = "[a-zA-Z]+";
    private static final String MESSAGE_AGAIN = "Again? Really? Try something else :)";

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception {
        System.out.println("Let's start the game!!!");
        Console console = System.console();
        String name = null;
        while (name == null) {
            name = getWord(console);
        }
        String lastname = null;
        while (lastname == null) {
            lastname = getWord(console);
        }
        System.out.println("\nNow try to solve secret name and lastname ;)");
        GameData gameData = new GameData ();
        gameData.initGameData(name, lastname);
        for (int i = 0; i < name.length (); i++) {
            gameData.starNameArray[i] = '*';
        }
        for (int i = 0; i < lastname.length (); i++) {
            gameData.starLastnameArray[i] = '*';
        }
        while (checkNotFinished(gameData)) {
            System.out.print("\nYour choice: ");
            String choice = reader.readLine();
            if (gameData.listOfWords.contains(choice) || gameData.listOfLetters.contains(choice)) {
                System.out.println(MESSAGE_AGAIN);
                continue;
            }
            if (checkChoiceAlreadySolved(gameData, choice, name, lastname)) {
                System.out.println(MESSAGE_AGAIN);
                continue;
            }
            if (checkInvalidInput(choice, name, lastname)) {
                System.out.println("You have to enter one letter or a whole name/lastname. Please try again.");
                continue;
            }
            if (choice.length() != 1) {
                wordProcessing(choice, name, lastname, gameData);
            }
            if (choice.length() == 1) {
                letterProcessing(choice, gameData);
            }
            finishGameMessage(!checkNotFinished(gameData));
        }
    }

    private static void printStar(GameData gameData) {
        for (char eachStarNameChar : gameData.starNameArray) {
            System.out.print(eachStarNameChar);
        }
        System.out.print(" ");
        for (char eachStarLastnameChar : gameData.starLastnameArray) {
            System.out.print(eachStarLastnameChar);
        }
        System.out.println();
    }

    private static void printRightMove(GameData gameData) {
        System.out.println("Right. Please see the interim result: ");
        printStar(gameData);
    }

    private static void printWrongMove() {
        System.out.println("Oops... It's a mistake. You can better!!! Let's continue.");
    }

    private static void printWholeWord(GameData gameData) {
        System.out.println("Wow, congratulations! The word(s) is(are) solved!");
        printStar(gameData);
    }

    private static void letterMessageValidation(boolean isRightMoveForName, boolean isRightMoveForLastname, GameData gameData) {
        if (isRightMoveForName && isRightMoveForLastname) {
            if (Arrays.equals(gameData.nameArray, gameData.starNameArray)
                    || Arrays.equals(gameData.lastnameArray, gameData.starLastnameArray)) {
                printWholeWord(gameData);
            }
            else {
                printRightMove(gameData);
            }
        }
        else if (isRightMoveForName) {
            if (Arrays.equals(gameData.nameArray, gameData.starNameArray)) {
                printWholeWord(gameData);
            }
            else {
                printRightMove(gameData);
            }
        }
        else if (isRightMoveForLastname) {
            if (Arrays.equals(gameData.lastnameArray, gameData.starLastnameArray)) {
                printWholeWord(gameData);
            }
            else printRightMove(gameData);
        }
        else {
            printWrongMove();
        }
    }

    private static void finishGameMessage(boolean isFinished) {
        if (isFinished) {
            System.out.print("\nWonderful!!! You did it!!! See you later...\n");
        }
    }

    private static boolean checkLetterRightMove(String choice, char[] array, char[] starArray) {
        boolean isLetterRightMove = false;
        for (int i = 0; i < array.length; i++) {
            if (choice.equalsIgnoreCase(Character.toString(array[i]))) {
                starArray[i] = array[i];
                isLetterRightMove = true;
            }
        }
        return isLetterRightMove;
    }

    private static void letterProcessing(String choice, GameData gameData) {
        boolean isRightMoveForName = false;
        boolean isRightMoveForLastname = false;
        if (!Arrays.equals(gameData.nameArray, gameData.starNameArray)) {
            isRightMoveForName = checkLetterRightMove(choice, gameData.nameArray, gameData.starNameArray);
        }
        if (!Arrays.equals(gameData.lastnameArray, gameData.starLastnameArray)) {
            isRightMoveForLastname = checkLetterRightMove(choice, gameData.lastnameArray, gameData.starLastnameArray);
        }
        gameData.listOfLetters.add(choice);
        letterMessageValidation(isRightMoveForName, isRightMoveForLastname, gameData);
    }

    private static void wordMessageValidation(boolean isWordOpened, GameData gameData) {
        if (isWordOpened) {
            printWholeWord(gameData);
        }
        else {
            printWrongMove();
        }
    }

    private static boolean checkArrayCopy(char[] array, char[] starArray) {
        System.arraycopy(array, 0, starArray, 0, array.length);
        return true;
    }

    private static void wordProcessing(String choice, String name, String lastname, GameData gameData) {
        boolean isWordOpened = false;
        if (choice.equalsIgnoreCase(name)) {
            isWordOpened = checkArrayCopy(gameData.nameArray, gameData.starNameArray);
        }
        if (choice.equalsIgnoreCase(lastname)) {
            isWordOpened = checkArrayCopy(gameData.lastnameArray, gameData.starLastnameArray);
        }
        gameData.listOfWords.add(choice);
        wordMessageValidation(isWordOpened, gameData);
    }

    private static String getWord(Console console) {
        String word = null;
        console.printf("Please enter a word ("+ REGEX_NAME_LASTNAME + "):");
        char[] secret = console.readPassword();
        String check = new String(secret);
        if (check.matches(REGEX_NAME_LASTNAME)) {
            word = check;
        }
        return word;
    }

    private static boolean checkChoiceAlreadySolved(GameData gameData, String choice, String name, String lastname) {
        return Arrays.equals(gameData.nameArray, gameData.starNameArray) && choice.equalsIgnoreCase(name)
                || Arrays.equals(gameData.lastnameArray, gameData.starLastnameArray) && choice.equalsIgnoreCase(lastname);
    }

    private static boolean checkInvalidInput(String choice, String name, String lastname) {
        return choice.length() != name.length()
                && choice.length() != lastname.length()
                && choice.length() != 1
                || !choice.matches(REGEX_NAME_LASTNAME);
    }

    private static boolean checkNotFinished(GameData gameData) {
        return !Arrays.equals(gameData.nameArray, gameData.starNameArray)
                || !Arrays.equals(gameData.lastnameArray, gameData.starLastnameArray);
    }
}

class GameData {
    List<String> listOfWords;
    List<String> listOfLetters;
    char[] nameArray;
    char[] lastnameArray;
    char[] starNameArray;
    char[] starLastnameArray;

    void initGameData(String name, String lastname) {
        this.listOfWords = new ArrayList<>();
        this.listOfLetters = new ArrayList<>();
        this.nameArray = name.toCharArray();
        this.lastnameArray = lastname.toCharArray();
        this.starNameArray = new char[name.length()];
        this.starLastnameArray = new char[lastname.length()];
    }
}