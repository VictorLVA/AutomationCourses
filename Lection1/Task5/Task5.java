import java.io.*;
import java.util.*;

public class Task5 {
    public static BufferedReader reader = new BufferedReader ( new InputStreamReader ( System.in ) );
    public static void main ( String[] args ) throws Exception {
        System.out.println ("Let's start the game!!!");
        Console console = System.console();
        console.printf("Please enter the secret name:");
        char[] secretName = console.readPassword();
        String name = new String(secretName);
        console.printf("Please enter the secret lastname:");
        char[] secretLastname = console.readPassword();
        String lastname = new String(secretLastname);
        System.out.println ( );
        System.out.println ("Now try to solve secret name and lastname ;)");
        List<String> listOfWords = new ArrayList<>(); // может в один лист все???
        List<String> listOfLetters = new ArrayList<>();
        char[] nameArray = name.toCharArray ( );
        char[] lastnameArray = lastname.toCharArray ( );
        char[] starNameArray = new char[name.length ( )];
        for (int i = 0 ; i < starNameArray.length; i++) {
            starNameArray[i] = '*';
        }
        char[] starLastnameArray = new char[lastname.length ()];
        for (int i = 0 ; i < starLastnameArray.length; i++) {
            starLastnameArray[i] = '*';
        }
        boolean checkResultName = false;
        boolean checkResultLastname = false;
        while (!checkResultName || !checkResultLastname) {
            System.out.println ( );
            System.out.print ("Your choice: ");
            String choice = reader.readLine ( );
            if (listOfWords.contains ( choice ) || listOfLetters.contains ( choice )) {
                System.out.println ("Again? Really? Try something else :)");
                continue;
            }
            if ((Arrays.equals(nameArray, starNameArray) && choice.equalsIgnoreCase ( name ))
                || (Arrays.equals(lastnameArray, starLastnameArray) && choice.equalsIgnoreCase ( lastname ))) {
                System.out.println ("Again? Really? Try something else :)");
                continue;
            }
            if (choice.length ( ) != name.length ( )
                    && choice.length ( ) != lastname.length ( )
                    && choice.length ( ) != 1
                    || !choice.matches ( "[a-zA-Z]+" )) {
                System.out.println ("You have to enter one letter or a whole name/lastname. Please try again.");
                continue;
            }
            if (choice.length ( ) != 1) {
                boolean checkRightMove = false;
                if (!Arrays.equals ( nameArray , starNameArray ) && choice.length ( ) == name.length ( )) {
                    if (choice.equalsIgnoreCase ( name )) {
                        System.arraycopy ( nameArray , 0 , starNameArray , 0 , nameArray.length );
                        checkRightMove = true;
                    }
                }
                if (!Arrays.equals ( lastnameArray , starLastnameArray ) && choice.length ( ) == lastname.length ( )) {
                    if (choice.equalsIgnoreCase ( lastname )) {
                        System.arraycopy ( lastnameArray , 0 , starLastnameArray , 0 , lastnameArray.length );
                        checkRightMove = true;
                    }
                }
                listOfWords.add ( choice );
                if (checkRightMove) {
                    printWholeWord ( starNameArray , starLastnameArray );
                }
                else {
                    printWrongMove ( );
                }

            }
            if (choice.length ( ) == 1 && choice.matches ( "[a-zA-Z]+" )) {
                boolean checkRightMove = false;
                if (!Arrays.equals ( nameArray , starNameArray )) {
                    for ( int i = 0 ; i < nameArray.length ; i++ ) {
                        if (choice.equalsIgnoreCase ( Character.toString ( nameArray[ i ] ) )) {
                            starNameArray[ i ] = nameArray[ i ];
                            checkRightMove = true;
                        }
                    }
                }
                if (!Arrays.equals ( lastnameArray , starLastnameArray )) {
                    for ( int i = 0 ; i < lastnameArray.length ; i++ ) {
                        if (choice.equalsIgnoreCase ( Character.toString ( lastnameArray[ i ] ) )) {
                            starLastnameArray[ i ] = lastnameArray[ i ];
                            checkRightMove = true;
                        }
                    }
                }
                listOfLetters.add ( choice );
                if (checkRightMove) {
                    if (Arrays.equals ( nameArray , starNameArray ) && !Arrays.equals ( lastnameArray , starLastnameArray )) {
                        printWholeWord ( starNameArray , starLastnameArray );
                    }
                    else if (!Arrays.equals ( nameArray , starNameArray ) && Arrays.equals ( lastnameArray , starLastnameArray )) {
                        printWholeWord ( starNameArray , starLastnameArray );
                    }
                    else if (Arrays.equals ( nameArray , starNameArray ) && Arrays.equals ( lastnameArray , starLastnameArray )) {
                        printWholeWord ( starNameArray , starLastnameArray );
                    }
                    else {
                        printRightMove ( starNameArray , starLastnameArray );
                    }
                }
                else {
                    printWrongMove ();
                }
            }
            checkResultName = Arrays.equals(nameArray, starNameArray);
            checkResultLastname = Arrays.equals(lastnameArray, starLastnameArray);
            if (checkResultName && checkResultLastname) {
                System.out.println ( );
                System.out.print ( "Wonderful!!! You did it!!! See you later..." );
                System.out.println ( );
            }
        }
    }

    private static void printStar ( char[] starNameArray , char[] starLastnameArray ) {
        for ( char eachStarNameChar : starNameArray ) {
            System.out.print ( eachStarNameChar );
        }
        System.out.print ( " " );
        for ( char eachStarLastnameChar : starLastnameArray ) {
            System.out.print ( eachStarLastnameChar );
        }
        System.out.println ( );
    }

    private static void printRightMove ( char[] starNameArray , char[] starLastnameArray ) {
        System.out.println ("Right. Please see the interim result: ");
        printStar ( starNameArray , starLastnameArray );
    }

    private static void printWrongMove ( ) {
        System.out.println ("Oops... It's a mistake. You can better!!! Let's continue.");
    }

    private static void printWholeWord ( char[] starNameArray , char[] starLastnameArray ) {
        System.out.println ("Wow, congratulations! The word(s) is(are) solved!");
        printStar ( starNameArray , starLastnameArray );
    }
}