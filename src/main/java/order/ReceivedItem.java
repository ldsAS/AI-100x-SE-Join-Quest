package order;

public class ReceivedItem {
    private final String productName;
    private final int quantity;

    public ReceivedItem(String productName, int quantity) {
        this.productName = productName;
        this.quantity = quantity;
    }

    public String getProductName() { return productName; }
    public int getQuantity() { return quantity; }
}
