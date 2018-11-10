import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task2L3 {
    public static void main(String[] args) {

        List<Student> allStudents = initStudents ();

        System.out.println("\nList of students:");
        printStudentsList(allStudents);

        System.out.println("\nLets sort students by age ASC:");
        sortListByAge(allStudents);
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

    private static void sortListByAge(List<Student> list) {
        list.sort(new AgeComparatorAsc());
    }

    private static List<Student> searchListByLastname (String search, List<Student> list) {
        List<Student> afterSearchList = new ArrayList<>();
        for (Student each : list) {
            if (each.getLastname ( ).startsWith ( search )) {
                afterSearchList.add ( each );
            }
        }
        return afterSearchList;
    }

    private static double getListAverageAge (List<Student> list) {
        double averageAge = 0;
        for (Student each : list) {
            averageAge += each.getAge();
        }
        return averageAge / list.size();
    }

    private static Map<Integer, Student> createAllStudentsMap (List<Student> list) {
        Map<Integer, Student> allStudentsMap = new HashMap<>();
        for (Student each : list) {
            allStudentsMap.put(each.getId(), each);
        }
        return allStudentsMap;
    }

    private static Map<Integer, Student> filterStudentsMap (Map<Integer, Student> map) {
        Map<Integer, Student> filterStudentMap = new HashMap<>();
        for (Map.Entry<Integer, Student> each : map.entrySet()) {
            if (each.getKey() > 5) {
                filterStudentMap.put(each.getKey(), each.getValue());
            }
        }
        return filterStudentMap;
    }
}