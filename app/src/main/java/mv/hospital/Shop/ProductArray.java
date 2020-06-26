package mv.hospital.Shop;

public class ProductArray {

    String name,price,description;
    int array,type;

    public ProductArray(String name,String price,int array,String description,int type){
        this.name = name;
        this.price = price;
        this.array = array;
        this.type = type;
        this.description = description;
    }


    public String getDescription() {
        return description;
    }

    public int getType() {
        return type;
    }

    public int getArray() {
        return array;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }
}
