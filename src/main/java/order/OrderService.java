package order;

import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private final List<PromotionPolicy> promotionPolicies = new ArrayList<>();

    public void addPromotionPolicy(PromotionPolicy policy) {
        promotionPolicies.add(policy);
    }

    public void clearPromotionPolicies() {
        promotionPolicies.clear();
    }

    public OrderSummary createOrder(List<OrderItem> items) {
        OrderSummary summary = null;
        for (PromotionPolicy policy : promotionPolicies) {
            summary = policy.apply(items, summary);
        }
        // 若沒有任何促銷，預設計算原始金額與收到商品
        if (summary == null) {
            int originalAmount = 0;
            List<ReceivedItem> receivedItems = new ArrayList<>();
            for (OrderItem item : items) {
                originalAmount += item.getQuantity() * item.getUnitPrice();
                receivedItems.add(new ReceivedItem(item.getProductName(), item.getQuantity()));
            }
            summary = new OrderSummary(originalAmount, 0, originalAmount, receivedItems);
        }
        return summary;
    }
}


