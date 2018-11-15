class ShoppingVoucher extends Voucher {

    private String residence;
    private String transfer;
    private String food;
    private String route;
    private String shops;

    ShoppingVoucher(int i) {
        super(i);
        this.residence = "Residence" + i;
        this.route = "Route" + i;
        this.shops = "Shops" + i;
        this.transfer = "Transfer" + i;
        this.food = "Food" + i;
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
