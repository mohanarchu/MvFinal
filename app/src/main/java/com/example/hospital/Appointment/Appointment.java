package com.example.hospital.Appointment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.example.hospital.AllShoes.AllShoes;
import com.example.hospital.Order.OrderPresenter;
import com.example.hospital.Order.OrderView;
import com.example.hospital.R;
import com.example.hospital.cart.orders.OrderPojo;
import com.google.gson.JsonObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Appointment extends AppCompatActivity implements OrderView {
    @BindView(R.id.patientName)
    EditText patientName;
    @BindView(R.id.pMale)
    RadioButton pMale;
    @BindView(R.id.pFemale)
    RadioButton pFemale;
    @BindView(R.id.RadiogroupPurpose)
    RadioGroup RadiogroupPurpose;
    @BindView(R.id.purposeLayout)
    LinearLayout purposeLayout;
    @BindView(R.id.pAge)
    EditText pAge;
    @BindView(R.id.pContactNumber)
    EditText pContactNumber;
    @BindView(R.id.pEmail)
    EditText pEmail;
    @BindView(R.id.pNatinality)
    EditText pNatinality;
    @BindView(R.id.pFax)
    EditText pFax;
    @BindView(R.id.pAddress)
    EditText pAddress;
    @BindView(R.id.pAddressTwo)
    EditText pAddressTwo;
    @BindView(R.id.pCity)
    EditText pCity;
    @BindView(R.id.pState)
    EditText pState;
    @BindView(R.id.pPincode)
    EditText pPincode;
    @BindView(R.id.cusEditText)
    TextView cusEditText;
    @BindView(R.id.createAppoinmemnt)
    CardView createAppoinmemnt;
    private OnFragmentInteractionListener mListener;
    Toolbar toolbar;
    Spinner ages;
    OrderPresenter appointMentView;
    RadioButton radioButton;
    ArrayList<Integer> age = new ArrayList<>();
      int male;
    ProgressDialog progressDialog;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_appointment);
        ButterKnife.bind(this);
        pMale.setChecked(true);
        appointMentView = new OrderPresenter(getApplicationContext(),this);
        RadiogroupPurpose.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int selectedId = radioGroup.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                radioButton = (RadioButton) findViewById(selectedId);

                String buttonString =  radioButton.getText().toString();
                if (buttonString.equals("Male")) {
                    male = 0;
                }else if (buttonString.equals("Female")) {
                   male = 1;
                }

            }
        });
        createAppoinmemnt.setOnClickListener(view -> {
          //  startActivity(new Intent(Appointment.this,BookApponement.class));
            if (nullCheck(patientName) || nullCheck(pAge) || nullCheck(pContactNumber) || nullCheck(pEmail)
               || nullCheck(pNatinality) || nullCheck(pAddress) || nullCheck(pCity) || nullCheck(pState)
               || nullCheck(pPincode) ) {
                 showToast("Fill all details");
            } else {
                if (!isValidEmail(pEmail.getText().toString())) {
                    showToast("Enter valid email");
                } else if (!isValid(pContactNumber.getText().toString())) {
                    showToast("Enter valid mobile number");
                } else if (pPincode.getText().toString().length() != 6) {
                    showToast("Enter valid pincode");
                } else {
                    String date  = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
                            Locale.getDefault()).format(new Date());
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("Name", patientName.getText().toString());
                    jsonObject.addProperty("Gender", male);
                    jsonObject.addProperty("Age", pAge.getText().toString());
                    jsonObject.addProperty("Nationality", pNatinality.getText().toString());
                    jsonObject.addProperty("City", pCity.getText().toString());
                    jsonObject.addProperty("State", pState.getText().toString());
                    jsonObject.addProperty("Country", "India");
                    jsonObject.addProperty("Pincode", pPincode.getText().toString());
                    jsonObject.addProperty("Mobile", pContactNumber.getText().toString());
                    jsonObject.addProperty("Email", pEmail.getText().toString());
                    jsonObject.addProperty("Fax",  "");
                    jsonObject.addProperty("RegDate",date);
                    JsonObject jsonObject1 = new JsonObject();
                    jsonObject1.add("data", jsonObject);
                    jsonObject1.addProperty("table", "Registration");
                    jsonObject1.addProperty("multipleInsert", false);
                    appointMentView.makeOrder(jsonObject1);
                }
            }
           // startActivity(new Intent(Appointment.this, BookApponement.class));
        });
        toolbar = findViewById(R.id.appointMents);
        toolbar.setTitle("Appointment");
        setSupportActionBar(toolbar);
        ArrayAdapter<Integer> spinnerArrayAdapter = new ArrayAdapter<Integer>(
                this, android.R.layout.simple_spinner_item, age);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //  ages.setAdapter(spinnerArrayAdapter);
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent1));


    }

    public static boolean isValid(String s)
    {

        Pattern p = Pattern.compile("(0/91)?[7-9][0-9]{9}");
        Matcher m = p.matcher(s);
        return (m.find() && m.group().equals(s));
    }
    public static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
    boolean nullCheck(EditText cusEditText) {
       return cusEditText.getText().toString().isEmpty();
    }

    @Override
    public void showProgress() {
        progressDialog = new ProgressDialog(Appointment.this);
        progressDialog.setMessage("Please wait..!");
        progressDialog.show();
        progressDialog.setCancelable(false);
    }

    @Override
    public void hideProgress() {
        if (progressDialog != null){
            progressDialog.dismiss();
        }
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void sucess(String id) {
        startActivity(new Intent(Appointment.this,BookApponement.class));
    }

    @Override
    public void placed() {
  //      startActivity(new Intent(Appointment.this,BookApponement.class));

    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.all_shoes, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.infoWhite) {
            Intent intent = new Intent(Appointment.this, AllShoes.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}
