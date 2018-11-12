class Voucher {
    private String name;
    private double price;
    private String country;
    private String startDate;
    private int countDays;

    public Voucher(String name, int price, String country, String startDate, int countDays) {
        this.name = name;
        this.price = price;
        this.country = country;
        this.startDate = startDate;
        this.countDays = countDays;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getCountry() {
        return country;
    }

    public String getStartDate() {
        return startDate;
    }

    public int getCountDays() {
        return countDays;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setCountDays(int countDays) {
        this.countDays = countDays;
    }
}
