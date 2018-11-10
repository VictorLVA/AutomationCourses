import java.util.*;
import java.util.stream.Collectors;

public class Task3L3 {
    public static void main(String[] args) {

        List<Student> allStudents = initStudents ();

        System.out.println("\nList of students:");
        printStudentsList(allStudents);

        System.out.println("\nLets sort students by age ASC:");
        allStudents = sortListByAge(allStudents);
        printStudentsList(allStudents);

        System.out.println("\nLets search");
        printStudentsList(searchListByLastname("1", allStudents));

        System.out.println("\nAverage age is: " + getListAverageAge(allStudents));

        System.out.println("\nLets create an allStudentsMap");
        Map<Integer, Student> allStudentsMap = createAllStudentsMap(allStudents);
        printStudentsMap(allStudentsMap);

        System.out.println("\nLets create a filterStudentsMap");
        Map<Integer, Student> filterStudentsMap = filterStudentsMap(allStudentsMap);
        printStudentsMap(filterStudentsMap);
    }

    private static List<Student> initStudents () {
        List<Student> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Student student = new Student();
            student.setId(i + 1);
            student.setName("Name" + (i + 1));
            student.setLastname(((int)(Math.random() * 2) + "Lastname" + (i + 1)));
            student.setAge(16 + (int)(Math.random() * (100 - 16)));
            list.add(student);
        }
        return list;
    }

    private static void printStudentsList(List<Student> list){
        System.out.print("Id:\t\t");
        System.out.print("Name:" + "\t\t");
        System.out.print("Lastname:" + "\t\t");
        System.out.print("Age:" + "\n");
        for (Student each : list) {
            System.out.print(each.getId() + "\t\t");
            System.out.print(each.getName() + "\t\t");
            System.out.print(each.getLastname() + "\t\t");
            System.out.println(each.getAge());
        }
    }

    private static void printStudentsMap(Map<Integer, Student> map){
        for (Map.Entry<Integer, Student> each : map.entrySet()) {
            System.out.println(each.getKey() + " - " + each.getValue().getName() + " " + each.getValue().getLastname());
        }
    }

    private static List<Student> sortListByAge(List<Student> list) {
        Comparator<Student> byAge = Comparator.comparingInt(Student::getAge);
        return list.stream()
                .sorted(byAge)
                .collect(Collectors.toList());
    }

    private static List<Student> searchListByLastname (String search, List<Student> list) {
        return list.stream()
                .filter(each -> each.getLastname().startsWith(search))
                .collect(Collectors.toList());
    }

    private static double getListAverageAge (List<Student> list) {
        return list.stream()
                .mapToDouble(each -> each.getAge())
                .average()
                .orElse(0);
    }

    private static Map<Integer, Student> createAllStudentsMap (List<Student> list) {
        return list.stream()
                .collect(Collectors.toMap(each -> each.getId(), each -> each));
    }

    private static Map<Integer, Student> filterStudentsMap (Map<Integer, Student> map) {
        return map.entrySet().stream()
                .filter(each -> each.getKey() > 5)
                .collect (Collectors.toMap(each -> each.getKey(), each -> each.getValue()));
    }
}