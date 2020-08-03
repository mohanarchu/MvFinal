package mv.hospital.Appointment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
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

import mv.hospital.AllShoes.AllShoes;
import mv.hospital.Order.OrderPresenter;
import mv.hospital.Order.OrderView;
import com.hospital.R;
import com.google.gson.JsonObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Appointment extends AppCompatActivity implements AppointmentModel {
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
    AppointMentView appointMentView;
    RadioButton radioButton;
    ArrayList<Integer> age = new ArrayList<>();
      int male;
      String genderText = "Male";
    ProgressDialog progressDialog;
    private String registrationNumber = "";

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_appointment);
        ButterKnife.bind(this);
        pMale.setChecked(true);
        appointMentView = new AppointMentView(getApplicationContext(),this);
        appointMentView.getAppointmentNumber();
        RadiogroupPurpose.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                // find the radiobutton by returned id
                radioButton = (RadioButton) findViewById(selectedId);
                String buttonString =  radioButton.getText().toString();
                if (buttonString.equals("Male")) {
                    male = 0;
                    genderText = "Male";
                }else if (buttonString.equals("Female")) {
                   male = 1;
                   genderText = "Female";
                }
            }
        });

        createAppoinmemnt.setOnClickListener(view -> {
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
                    progressDialog = new ProgressDialog(Appointment.this);
                    progressDialog.setMessage("Please wait..!");
                    progressDialog.show();
                    progressDialog.setCancelable(false);
                    appointMentView.getUserValidation(pEmail.getText().toString());
                }
            }
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

    void makeRegistration(boolean isUpdate,String patientId,String regNo){
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
        jsonObject.addProperty("address",
                pAddress.getText().toString() +","+pAddressTwo.getText().toString());
        jsonObject.addProperty("Fax",  "");
        jsonObject.addProperty("RegDate",date);
        jsonObject.addProperty("RegistrationId", regNo );
        JsonObject jsonObject1 = new JsonObject();
        jsonObject1.add("data", jsonObject);
        jsonObject1.addProperty("table", "Registration");
        jsonObject1.addProperty("multipleInsert", false);
        if (isUpdate) {
            JsonObject jsonObject2 = new JsonObject();
            jsonObject2.addProperty("table","Registration");
            jsonObject2.addProperty("refernce","patientid");
            jsonObject2.add("data",jsonObject);
            appointMentView.makeUpdate(patientId,jsonObject2);
        } else {
            appointMentView.makeOrder(jsonObject1);
        }
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

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showPatientId(String id,String regId) {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
        if (id.equals("-1")) {
            this.registrationNumber = "MVH"+(Integer.parseInt(registrationNumber) + 1);
            makeRegistration(false,"",registrationNumber);
        } else {
            this.registrationNumber =  regId == null ? "MVH"+(Integer.parseInt(registrationNumber) + 1) : regId;
            makeRegistration(true,id,registrationNumber);
        }
    }

    void showActivity(String patientId,String regNo) {
        Intent i = new Intent(Appointment.this,BookApponement.class);
        i.putExtra("name",patientName.getText().toString());
        i.putExtra("gender",genderText);
        i.putExtra("regId",regNo);
        i.putExtra("age", pAge.getText().toString());
        i.putExtra("Nationality",pNatinality.getText().toString());
        i.putExtra("Address",pAddress.getText().toString()+","+pAddressTwo.getText().toString());
        i.putExtra("City",pCity.getText().toString());
        i.putExtra("State",pState.getText().toString());
        i.putExtra("Pincode",pPincode.getText().toString());
        i.putExtra("Mobile",pContactNumber.getText().toString());
        i.putExtra("Email",pEmail.getText().toString());
        i.putExtra("patientId",patientId);
        startActivity(i);
    }

    @Override
    public void createdPatientid(String id) {
        showActivity(id,this.registrationNumber);
    }

    @Override
    public void showRegNumber(String id) {
        this.registrationNumber = id;
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
