import java.util.Scanner;

public class Task2L1 {
    public static void main ( String[] args ) {
        int mode = 0;
        int minMark = 1;
        int maxMark = 5;
        Scanner in = new Scanner ( System.in );
        System.out.print ( "Please select a mode (1-Common, 2-Star1, 3-Star2): " );
        mode = in.nextInt ( );
        if (mode == 1) {
            float sumMark = 0;
            float avgMark = 0;
            int StudentCount = 5;
            Student[] allstudents = new Student[ StudentCount ]; //зачем этот массив для решения задачи?
            StudentMark[] results = new StudentMark[ StudentCount ]; //зачем этот массив для решения задачи?
            for ( int i = 0 ; i < StudentCount ; i++ ) {
                int mark = minMark + (int) (Math.random ( ) * maxMark);
                results[ i ] = new StudentMark ( mark );
                sumMark += results[ i ].mark;
            }
            avgMark = sumMark / StudentCount;
            System.out.println ();
            System.out.println ( "Average mark for the group is: " + avgMark );
        }
        if (mode == 2) {
            float sumMark = 0;
            float avgMark = 0;
            int StudentCount = 5;
            int ticketsCount = 10;
            Student[] allstudents = new Student[ StudentCount ]; //зачем этот массив для решения задачи?
            Ticket[] alltickets = new Ticket[ ticketsCount ];
            StudentMark[] results = new StudentMark[ StudentCount ];
            StudentTicket[] studenttickets = new StudentTicket[StudentCount];
            for ( int i = 0 ; i < ticketsCount ; i++ ) {
                alltickets[ i ] = new Ticket ( i+1, "To be or not to be #"+(i+1)+"?" );
            }
            for ( int i = 0 ; i < StudentCount ; i++ ) {
                studenttickets[ i ] = new StudentTicket ( 0 );
            }
            System.out.println ();
            for ( int i = 0 ; i < StudentCount ; i++ ) {
                int ticket = 1 + (int) (Math.random ( ) * ticketsCount);
                while (studenttickets[i].id == 0) {
                    int check = 0;
                    for (int j = 0; j < StudentCount; j++){
                        if (ticket == studenttickets[j].id){
                            check++;
                        }
                    }
                    if (check==0){
                        studenttickets[ i ] = new StudentTicket ( ticket );
                    }
                    else {
                        ticket = 1 + (int) (Math.random ( ) * ticketsCount);
                    }
                }
                int mark = minMark + (int) (Math.random ( ) * maxMark);
                results[ i ] = new StudentMark ( mark );
                sumMark += results[ i ].mark;
                System.out.print ( "Student #"+(i+1)+" passed exam with a mark " + results[i].mark +". ");
                System.out.println ( "Ticket was: " + studenttickets[i].id+". Question was: "+alltickets[studenttickets[i].id-1].question);
            }
            avgMark = sumMark / StudentCount;
            System.out.println ();
            System.out.println ( "Average mark for the group is: " + avgMark );
            //for (int i = 0; i<StudentCount; i++) {
            //    System.out.print ( "Student #"+(i+1)+" passed exam with a mark " + results[i].mark +". ");
            //    System.out.println ( "Ticket was: " + studenttickets[i].id+". Question was: "+alltickets[studenttickets[i].id-1].question);
            //}
        }
        if (mode == 3) {
            int StudentCount = 5;
            int ticketsCount = 10;
            int groupCount = 2;
            Student[][] allstudents = new Student[ groupCount ][ StudentCount ];
            Ticket[] alltickets = new Ticket[ ticketsCount ];
            StudentMark[][] results = new StudentMark[ groupCount ] [ StudentCount ];
            StudentTicket[][] studenttickets = new StudentTicket[ groupCount ] [ StudentCount ];
            Group[] allgroups = new Group[ groupCount ];
            for ( int i = 0 ; i < ticketsCount ; i++ ) {
                alltickets[ i ] = new Ticket ( i + 1 , "To be or not to be #" + (i + 1) + "?" );
            }
            for ( int j = 0 ; j < groupCount ; j++ ) {
                for ( int i = 0 ; i < StudentCount ; i++ ) {
                    studenttickets[ j ][ i ] = new StudentTicket ( 0 );
                }
            }
            for ( int i = 0 ; i < groupCount ; i++ ) {
                allgroups[ i ] = new Group ( "Group #" + (i + 1) );
            }
            for ( int gr = 0 ; gr < groupCount ; gr++ ) {
                float sumMark = 0;
                float avgMark = 0;
                for ( int i = 0 ; i < StudentCount ; i++ ) {
                    allstudents[ gr ][ i ] = new Student ( "Name-"+(gr+1)+"-"+(i+1), "Lastname-"+(gr+1)+"-"+(i+1) );
                    int ticket = 1 + (int) (Math.random ( ) * ticketsCount);
                    while (studenttickets[ gr ][ i ].id == 0) {
                        int check = 0;
                        for ( int j = 0 ; j < StudentCount ; j++ ) {
                            if (ticket == studenttickets[ gr ][ j ].id) {
                                check++;
                            }
                        }
                        if (check == 0) {
                            studenttickets[ gr ][ i ] = new StudentTicket ( ticket );
                        } else {
                            ticket = 1 + (int) (Math.random ( ) * ticketsCount);
                        }
                    }
                    int mark = minMark + (int) (Math.random ( ) * maxMark);
                    results[ gr ][ i ] = new StudentMark ( mark );
                    sumMark += results[ gr ][ i ].mark;
                    //System.out.print ( "Student " + allstudents[gr][i].name);
                    //System.out.println ( ". Mark is " + results[gr][i].mark);
                }
                avgMark = sumMark / StudentCount;
                System.out.println ();
                System.out.println ( "Average mark for the "+allgroups[gr].number+" is: " + avgMark );
                int indexmin=0;
                int indexmax=0;
                int min=0;
                int max=0;
                for (int i = 1; i < StudentCount; i++) {
                    if (results[gr][i].mark < results[gr][indexmin].mark) {
                        indexmin = i;
                        min=results[gr][i].mark;}
                    else {
                        min=results[gr][indexmin].mark;
                        }

                    if (results[gr][i].mark > results[gr][indexmax].mark){
                        indexmax = i;
                        max=results[gr][i].mark;
                    }
                    else {
                        max=results[gr][indexmax].mark;
                    }
                }
                //System.out.println ( "IndexMin "+indexmin+ " Min "+min);
                //System.out.println ( "IndexMax "+indexmax+ " Max "+max);
                System.out.println ( "   The best student(s) in the " + allgroups[gr].number + ": ");
                for (int i = 0; i < StudentCount; i++) {
                    if (max == results[ gr ][ i ].mark) {
                        System.out.println ( "        - " + allstudents[ gr ][ i ].name + " " + allstudents[ gr ][ i ].lastName );
                    }
                }
                if ( min != max ) {
                    System.out.println ( "   The worst student(s) in the " + allgroups[gr].number + ": ");
                    for ( int i = 0 ; i < StudentCount ; i++ ) {
                        if (min == results[ gr ][ i ].mark) {
                            System.out.println ( "        - " + allstudents[ gr ][ i ].name + " " + allstudents[ gr ][ i ].lastName );
                        }
                    }
                }
            }
            int GRindexmin=0;
            int GRindexmax=0;
            int STindexmax=0;
            int STindexmin=0;
            int totalmin=0;
            int totalmax=0;
            for (int j = 0; j < groupCount; j++) {
                for ( int i = 1 ; i < StudentCount ; i++ ) {
                    if (results[ j ][ i ].mark < results[ GRindexmin ][ STindexmin ].mark) {
                        STindexmin = i;
                        GRindexmin = j;
                        totalmin = results[ j ][ i ].mark;
                    } else {
                        totalmin = results[ GRindexmin ][ STindexmin ].mark;
                    }

                    if (results[ j ][ i ].mark > results[ GRindexmax ][ STindexmax ].mark) {
                        STindexmax = i;
                        GRindexmax = j;
                        totalmax = results[ j ][ i ].mark;
                    } else {
                        totalmax = results[ GRindexmax ][ STindexmax ].mark;
                    }
                }
            }
            System.out.println ();
            System.out.println ( "Totally the best student(s):");
            for (int j = 0; j < groupCount; j++) {
                for ( int i = 0 ; i < StudentCount ; i++ ) {
                    if (totalmax == results[ j ][ i ].mark) {
                        System.out.println ( "     - " + allstudents[ j ][ i ].name + " " + allstudents[ j ][ i ].lastName );
                    }
                }
            }
            //System.out.println ("ttlMAX "+totalmax);
            //System.out.println ("ttlMIN "+totalmin);
            if (totalmin != totalmax) {
                System.out.println ();
                System.out.println ( "Totally the worst student(s):" );
                for (int j = 0; j < groupCount; j++) {
                    for ( int i = 0 ; i < StudentCount ; i++ ) {
                        if (totalmin == results[ j ][ i ].mark) {
                                 System.out.println ( "     - " + allstudents[ j ][ i ].name + " " + allstudents[ j ][ i ].lastName );
                        }
                    }
                }
            }
        }
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

class Ticket {
    int id;
    String question;

    public
    Ticket ( int id, String question ) {
        this.id = id;
        this.question = question;
    }
}

class StudentTicket {
    int id;

    public
    StudentTicket ( int id ) {
        this.id = id;
    }
}

class Group {
    String number;

    public
    Group ( String number ) {
        this.number = number;
    }
}