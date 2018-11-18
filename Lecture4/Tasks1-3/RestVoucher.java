class RestVoucher extends Voucher {

    private String residence;
    private String transfer;
    private String food;

    RestVoucher(String[] voucherDetails) {
        super(voucherDetails);
        try {
            this.residence = voucherDetails[7];
            this.transfer = voucherDetails[8];
            this.food = voucherDetails[9];
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("RestVouchers. Something wrong with the imported vouchers");
            ex.printStackTrace();
            System.exit(0);
        }
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
