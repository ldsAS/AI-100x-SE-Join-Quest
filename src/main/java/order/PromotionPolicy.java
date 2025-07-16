package order;

import java.util.List;

public interface PromotionPolicy {
    OrderSummary apply(List<OrderItem> items, OrderSummary currentSummary);
}
