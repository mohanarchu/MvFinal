package mv.hospital.Register;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.hospital.R;
import mv.hospital.cart.Cart;
import mv.hospital.profile.Profile;
import mv.hospital.profile.Shared;
import com.goodiebag.pinview.Pinview;
import com.google.gson.JsonObject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class OtpView extends AppCompatActivity implements OtpValView{

    String mobileNumber;
    @BindView(R.id.otpCode)
    Pinview otpCode;
    @BindView(R.id.otpSubmitText)
    TextView otpSubmitText;
    @BindView(R.id.submitOtp)
    CardView submitOtp;
    OtpValidate otpValidate;

    int key;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_view);
        ButterKnife.bind(this);
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));
        otpCode.requestFocus();
        otpCode.setFocusable(true);
        otpValidate = new OtpValidate(getApplicationContext(),this);
        if (getIntent().getExtras() != null) {
            mobileNumber = getIntent().getStringExtra("mobileNumber");
            key = getIntent().getIntExtra("key",0);
        }
        submitOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (otpCode.getValue().equals("")) {
                    showToast("Enter OTP");
                } else if (otpCode.getValue().length() != 4) {
                    showToast("Enter valid OTP");
                }else {
                    JsonObject jsonObject1 = new JsonObject();
                  jsonObject1.addProperty("phone_number",mobileNumber);
                  jsonObject1.addProperty("otp",otpCode.getValue());
                  JsonObject jsonObject = new JsonObject();
                  jsonObject.add("data",jsonObject1);
                  jsonObject.addProperty("table", "Customer");
                  otpValidate.getLogin(jsonObject);

                }
            }
        });

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void success() {
        Shared.LoginShared(true,getApplicationContext());

        Toast.makeText(getApplicationContext(),"Validated successfully",Toast.LENGTH_SHORT).show();
        if (key == 1){
            startActivity(new Intent(this, Profile.class));
        }else {
            startActivity(new Intent(this, Cart.class));
        }

        finish();
    }

    @Override
    public void faliure() {

    }
}
