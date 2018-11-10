import java.util.*;

public class Task4L3 {
    public static void main(String[] args) {

        List <Integer> arrayList = new ArrayList <> ( );
        List <Integer> linkedList = new LinkedList <> ( );
        initList(arrayList);
        initList(linkedList);

        System.out.print("ArrayList. ");
        findTimeToAdd(1000, arrayList);
        System.out.print("LinkedList. ");
        findTimeToAdd(1000, linkedList);

        System.out.print("ArrayList. ");
        findTimeToDelete(1000, arrayList);
        System.out.print("LinkedList. ");
        findTimeToDelete(1000, linkedList);

        System.out.print("ArrayList. ");
        findTimeToSearch(1000, arrayList);
        System.out.print("LinkedList. ");
        findTimeToSearch(1000, linkedList);
    }

    private static void initList (List<Integer> list) {
        for (int i = 0; i < 10000000; i++) {
           list.add((int)(Math.random() * 10000000));
        }
    }

    private static void findTimeToAdd (int count, List<Integer> list) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            list.add((int)(Math.random() * 10000000));
        }
        long finish = System.currentTimeMillis();
        long operationTime = finish - start;
        System.out.println("The adding operation time is: " + operationTime + "ms");
    }

    private static void findTimeToDelete (int count, List<Integer> list) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            list.remove((int)(Math.random() * 10000000));
        }
        long finish = System.currentTimeMillis();
        long operationTime = finish - start;
        System.out.println("The deleting operation time is: " + operationTime + "ms");
    }

    private static void findTimeToSearch (int count, List<Integer> list) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            list.get((int)(Math.random() * 10000000));
        }
        long finish = System.currentTimeMillis();
        long operationTime = finish - start;
        System.out.println("The searching operation time is: " + operationTime + "ms");
    }
}