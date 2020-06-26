package mv.hospital.ProductDB;

public class proLocalArray {


    public proLocalArray()
    {

    }

    String id,name,code,size,quantity,color,price,serial,totalAmount,imageURL;
    String[] sizeArray,ColorArray;
    public proLocalArray(String id,String name,String code,
                         String size,String color,String quantity,String price,String[] sizeArray,String[] colorArray,String serial,String totalAmount,String imageUrl)
    {
        this.id = id;
        this.name = name;
        this.code = code;
        this.color = color;
        this.quantity = quantity;
        this.size = size;
        this.price = price;
        this.sizeArray =sizeArray;
        this.ColorArray = colorArray;
        this.totalAmount = totalAmount;
        this.serial = serial;
        this.imageURL = imageUrl;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String[] getColorArray() {
        return ColorArray;
    }

    public String[] getSizeArray() {
        return sizeArray;
    }

    public void setColorArray(String[] colorArray) {
        ColorArray = colorArray;
    }

    public void setSizeArray(String[] sizeArray) {
        this.sizeArray = sizeArray;
    }

    public String getCode() {
        return code;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getSize() {
        return size;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }



    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }


}
