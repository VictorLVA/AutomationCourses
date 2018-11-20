abstract class Voucher {
    private String name;
    private double price;
    private String country;
    private String startDate;
    private int countDays;
    private int discount;
    private double cost;
    private String formattedCost;

    Voucher(int i) {
        name = "VoucherName" + i;
        price = 50 + (int) (Math.random() * 10000);
        country = "GB";
        startDate = "07.12.2018";
        countDays = 3 + (int) (Math.random() * 30);
        discount = (int) (Math.random() * 51);
        cost = price - price * discount / 100;
        formattedCost = String.format("%.2f", cost);
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
                price + "$ | " +
                country + " | " +
                "Start: " + startDate + " | " +
                "Days: " + countDays + " | " +
                "Discount: " + discount + " | " +
                "FINAL COST: " + formattedCost + "$ | ";
    }
}
