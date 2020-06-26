package mv.hospital.cart;

public class ColorPojo {
    private Result[] result;

    private String status;

    public Result[] getResult ()
    {
        return result;
    }

    public void setResult ( Result[] result)
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

    public class Result {
        private String ProductId;

        private String ColorName;

        public String getProductId() {
            return ProductId;
        }

        public void setProductId(String ProductId) {
            this.ProductId = ProductId;
        }

        public String getColorName() {
            return ColorName;
        }

        public void setColorName(String ColorName) {
            this.ColorName = ColorName;
        }

        @Override
        public String toString() {
            return "ClassPojo [ProductId = " + ProductId + ", ColorName = " + ColorName + "]";
        }
    }
}
