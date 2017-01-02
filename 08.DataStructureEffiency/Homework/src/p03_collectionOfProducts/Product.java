package p03_collectionOfProducts;

public class Product {
    private String id;
    private String title;
    private String supplier;
    private double price;

    public Product(String id, String title, String supplier, double price) {
        this.setId(id);
        this.setTitle(title);
        this.setSupplier(supplier);
        this.setPrice(price);
    }

    public String getId() {
        return id;
    }

    private void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    private void setTitle(String title) {
        this.title = title;
    }

    public String getSupplier() {
        return supplier;
    }

    private void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public double getPrice() {
        return price;
    }

    private void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return this.getId() + " " + this.getTitle() + " "  + this.getSupplier() + " " + getPrice();
    }
}
