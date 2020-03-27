package com.example.hospital.AllShoes;

import android.graphics.drawable.Drawable;

public class AllShoeArray {


    private Drawable drawable;
    private String header,description;

    public AllShoeArray (Drawable drawable,String header,String description){
        this.description = description;
        this.header = header;
        this.drawable = drawable;
    }

    public String getDescription() {
        return description;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public String getHeader() {
        return header;
    }
}
