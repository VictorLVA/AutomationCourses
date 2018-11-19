import predefinedExceptions.RegexException;

class VoucherFactory {
    static Voucher getVoucher(String[] voucherDetails) throws RegexException {
        switch (Integer.parseInt(voucherDetails[0])) {
            case 1:
                return new RestVoucher(voucherDetails);
            case 2:
                return new CureVoucher(voucherDetails);
            case 3:
                return new ShoppingVoucher(voucherDetails);
        }
        return null;
    }
}
