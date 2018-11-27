import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import predefinedExceptions.RegexException;

class VoucherHelper {

    private static final String REGEX_YES_NO = "[ynYN]";
    private static final String MESSAGE_TRY_AGAIN = "Please check the entered value. ";
    private static final String MESSAGE_SORRY = "\nSorry, we don't have such vouchers :(";

    private static int notDownloadedCount = 0;
    private static int fileStringIndex = 0;

    static void readAndCreateVouchersFromFile(List<Voucher> listOfVouchers) {
        try {
            BufferedReader readVouchersFile = new BufferedReader(new FileReader("initVouchers.txt"));
            String voucherDetailsFromFile;
            while ((voucherDetailsFromFile = readVouchersFile.readLine()) != null) {
                fileStringIndex++;
                try {
                    VoucherFactory.createVoucherToList(voucherDetailsFromFile, listOfVouchers);
                } catch (NumberFormatException
                        | ArithmeticException
                        | ArrayIndexOutOfBoundsException
                        | RegexException initEx) {
                    System.out.print("\ninitVouchers.txt -> String " + fileStringIndex + ": Voucher has invalid data and cannot be imported!\n");
                    initEx.printStackTrace();
                    notDownloadedCount++;
                }
            }
        } catch (IOException inputEx) {
            inputEx.printStackTrace();
            System.exit(0);
        } finally {
            if (notDownloadedCount != 0) {
                System.out.println("\nTotally " + notDownloadedCount + " vouchers were not imported from the file!!!\n");
            }
        }
    }

    static int getUserSelection() {
        int selection = 0;
        while (selection == 0) {
            System.out.print("Lets select needed type of the vouchers (1-Rest, 2-Cure, 3-Shopping): ");
            Scanner input = new Scanner(System.in);
            int userInput;
            try {
                userInput = input.nextInt();
            } catch (InputMismatchException inputMismatchEx) {
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

    static List<Voucher> provideFirstOffer(int selection, List<Voucher> listOfVouchers, List<Voucher> listOfSelection) {
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
        return listOfSelection;
    }

    static boolean isAdvancedSearchNeeded() {
        String userAnswer = null;
        while (userAnswer == null) {
            System.out.print("Do you want advanced filters? (y/n) ");
            Scanner input = new Scanner(System.in);
            String userInput;
            userInput = input.nextLine();
            if (userInput.matches(REGEX_YES_NO)) {
                userAnswer = userInput;
            } else {
                System.out.print(MESSAGE_TRY_AGAIN);
            }
        }
        return userAnswer.equalsIgnoreCase("y");
    }

    static List<Voucher> searchMinimumDaysCount(List<Voucher> listOfVouchers) {
        int countDays = getUserCountDays();
        return listOfVouchers.stream()
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
            } catch (InputMismatchException inputMismatchEx) {
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

    static List<Voucher> searchWithFood(List<Voucher> listOfSelection) {
        if (!isUserNeedFood()) {
            return listOfSelection.stream()
                                  .filter(voucher -> voucher.getFood().equalsIgnoreCase("no"))
                                  .collect(Collectors.toList());
        } else {
            return listOfSelection.stream()
                                  .filter(voucher -> !voucher.getFood().equalsIgnoreCase("no"))
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
            if (userInput.matches(REGEX_YES_NO)) {
                food = userInput;
            } else {
                System.out.print(MESSAGE_TRY_AGAIN);
            }
        }
        return food.equalsIgnoreCase("y");
    }

    static List<Voucher> searchWithTransfer(List<Voucher> listOfSelection) {
        if (!isUserNeedTransfer()) {
            return listOfSelection.stream()
                                  .filter(each -> each.getTransfer().equalsIgnoreCase("no"))
                                  .collect(Collectors.toList());
        } else {
            return listOfSelection.stream()
                                  .filter(each -> !each.getTransfer().equalsIgnoreCase("no"))
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
            if (userInput.matches(REGEX_YES_NO)) {
                transfer = userInput;
            } else {
                System.out.print(MESSAGE_TRY_AGAIN);
            }
        }
        return transfer.equalsIgnoreCase("y");
    }

    static void selectVoucher(List<Voucher> listOfSelection) {
        int likeVoucherId = 0;
        while (likeVoucherId == 0) {
            System.out.print("Lets select a voucher you like (by ID): ");
            Scanner input = new Scanner(System.in);
            int userInput;
            try {
                userInput = input.nextInt();
            } catch (InputMismatchException inputMismatchEx) {
                System.out.print(MESSAGE_TRY_AGAIN);
                continue;
            }
            if (userInput >= 1 && userInput <= listOfSelection.size()) {
                likeVoucherId = userInput;
            } else {
                System.out.print(MESSAGE_TRY_AGAIN);
            }
        }
        System.out.print("\nYou've selected:\n" + "ID: " + (likeVoucherId) + " | " + listOfSelection.get(likeVoucherId - 1));
        writeDetailsToFile(listOfSelection.get(likeVoucherId - 1));
        saveVoucherToFile(listOfSelection.get(likeVoucherId - 1));
    }

    static void provideFinalOffer(List<Voucher> listOfSelection) {
        if (!listOfSelection.isEmpty()) {
            for (int i = 0; i < listOfSelection.size(); i++) {
                System.out.print("ID: " + (i + 1) + " | " + listOfSelection.get(i));
            }
            selectVoucher(listOfSelection);
        } else {
            System.out.println(MESSAGE_SORRY);
            System.exit(0);
        }
    }

    private static void writeDetailsToFile(Voucher selectedVoucher) {
        String[] voucherDetails = selectedVoucher.toString().split(" \\| ");
        try {
            FileWriter voucherDetailsWriter = new FileWriter("Your voucher.txt");
            voucherDetailsWriter.write("Your selected voucher details are listed below:" + System.lineSeparator());
            for (String voucherDetail : voucherDetails) {
                voucherDetailsWriter.write(System.lineSeparator() + voucherDetail);
            }
            voucherDetailsWriter.close();
            System.out.println("\nVoucher details have been written to file \"Your voucher.txt\"");
        } catch (IOException outputEx) {
            System.out.println("Cannot write your voucher's details!");
            outputEx.printStackTrace();
        }
    }

    private static void saveVoucherToFile(Voucher selectedVoucher) {
        try {
            ObjectOutputStream objectWriter = new ObjectOutputStream(new FileOutputStream("voucher.vch"));
            objectWriter.writeObject(selectedVoucher);
            objectWriter.close();
            System.out.println("Voucher saved to \"voucher.vch\"");
        } catch (IOException outputEx) {
            System.out.println("Cannot save your voucher to file!");
            outputEx.printStackTrace();
        }
    }
}
