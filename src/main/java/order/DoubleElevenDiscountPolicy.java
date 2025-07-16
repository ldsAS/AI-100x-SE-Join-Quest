package order;

import java.util.List;

public class DoubleElevenDiscountPolicy implements PromotionPolicy {
    @Override
    public OrderSummary apply(List<OrderItem> items, OrderSummary currentSummary) {
        int originalAmount = 0;
        int discount = 0;
        for (OrderItem item : items) {
            int qty = item.getQuantity();
            int unitPrice = item.getUnitPrice();
            int sets = qty / 10;
            int remain = qty % 10;
            // 每種商品各自計算滿件折扣，不可跨商品合併
            originalAmount += qty * unitPrice;
            discount += sets * 10 * unitPrice * 20 / 100;
        }
        int totalAmount = originalAmount - discount;
        List<ReceivedItem> receivedItems = currentSummary != null ? currentSummary.getReceivedItems() : null;
        if (receivedItems == null) {
            receivedItems = new java.util.ArrayList<>();
            for (OrderItem item : items) {
                receivedItems.add(new ReceivedItem(item.getProductName(), item.getQuantity()));
            }
        }
        return new OrderSummary(originalAmount, discount, totalAmount, receivedItems);
    }
}

