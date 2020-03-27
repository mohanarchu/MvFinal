package com.example.hospital.aboutUs;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.example.hospital.R;
public class BlogView extends AppCompatActivity {


    LinearLayout factVisibe,breakVisibble;
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_view);


        factVisibe = findViewById(R.id.factVisible);
        breakVisibble = findViewById(R.id.breakVisibble);
        Window window =  getWindow();

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(),R.color.colorAccent));
        if (getIntent().getIntExtra("key",0) == 1){
            factVisibe.setVisibility(View.VISIBLE);
        }else {
            breakVisibble.setVisibility(View.VISIBLE);
        }
    }
}
