import java.util.Locale;

abstract class Voucher {

    private String name;
    private double price;
    private String country;
    private String startDate;
    private int countDays;
    private int discount;
    private double cost;
    private String formattedCost;

    Voucher(String[] voucherDetails) {
        this.name = voucherDetails[1];
        this.price = Double.parseDouble(voucherDetails[2]);
        this.country = voucherDetails[3];
        this.startDate = voucherDetails[4];
        this.countDays = Integer.parseInt(voucherDetails[5]);
        this.discount = Integer.parseInt(voucherDetails[6]);
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
