package com.example.hospital.cart.OldOrders;

public class OldPojo {

    private Result[] result;

    private String status;

    public Result[] getResult ()
    {
        return result;
    }

    public void setResult (Result[] result)
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

    public class Result
    {
        private String Description;

        private String ProductSize;

        private String ProductName;

        private String UserId;

        private String ReviewId;

        private String ProductImage;

        private String Color;

        private String Amount;

        private String Rating;

        private String Quantity;

        private String ProductId;

        private String ProductPrice;

        private String OrderDate;

        public String getProductSize ()
        {
            return ProductSize;
        }

        public String getDescription() {
            return Description;
        }

        public void setProductSize (String ProductSize)
        {
            this.ProductSize = ProductSize;
        }

        public String getProductName ()
        {
            return ProductName;
        }

        public String getReviewId() {
            return ReviewId;
        }

        public void setProductName (String ProductName)
        {
            this.ProductName = ProductName;
        }

        public String getUserId ()
        {
            return UserId;
        }

        public void setUserId (String UserId)
        {
            this.UserId = UserId;
        }

        public String getProductImage ()
        {
            return ProductImage;
        }

        public void setProductImage (String ProductImage)
        {
            this.ProductImage = ProductImage;
        }

        public String getColor ()
        {
            return Color;
        }

        public void setColor (String Color)
        {
            this.Color = Color;
        }

        public String getAmount ()
        {
            return Amount;
        }

        public void setAmount (String Amount)
        {
            this.Amount = Amount;
        }

        public String getRating ()
        {
            return Rating;
        }

        public void setRating (String Rating)
        {
            this.Rating = Rating;
        }

        public String getQuantity ()
        {
            return Quantity;
        }

        public void setQuantity (String Quantity)
        {
            this.Quantity = Quantity;
        }

        public String getProductId ()
        {
            return ProductId;
        }

        public void setProductId (String ProductId)
        {
            this.ProductId = ProductId;
        }

        public String getProductPrice ()
        {
            return ProductPrice;
        }

        public void setProductPrice (String ProductPrice)
        {
            this.ProductPrice = ProductPrice;
        }

        public String getOrderDate ()
        {
            return OrderDate;
        }

        public void setOrderDate (String OrderDate)
        {
            this.OrderDate = OrderDate;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [ProductSize = "+ProductSize+", ProductName = "+ProductName+", UserId = "+UserId+", ProductImage = "+ProductImage+", Color = "+Color+", Amount = "+Amount+", Rating = "+Rating+", Quantity = "+Quantity+", ProductId = "+ProductId+", ProductPrice = "+ProductPrice+", OrderDate = "+OrderDate+"]";
        }
    }


}
