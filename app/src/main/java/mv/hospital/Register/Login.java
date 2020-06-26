package mv.hospital.Register;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.hospital.R;
import com.google.gson.JsonObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Login extends AppCompatActivity implements LoginView{


    CardView cardView;
    @BindView(R.id.PhoneNumverValidate)
    EditText PhoneNumverValidate;
    @BindView(R.id.passWord)
    EditText passWord;
    @BindView(R.id.doLogin)
    CardView doLogin;
    LoginPresent loginPresent;
    ProgressDialog progressDialog;
    int key;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        loginPresent = new LoginPresent(getApplicationContext(),this);
        PhoneNumverValidate = findViewById(R.id.PhoneNumverValidate);
        if (getIntent().getExtras() != null){
            key = getIntent().getIntExtra("key",0);
        }else {
            key = 0;
        }

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));
        cardView = findViewById(R.id.doLogin);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(Login.this, OtpView.class);
//                intent.putExtra("mobileNumber",PhoneNumverValidate.getText().toString());
//                startActivity(intent);
          //        startActivity(new Intent(Login.this, OtpView.class));
              if (PhoneNumverValidate.getText().toString().equals("")){
                  showToast("Enter your mobile number");
              }else if (!isValid(PhoneNumverValidate.getText().toString())){
                  showToast("Enter valid mobile number");
              }else {
                  JsonObject jsonObject1 = new JsonObject();
                  jsonObject1.addProperty("phone_number", PhoneNumverValidate.getText().toString().trim());
                  JsonObject jsonObject = new JsonObject();
                  jsonObject.add("data",jsonObject1);
                  jsonObject.addProperty("table", "Customer");
                  loginPresent.getLogin(jsonObject);
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
    public void showProgress() {
      progressDialog = new ProgressDialog(this);
      progressDialog.setMessage("Please wait");
      progressDialog.show();

    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
    }

    @Override
    public void doRegister(JsonObject jsonObject) {
       loginPresent.doRegister(jsonObject);
    }

    @Override
    public void doLogin(String otp) {
        Intent intent = new Intent(this, OtpView.class);
        intent.putExtra("key",key);
        intent.putExtra("mobileNumber",PhoneNumverValidate.getText().toString());
        startActivity(intent);
        finish();
    }
}
