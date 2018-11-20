import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Task1L3 {
    private static final String MESSAGE_TRY_AGAIN = "Please check the entered value. ";
    private static final String MESSAGE_SORRY = "\nSorry, we don't have such vouchers :(";

    private static List<Voucher> listOfVouchers = new ArrayList<>();
    private static List<Voucher> listOfSelection = new ArrayList<>();

    public static void main(String[] args) {
        createRandomVouchers();
        provideFirstOffer(getUserSelection());
        if (!isAdvancedSearchNeeded()) {
            selectVoucher();
        } else {
            searchMinimumDaysCount();
            searchWithFood();
            searchWithTransfer();
            provideFinalOffer();
        }
    }

    private static void createRandomVouchers() {
        for (int i = 20; i != 0; i--) {
            int creationType = (int) (Math.random() * 3);
            switch (creationType) {
                case 0:
                    Voucher restVoucher = new RestVoucher(i);
                    listOfVouchers.add(restVoucher);
                    break;
                case 1:
                    Voucher cureVoucher = new CureVoucher(i);
                    listOfVouchers.add(cureVoucher);
                    break;
                case 2:
                    Voucher shoppingVoucher = new ShoppingVoucher(i);
                    listOfVouchers.add(shoppingVoucher);
            }
        }
    }

    private static int getUserSelection() {
        int selection = 0;
        while (selection == 0) {
            System.out.print("Lets select needed type of the vouchers (1-Rest, 2-Cure, 3-Shopping): ");
            Scanner input = new Scanner(System.in);
            int userInput;
            try {
                userInput = input.nextInt();
            } catch (Exception e) {
                System.out.print(MESSAGE_TRY_AGAIN);
                continue;
            }
            if (userInput >= 1 && userInput <= 3) {
                selection = userInput;
            } else {
                System.out.print(MESSAGE_TRY_AGAIN);
            }
        }
        return selection;
    }

    private static void provideFirstOffer(int selection) {
        Comparator<Voucher> byCost = Comparator.comparingDouble(Voucher::getCost);
        switch (selection) {
            case 1:
                listOfSelection = listOfVouchers.stream()
                                                .filter(voucher -> voucher.getClass() == RestVoucher.class)
                                                .sorted(byCost)
                                                .collect(Collectors.toList());
                break;
            case 2:
                listOfSelection = listOfVouchers.stream()
                                                .filter(voucher -> voucher.getClass() == CureVoucher.class)
                                                .sorted(byCost)
                                                .collect(Collectors.toList());
                break;
            case 3:
                listOfSelection = listOfVouchers.stream()
                                                .filter(voucher -> voucher.getClass() == ShoppingVoucher.class)
                                                .sorted(byCost)
                                                .collect(Collectors.toList());
        }
        if (!listOfSelection.isEmpty()) {
            for (int i = 0; i < listOfSelection.size(); i++) {
                System.out.print("ID: " + (i + 1) + " | " + listOfSelection.get(i));
            }
        } else {
            System.out.println(MESSAGE_SORRY);
            System.exit(0);
        }
    }

    private static boolean isAdvancedSearchNeeded() {
        String userAnswer = null;
        while (userAnswer == null) {
            System.out.print("Do you want advanced filters? (y/n) ");
            Scanner input = new Scanner(System.in);
            String userInput;
            userInput = input.nextLine();
            if (userInput.matches("[ynYN]")) {
                userAnswer = userInput;
            } else {
                System.out.print(MESSAGE_TRY_AGAIN);
            }
        }
        return (userAnswer.equalsIgnoreCase("y"));
    }

    private static void searchMinimumDaysCount() {
        int countDays = getUserCountDays();
        listOfSelection = listOfSelection.stream()
                                         .filter(voucher -> voucher.getCountDays() >= countDays)
                                         .collect(Collectors.toList());
    }

    private static int getUserCountDays() {
        int countDays = 0;
        while (countDays == 0) {
            System.out.print("How many days minimum you need? ");
            Scanner input = new Scanner(System.in);
            int userInput;
            try {
                userInput = input.nextInt();
            } catch (Exception e) {
                System.out.print(MESSAGE_TRY_AGAIN);
                continue;
            }
            if (userInput >= 1) {
                countDays = userInput;
            } else {
                System.out.print(MESSAGE_TRY_AGAIN);
            }
        }
        return countDays;
    }

    private static void searchWithFood() {
        if (!isUserNeedFood()) {
            listOfSelection = listOfSelection.stream()
                                             .filter(voucher -> voucher.getFood().isBlank())
                                             .collect(Collectors.toList());
        } else {
            listOfSelection = listOfSelection.stream()
                                             .filter(voucher -> !voucher.getFood().isBlank())
                                             .collect(Collectors.toList());
        }
    }

    private static boolean isUserNeedFood() {
        String food = null;
        while (food == null) {
            System.out.print("Do you need food? (y/n) ");
            Scanner input = new Scanner(System.in);
            String userInput;
            userInput = input.nextLine();
            if (userInput.matches("[ynYN]")) {
                food = userInput;
            } else {
                System.out.print(MESSAGE_TRY_AGAIN);
            }
        }
        return food.equalsIgnoreCase("y");
    }

    private static void searchWithTransfer() {
        if (!isUserNeedTransfer()) {
            listOfSelection = listOfSelection.stream()
                                             .filter(each -> each.getTransfer().isBlank())
                                             .collect(Collectors.toList());
        } else {
            listOfSelection = listOfSelection.stream()
                                             .filter(each -> !each.getTransfer().isBlank())
                                             .collect(Collectors.toList());
        }
    }

    private static boolean isUserNeedTransfer() {
        String transfer = null;
        while (transfer == null) {
            System.out.print("Do you need transfer? (y/n) ");
            Scanner input = new Scanner(System.in);
            String userInput;
            userInput = input.nextLine();
            if (userInput.matches("[ynYN]")) {
                transfer = userInput;
            } else {
                System.out.print(MESSAGE_TRY_AGAIN);
            }
        }
        return transfer.equalsIgnoreCase("y");
    }

    private static void selectVoucher() {
        int likeVoucherId = 0;
        while (likeVoucherId == 0) {
            System.out.print("Lets select a voucher you like (by ID): ");
            Scanner input = new Scanner(System.in);
            int userInput;
            try {
                userInput = input.nextInt();
            } catch (Exception e) {
                System.out.print(MESSAGE_TRY_AGAIN);
                continue;
            }
            if (userInput >= 1 && userInput <= listOfSelection.size()) {
                likeVoucherId = userInput;
            } else {
                System.out.print(MESSAGE_TRY_AGAIN);
            }
        }
        System.out.print("\nYou select:\n" + "ID: " + (likeVoucherId) + " | " + listOfSelection.get(likeVoucherId - 1));
    }

    private static void provideFinalOffer() {
        if (!listOfSelection.isEmpty()) {
            for (int i = 0; i < listOfSelection.size(); i++) {
                System.out.print("ID: " + (i + 1) + " | " + listOfSelection.get(i));
            }
            selectVoucher();
        } else {
            System.out.println(MESSAGE_SORRY);
        }
    }
}