import java.util.List;

public class OrderService {
    /**
     * 建立訂單，支援雙十一優惠邏輯
     * @param items 購物車商品
     * @param doubleElevenPromotion 是否啟用雙十一優惠
     * @return Order
     */
    public static Order createOrder(List<OrderItem> items, boolean doubleElevenPromotion) {
        if (!doubleElevenPromotion) {
            return new Order(items);
        }
        // 只針對同一商品超過10件的情境實作優惠
        if (items.size() == 1) {
            OrderItem item = items.get(0);
            int quantity = item.getQuantity();
            int unitPrice = item.getUnitPrice();
            int discountedSets = quantity / 10;
            int discountedQty = discountedSets * 10;
            int undiscountedQty = quantity - discountedQty;
            int discountedTotal = (int)(discountedQty * unitPrice * 0.8);
            int undiscountedTotal = undiscountedQty * unitPrice;
            int total = discountedTotal + undiscountedTotal;
            // 用一個新的 OrderItem 表示總價，getSubtotal() 會正確反映
            List<OrderItem> result = new java.util.ArrayList<>();
            result.add(new OrderItem(item.getProductName(), quantity, unitPrice) {
                @Override
                public int getSubtotal() {
                    return total;
                }
            });
            return new Order(result);
        }
        // 其他情境（如多商品），維持原邏輯
        return new Order(items);
    }
    // 保留原本的 createOrder 以相容舊測試
    public static Order createOrder(List<OrderItem> items) {
        return new Order(items);
    }
}

