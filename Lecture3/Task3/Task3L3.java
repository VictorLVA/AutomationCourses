import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Task3L3 {
    public static void main(String[] args) {
        List<Student> allStudents = initListOfStudents();
        System.out.println("\nList of students:");
        printStudentsList(allStudents);
        System.out.println("\nLets sort students by age ASC:");
        allStudents = sortListByAge(allStudents);
        printStudentsList(allStudents);
        System.out.println("\nLets search:");
        printStudentsList(searchStudentsByLastname("1", allStudents));
        System.out.println("\nAverage age is: " + getStudentsAverageAge(allStudents));
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

    private static List<Student> sortListByAge(List<Student> listOfStudents) {
        Comparator<Student> byAge = Comparator.comparingInt(Student::getAge);
        return listOfStudents.stream()
                             .sorted(byAge)
                             .collect(Collectors.toList());
    }

    private static List<Student> searchStudentsByLastname(String search, List<Student> listOfStudents) {
        return listOfStudents.stream()
                             .filter(studentFromList -> studentFromList.getLastname().startsWith(search))
                             .collect(Collectors.toList());
    }

    private static double getStudentsAverageAge(List<Student> listOfStudents) {
        return listOfStudents.stream()
                             .mapToDouble(studentFromList -> studentFromList.getAge())
                             .average()
                             .orElse(0);
    }

    private static Map<Integer, Student> createAllStudentsMap(List<Student> listOfStudents) {
        return listOfStudents.stream()
                             .collect(Collectors.toMap(studentFromList -> studentFromList.getId(), studentFromList -> studentFromList));
    }

    private static Map<Integer, Student> filterStudentsMap(Map<Integer, Student> mapOfStudents) {
        return mapOfStudents.entrySet().stream()
                            .filter(studentFromMap -> studentFromMap.getKey() > 5)
                            .collect(Collectors.toMap(studentFromMap -> studentFromMap.getKey(), studentFromMap -> studentFromMap.getValue()));
    }
}