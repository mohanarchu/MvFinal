package com.example.hospital.aboutUs;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
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
import com.example.hospital.Register.Login;
import com.example.hospital.Shop.Sopping;
import com.example.hospital.cart.Cart;
import com.example.hospital.profile.Profile;
import com.example.hospital.profile.Shared;

import static android.graphics.text.LineBreaker.JUSTIFICATION_MODE_INTER_WORD;

public class Blog extends AppCompatActivity {

    Toolbar mainTool;
    LinearLayout founder;
    LinearLayout showBlog,showBlogs;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog);

        founder = findViewById(R.id.aboutFounder);
        mainTool = findViewById(R.id.blogTool);
        showBlog = findViewById(R.id.showBlog);
        showBlogs = findViewById(R.id.showBlogs);
        showBlogs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new  Intent(Blog.this,BlogView.class);
                intent.putExtra("key",1);
                startActivity(intent);

               // startActivity(new Intent(Blog.this,BlogView.class));
            }

        });
        showBlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new  Intent(Blog.this,BlogView.class);
                intent.putExtra("key",2);
                startActivity(intent);
                //startActivity(new Intent(Blog.this,BlogView.class));
            }

        });
        mainTool.setTitle("Blogs");

        setSupportActionBar(mainTool);
        Window window =  getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(),R.color.colorAccent1));
        founder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Founder founder = new Founder();
                founder.show(getSupportFragmentManager(),"TAG");
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.all_shoes,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.infoWhite){
            Intent intent = new Intent(Blog.this, AllShoes.class);
            //  intent.putExtra("key",1);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }


}
