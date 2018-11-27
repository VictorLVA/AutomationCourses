import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import predefinedExceptions.DaysCountException;
import predefinedExceptions.EmptyListException;
import predefinedExceptions.RegexException;
import predefinedExceptions.SelectedVoucherTypeException;
import predefinedExceptions.VoucherNotFoundException;
import predefinedExceptions.YesNoAnswerException;

public class Task4L4 {

    private static final String REGEX_YES_NO = "[ynYN]";
    private static final String MESSAGE_TRY_AGAIN = "Please check the entered value. ";
    private static final String MESSAGE_SORRY = "\nSorry, we don't have such vouchers :(";
    private static final String MESSAGE_OPS = "Ops... Something went wrong.";

    private static List<Voucher> listOfVouchers = new ArrayList<>();
    private static List<Voucher> listOfSelection = new ArrayList<>();

    public static void main(String[] args) {
        try {
            readVouchersFromFile();
            provideFirstOffer(getUserSelection());
            if (!isAdvancedSearchNeeded()) {
                selectVoucher();
            } else {
                searchMinimumDaysCount();
                searchWithFood();
                searchWithTransfer();
                provideFinalOffer();
            }
        } catch (OutOfMemoryError | NullPointerException mainFlowEx) {
            System.out.println(MESSAGE_OPS);
            System.exit(0);
        }
    }

    private static void readVouchersFromFile() {
        int notDownloadedCount = 0;
        try {
            BufferedReader readVouchersFile = new BufferedReader(new FileReader("initVouchers.txt"));
            String sCurrentLine;
            while ((sCurrentLine = readVouchersFile.readLine()) != null) {
                String[] voucherDetails = sCurrentLine.split(";");
                try {
                    if (voucherDetails[0].matches("[1-3]")) {
                        Voucher newVoucher = VoucherFactory.getVoucher(voucherDetails);
                        listOfVouchers.add(newVoucher);
                    } else {
                        notDownloadedCount++;
                    }
                } catch (NumberFormatException
                        | ArithmeticException
                        | ArrayIndexOutOfBoundsException
                        | RegexException initEx) {
                    notDownloadedCount++;
                }
            }
        } catch (IOException inputEx) {
            inputEx.printStackTrace();
            System.exit(0);
        }
        if (notDownloadedCount != 0) {
            System.out.println("\nThere are some problems with " + notDownloadedCount + " vouchers within the file\n");
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
            } catch (InputMismatchException inputMismatchEx) {
                System.out.print(MESSAGE_TRY_AGAIN);
                continue;
            }
            try {
                if (userInput >= 1 && userInput <= 3) {
                    selection = userInput;
                } else {
                    throw new SelectedVoucherTypeException(MESSAGE_TRY_AGAIN);
                }
            } catch (SelectedVoucherTypeException selectedVoucherTypeEx) {
                System.out.print(selectedVoucherTypeEx.getMessage());
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
        try {
            if (!listOfSelection.isEmpty()) {
                for (int i = 0; i < listOfSelection.size(); i++) {
                    System.out.print("ID: " + (i + 1) + " | " + listOfSelection.get(i));
                }
            } else {
                throw new EmptyListException(MESSAGE_SORRY);
            }
        } catch (EmptyListException emptyListEx) {
            emptyListEx.getMessageAndExit();
        }
    }

    private static boolean isAdvancedSearchNeeded() {
        String userAnswer = null;
        while (userAnswer == null) {
            System.out.print("Do you want advanced filters? (y/n) ");
            Scanner input = new Scanner(System.in);
            String userInput;
            userInput = input.nextLine();
            try {
                if (userInput.matches(REGEX_YES_NO)) {
                    userAnswer = userInput;
                } else {
                    throw new YesNoAnswerException(MESSAGE_TRY_AGAIN);
                }
            } catch (YesNoAnswerException yesNoAnswerEx) {
                System.out.print(yesNoAnswerEx.getMessage());
            }
        }
        return userAnswer.equalsIgnoreCase("y");
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
            } catch (InputMismatchException inputMismatchEx) {
                System.out.print(MESSAGE_TRY_AGAIN);
                continue;
            }
            try {
                if (userInput >= 1) {
                    countDays = userInput;
                } else {
                    throw new DaysCountException(MESSAGE_TRY_AGAIN);
                }
            } catch (DaysCountException daysCountEx) {
                System.out.print(daysCountEx.getMessage());
            }
        }
        return countDays;
    }

    private static void searchWithFood() {
        if (!isUserNeedFood()) {
            listOfSelection = listOfSelection.stream()
                                             .filter(voucher -> voucher.getFood().equalsIgnoreCase("no"))
                                             .collect(Collectors.toList());
        } else {
            listOfSelection = listOfSelection.stream()
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
            try {
                if (userInput.matches(REGEX_YES_NO)) {
                    food = userInput;
                } else {
                    throw new YesNoAnswerException(MESSAGE_TRY_AGAIN);
                }
            } catch (YesNoAnswerException yesNoAnswerEx) {
                System.out.print(yesNoAnswerEx.getMessage());
            }
        }
        return food.equalsIgnoreCase("y");
    }

    private static void searchWithTransfer() {
        if (!isUserNeedTransfer()) {
            listOfSelection = listOfSelection.stream()
                                             .filter(each -> each.getTransfer().equalsIgnoreCase("no"))
                                             .collect(Collectors.toList());
        } else {
            listOfSelection = listOfSelection.stream()
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
            try {
                if (userInput.matches(REGEX_YES_NO)) {
                    transfer = userInput;
                } else {
                    throw new YesNoAnswerException(MESSAGE_TRY_AGAIN);
                }
            } catch (YesNoAnswerException yesNoAnswerEx) {
                System.out.print(yesNoAnswerEx.getMessage());
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
            } catch (InputMismatchException inputMismatchEx) {
                System.out.print(MESSAGE_TRY_AGAIN);
                continue;
            }
            try {
                if (userInput >= 1 && userInput <= listOfSelection.size()) {
                    likeVoucherId = userInput;
                } else {
                    throw new VoucherNotFoundException(MESSAGE_TRY_AGAIN);
                }
            } catch (VoucherNotFoundException voucherNotFoundEx) {
                System.out.print(voucherNotFoundEx.getMessage());
            }
        }
        System.out.print("\nYou've selected:\n" + "ID: " + (likeVoucherId) + " | " + listOfSelection.get(likeVoucherId - 1));
        writeSelectedToFile(listOfSelection.get(likeVoucherId - 1));
    }

    private static void provideFinalOffer() {
        try {
            if (!listOfSelection.isEmpty()) {
                for (int i = 0; i < listOfSelection.size(); i++) {
                    System.out.print("ID: " + (i + 1) + " | " + listOfSelection.get(i));
                }
                selectVoucher();
            } else {
                throw new EmptyListException(MESSAGE_SORRY);
            }
        } catch (EmptyListException emptyListEx) {
            emptyListEx.getMessageAndExit();
        }
    }

    private static void writeSelectedToFile(Voucher selectedVoucher) {
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
            System.out.println("Cannot write output file!");
            outputEx.printStackTrace();
        }
    }
}