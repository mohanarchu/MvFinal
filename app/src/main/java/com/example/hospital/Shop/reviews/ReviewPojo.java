package com.example.hospital.Shop.reviews;

import com.example.hospital.cart.pojo.ProductCommomPojo;

public class ReviewPojo {

    private ProductCommomPojo.ProductReview[] result;

    private String status;

    public ProductCommomPojo.ProductReview[] getResult ()
    {
        return result;
    }

    public void setResult (ProductCommomPojo.ProductReview[] result)
    {
        this.result = result;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [result = "+result+", status = "+status+"]";
    }
}
