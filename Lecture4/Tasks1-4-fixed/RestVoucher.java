class RestVoucher extends Voucher {

    private String residence;
    private String transfer;
    private String food;

    RestVoucher(String name, double price, String country, String startDate, int countDays,
                int discount, String residence, String transfer, String food) {
        super(name, price, country, startDate, countDays, discount);
        this.residence = residence;
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
                "Transfer: " + transfer + " | " +
                "Food: " + food + "\n";
    }
}
