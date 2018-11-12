import java.util.*;
import java.util.stream.Collectors;

public class Task1L3 {

    private static List<Voucher> listOfVouchers = new ArrayList<>();
    private static List<Voucher> listOfSelection = new ArrayList<>();
    private static Map<Integer, Voucher> mapOfSelection = new TreeMap<>();

    public static void main(String[] args) {
        createRandomVouchers();
        int selection =  getUserSelection();
        provideFirstOffer(listOfVouchers, selection);
        String advanced = needAdvancedSearch();
        if (advanced.equalsIgnoreCase("n")) {
            selectVoucher();
        }
        if (advanced.equalsIgnoreCase("y")) {
            searchDaysCount();
            searchFood();
            searchTransfer();
            provideFinalOffer();
        }
    }

    private static void createRandomVouchers() {
        for (int i = 50; i != 0; i--) {
            int creationType = (int)(Math.random()*3);
            if (creationType == 0) {
                RestVoucher restVoucher = new RestVoucher();
                restVoucher.initVoucher(i);
                listOfVouchers.add(restVoucher);
            }
            else if (creationType == 1){
                CureVoucher cureVoucher = new CureVoucher();
                cureVoucher.initVoucher(i);
                listOfVouchers.add(cureVoucher);
            }
            else {
                ShoppingVoucher shoppingVoucher = new ShoppingVoucher();
                shoppingVoucher.initVoucher(i);
                listOfVouchers.add(shoppingVoucher);
            }
        }
    }

    private static int getUserSelection() {
        int selection = 0;
        while (selection == 0) {
            System.out.print("Lets select needed type of the vouchers (1-Rest, 2-Cure, 3-Shopping): ");
            Scanner input = new Scanner ( System.in );
            int check;
            try {
                check = input.nextInt();
            }
            catch (Exception e) {
                continue;
            }
            if (check >= 1 && check <= 3) {
                selection = check;
            }
        }
        return selection;
    }

    private static void provideFirstOffer (List<Voucher> listOfVouchers, int selection) {
        if (selection == 1) {
            Comparator<Voucher> byCost = Comparator.comparingDouble(Voucher::getCost);
            listOfSelection = listOfVouchers.stream()
                    .filter(each -> each.getClass() == RestVoucher.class)
                    .sorted(byCost)
                    .collect(Collectors.toList());
            for (int i = 0; i < listOfSelection.size(); i++) {
                mapOfSelection.put(i+1, listOfSelection.get(i));
            }
        }
        else if (selection == 2) {
            Comparator<Voucher> byCost = Comparator.comparingDouble(Voucher::getCost);
            listOfSelection = listOfVouchers.stream()
                    .filter(each -> each.getClass() == CureVoucher.class)
                    .sorted(byCost)
                    .collect(Collectors.toList());
            for (int i = 0; i < listOfSelection.size(); i++) {
                mapOfSelection.put(i+1, listOfSelection.get(i));
            }
        }
        else {
            Comparator<Voucher> byCost = Comparator.comparingDouble(Voucher::getCost);
            listOfSelection = listOfVouchers.stream()
                    .filter(each -> each.getClass() == ShoppingVoucher.class)
                    .sorted(byCost)
                    .collect(Collectors.toList());
            for (int i = 0; i < listOfSelection.size(); i++) {
                mapOfSelection.put(i+1, listOfSelection.get(i));
            }
        }
        if (!mapOfSelection.isEmpty()) {
            for ( Map.Entry <Integer, Voucher> each : mapOfSelection.entrySet ( ) ) {
                System.out.print ( "ID: " + each.getKey ( ) + " " );
                each.getValue ( ).printVoucher ( );
            }
        }
        else {
            System.out.println("Sorry, we don't have such vouchers :(");
            System.exit(0);
        }
    }

    private static String needAdvancedSearch() {
        String advanced = null;
        while (advanced == null) {
            System.out.print ( "Do you want advanced filters? (y/n) " );
            Scanner input = new Scanner ( System.in );
            String check;
            check = input.nextLine ( );
            if (check.matches ( "[ynYN]" )) {
                advanced = check;
            }
        }
        return advanced;
    }

