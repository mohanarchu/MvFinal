package mv.hospital.Shop.address;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatSpinner;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.JsonObject;
import com.hospital.R;
import com.squareup.moshi.Json;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import mv.hospital.Appointment.BookApponement;
import mv.hospital.base.BaseActivity;
import mv.hospital.profile.Shared;

public class AddressActivity extends BaseActivity implements AddressPresenter.AddressView {


    AddressPresenter addressPresenter;

    AddressAdapter addressAdapter;
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.dPhone)
    EditText dPhone;
    @BindView(R.id.dEmail)
    EditText dEmail;
    @BindView(R.id.dStreet)
    EditText dStreet;
    @BindView(R.id.streetInput)
    TextInputLayout streetInput;
    @BindView(R.id.dState)
    EditText dState;
    @BindView(R.id.dCity)
    EditText dCity;
    @BindView(R.id.dCountry)
    EditText dCountry;
    @BindView(R.id.dPincode)
    EditText dPincode;
    @BindView(R.id.add_add)
    CardView addAddress;
    @BindView(R.id.state_spinner)
    EditText stateSpinner;
    boolean isFirst = false;
    @Override
    protected int layoutRes() {
        return R.layout.address_activity;
    }
    @SuppressLint("NewApi")
    @Override
    protected void onViewBound() {

        Intent intent = getIntent();
        if (intent != null) {
            isFirst = intent.getBooleanExtra("isFirst",false);
        }
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));
        addressPresenter = new AddressPresenter(getApplicationContext(), this);
        //addressPresenter.getStates();
        addAddress.setOnClickListener(view -> {
            if (name.getText().toString().isEmpty() ||   dPhone.getText().toString().isEmpty() ||
                    dStreet.getText().toString().isEmpty() || dCity.getText().toString().isEmpty() ||
                    dCountry.getText().toString().isEmpty() || dPincode.getText().toString().isEmpty() ){
                showToast("Fill all details");
            }else {
                if(!isValid(dPhone.getText().toString())) {
                    showToast("Enter valid mobile number");
                } else {
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("name",name.getText().toString()  );
                    jsonObject.addProperty("address", dStreet.getText().toString());
                    jsonObject.addProperty("phone",dPhone.getText().toString());
                    jsonObject.addProperty("state", stateSpinner.getText().toString());
                    jsonObject.addProperty("city", dCity.getText().toString());
                    jsonObject.addProperty("postalcode",dPincode.getText().toString());
                    jsonObject.addProperty("phone", dPhone.getText().toString());
                    jsonObject.addProperty("customerid", Shared.id(getApplicationContext()));
                    if (isFirst) {
                        jsonObject.addProperty("[primary]",1);
                    }
                    addressPresenter.makeOrder(jsonObject);
                }
            }

        });
    }
    public static boolean isValid(String s)
    {
        Pattern p = Pattern.compile("(0/91)?[7-9][0-9]{9}");
        Matcher m = p.matcher(s);
        return (m.find() && m.group().equals(s));
    }

    @Override
    public void hideProgress() {
        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_OK,returnIntent);
        finish();

    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDetails(String message) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void showResult(AddressPojo.Result[] results) {

    }

    @Override
    public void showStates(StatePojo.Result[] results) {
//        String[] states = new String[results.length];
//        for (int i =0;i<results.length;i++) {
//            states[i] = results[i].getStateName();
//        }
//        ArrayAdapter aa = new ArrayAdapter(AddressActivity.this, android.R.layout.simple_spinner_item, states);
//        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        //Setting the ArrayAdapter data on the Spinner
//        stateSpinner.setAdapter(aa);


    }

}
