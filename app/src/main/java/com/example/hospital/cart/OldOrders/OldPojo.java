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
        private String ProductName;

        private String ProductCode;

        private String ProductImageId;

        private String Color;

        private String Amount;

        private String Quantity;

        private String Thumbimage;

        private String[] ProductId;

        private String ProductPrice;

        private String ProductdiscountPrice;

        private String OrderId;

        private String ProductSize;

        private String Largeimage;

        private String CategoryName;

        private String OrderDetailsId;

        public String getProductName ()
        {
            return ProductName;
        }

        public void setProductName (String ProductName)
        {
            this.ProductName = ProductName;
        }

        public String getProductCode ()
        {
            return ProductCode;
        }

        public void setProductCode (String ProductCode)
        {
            this.ProductCode = ProductCode;
        }

        public String getProductImageId ()
        {
            return ProductImageId;
        }

        public void setProductImageId (String ProductImageId)
        {
            this.ProductImageId = ProductImageId;
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

        public String getQuantity ()
        {
            return Quantity;
        }

        public void setQuantity (String Quantity)
        {
            this.Quantity = Quantity;
        }

        public String getThumbimage ()
        {
            return Thumbimage;
        }

        public void setThumbimage (String Thumbimage)
        {
            this.Thumbimage = Thumbimage;
        }

        public String[] getProductId ()
        {
            return ProductId;
        }

        public void setProductId (String[] ProductId)
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

        public String getProductdiscountPrice ()
        {
            return ProductdiscountPrice;
        }

        public void setProductdiscountPrice (String ProductdiscountPrice)
        {
            this.ProductdiscountPrice = ProductdiscountPrice;
        }

        public String getOrderId ()
        {
            return OrderId;
        }

        public void setOrderId (String OrderId)
        {
            this.OrderId = OrderId;
        }

        public String getProductSize ()
        {
            return ProductSize;
        }

        public void setProductSize (String ProductSize)
        {
            this.ProductSize = ProductSize;
        }

        public String getLargeimage ()
        {
            return Largeimage;
        }

        public void setLargeimage (String Largeimage)
        {
            this.Largeimage = Largeimage;
        }

        public String getCategoryName ()
        {
            return CategoryName;
        }

        public void setCategoryName (String CategoryName)
        {
            this.CategoryName = CategoryName;
        }

        public String getOrderDetailsId ()
        {
            return OrderDetailsId;
        }

        public void setOrderDetailsId (String OrderDetailsId)
        {
            this.OrderDetailsId = OrderDetailsId;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [ProductName = "+ProductName+", ProductCode = "+ProductCode+", ProductImageId = "+ProductImageId+", Color = "+Color+", Amount = "+Amount+", Quantity = "+Quantity+", Thumbimage = "+Thumbimage+", ProductId = "+ProductId+", ProductPrice = "+ProductPrice+", ProductdiscountPrice = "+ProductdiscountPrice+", OrderId = "+OrderId+", ProductSize = "+ProductSize+", Largeimage = "+Largeimage+", CategoryName = "+CategoryName+", OrderDetailsId = "+OrderDetailsId+"]";
        }
    }
}
