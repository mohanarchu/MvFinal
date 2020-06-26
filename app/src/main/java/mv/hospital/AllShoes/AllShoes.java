package mv.hospital.AllShoes;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hospital.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AllShoes extends AppCompatActivity {


    public ArrayList<AllShoeArray> allShoeArrays;
    @BindView(R.id.allshoesRecycler)
    RecyclerView allshoesRecycler;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_shoes);
        ButterKnife.bind(this);
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent1));
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        allShoeArrays = new ArrayList<>();

        allShoeArrays.add(new AllShoeArray(
                getDrawable(R.drawable.asset1), "light weight", "Easy on the feet."));
        allShoeArrays.add(new AllShoeArray(
                getDrawable(R.drawable.asset2), "Special Insole",
                "Designed for people with foot problems like loss of sensation called neuropathy pain and numbness in the feet."));
        allShoeArrays.add(new AllShoeArray(
                getDrawable(R.drawable.asset3), "Shock Absorbing Sole", "Helps in preventing injuries by reducing" +
                " stress on your joints and soft tissues."));
        allShoeArrays.add(new AllShoeArray(
                getDrawable(R.drawable.asset4), "SOFT FABRIC", "For a comfortable fit."));
        allShoeArrays.add(new AllShoeArray(
                getDrawable(R.drawable.asset5), "AIR POCKETS", "Allows your feet to breathe."));
        allShoeArrays.add(new AllShoeArray(
                getDrawable(R.drawable.asset6), "Comfort lining", "Reduces the strain on your heel."));
        allShoeArrays.add(new AllShoeArray(
                getDrawable(R.drawable.asset7), "Foam padding", "Provides cushioning and comfort."));
        allShoeArrays.add(new AllShoeArray(
                getDrawable(R.drawable.asset8), "Padded Collar", "Eliminates pressure points."));


        allshoesRecycler.setLayoutManager(layoutManager);
        allshoesRecycler.setAdapter(new ShoesAdapter());


    }

    public class ShoesAdapter extends RecyclerView.Adapter<ViewHolder> {



        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(getApplicationContext()).
                    inflate(R.layout.allshoe_design, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

            holder.itemView.getLayoutParams().height = getHeight()/4;
            holder.allShoesDes.setText(allShoeArrays.get(position).getDescription());
            holder.allshoesHeader.setText(allShoeArrays.get(position).getHeader());
            holder.allshoesImage.setImageDrawable(allShoeArrays.get(position).getDrawable());

        }

        @Override
        public int getItemCount() {
            return allShoeArrays.size();
        }
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.allshoesImage)
        ImageView allshoesImage;
        @BindView(R.id.allshoesHeader)
        TextView allshoesHeader;
        @BindView(R.id.allShoesDes)
        TextView allShoesDes;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }


    public  int getHeight(){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        return height;
    }
}
