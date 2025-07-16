package order;

import java.util.ArrayList;
import java.util.List;

public class BogoCosmeticsPolicy implements PromotionPolicy {
    @Override
    public OrderSummary apply(List<OrderItem> items, OrderSummary currentSummary) {
        List<ReceivedItem> receivedItems = new ArrayList<>();
        for (OrderItem item : items) {
            if (item instanceof ProductItem) {
                ProductItem p = (ProductItem) item;
                if ("cosmetics".equalsIgnoreCase(p.getCategory())) {
                    receivedItems.add(new ReceivedItem(item.getProductName(), item.getQuantity() * 2));
                    continue;
                }
            }
            receivedItems.add(new ReceivedItem(item.getProductName(), item.getQuantity()));
        }
        int originalAmount = 0;
        for (OrderItem item : items) {
            originalAmount += item.getQuantity() * item.getUnitPrice();
        }
        int appliedDiscount = currentSummary != null ? currentSummary.getDiscount() : 0;
        int totalAmount = originalAmount - appliedDiscount;
        return new OrderSummary(originalAmount, appliedDiscount, totalAmount, receivedItems);
    }
}
