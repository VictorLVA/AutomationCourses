import java.util.Scanner;

public class Task2L1 {

    public static void main(String[] args) {
        int mode;
        int minMark = 1;
        int maxMark = 5;
        Scanner in = new Scanner(System.in);
        System.out.print("Please select a mode (1-Common, 2-Star1, 3-Star2): ");
        mode = in.nextInt();
        if (mode == 1) {
            float sumMark = 0;
            int studentCount = 5;
            for (int i = 0; i < studentCount; i++) {
                int mark = passExam(minMark, maxMark);
                sumMark += mark;
            }
            float avgMark = sumMark / studentCount;
            System.out.println();
            System.out.println("Average mark for the group is: " + avgMark);
        }
        if (mode == 2) {
            float sumMark = 0;
            int studentCount = 5;
            int ticketsCount = 10;
            Ticket[] allTickets = new Ticket[ticketsCount];
            StudentMark[] results = new StudentMark[studentCount];
            StudentTicket[] studentTickets = new StudentTicket[studentCount];
            for (int i = 0; i < ticketsCount; i++) {
                allTickets[i] = new Ticket (i + 1, "To be or not to be #" + (i + 1) + "?");
            }
            nullTickets(studentCount, studentTickets);
            System.out.println();
            for (int i = 0; i < studentCount; i++) {
                int ticket = getTicket(ticketsCount);
                while (studentTickets[i].id == 0) {
                    int check = checkTicket(studentCount, studentTickets, ticket);
                    if (check == 0){
                        studentTickets[i] = new StudentTicket(ticket);
                    }
                    else {
                        ticket = getTicket(ticketsCount);
                    }
                }
                sumMark = saveResultsGetSum(minMark, maxMark, sumMark, results, i);
                System.out.print("Student #" + (i + 1) + " passed exam with a mark " + results[i].mark + ". ");
                System.out.println("Ticket was: " + studentTickets[i].id + ". Question was: " + allTickets[studentTickets[i].id - 1].question);
            }
            float avgMark = sumMark / studentCount;
            System.out.println();
            System.out.println("Average mark for the group is: " + avgMark);
        }
        if (mode == 3) {
            int studentCount = 5;
            int ticketsCount = 10;
            int groupCount = 2;
            Student[][] allStudents = new Student[groupCount][studentCount];
            StudentMark[][] results = new StudentMark[groupCount][studentCount];
            StudentTicket[][] studentTickets = new StudentTicket[groupCount][studentCount];
            Group[] allgroups = new Group[groupCount];
            for (int j = 0; j < groupCount; j++) {
                nullTickets(studentCount, studentTickets[j]);
            }
            for (int i = 0; i < groupCount; i++) {
                allgroups[i] = new Group ("Group #" + (i + 1));
            }
            for (int gr = 0; gr < groupCount; gr++) {
                float sumMark = 0;
                for (int i = 0; i < studentCount; i++) {
                    allStudents[gr][i] = new Student ("Name-" + (gr + 1) + "-" + (i + 1), "Lastname-" + (gr + 1) + "-" + (i + 1));
                    int ticket = getTicket(ticketsCount);
                    while (studentTickets[gr][i].id == 0) {
                        int check = checkTicket(studentCount, studentTickets[gr], ticket);
                        if (check == 0) {
                            studentTickets[gr][i] = new StudentTicket(ticket);
                        }
                        else {
                            ticket = getTicket(ticketsCount);
                        }
                    }
                    sumMark = saveResultsGetSum(minMark, maxMark, sumMark, results[gr], i);
                }
                float avgMark = sumMark / studentCount;
                System.out.println();
                System.out.println("Average mark for the " + allgroups[gr].number + " is: " + avgMark);
                int indexMin = 0;
                int min = 0;
                int indexMax = 0;
                int max = 0;
                for (int i = 1; i < studentCount; i++) {
                    if (results[gr][i].mark < results[gr][indexMin].mark) {
                        indexMin = i;
                        min = results[gr][i].mark;
                    }
                    else {
                        min = results[gr][indexMin].mark;
                    }
                    if (results[gr][i].mark > results[gr][indexMax].mark) {
                        indexMax = i;
                        max = results[gr][i].mark;
                    }
                    else {
                        max = results[gr][indexMax].mark;
                    }
                }
                System.out.println("   The best student(s) in the " + allgroups[gr].number + ": ");
                printStudentByMark(studentCount, max, results[gr], "        - ", allStudents[gr]);
                if (min != max) {
                    System.out.println("   The worst student(s) in the " + allgroups[gr].number + ": ");
                    printStudentByMark(studentCount, min, results[gr], "        - ", allStudents[gr]);
                }
            }
            int grIndexMin = 0;
            int grIndexMax = 0;
            int stIndexMax = 0;
            int stIndexMin = 0;
            int totalMin = 0;
            int totalMax = 0;
            for (int j = 0; j < groupCount; j++) {
                for (int i = 1; i < studentCount; i++) {
                    if (results[j][i].mark < results[grIndexMin][stIndexMin].mark) {
                        stIndexMin = i;
                        grIndexMin = j;
                        totalMin = results[j][i].mark;
                    }
                    else {
                        totalMin = results[grIndexMin][stIndexMin].mark;
                    }
                    if (results[j][i].mark > results[grIndexMax][stIndexMax].mark) {
                        stIndexMax = i;
                        grIndexMax = j;
                        totalMax = results[j][i].mark;
                    }
                    else {
                        totalMax = results[grIndexMax][stIndexMax].mark;
                    }
                }
            }
            System.out.println();
            System.out.println("Totally the best student(s):");
            for (int j = 0; j < groupCount; j++) {
                printStudentByMark(studentCount, totalMax, results[j], "     - ", allStudents[j]);
            }
            if (totalMin != totalMax) {
                System.out.println();
                System.out.println("Totally the worst student(s):");
                for (int j = 0; j < groupCount; j++) {
                    printStudentByMark(studentCount, totalMin, results[j], "     - ", allStudents[j]);
                }
            }
        }
    }

