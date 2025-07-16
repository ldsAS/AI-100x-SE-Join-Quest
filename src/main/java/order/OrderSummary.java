package order;

import java.util.List;

public class OrderSummary {
    private final int originalAmount;
    private final int discount;
    private final int totalAmount;
    private final List<ReceivedItem> receivedItems;

    public OrderSummary(int originalAmount, int discount, int totalAmount, List<ReceivedItem> receivedItems) {
        this.originalAmount = originalAmount;
        this.discount = discount;
        this.totalAmount = totalAmount;
        this.receivedItems = receivedItems;
    }

    public int getOriginalAmount() { return originalAmount; }
    public int getDiscount() { return discount; }
    public int getTotalAmount() { return totalAmount; }
    public List<ReceivedItem> getReceivedItems() { return receivedItems; }
}

