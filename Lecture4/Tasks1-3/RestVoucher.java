import predefinedExceptions.RegexException;

class RestVoucher extends Voucher {

    private String residence;
    private String transfer;
    private String food;

    RestVoucher(String[] voucherDetails) throws RegexException {
        super(voucherDetails);
        this.residence = voucherDetails[7];
        this.transfer = voucherDetails[8];
        this.food = voucherDetails[9];
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
