package mv.hospital;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.hospital.R;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;


public class Splash extends AppCompatActivity {

    @BindView(R.id.gifRepeatImage)
    GifImageView gifRepeatImage;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
        window.setNavigationBarColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
        ImageView imageView = (ImageView) findViewById(R.id.imageToVisioble);
//        Intent mainIntent = new Intent(Splash.this, MainActivity.class);
//        Splash.this.startActivity(mainIntent);
//        Splash.this.finish();
        try {
            pl.droidsonroids.gif.GifDrawable drawable = new GifDrawable( getResources(),R.drawable.splash_gif);
            drawable.setLoopCount(1);
            gifRepeatImage.setImageDrawable(drawable);
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                imageView.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent mainIntent = new Intent(Splash.this, MainActivity.class);
                        Splash.this.startActivity(mainIntent);
                        Splash.this.finish();
                    }
                }, 1500);
            }
        }, 2400);
    }
}
