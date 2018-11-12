class ShoppingVoucher extends Voucher {

    private String residence;
    private String transfer;
    private String food;
    private String route;
    private String shops;

    @Override
    public String getFood() {
        return food;
    }

    @Override
    public String getTransfer() {
        return transfer;
    }

    @Override
    public void initVoucher(int i) {
        super.initVoucher(i);
        this.residence = "Residence" + i;
        this.route = "Route" + i;
        this.shops = "Shops" + i;
        this.transfer = "Transfer" + i;
        this.food = "Food" + i;
    }

    @Override
    public void printVoucher() {
        super.printVoucher();
        System.out.print("Residence: " + residence + " ");
        System.out.print("Route: " + route + " ");
        System.out.print("Shops: " + shops + " ");
        System.out.print("Transfer: " + transfer + " ");
        System.out.println("Food: " + food + " ");
    }
}
