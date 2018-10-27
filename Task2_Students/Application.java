import java.util.Scanner;

public class Application {
    public static void main ( String[] args ) {

        float sumMark = 0;
        int minMark = 1;
        int maxMark = 5;

        Scanner in = new Scanner ( System.in );
        System.out.print ( "Please enter a students count: " );
        int StudentCount = in.nextInt ( );

        Student[] allstudents = new Student[ StudentCount ];
        StudentMark[] results = new StudentMark[ StudentCount ];

        for ( int i = 0 ; i < StudentCount ; i++ ) {
             int mark = minMark + (int) (Math.random() * maxMark);
             results[ i ] = new StudentMark ( mark );
             sumMark += results[ i ].mark;
            }

        float avgMark = sumMark / StudentCount;
        System.out.println ( "Average mark for all students is: " + avgMark );
    }
}

class Student {
    String name;
    String lastName;

    public
    Student ( String name , String lastName ) {
        this.name = name;
        this.lastName = lastName;
    }
}

class StudentMark {
    int mark;

    public
    StudentMark ( int mark ) {
        this.mark = mark;
    }
}