package mv.hospital.cart;

public class SizePojo {
    private Result[] result;

    private String status;

    public  Result[] getResult ()
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
        private String Size;

        private String Quantity;

        private String ProductId;

        public String getSize() {
            return Size;
        }

        public void setSize(String Size) {
            this.Size = Size;
        }

        public String getQuantity() {
            return Quantity;
        }

        public void setQuantity(String Quantity) {
            this.Quantity = Quantity;
        }

        public String getProductId() {
            return ProductId;
        }

        public void setProductId(String ProductId) {
            this.ProductId = ProductId;
        }

        @Override
        public String toString() {
            return "ClassPojo [Size = " + Size + ", Quantity = " + Quantity + ", ProductId = " + ProductId + "]";
        }
    }
}
