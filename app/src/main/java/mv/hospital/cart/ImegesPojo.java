package mv.hospital.cart;

public class ImegesPojo {
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
        private String ProductImageId;

        private String Largeimage;

        private String Thumbimage;

        private String ProductId;

        public String getProductImageId ()
        {
            return ProductImageId;
        }

        public void setProductImageId (String ProductImageId)
        {
            this.ProductImageId = ProductImageId;
        }

        public String getLargeimage ()
        {
            return Largeimage;
        }

        public void setLargeimage (String Largeimage)
        {
            this.Largeimage = Largeimage;
        }

        public String getThumbimage ()
        {
            return Thumbimage;
        }

        public void setThumbimage (String Thumbimage)
        {
            this.Thumbimage = Thumbimage;
        }

        public String getProductId ()
        {
            return ProductId;
        }

        public void setProductId (String ProductId)
        {
            this.ProductId = ProductId;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [ProductImageId = "+ProductImageId+", Largeimage = "+Largeimage+", Thumbimage = "+Thumbimage+", ProductId = "+ProductId+"]";
        }
    }

}
