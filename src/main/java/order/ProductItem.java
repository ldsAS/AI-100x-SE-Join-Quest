package order;

public class ProductItem extends OrderItem {
    private final String category;
    public ProductItem(String productName, String category, int quantity, int unitPrice) {
        super(productName, quantity, unitPrice);
        this.category = category;
    }
    public String getCategory() { return category; }
}
