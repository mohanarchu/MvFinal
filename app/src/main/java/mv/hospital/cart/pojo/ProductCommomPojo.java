package mv.hospital.cart.pojo;

public class ProductCommomPojo {
    private Result[] result;

    private ProductReview[] productReview;

    private String status;

    public Result[] getResult ()
    {
        return result;
    }

    public void setResult (Result[] result)
    {
        this.result = result;
    }

    public ProductReview[] getProductReview ()
    {
        return productReview;
    }

    public void setProductReview (ProductReview[] productReview)
    {
        this.productReview = productReview;
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
        return "ClassPojo [result = "+result+", productReview = "+productReview+", status = "+status+"]";
    }
    public class ProductReview
    {
        private String ProductReview;

        private String ReviewDate;

        private String UserId;

        private String Reply;

        private String ReplyDate;

        private String Rating;

        private String ProductId;

        private String id;

        private String UserName;

        public String getUserName() {
            return UserName;
        }

        public String getDescription ()
        {
            return ProductReview;
        }

        public void setDescription (String Description)
        {
            this.ProductReview = Description;
        }

        public String getReviewDate ()
        {
            return ReviewDate;
        }

        public void setReviewDate (String ReviewDate)
        {
            this.ReviewDate = ReviewDate;
        }

        public String getUserId ()
        {
            return UserId;
        }

        public void setUserId (String UserId)
        {
            this.UserId = UserId;
        }


        public String getReply() {
            return Reply;
        }

        public String getReplyDate() {
            return ReplyDate;
        }

        public String getRating ()
        {
            return Rating;
        }

        public void setRating (String Rating)
        {
            this.Rating = Rating;
        }

        public String getProductId ()
        {
            return ProductId;
        }

        public void setProductId (String ProductId)
        {
            this.ProductId = ProductId;
        }

        public String getId ()
        {
            return id;
        }

        public void setId (String id)
        {
            this.id = id;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [Description = "+ProductReview+", ReviewDate = "+ReviewDate+", UserId = "+UserId+", Rating = "+Rating+", ProductId = "+ProductId+", id = "+id+"]";
        }
    }
    public class Result
    {
        private String CategoryId;

        private String Description;

        private String Colors;

        private String Price;

        private String Size;

        private String TotalReviews;

        private String ProductId;

        private String Image;


        private String ProductName;

        private String UserRating;

        private String ReviewId;

        private String ProductCode;

        private String OrderCount;

        private String UserDescription;
        public String getUserRating() {
            return UserRating;
        }

        public String getProductCode() {
            return ProductCode;
        }

        public String getUserDescription() {
            return UserDescription;
        }

        public String getOrderCount() {
            return OrderCount;
        }

        public String getProductName() {
            return ProductName;
        }
        public String getCategoryId ()
        {
            return CategoryId;
        }


        public void setCategoryId (String CategoryId)
        {
            this.CategoryId = CategoryId;
        }

        public String getDescription ()
        {
            return Description;
        }

        public void setDescription (String Description)
        {
            this.Description = Description;
        }

        public String getColors ()
        {
            return Colors;
        }

        public void setColors (String Colors)
        {
            this.Colors = Colors;
        }

        public String getPrice ()
        {
            return Price;
        }

        public void setPrice (String Price)
        {
            this.Price = Price;
        }

        public String getSize ()
        {
            return Size;
        }

        public void setSize (String Size)
        {
            this.Size = Size;
        }

        public String getTotalReviews ()
        {
            return TotalReviews;
        }

        public void setTotalReviews (String TotalReviews)
        {
            this.TotalReviews = TotalReviews;
        }

        public String getProductId ()
        {
            return ProductId;
        }

        public void setProductId (String ProductId)
        {
            this.ProductId = ProductId;
        }

        public String getImage ()
        {
            return Image;
        }

        public void setImage (String Image)
        {
            this.Image = Image;
        }

        public String getReviewId() {
            return ReviewId;
        }



        @Override
        public String toString()
        {
            return "ClassPojo [CategoryId = "+CategoryId+", Description = "+Description+", Colors = "+Colors+", Price = "+Price+", Size = "+Size+", TotalReviews = "+TotalReviews+", ProductId = "+ProductId+", Image = "+Image+"]";
        }
    }




}
