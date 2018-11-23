class ShoppingVoucher extends Voucher {

    private String residence;
    private String route;
    private String shops;
    private String transfer;
    private String food;

    ShoppingVoucher(String name, double price, String country, String startDate, int countDays,
                    int discount, String residence, String route, String shops, String transfer,
                    String food) {
        super(name, price, country, startDate, countDays, discount);
        this.residence = residence;
        this.route = route;
        this.shops = shops;
        this.transfer = transfer;
        this.food = food;
    }

    @Override
    public String getFood() {
        return food;
    }

    @Override
    public String getTransfer() {
        return transfer;
    }

    @Override
    public String toString() {
        return super.toString() +
                "Residence: " + residence + " | " +
                "Route: " + route + " | " +
                "Shops: " + shops + " | " +
                "Transfer: " + transfer + " | " +
                "Food: " + food + "\n";
    }
}
