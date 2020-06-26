package mv.hospital.Shop;

public class ProductPojo {
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

    public class ProductId {
//        private String ProductId;
//        public String getProductId() {
//            return ProductId;
//        }
    }





    public static class Result
    {
        private String Thumbimage;

        private String Largeimage;

        private String CategoryId;

        private String SEO_keywords ;

        private String Description;

        private String ProductName;

        private String ProductCode;

        private String ProductImage;

        private String SEO_Subject;

        private String ProductId;



        private String SEO_Title;

        private String Price;

        private String AddedOn;

        private String UpdatedOn;

        private String SEO_Description;

        private String status;

        private String TotalReview;

        private String rating;

        public String getTotalReview() {
            return TotalReview;
        }

        public String getRating() {
            return rating;
        }

        public String getCategoryId ()
        {
            return CategoryId;
        }

        public void setCategoryId (String CategoryId)
        {
            this.CategoryId = CategoryId;
        }


        public String getThumbnailImage() {
            return Thumbimage;
        }

        public String getSEO_keywords  ()
        {
            return SEO_keywords ;
        }

        public void setSEO_keywords  (String SEO_keywords )
        {
            this.SEO_keywords  = SEO_keywords ;
        }

        public String getDescription ()
        {
            return Description;
        }

        public String getLargeimage() {
            return Largeimage;
        }

        public void setDescription (String Description)
        {
            this.Description = Description;
        }

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

        public String getProductImage ()
        {
            return ProductImage;
        }

        public void setProductImage (String ProductImage)
        {
            this.ProductImage = ProductImage;
        }

        public String getSEO_Subject ()
        {
            return SEO_Subject;
        }

        public void setSEO_Subject (String SEO_Subject)
        {
            this.SEO_Subject = SEO_Subject;
        }

        public String getProductId() {
            return ProductId;
        }

        public String getSEO_Title ()
        {
            return SEO_Title;
        }

        public void setSEO_Title (String SEO_Title)
        {
            this.SEO_Title = SEO_Title;
        }

        public String getPrice ()
        {
            return Price;
        }

        public void setPrice (String Price)
        {
            this.Price = Price;
        }

        public String getAddedOn ()
        {
            return AddedOn;
        }

        public void setAddedOn (String AddedOn)
        {
            this.AddedOn = AddedOn;
        }

        public String getUpdatedOn ()
        {
            return UpdatedOn;
        }

        public void setUpdatedOn (String UpdatedOn)
        {
            this.UpdatedOn = UpdatedOn;
        }

        public String getSEO_Description ()
        {
            return SEO_Description;
        }

        public void setSEO_Description (String SEO_Description)
        {
            this.SEO_Description = SEO_Description;
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
            return "ClassPojo [CategoryId = "+CategoryId+", SEO_keywords  = "+SEO_keywords +", Description = "+Description+", ProductName = "+ProductName+", ProductCode = "+ProductCode+", ProductImage = "+ProductImage+", SEO_Subject = "+SEO_Subject+", ProductId = "+ProductId+", SEO_Title = "+SEO_Title+", Price = "+Price+", AddedOn = "+AddedOn+", UpdatedOn = "+UpdatedOn+", SEO_Description = "+SEO_Description+", status = "+status+"]";
        }
    }


}
