import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task2L3 {
    public static void main(String[] args) {
        List<Student> allStudents = initListOfStudents();
        System.out.println("\nList of students:");
        printStudentsList(allStudents);
        System.out.println("\nLets sort students by age ASC:");
        sortListByAge(allStudents);
        printStudentsList(allStudents);
        System.out.println("\nLets search:");
        printStudentsList(searchStudentsByLastname("1", allStudents));
        System.out.println("\nAverage age is: " + getListAverageAge(allStudents));
        System.out.println("\nLets create an allStudentsMap:");
        Map<Integer, Student> allStudentsMap = createAllStudentsMap(allStudents);
        printStudentsMap(allStudentsMap);
        System.out.println("\nLets create a filteredStudentsMap:");
        Map<Integer, Student> filteredStudentsMap = filterStudentsMap(allStudentsMap);
        printStudentsMap(filteredStudentsMap);
    }

    private static List<Student> initListOfStudents() {
        List<Student> listOfStudents = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Student student = new Student(i);
            listOfStudents.add(student);
        }
        return listOfStudents;
    }

    private static void printStudentsList(List<Student> listOfStudents) {
        for (Student studentFromList : listOfStudents) {
            System.out.print(studentFromList);
        }
    }

    private static void printStudentsMap(Map<Integer, Student> mapOfStudents) {
        for (Map.Entry<Integer, Student> studentFromMap : mapOfStudents.entrySet()) {
            System.out.print(studentFromMap);
        }
    }

    private static void sortListByAge(List<Student> listOfStudents) {
        listOfStudents.sort(new AgeComparatorAsc());
    }

    private static List<Student> searchStudentsByLastname(String search, List<Student> listOfStudents) {
        List<Student> afterSearchList = new ArrayList<>();
        for (Student studentFromList : listOfStudents) {
            if (studentFromList.getLastname().startsWith(search)) {
                afterSearchList.add(studentFromList);
            }
        }
        return afterSearchList;
    }

    private static double getListAverageAge(List<Student> listOfStudents) {
        double averageAge = 0;
        for (Student studentFromList : listOfStudents) {
            averageAge += studentFromList.getAge();
        }
        return averageAge / listOfStudents.size();
    }

    private static Map<Integer, Student> createAllStudentsMap(List<Student> listOfStudents) {
        Map<Integer, Student> allStudentsMap = new HashMap<>();
        for (Student studentFromList : listOfStudents) {
            allStudentsMap.put(studentFromList.getId(), studentFromList);
        }
        return allStudentsMap;
    }

    private static Map<Integer, Student> filterStudentsMap(Map<Integer, Student> mapOfStudents) {
        Map<Integer, Student> filteredStudentMap = new HashMap<>();
        for (Map.Entry<Integer, Student> studentFromMap : mapOfStudents.entrySet()) {
            if (studentFromMap.getKey() > 5) {
                filteredStudentMap.put(studentFromMap.getKey(), studentFromMap.getValue());
            }
        }
        return filteredStudentMap;
    }
}