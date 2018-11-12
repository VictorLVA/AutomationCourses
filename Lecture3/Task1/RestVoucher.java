class RestVoucher extends Voucher{
    private Voucher voucher;
    private String residence;
    private String transfer;
    private String food;

    public RestVoucher(String name, int price, String country, String startDate, int countDays, String residence, String transfer) {
        super(name, price, country, startDate, countDays);
        this.residence = residence;
    }

}
