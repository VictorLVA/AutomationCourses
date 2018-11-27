import java.util.Locale;

import predefinedExceptions.RegexException;

abstract class Voucher {

    private String name;
    private double price;
    private String country;
    private String startDate;
    private int countDays;
    private int discount;
    private double cost;
    private String formattedCost;

    Voucher(String[] voucherDetails) throws RegexException {
        this.name = voucherDetails[1];
        this.price = Double.parseDouble(voucherDetails[2]);
        if (voucherDetails[3].matches("[A-Z][A-Z]|[A-Z][A-Z][A-Z]")) {
            this.country = voucherDetails[3];
        } else {
            throw new RegexException();
        }
        if (voucherDetails[4].matches("(0?[1-9]|[12][0-9]|3[01])[.](0?[1-9]|1[012])[.]\\d{4}")) {
            this.startDate = voucherDetails[4];
        } else {
            throw new RegexException();
        }
        if (voucherDetails[5].matches("[1-9][0-9]?")) {
            this.countDays = Integer.parseInt(voucherDetails[5]);
        } else {
            throw new RegexException();
        }
        if (voucherDetails[6].matches("\\d{1,2}")) {
            this.discount = Integer.parseInt(voucherDetails[6]);
        } else {
            throw new RegexException();
        }
        this.cost = this.price - this.price * this.discount / 100;
        formattedCost = String.format(Locale.US, "%.2f", this.cost);
    }

    int getCountDays() {
        return countDays;
    }

    double getCost() {
        return cost;
    }

    public abstract String getFood();

    public abstract String getTransfer();

    @Override
    public String toString() {
        return name + " | " +
                "First price: " + price + "$ | " +
                "Country: " + country + " | " +
                "Start date: " + startDate + " | " +
                "Days count: " + countDays + " | " +
                "Discount: " + discount + "% | " +
                "FINAL COST: " + formattedCost + "$ | ";
    }
}
