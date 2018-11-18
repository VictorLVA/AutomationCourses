class ShoppingVoucher extends Voucher {

    private String residence;
    private String transfer;
    private String food;
    private String route;
    private String shops;

    ShoppingVoucher(String[] voucherDetails) {
        super(voucherDetails);
        this.residence = voucherDetails[7];
        this.route = voucherDetails[8];
        this.shops = voucherDetails[9];
        this.transfer = voucherDetails[10];
        this.food = voucherDetails[11];
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
