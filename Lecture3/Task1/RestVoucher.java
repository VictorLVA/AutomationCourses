class RestVoucher extends Voucher {

    private String residence;
    private String transfer;
    private String food;

    RestVoucher(int i) {
        super(i);
        this.residence = "Residence" + i;
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
                "Transfer: " + transfer + " | " +
                "Food: " + food + "\n";
    }
}
