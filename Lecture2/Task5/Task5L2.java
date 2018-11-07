import java.io.*;
import java.util.*;

public class Task5L2 {

    private static final String REGEX_NAME_LASTNAME = "[a-zA-Z]+";
    private static final String MESSAGE_AGAIN = "Again? Really? Try something else :)";

    private static BufferedReader reader = new BufferedReader ( new InputStreamReader ( System.in ) );

    public static
    void main ( String[] args ) throws Exception {
        System.out.println ( "Let's start the game!!!" );
        Console console = System.console ( );
        String name = null;
        while (name == null)
            name = getWord ( console );
        String lastname = null;
        while (lastname == null)
            lastname = getWord ( console );
        System.out.println ( "\nNow try to solve secret name and lastname ;)" );
        List <String> listOfWords = new ArrayList <> ( );
        List <String> listOfLetters = new ArrayList <> ( );
        char[] nameArray = name.toCharArray ( );
        char[] lastnameArray = lastname.toCharArray ( );
        char[] starNameArray = new char[ name.length ( ) ];
        for ( int i = 0 ; i < starNameArray.length ; i++ )
            starNameArray[ i ] = '*';
        char[] starLastnameArray = new char[ lastname.length ( ) ];
        for ( int i = 0 ; i < starLastnameArray.length ; i++ )
            starLastnameArray[ i ] = '*';
        boolean checkResultName = false;
        boolean checkResultLastname = false;
        while (!checkResultName || !checkResultLastname) {
            System.out.print ( "\nYour choice: " );
            String choice = reader.readLine ( );
            if (listOfWords.contains ( choice ) || listOfLetters.contains ( choice )) {
                System.out.println ( MESSAGE_AGAIN );
                continue;
            }
            if ((Arrays.equals ( nameArray , starNameArray ) && choice.equalsIgnoreCase ( name )) || (Arrays.equals ( lastnameArray , starLastnameArray ) && choice.equalsIgnoreCase ( lastname ))) {
                System.out.println ( MESSAGE_AGAIN );
                continue;
            }
            if (choice.length ( ) != name.length ( ) && choice.length ( ) != lastname.length ( ) && choice.length ( ) != 1 || !choice.matches ( REGEX_NAME_LASTNAME )) {
                System.out.println ( "You have to enter one letter or a whole name/lastname. Please try again." );
                continue;
            }
            if (choice.length ( ) != 1)
                wordProcessing ( nameArray , starNameArray , choice , name , lastnameArray , starLastnameArray , lastname , listOfWords );
            if (choice.length ( ) == 1)
                letterProcessing ( nameArray , starNameArray , choice , lastnameArray , starLastnameArray , listOfLetters );
            checkResultName = Arrays.equals ( nameArray , starNameArray );
            checkResultLastname = Arrays.equals ( lastnameArray , starLastnameArray );
            finishGameValidation ( checkResultName , checkResultLastname );
        }
    }

    private static void printStar ( char[] starNameArray , char[] starLastnameArray ) {
        for ( char eachStarNameChar : starNameArray )
            System.out.print ( eachStarNameChar );
        System.out.print ( " " );
        for ( char eachStarLastnameChar : starLastnameArray )
            System.out.print ( eachStarLastnameChar );
        System.out.println ( );
    }

    private static void printRightMove ( char[] starNameArray , char[] starLastnameArray ) {
        System.out.println ( "Right. Please see the interim result: " );
        printStar ( starNameArray , starLastnameArray );
    }

    private static void printWrongMove () {
        System.out.println ( "Oops... It's a mistake. You can better!!! Let's continue." );
    }

    private static void printWholeWord ( char[] starNameArray , char[] starLastnameArray ) {
        System.out.println ( "Wow, congratulations! The word(s) is(are) solved!" );
        printStar ( starNameArray , starLastnameArray );
    }

