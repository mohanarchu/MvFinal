package mv.hospital.Appointment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.Layout;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import mv.hospital.Appointment.model.BranchModel;
import mv.hospital.MainActivity;
import mv.hospital.Order.OrderPresenter;
import mv.hospital.Order.OrderView;
import com.hospital.R;
import com.google.gson.JsonObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BookApponement extends AppCompatActivity implements OrderView , BookingPresenter.BookingView {


    Spinner myBranch;
    ArrayList<String> branches;

    String[] rooms = {"--Select--", "Deluxe AC Room", "General ward", "Non-Ac Single Room", "VIp Suite Room", "Sharing Room"};
    String[] pickUp = {"--Select--", "AIRPORT-DOMESTIC", "AIRPORT-INTERNATIONAL", "AIRPORT-CENTRAL", "RAILWAY-EGMORE", "CMBP Koyambedu", "Koyambedu omni"};
    @BindView(R.id.appoinmentTool)
    Toolbar appoinmentTool;
    @BindView(R.id.doctorName)
    EditText doctorName;
    @BindView(R.id.apoointmentDate2)
    TextView apoointmentDate2;
    @BindView(R.id.cWaving)
    RadioButton cWaving;
    @BindView(R.id.aPatentFromGroup2)
    RadioButton aPatentFromGroup2;
    @BindView(R.id.aPatentFromGroup1)
    RadioGroup aPatentFromGroup1;
    @BindView(R.id.aPatentFromGroup)
    LinearLayout aPatentFromGroup;
    @BindView(R.id.aAppintmentType1)
    RadioButton aAppintmentType1;
    @BindView(R.id.aAppintmentType2)
    RadioButton aAppintmentType2;
    @BindView(R.id.aAppintmentType)
    RadioGroup aAppintmentType;

    @BindView(R.id.aRequiredGroup1)
    RadioButton aRequiredGroup1;
    @BindView(R.id.aRequiredGroup2)
    RadioButton aRequiredGroup2;
    @BindView(R.id.aRequiredGroup)
    RadioGroup aRequiredGroup;

    @BindView(R.id.roomTypespinner)
    Spinner roomTypespinner;
    @BindView(R.id.roomTypeSpiinnerView)
    LinearLayout roomTypeSpiinnerView;
    @BindView(R.id.pickupSpinner)
    Spinner pickupSpinner;
    RadioButton adminnsionRequiredRadio, appointmentTypeRadio, patientFromRdaio;
    String appintmentType = "OP", patientFrom = "Outstation", adminnisonRequired = "No",
            roomType = rooms[0], pickupType = pickUp[0];
    OrderPresenter orderPresenter;
    @BindView(R.id.saveAppointment)
    CardView saveAppointment;
    DatePickerDialog datePickerDialog;
    Calendar calendar;
    @BindView(R.id.appointmentDate1)
    TextView appointmentDate1;
    @BindView(R.id.branchesSpinner)
    Spinner branchSpinner;
    private int year, month, day;
    String date1,date2 = "";
    ProgressDialog progressDialog;
    BookingPresenter bookingPresenter;

    String patientId = "";
    String branchId = "",branchName = "";
    private String appointmentN0;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_apponement);
        ButterKnife.bind(this);
        aAppintmentType1.setChecked(true);
        aPatentFromGroup2.setChecked(true);
        aRequiredGroup2.setChecked(true);
        appoinmentTool = findViewById(R.id.appoinmentTool);
        appoinmentTool.setTitle("E-Appointments");
        orderPresenter = new OrderPresenter(getApplicationContext(), this);
        bookingPresenter = new BookingPresenter(getApplicationContext(),this);
        setSupportActionBar(appoinmentTool);
        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp);
        upArrow.setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        roomTypeSpiinnerView.setVisibility(View.GONE);
        window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent1));
        aRequiredGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                // find the radiobutton by returned id
                adminnsionRequiredRadio = (RadioButton) findViewById(selectedId);
                adminnisonRequired = adminnsionRequiredRadio.getText().toString();
                if (adminnisonRequired.equals("Yes")) {
                    roomTypeSpiinnerView.setVisibility(View.VISIBLE);
                } else if (adminnisonRequired.equals("No")) {
                    roomTypeSpiinnerView.setVisibility(View.GONE);
                }
            }
        });


        aAppintmentType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int selectedId = radioGroup.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                appointmentTypeRadio = (RadioButton) findViewById(selectedId);

                appintmentType = appointmentTypeRadio.getText().toString();


            }
        });

        aPatentFromGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int selectedId = radioGroup.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                patientFromRdaio = (RadioButton) findViewById(selectedId);

                patientFrom = patientFromRdaio.getText().toString();


            }
        });
        ArrayAdapter aa = new ArrayAdapter(BookApponement.this, android.R.layout.simple_spinner_item, rooms);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        roomTypespinner.setAdapter(aa);
        roomTypespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                roomType = rooms[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        ArrayAdapter aas = new ArrayAdapter(BookApponement.this,
                android.R.layout.simple_spinner_item, pickUp);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        pickupSpinner.setAdapter(aas);
        pickupSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                pickupType = pickUp[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        appointmentDate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDate1();
            }
        });
        apoointmentDate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               setDate2();
            }
        });
        setCalander();
        bookingPresenter.getBranches();
        Intent intent = getIntent();
        if (intent != null) {
            patientId = intent.getStringExtra("patientId");
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    void setCalander() {
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        //   showDate();


    }
    @Override
    public void showProgress() {
        progressDialog = new ProgressDialog(BookApponement.this);
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
    public void showBranches(BranchModel.Result[] branches) {
        ArrayList<String> branch = new ArrayList<>();
        for (int i=0;i<branches.length;i++) {
            branch.add(branches[i].getBranch());
        }
        branchId =  branches[0].getBranchId();
        ArrayAdapter aa = new ArrayAdapter(BookApponement.this, android.R.layout.simple_spinner_item, branch);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        branchSpinner.setAdapter(aa);
        branchSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                branchName = branches[position].getBranch();
                branchId =  branches[position].getBranchId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void showAppNumbers(String RegistrationId, String Appointmentid) {
        saveAppointment(Appointmentid);
    }

    @Override
    public void mailSuccess() {
        showDialogue();
    }


    @SuppressLint("NewApi")
    void showDialogue() {
        AlertDialog alertDialog = new AlertDialog.Builder(BookApponement.this).create();
        alertDialog.setMessage(Html.fromHtml("<font color='#FF7F27'>"+
                getResources().getString(R.string.appintment_message)+"</font>"));
        alertDialog.setCancelable(false);
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                (dialog, which) -> {
                    startActivity(new Intent(BookApponement.this, MainActivity.class));
                    finish();
        });
        alertDialog.show();
        TextView textView = (TextView) alertDialog.findViewById(android.R.id.message);
        textView.setGravity(Gravity.CENTER);
        textView.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
        Button button1 = alertDialog.findViewById(android.R.id.button1);
        button1.setTextSize(20);
        textView.setTextSize(18.0f);
    }

    @Override
    public void sucess(String id) {
       sendEmail();
       showDialogue();
    }

    @Override
    public void placed() {
         showDialogue();
    }
    void sendEmail(){

        JsonObject jsonObject = new JsonObject();
        Intent intent = getIntent();
        jsonObject.addProperty("name",intent.getStringExtra("name"));
        jsonObject.addProperty("gender",intent.getStringExtra("gender"));
        jsonObject.addProperty("age",intent.getStringExtra("age"));
        jsonObject.addProperty("registrationId",intent.getStringExtra("regId"));
        jsonObject.addProperty("nationality",intent.getStringExtra("Nationality"));
        jsonObject.addProperty("address",intent.getStringExtra("Address"));
        jsonObject.addProperty("city",intent.getStringExtra("City"));
        jsonObject.addProperty("state",intent.getStringExtra("State"));
        jsonObject.addProperty("country","India");
        jsonObject.addProperty("pincode",intent.getStringExtra("Pincode"));
        jsonObject.addProperty("mobileNumber",intent.getStringExtra("Mobile"));
        jsonObject.addProperty("email",intent.getStringExtra("Email"));
        jsonObject.addProperty("dno","");
        jsonObject.addProperty("doctotName",doctorName.getText().toString());
        jsonObject.addProperty("appoinmentNo",appointmentN0);
        jsonObject.addProperty("fax","");
        jsonObject.addProperty("branchName",branchName);
        jsonObject.addProperty("appoinmentDate",appointmentDate1.getText().toString());
        jsonObject.addProperty("patientfrom",patientFrom);
        jsonObject.addProperty("appointmentType",appintmentType);
        jsonObject.addProperty("roomType",roomType.equals("--Select--") ? "" :
                roomType);
        jsonObject.addProperty("pickupDetails",pickupSpinner.getSelectedItem().toString().equals("--Select--") ? ""
                : pickupSpinner.getSelectedItem().toString());
        jsonObject.addProperty("origin","");
        jsonObject.addProperty("timeArrival","");
        jsonObject.addProperty("timeArrival","");
        jsonObject.addProperty("arrivalPlace","");
        bookingPresenter.sendMail(jsonObject);

    }


    public void setDate1() {
        datePickerDialog = new DatePickerDialog(BookApponement.this, validDatelistener, calendar
                .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        datePickerDialog.show();
    }


    public void setDate2() {
        datePickerDialog = new DatePickerDialog(BookApponement.this, memoDateListener, calendar
                .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        datePickerDialog.show();
    }

    private DatePickerDialog.OnDateSetListener memoDateListener = (arg0, arg1, arg2, arg3) -> {
        showDate(arg1, arg2 + 1, arg3);
    };

    private DatePickerDialog.OnDateSetListener validDatelistener = (arg0, arg1, arg2, arg3) -> {
        showDates(arg1, arg2 + 1, arg3);
    };

    private void showDates(int year, int month, int day) {
        appointmentDate1.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
        date1 = String.valueOf(new StringBuilder().append(day).append("-")
                .append(month).append("-").append(year));
    }

    private void showDate(int year, int month, int day) {
        apoointmentDate2.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
        date2 = String.valueOf(new StringBuilder().append(day).append("-")
                .append(month).append("-").append(year));
    }

    void saveAppointment(String appointmentNumber) {
        this.appointmentN0 = "AP"+ (Integer.parseInt(appointmentNumber) + 1);
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS",
                Locale.getDefault()).format(new Date());
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("RequestDate", date);
        jsonObject.addProperty("IsPickup",
                !pickupSpinner.getSelectedItem().toString().equals(pickUp[0]) ? 1 : 0);
        jsonObject.addProperty("Patientfrom", patientFrom.equals("Chennai") ?
                String.valueOf(1) : String.valueOf(2));
        jsonObject.addProperty("IsAdmission", adminnisonRequired.equals("Yes") ? 1 : 0);
       // jsonObject.addProperty("RoomType", roomType.equals("--Select--") ? "" : roomType);
        jsonObject.addProperty("DoctorName", doctorName.getText().toString());
        jsonObject.addProperty("Appointmenttype",appintmentType );
        jsonObject.addProperty("Date1", getDate(date1));
        jsonObject.addProperty("Date2", getDate(date2));
        jsonObject.addProperty("UserId",patientId);
        jsonObject.addProperty("BranchId",branchId);
        jsonObject.addProperty("Status",1);
        jsonObject.addProperty("AppoinmentNo","AP"+ (Integer.parseInt(appointmentNumber) + 1));
        jsonObject.addProperty("AppoinmentId",Integer.parseInt(appointmentNumber) + 1);
        JsonObject jsonObject1 = new JsonObject();
        jsonObject1.add("data", jsonObject);
        jsonObject1.addProperty("table", "Appoinments");
        jsonObject1.addProperty("multipleInsert", false);
        orderPresenter.makeOrder(jsonObject1);
    }

    @OnClick(R.id.saveAppointment)
    public void onViewClicked() {

        if (appointmentDate1.getText().toString().isEmpty()) {
            showToast("Choose appointment date");
        }  else if ( doctorName.getText().toString().isEmpty()) {
            showToast("Enter doctor name");
        }  else{
          bookingPresenter.getAppointmentNumber();
        }
    }
    public static String getDate(String hr) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdfs = new SimpleDateFormat("dd-MM-yyyy");
        Date dt;
        try {
            dt = sdfs.parse(hr);
            System.out.println("Time Display: " + sdf.format(dt));
            return  sdf.format(dt);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }
}
