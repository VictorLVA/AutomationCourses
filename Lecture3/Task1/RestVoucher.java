class RestVoucher extends Voucher {

    private String residence;
    private String transfer;
    private String food;

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
        this.transfer = "Transfer" + i;
        this.food = "Food" + i;
    }

    @Override
    public void printVoucher() {
        super.printVoucher();
        System.out.print("Residence: " + residence + " ");
        System.out.print("Transfer: " + transfer + " ");
        System.out.println("Food: " + food + " ");
    }
}