    private static void printStudentByMark(int studentCount, int value, StudentMark[] result, String s, Student[] allStudent) {
        for (int i = 0; i < studentCount; i++) {
            if (value == result[i].mark) {
                System.out.println(s + allStudent[i].name + " " + allStudent[i].lastName);
            }
        }
    }

    private static void nullTickets(int studentCount, StudentTicket[] studentTickets) {
        for (int i = 0; i < studentCount; i++) {
            studentTickets[i] = new StudentTicket(0);
        }
    }

    private static float saveResultsGetSum(int minMark, int maxMark, float sumMark, StudentMark[] results, int i) {
        int mark = passExam(minMark, maxMark);
        results[i] = new StudentMark(mark);
        sumMark += results[i].mark;
        return sumMark;
    }

    private static int checkTicket(int studentCount, StudentTicket[] studentTickets, int ticket) {
        int check = 0;
        for (int j = 0; j < studentCount; j++) {
            if (ticket == studentTickets[j].id) {
                check++;
            }
        }
        return check;
    }

    private static int getTicket(int ticketsCount) {
        return 1 + (int)(Math.random() * ticketsCount);
    }

    private static int passExam(int minMark, int maxMark) {
        return minMark + (int)(Math.random() * maxMark);
    }
}

class Student {
    String name;
    String lastName;

    Student (String name , String lastName) {
        this.name = name;
        this.lastName = lastName;
    }
}

class StudentMark {
    int mark;

    StudentMark (int mark) {
        this.mark = mark;
    }
}

class Ticket {
    int id;
    String question;

    Ticket (int id, String question) {
        this.id = id;
        this.question = question;
    }
}

class StudentTicket {
    int id;

    StudentTicket (int id) {
        this.id = id;
    }
}

class Group {
    String number;

    Group (String number) {
        this.number = number;
    }
}