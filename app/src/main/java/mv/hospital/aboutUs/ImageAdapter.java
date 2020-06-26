package mv.hospital.aboutUs;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hospital.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Context.WINDOW_SERVICE;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.Holder> {


    Context context;

    Drawable[] drawables;

    public ImageAdapter(Context context, Drawable[] drawables) {
        this.context = context;
        this.drawables = drawables;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.image_view, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        holder.itemView.getLayoutParams().height = (int) (getScreenWidth() / 2);

        holder.setImages(drawables[position]);



    }

    @Override
    public int getItemCount() {
        return drawables.length ;
    }

    class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.productImage)
        ImageView productImage;
        public Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }


        public void setImages(Drawable drawable) {
            productImage.setImageDrawable(drawable);
        }
    }
    public int getScreenWidth() {
        WindowManager wm = (WindowManager) context.getSystemService(WINDOW_SERVICE);
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        return height;
    }
    public int getScreenWidths() {
        WindowManager wm = (WindowManager) context.getSystemService(WINDOW_SERVICE);
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        return width;
    }
}
