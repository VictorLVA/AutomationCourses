abstract class Voucher {
    private String name;
    private double price;
    private String country;
    private String startDate;
    private int countDays;
    private int discount;
    private double cost;

    int getCountDays() {
        return countDays;
    }

    double getCost() {
        return cost;
    }

    public abstract String getFood();

    public abstract String getTransfer();

    public void initVoucher(int i) {
        this.name = "Name" + i;
        this.price = i;
        this.country = "Country" + i;
        this.startDate = "StartDate" + i;
        this.countDays = i;
        this.discount = i;
        this.cost = this.price - this.price * this.discount / 100;
    }

    public void printVoucher() {
        System.out.print("Name: " + name + " ");
        System.out.print("Price: " + price + " ");
        System.out.print("Country: " + country + " ");
        System.out.print("StartDate: " + startDate + " ");
        System.out.print("CountDays: " + countDays + " ");
        System.out.print("Discount: " + discount + " ");
        System.out.print("Cost: " + cost + " ");
    }
}
