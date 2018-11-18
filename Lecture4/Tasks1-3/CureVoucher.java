class CureVoucher extends Voucher {

    private String hospital;
    private String personal;
    private String medicalSupplies;
    private String transfer;
    private String food;

    CureVoucher(String[] voucherDetails) {
        super(voucherDetails);
        try {
            this.hospital = voucherDetails[7];
            this.personal = voucherDetails[8];
            this.medicalSupplies = voucherDetails[9];
            this.transfer = voucherDetails[10];
            this.food = voucherDetails[11];
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("CureVouchers. Something wrong with the imported vouchers");
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
                "Hospital: " + hospital + " | " +
                "Personal: " + personal + " | " +
                "MedicalSupplies: " + medicalSupplies + " | " +
                "Transfer: " + transfer + " | " +
                "Food: " + food + "\n";
    }
}
