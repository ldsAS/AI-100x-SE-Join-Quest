public class OrderItem {
    private final String productName;
    private final int quantity;
    private final int unitPrice;

    public OrderItem(String productName, int quantity, int unitPrice) {
        this.productName = productName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public int getSubtotal() {
        return quantity * unitPrice;
    }
}