    private static void letterMessageValidation ( boolean checkRightMoveName , boolean checkRightMoveLastname , char[] nameArray , char[] lastnameArray , char[] starNameArray , char[] starLastnameArray ) {
        if (checkRightMoveName && checkRightMoveLastname)
            if (Arrays.equals ( nameArray , starNameArray ) || Arrays.equals ( lastnameArray , starLastnameArray ))
                printWholeWord ( starNameArray , starLastnameArray );
            else printRightMove ( starNameArray , starLastnameArray );
        else if (checkRightMoveName)
            if (Arrays.equals ( nameArray , starNameArray ))
                printWholeWord ( starNameArray , starLastnameArray );
            else printRightMove ( starNameArray , starLastnameArray );
        else if (checkRightMoveLastname)
            if (Arrays.equals ( lastnameArray , starLastnameArray ))
                printWholeWord ( starNameArray , starLastnameArray );
            else printRightMove ( starNameArray , starLastnameArray );
        else printWrongMove ( );
    }

    private static void finishGameValidation ( boolean checkResultName , boolean checkResultLastname ) {
        if (checkResultName && checkResultLastname)
            System.out.print ( "\nWonderful!!! You did it!!! See you later...\n" );
    }

    private static boolean letterMoveValidation ( String choice , int arraylength , char[] array , char[] starArray , boolean checkRightMove ) {
        for ( int i = 0 ; i < arraylength ; i++ )
            if (choice.equalsIgnoreCase ( Character.toString ( array[ i ] ) )) {
                starArray[ i ] = array[ i ];
                checkRightMove = true;
            }
        return checkRightMove;
    }

    private static void letterProcessing ( char[] nameArray , char[] starNameArray , String choice , char[] lastnameArray , char[] starLastnameArray , List <String> listOfLetters ) {
        boolean checkRightMoveName = false;
        boolean checkRightMoveLastname = false;
        if (!Arrays.equals ( nameArray , starNameArray ))
            checkRightMoveName = letterMoveValidation ( choice , nameArray.length , nameArray , starNameArray , checkRightMoveName );
        if (!Arrays.equals ( lastnameArray , starLastnameArray ))
            checkRightMoveLastname = letterMoveValidation ( choice , lastnameArray.length , lastnameArray , starLastnameArray , checkRightMoveLastname );
        listOfLetters.add ( choice );
        letterMessageValidation ( checkRightMoveName , checkRightMoveLastname , nameArray , lastnameArray , starNameArray , starLastnameArray );
    }

    private static void wordMessageValidation ( boolean checkRightMove , char[] starNameArray , char[] starLastnameArray ) {
        if (checkRightMove)
            printWholeWord ( starNameArray , starLastnameArray );
        else printWrongMove ( );
    }

    private static boolean wordMoveValidation ( char[] array , char[] starArray ) {
        System.arraycopy ( array , 0 , starArray , 0 , array.length );
        return true;
    }

    private static void wordProcessing ( char[] nameArray , char[] starNameArray , String choice , String name , char[] lastnameArray , char[] starLastnameArray , String lastname , List <String> listOfWords ) {
        boolean checkRightMove = false;
        if (!Arrays.equals ( nameArray , starNameArray ) && choice.length ( ) == name.length ( ) && choice.equalsIgnoreCase ( name ))
            checkRightMove = wordMoveValidation ( nameArray , starNameArray );
        if (!Arrays.equals ( lastnameArray , starLastnameArray ) && choice.length ( ) == lastname.length ( ) && choice.equalsIgnoreCase ( lastname ))
            checkRightMove = wordMoveValidation ( lastnameArray , starLastnameArray );
        listOfWords.add ( choice );
        wordMessageValidation ( checkRightMove , starNameArray , starLastnameArray );
    }

    private static String getWord ( Console console ) {
        String word = null;
        console.printf ( "Please enter a word (name or lastname):" );
        char[] secret = console.readPassword ( );
        String check = new String ( secret );
        if (check.matches ( REGEX_NAME_LASTNAME )) {
            word = check;
        }
        return word;
    }
}