import java.util.Collections;
import java.util.List;

public class Order {
    private final List<OrderItem> items;

    public Order(List<OrderItem> items) {
        this.items = Collections.unmodifiableList(items);
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public int getTotalAmount() {
        return items.stream().mapToInt(OrderItem::getSubtotal).sum();
    }
}
