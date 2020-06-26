package mv.hospital.profile;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.hospital.R;
import mv.hospital.ShoppingMain;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.JsonObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Profile extends AppCompatActivity implements ProfileView {

    SharedPreferences sharedPreferences;

    @BindView(R.id.progressCustomer)
    RelativeLayout progressCustomer;
    @BindView(R.id.aName)
    EditText aName;
    @BindView(R.id.aPhone)
    EditText aPhone;
    @BindView(R.id.phoneInput)
    TextInputLayout phoneInput;
    @BindView(R.id.aEmail)
    EditText aEmail;
    @BindView(R.id.bloodGroup)
    EditText bloodGroup;
    @BindView(R.id.dob)
    TextView dob;
    @BindView(R.id.aDoor)
    EditText aDoor;
    @BindView(R.id.doorInput)
    TextInputLayout doorInput;
    @BindView(R.id.aStreet)
    EditText aStreet;
    @BindView(R.id.aState)
    EditText aState;
    @BindView(R.id.qCity)
    EditText qCity;
    @BindView(R.id.aZipCode)
    EditText aZipCode;

    @BindView(R.id.createCustomer)
    CardView createCustomer;

    String serverDtae;
    ProfilePresenter profilePresenter;
    @BindView(R.id.radioButton1)
    RadioButton radioButton1;
    @BindView(R.id.radioGroup1)
    RadioGroup radioGroup1;
    @BindView(R.id.radioButton2)
    RadioButton radioButton2;
    ProgressDialog progressDialog;
    final Calendar myCalendar = Calendar.getInstance();
    @BindView(R.id.cusToolbar)
    Toolbar cusToolbar;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        setSupportActionBar(cusToolbar);
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));
        profilePresenter = new ProfilePresenter(getApplicationContext(), this);
        sharedPreferences = getApplicationContext().getSharedPreferences("Reg", 0);
        aPhone.setText(Shared.phone(getApplicationContext()));
        aPhone.setEnabled(false);
        aPhone.setClickable(false);
        success();
        createCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JsonObject jsonObject1 = new JsonObject();
                if (aEmail.getText().toString().isEmpty()){

                }

                jsonObject1.addProperty("table", "Customer");
                jsonObject1.addProperty("refernce", "phone_number");
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("name", aName.getText().toString().trim());

                if (!dob.getText().toString().isEmpty()){
                    jsonObject.addProperty("dob", dob.getText().toString().trim());
                }
                RadioButton gender = (RadioButton) findViewById(radioGroup1.getCheckedRadioButtonId());
                if (gender != null) {
                    jsonObject.addProperty("gender", gender.getText().toString());
                }
                jsonObject.addProperty("address", aStreet.getText().toString().trim());
                jsonObject.addProperty("city", qCity.getText().toString().trim());
                jsonObject.addProperty("state", aState.getText().toString().trim());
                jsonObject.addProperty("country", "India");
                jsonObject.addProperty("postal_code", aZipCode.getText().toString().trim());
                jsonObject.addProperty("blood_group", bloodGroup.getText().toString().trim());
                jsonObject1.add("data", jsonObject);
                if (!aEmail.getText().toString().isEmpty()){
                    if (isValidEmail(aEmail.getText().toString().trim())) {
                        jsonObject.addProperty("email", aEmail.getText().toString().trim());
                        profilePresenter.update(jsonObject1, aPhone.getText().toString());
                    }else {
                        showToast("Enter valid email id");

                    }
                } else {
                    jsonObject.addProperty("email", "");
                    profilePresenter.update(jsonObject1, aPhone.getText().toString());
                }

              //  showProgress();
            }
        });

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub

                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };
        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(Profile.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.logout) {

            showDialog(Profile.this,"Are you sure you want to logout","");

        }
        return super.onOptionsItemSelected(item);
    }
    public void showDialog(Activity activity, String title, CharSequence message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        if (title != null) builder.setTitle(title);

        builder.setMessage(message);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                getApplicationContext().getSharedPreferences("Reg", 0).edit().clear().apply();
                getApplicationContext().getSharedPreferences("Details", 0).edit().clear().apply();
                startActivity(new Intent(Profile.this, ShoppingMain.class));
                finish();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }
    private void updateLabel() {
        String myFormat = "dd/MMM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        dob.setText(sdf.format(myCalendar.getTime()));
        String myFormats = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdfs = new SimpleDateFormat(myFormats, Locale.US);
        serverDtae = sdfs.format(myCalendar.getTime());

    }

    public final static boolean isValidEmail(CharSequence target) {
        return Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    @Override
    public void showProgress() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait");
        progressDialog.show();

    }

    @Override
    public void hideProgress() {
        if (progressDialog.isShowing()){
            progressDialog.dismiss();
        }
       // progressDialog.dismiss();
    }

    @Override
    public void showToast(String message) {

        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void success() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("Id", aPhone.getText().toString().trim());
        jsonObject.addProperty("refernce", "phone_number");
        jsonObject.addProperty("table", "Customer");
        profilePresenter.showCustomer(jsonObject);
    }

    @Override
    public void failure() {

    }

    @Override
    public void shoeCustome(CusPojo.Result[] cusPojo) {
        CusPojo.Result c = cusPojo[0];

        aName.setText(c.getName() != null ? cusPojo[0].getName() : "");
        aPhone.setText(c.getPhone_number() != null ? cusPojo[0].getPhone_number() : "");
        aEmail.setText(c.getEmail() != null ? cusPojo[0].getEmail() : "");
        bloodGroup.setText(c.getBlood_group() != null ? cusPojo[0].getBlood_group() : "");
        aState.setText(c.getState() != null ? cusPojo[0].getState() : "");
        aZipCode.setText(c.getPostal_code() != null ? cusPojo[0].getPostal_code() : "");
        qCity.setText(c.getCity() != null ? cusPojo[0].getCity() : "");
        if (c.getGender() != null) {
            if (cusPojo[0].getGender().equals("Male")) {
                radioButton1.setChecked(true);
                radioButton2.setChecked(false);
            } else {
                radioButton2.setChecked(true);
                radioButton1.setChecked(false);
            }
        }
        if (cusPojo[0].getDob() != null) {
            @SuppressLint("SimpleDateFormat") SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            @SuppressLint("SimpleDateFormat") SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MMM/yyyy");
            Date date = null;
            try {
                date = inputFormat.parse(cusPojo[0].getDob());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String formattedDate = outputFormat.format(date);
            dob.setText(cusPojo[0].equals("1900-01-01T00:00:00.000Z") ? "" : formattedDate);
            Log.i("TAG", "Date of " + cusPojo[0].getDob());

        }else {
        dob.setText("");
        }

        aStreet.setText(c.getAddress() != null ? cusPojo[0].getAddress() : "");
        Shared.putDetails(cusPojo, getApplicationContext());
        progressDialog.dismiss();
    }
}
