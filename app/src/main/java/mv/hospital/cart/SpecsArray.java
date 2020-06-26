package mv.hospital.cart;

import android.graphics.drawable.Drawable;

public class SpecsArray {

    Drawable drawable;
    String text;
    public SpecsArray(Drawable drawable,String text){
        this.drawable = drawable;
        this.text =  text;
    }

    public String getText() {
        return text;
    }

    public Drawable getDrawable() {
        return drawable;
    }
}
