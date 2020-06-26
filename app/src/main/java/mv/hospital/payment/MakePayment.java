package mv.hospital.payment;

import com.hospital.R;
import mv.hospital.base.BaseActivity;

public class MakePayment extends BaseActivity {


    PaymentModel    paymentModel;
//
//    @BindView(R.id.makePayments)
//    CardView makePayments;
   // PayUmoneySdkInitializer.PaymentParam mPaymentParams = null;
    @Override
    protected int layoutRes() {
        return R.layout.activity_make_payment;
    }


    @Override
    protected void onViewBound() {
      //  paymentModel = new PaymentModel(getApplicationContext(),this);

    }



//    @OnClick(R.id.makePayments)
//    public void onViewClicked() {
//
//        launchPaymentFlow();
//    }
    private void launchPaymentFlow() {
      /*  PayUmoneyConfig payUmoneyConfig = PayUmoneyConfig.getInstance();
        payUmoneyConfig.setPayUmoneyActivityTitle("Buy" + getResources().getString(R.string.nike_power_run));
        payUmoneyConfig.setDoneButtonText("Pay " + getResources().getString(R.string.Rupees) + getResources().getString(R.string.txt_product_price));

        PayUmoneySdkInitializer.PaymentParam.Builder builder = new PayUmoneySdkInitializer.PaymentParam.Builder();
        builder.setAmount(String.valueOf(convertStringToDouble(getResources().getString(R.string.txt_product_price))))
                .setTxnId(System.currentTimeMillis() + "")
                .setPhone(Constants.MOBILE)
                .setProductName(getResources().getString(R.string.nike_power_run))
                .setFirstName(Constants.FIRST_NAME)
                .setEmail(Constants.EMAIL)
                .setsUrl(Constants.SURL)
                .setfUrl(Constants.FURL)
                .setUdf1("")
                .setUdf2("")
                .setUdf3("")
                .setUdf4("")
                .setUdf5("")
                .setUdf6("")
                .setUdf7("")
                .setUdf8("")
                .setUdf9("")
                .setUdf10("")
                .setIsDebug(Constants.DEBUG)
                .setKey(Constants.MERCHANT_KEY)
                .setMerchantId(Constants.MERCHANT_ID);

        try {
              mPaymentParams = builder.build();
        //    calculateHashInServer(mPaymentParams);
            paymentModel.makeHashRequest(mPaymentParams);
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
          //  mTxvBuy.setEnabled(true);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PayUmoneyFlowManager.REQUEST_CODE_PAYMENT && resultCode == RESULT_OK && data != null) {

            TransactionResponse transactionResponse = data.getParcelableExtra(PayUmoneyFlowManager.INTENT_EXTRA_TRANSACTION_RESPONSE);
            ResultModel resultModel = data.getParcelableExtra(PayUmoneyFlowManager.ARG_RESULT);

            if (transactionResponse != null && transactionResponse.getPayuResponse() != null) {

                if (transactionResponse.getTransactionStatus().equals(TransactionResponse.TransactionStatus.SUCCESSFUL)) {
                    showAlert("Payment Successful");
                } else if (transactionResponse.getTransactionStatus().equals(TransactionResponse.TransactionStatus.CANCELLED)) {
                    showAlert("Payment Cancelled");
                } else if (transactionResponse.getTransactionStatus().equals(TransactionResponse.TransactionStatus.FAILED)) {
                    showAlert("Payment Failed");
                }

            } else if (resultModel != null && resultModel.getError() != null) {
                Toast.makeText(this, "Error check log", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Both objects are null", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == PayUmoneyFlowManager.REQUEST_CODE_PAYMENT && resultCode == RESULT_CANCELED) {
            showAlert("Payment Cancelled");
        }
    }

    private Double convertStringToDouble(String str) {
        return Double.parseDouble(str);
    }
    private void showAlert(String msg){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage(msg);
        alertDialog.setCancelable(false);
        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alertDialog.show();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showToast(String message) {

    }

    @Override
    public void success(String merchantHash) {
        if (merchantHash.isEmpty() || merchantHash.equals("")) {
            Toast.makeText(MakePayment.this, "Could not generate hash", Toast.LENGTH_SHORT).show();
        } else {
            mPaymentParams.setMerchantHash(merchantHash);
            PayUmoneyFlowManager.startPayUMoneyFlow(mPaymentParams, MakePayment.this,
                    R.style.PayUMoney, true);
        }
    }*/
    }
}
