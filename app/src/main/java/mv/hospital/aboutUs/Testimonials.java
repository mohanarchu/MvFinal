package mv.hospital.aboutUs;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hospital.R;
import mv.hospital.base.BaseActivity;

import java.net.MalformedURLException;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Testimonials extends BaseActivity {



    Drawable[] drawables = new Drawable[17];
    @BindView(R.id.img_thumnail)
    ImageView imgThumnail;
    @BindView(R.id.iv_play_pause)
    ImageView ivPlayPause;
    @BindView(R.id.webvideo_layout2)
    RelativeLayout webvideoLayout2;
    @BindView(R.id.img_thumnails)
    ImageView imgThumnails;
    @BindView(R.id.iv_play_pauses)
    ImageView ivPlayPauses;
    @BindView(R.id.webvideo_layout22)
    RelativeLayout webvideoLayout22;

    @Override
    protected int layoutRes() {
        return R.layout.activity_testimonials;
    }

    @Override
    protected void onViewBound() {

        LinearLayoutManager linearLayoutManager = new
                LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);

        setVideo();
        setVideos();
    }

    public void setVideos() {
        try {

            //   https://youtu.be/aYXfpmniT-s
            String videoId = extractYoutubeId("http://www.youtube.com/watch?v=oRwdLV8PBV0");
            Log.e("VideoId is->", "" + videoId);
            String img_url = "http://img.youtube.com/vi/" + videoId + "/0.jpg"; // this is link which will give u thumnail image of that video
            // picasso jar file download image for u and set image in imagview

            Glide.with(getApplicationContext())
                    .load(img_url)
                    .into(imgThumnails);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void setVideo() {
        try {

            //   https://youtu.be/aYXfpmniT-s
            String videoId = extractYoutubeId("http://www.youtube.com/watch?v=aYXfpmniT-s");
            Log.e("VideoId is->", "" + videoId);
            String img_url = "http://img.youtube.com/vi/" + videoId + "/0.jpg"; // this is link which will give u thumnail image of that video
            // picasso jar file download image for u and set image in imagview

            Glide.with(getApplicationContext())
                    .load(img_url)
                    .into(imgThumnail);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }


    public String extractYoutubeId(String url) throws MalformedURLException {
        String query = new URL(url).getQuery();
        String[] param = query.split("&");
        String id = null;
        for (String row : param) {
            String[] param1 = row.split("=");
            if (param1[0].equals("v")) {
                id = param1[1];
            }
        }
        return id;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.webvideo_layout2, R.id.webvideo_layout22})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.webvideo_layout2:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=aYXfpmniT-s"));
                startActivity(browserIntent);
                break;
            case R.id.webvideo_layout22:
                Intent browserIntents = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=oRwdLV8PBV0"));
                startActivity(browserIntents);
                break;
        }
    }
}