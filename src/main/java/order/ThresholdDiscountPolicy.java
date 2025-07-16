package order;

import java.util.List;

public class ThresholdDiscountPolicy implements PromotionPolicy {
    private final int threshold;
    private final int discount;

    public ThresholdDiscountPolicy(int threshold, int discount) {
        this.threshold = threshold;
        this.discount = discount;
    }

    @Override
    public OrderSummary apply(List<OrderItem> items, OrderSummary currentSummary) {
        int originalAmount = currentSummary != null ? currentSummary.getOriginalAmount() : 0;
        if (originalAmount == 0) {
            for (OrderItem item : items) {
                originalAmount += item.getQuantity() * item.getUnitPrice();
            }
        }
        int appliedDiscount = (originalAmount >= threshold) ? discount : 0;
        int totalAmount = originalAmount - appliedDiscount;
        return new OrderSummary(originalAmount, appliedDiscount, totalAmount, currentSummary.getReceivedItems());
    }
}
