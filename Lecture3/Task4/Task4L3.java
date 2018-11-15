import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Task4L3 {
    public static void main(String[] args) {

        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();
        initList(arrayList);
        initList(linkedList);
        System.out.print("\nArrayList. ");
        findTimeToAdd(1000, arrayList);
        System.out.print("LinkedList. ");
        findTimeToAdd(1000, linkedList);
        System.out.print("\nArrayList. ");
        findTimeToDelete(1000, arrayList);
        System.out.print("LinkedList. ");
        findTimeToDelete(1000, linkedList);
        System.out.print("\nArrayList. ");
        findTimeToSearch(1000, arrayList);
        System.out.print("LinkedList. ");
        findTimeToSearch(1000, linkedList);
    }

    private static void initList(List<Integer> anylist) {
        for (int i = 0; i < 10000000; i++) {
            anylist.add((int) (Math.random() * 10000000));
        }
    }

    private static void findTimeToAdd(int count, List<Integer> anylist) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            anylist.add((int) (Math.random() * 10000000));
        }
        long finish = System.currentTimeMillis();
        long operationTime = finish - start;
        System.out.println("The adding operation time is: " + operationTime + "ms");
    }

    private static void findTimeToDelete(int count, List<Integer> list) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            list.remove((int) (Math.random() * 10000000));
        }
        long finish = System.currentTimeMillis();
        long operationTime = finish - start;
        System.out.println("The deleting operation time is: " + operationTime + "ms");
    }

    private static void findTimeToSearch(int count, List<Integer> list) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            list.get((int) (Math.random() * 10000000));
        }
        long finish = System.currentTimeMillis();
        long operationTime = finish - start;
        System.out.println("The searching operation time is: " + operationTime + "ms");
    }
}