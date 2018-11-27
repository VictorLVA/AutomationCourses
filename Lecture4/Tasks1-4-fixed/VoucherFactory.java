import java.util.List;

import predefinedExceptions.RegexException;

class VoucherFactory {

    private static final String VOUCHERS_TYPES = "[1-3]";
    private static final String COUNTRY_CODE_FORMAT = "[A-Z][A-Z]|[A-Z][A-Z][A-Z]";
    private static final String START_DAY_FORMAT = "(0?[1-9]|[12][0-9]|3[01])[.](0?[1-9]|1[012])[.]\\d{4}";
    private static final String COUNT_DAY_FORMAT = "[1-9][0-9]?";
    private static final String DISCOUNT_FORMAT = "\\d{1,2}";

    static void createVoucherToList(String voucherDetailsFromFile, List<Voucher> listOfVouchers) throws RegexException {
        String[] voucherDetails = voucherDetailsFromFile.split(";");
        if (voucherDetails[0].matches(VOUCHERS_TYPES)) {
            String name = voucherDetails[1];
            double price = Double.parseDouble(voucherDetails[2]);
            String country = regexParseCountry(voucherDetails);
            String startDate = regexParseStartDate(voucherDetails);
            int countDays = regexParseCountDays(voucherDetails);
            int discount = regexParseDiscount(voucherDetails);
            switch (voucherDetails[0]) {
                case "1":
                    String residence = voucherDetails[7];
                    String transfer = voucherDetails[8];
                    String food = voucherDetails[9];
                    listOfVouchers.add(new RestVoucher(name, price, country, startDate, countDays, discount,
                                                       residence, transfer, food));
                    break;
                case "2":
                    String hospital = voucherDetails[7];
                    String personal = voucherDetails[8];
                    String medicalSupplies = voucherDetails[9];
                    transfer = voucherDetails[10];
                    food = voucherDetails[11];
                    listOfVouchers.add(new CureVoucher(name, price, country, startDate, countDays, discount,
                                                       hospital, personal, medicalSupplies, transfer, food));
                    break;
                case "3":
                    residence = voucherDetails[7];
                    String route = voucherDetails[8];
                    String shops = voucherDetails[9];
                    transfer = voucherDetails[10];
                    food = voucherDetails[11];
                    listOfVouchers.add(new ShoppingVoucher(name, price, country, startDate, countDays, discount,
                                                           residence, route, shops, transfer, food));
            }
        } else {
            throw new RegexException();
        }
    }

    private static String regexParseCountry(String[] voucherDetails) throws RegexException {
        String country;
        if ((voucherDetails[3]).matches(COUNTRY_CODE_FORMAT)) {
            country = voucherDetails[3];
        } else {
            throw new RegexException();
        }
        return country;
    }

    private static String regexParseStartDate(String[] voucherDetails) throws RegexException {
        String startDate;
        if (voucherDetails[4].matches(START_DAY_FORMAT)) {
            startDate = voucherDetails[4];
        } else {
            throw new RegexException();
        }
        return startDate;
    }

    private static int regexParseCountDays(String[] voucherDetails) throws RegexException {
        int countDays;
        if (voucherDetails[5].matches(COUNT_DAY_FORMAT)) {
            countDays = Integer.parseInt(voucherDetails[5]);
        } else {
            throw new RegexException();
        }
        return countDays;
    }

    private static int regexParseDiscount(String[] voucherDetails) throws RegexException {
        int discount;
        if (voucherDetails[6].matches(DISCOUNT_FORMAT)) {
            discount = Integer.parseInt(voucherDetails[6]);
        } else {
            throw new RegexException();
        }
        return discount;
    }
}