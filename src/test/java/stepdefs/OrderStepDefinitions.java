package stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import io.cucumber.datatable.DataTable;
import static org.junit.jupiter.api.Assertions.*;

import order.OrderItem;
import order.OrderService;
import order.OrderSummary;
import order.ReceivedItem;
import order.ProductItem;
import order.PromotionPolicy;
import order.ThresholdDiscountPolicy;
import order.BogoCosmeticsPolicy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderStepDefinitions {
    private OrderService orderService;
    private List<OrderItem> orderItems;
    private OrderSummary orderSummary;

    @Given("no promotions are applied")
    public void no_promotions_are_applied() {
        orderService = new OrderService();
    }

    @Given("the threshold discount promotion is configured:")
    public void the_threshold_discount_promotion_is_configured(DataTable table) {
        Map<String, String> config = table.asMaps(String.class, String.class).get(0);
        int threshold = Integer.parseInt(config.get("threshold"));
        int discount = Integer.parseInt(config.get("discount"));
        orderService = new OrderService();
        orderService.addPromotionPolicy(new ThresholdDiscountPolicy(threshold, discount));
    }

    @Given("the buy one get one promotion for cosmetics is active")
    public void the_bogo_promotion_for_cosmetics_is_active() {
        if (orderService == null) orderService = new OrderService();
        orderService.addPromotionPolicy(new BogoCosmeticsPolicy());
    }

    @When("a customer places an order with:")
    public void a_customer_places_an_order_with(DataTable table) {
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);
        orderItems = new ArrayList<>();
        for (Map<String, String> row : rows) {
            String productName = row.get("productName");
            int quantity = Integer.parseInt(row.get("quantity"));
            int unitPrice = Integer.parseInt(row.get("unitPrice"));
            if (row.containsKey("category")) {
                String category = row.get("category");
                orderItems.add(new ProductItem(productName, category, quantity, unitPrice));
            } else {
                orderItems.add(new OrderItem(productName, quantity, unitPrice));
            }
        }
        orderSummary = orderService.createOrder(orderItems);
    }



    @And("the customer should receive:")
    public void the_customer_should_receive(DataTable table) {
        List<Map<String, String>> expected = table.asMaps(String.class, String.class);
        List<ReceivedItem> received = orderSummary.getReceivedItems();
        assertEquals(expected.size(), received.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i).get("productName"), received.get(i).getProductName());
            assertEquals(Integer.parseInt(expected.get(i).get("quantity")), received.get(i).getQuantity());
        }
    }

}
