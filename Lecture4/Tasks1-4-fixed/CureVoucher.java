class CureVoucher extends Voucher {

    private String hospital;
    private String personal;
    private String medicalSupplies;
    private String transfer;
    private String food;

    CureVoucher(String name, double price, String country, String startDate, int countDays,
                int discount, String hospital, String personal, String medicalSupplies, String transfer,
                String food) {
        super(name, price, country, startDate, countDays, discount);
        this.hospital = hospital;
        this.personal = personal;
        this.medicalSupplies = medicalSupplies;
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
                "Hospital: " + hospital + " | " +
                "Personal: " + personal + " | " +
                "MedicalSupplies: " + medicalSupplies + " | " +
                "Transfer: " + transfer + " | " +
                "Food: " + food + "\n";
    }
}
