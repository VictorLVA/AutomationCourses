import java.io.Serializable;
import java.util.Locale;

abstract class Voucher implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private double price;
    private String country;
    private String startDate;
    private int countDays;
    private int discount;
    private double cost;
    private String formattedCost;

    Voucher(String name, double price, String country, String startDate, int countDays, int discount) {
        this.name = name;
        this.price = price;
        this.country = country;
        this.startDate = startDate;
        this.countDays = countDays;
        this.discount = discount;
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
