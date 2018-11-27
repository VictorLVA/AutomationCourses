import java.util.ArrayList;
import java.util.List;

public class Tasks1_4_fixed {

    private static final String MESSAGE_OPS = "Ops... Something went wrong.";

    private static List<Voucher> listOfVouchers = new ArrayList<>();
    private static List<Voucher> listOfSelection = new ArrayList<>();

    public static void main(String[] args) {
        try {
            VoucherHelper.readAndCreateVouchersFromFile(listOfVouchers);
            listOfSelection = VoucherHelper.provideFirstOffer(VoucherHelper.getUserSelection(), listOfVouchers, listOfSelection);
            if (!VoucherHelper.isAdvancedSearchNeeded()) {
                VoucherHelper.selectVoucher(listOfSelection);
            } else {
                listOfSelection = VoucherHelper.searchMinimumDaysCount(listOfSelection);
                listOfSelection = VoucherHelper.searchWithFood(listOfSelection);
                listOfSelection = VoucherHelper.searchWithTransfer(listOfSelection);
                VoucherHelper.provideFinalOffer(listOfSelection);
            }
        } catch (OutOfMemoryError | NullPointerException mainFlowEx) {
            System.out.println(MESSAGE_OPS);
            System.exit(0);
        }
    }
}