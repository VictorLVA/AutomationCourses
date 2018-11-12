class CureVoucher extends Voucher {

    private String hospital;
    private String personal;
    private String medicalSupplies;
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
        this.hospital = "Hospital" + i;
        this.personal = "Personal" + i;
        this.medicalSupplies = "MedicalSupplies" + i;
        this.transfer = "Transfer" + i;
        this.food = "Food" + i;
    }

    @Override
    public void printVoucher() {
        super.printVoucher();
        System.out.print("Hospital: " + hospital + " ");
        System.out.print("Personal: " + personal + " ");
        System.out.print("MedicalSupplies: " + medicalSupplies + " ");
        System.out.print("Transfer: " + transfer + " ");
        System.out.println("Food: " + food + " ");
    }
}
