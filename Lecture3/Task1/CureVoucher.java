class CureVoucher extends Voucher {

    private String hospital;
    private String personal;
    private String medicalSupplies;
    private String transfer;
    private String food;

    CureVoucher(int i) {
        super(i);
        this.hospital = "Hospital" + i;
        this.personal = "Personal" + i;
        this.medicalSupplies = "MedicalSupplies" + i;
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
                "Hospital: " + hospital + " | " +
                "Personal: " + personal + " | " +
                "MedicalSupplies: " + medicalSupplies + " | " +
                "Transfer: " + transfer + " | " +
                "Food: " + food + "\n";
    }
}
