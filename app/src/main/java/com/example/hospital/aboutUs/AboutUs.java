package com.example.hospital.aboutUs;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hospital.AllShoes.AllShoes;
import com.example.hospital.Appointment.Appointment;
import com.example.hospital.R;
import static android.graphics.text.LineBreaker.JUSTIFICATION_MODE_INTER_WORD;
public class AboutUs extends AppCompatActivity {
    Toolbar mainTool;
    TextView textView;
    @SuppressLint({"WrongConstant", "InlinedApi"})
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        textView = findViewById(R.id.textViews);
        mainTool = findViewById(R.id.aboutUstool);
        mainTool.setTitle("About Us");
        setSupportActionBar(mainTool);
        Window window =  getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(),R.color.colorAccent1));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.all_shoes,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.infoWhite){
            Intent intent = new Intent(AboutUs.this, AllShoes.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}