    private static void searchDaysCount() {
        int countDays = getUserCountDays();
        listOfSelection = listOfSelection.stream()
                .filter(each -> each.getCountDays() >= countDays)
                .collect(Collectors.toList());
        for (int i = 0; i < listOfSelection.size(); i++) {
            mapOfSelection.put(i+1, listOfSelection.get(i));
        }
    }

    private static int getUserCountDays() {
        int countDays = 0;
        while (countDays == 0) {
            System.out.print("How many days minimum you need? ");
            Scanner input = new Scanner ( System.in );
            int check;
            try {
                check = input.nextInt();
            }
            catch (Exception e) {
                continue;
            }
            if (check >= 1) {
                countDays = check;
            }
        }
        return countDays;
    }

    private static void searchFood() {
        String food = getUserFood();
        if (food.equalsIgnoreCase("n")) {
            listOfSelection = listOfSelection.stream()
                    .filter(each -> each.getFood().isBlank())
                    .collect(Collectors.toList());
            for (int i = 0; i < listOfSelection.size(); i++) {
                mapOfSelection.put(i+1, listOfSelection.get(i));
            }
        }
        else if (food.equalsIgnoreCase("y")) {
            listOfSelection = listOfSelection.stream()
                    .filter(each -> !each.getFood().isBlank())
                    .collect(Collectors.toList());
            for (int i = 0; i < listOfSelection.size(); i++) {
                mapOfSelection.put(i+1, listOfSelection.get(i));
            }
        }
    }

    private static String getUserFood() {
        String food = null;
        while (food == null) {
            System.out.print("Do you need food? (y/n) ");
            Scanner input = new Scanner(System.in);
            String check;
            check = input.nextLine();
            if (check.matches("[ynYN]")) {
                food = check;
            }
        }
        return food;
    }

    private static void searchTransfer() {
        String transfer = getUserTransfer();
        if (transfer.equalsIgnoreCase("n")) {
            listOfSelection = listOfSelection.stream()
                    .filter(each -> each.getTransfer().isBlank())
                    .collect(Collectors.toList());
            mapOfSelection.clear();
            for (int i = 0; i < listOfSelection.size(); i++) {
                mapOfSelection.put(i+1, listOfSelection.get(i));
            }
        }
        else if (transfer.equalsIgnoreCase("y")) {
            listOfSelection = listOfSelection.stream()
                    .filter(each -> !each.getTransfer().isBlank())
                    .collect(Collectors.toList());
            mapOfSelection.clear();
            for (int i = 0; i < listOfSelection.size(); i++) {
                mapOfSelection.put(i+1, listOfSelection.get(i));
            }
        }
    }

    private static String getUserTransfer() {
        String transfer = null;
        while (transfer == null) {
            System.out.print("Do you need transfer? (y/n) ");
            Scanner input = new Scanner ( System.in );
            String check;
            check = input.nextLine();
            if (check.matches("[ynYN]")) {
                transfer = check;
            }
        }
        return transfer;
    }

    private static void selectVoucher() {
        System.out.print ( "Lets select a voucher you like (by ID): " );
        Scanner input1 = new Scanner(System.in);
        int like = input1.nextInt();
        for ( Map.Entry <Integer, Voucher> each : mapOfSelection.entrySet()) {
            if (each.getKey() == like) {
                System.out.print("\nYou select:\n" + "ID: " + each.getKey() + " ");
                each.getValue().printVoucher();
            }
        }
    }

    private static void provideFinalOffer() {
        if (!mapOfSelection.isEmpty()) {
            for (Map.Entry<Integer, Voucher> each : mapOfSelection.entrySet()) {
                System.out.print ( "ID: " + each.getKey ( ) + " " );
                each.getValue ( ).printVoucher ( );
            }
            selectVoucher ( );
        }
        else {
            System.out.println("\nSorry, we don't have such vouchers :(");
        }
    }
}
