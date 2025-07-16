package stepdefs;

import io.cucumber.java.zh_cn.假如;
import io.cucumber.java.zh_cn.当;
import io.cucumber.java.zh_cn.那么;
import io.cucumber.java.zh_cn.并且;
import static org.junit.jupiter.api.Assertions.*;
import io.cucumber.datatable.DataTable;

public class DoubleElevenStepDefinitions {
    private order.OrderService orderService;
    private java.util.List<order.OrderItem> orderItems;
    private order.OrderSummary orderSummary;
    private int unitPrice;

    @假如("雙十一優惠活動已啟用")
    public void enable_double_eleven_promotion() {
        orderService = new order.OrderService();
        orderService.addPromotionPolicy(new order.DoubleElevenDiscountPolicy());
        orderItems = new java.util.ArrayList<>();
    }

    @并且("一件商品單價為 {int}")
    public void set_product_price(int price) {
        this.unitPrice = price;
    }

    @并且("我的購物車內有 {string}，單價 {int} 元，數量 {int} 件")
    public void add_cart_item(String name, int price, int qty) {
        orderItems.add(new order.OrderItem(name, qty, price));
    }

    @当("我結帳")
    public void checkout() {
        orderSummary = orderService.createOrder(orderItems);
    }

    @当("我購買了 {int} 件該商品並結帳")
    public void buy_and_checkout(int qty) {
        orderItems.clear();
        orderItems.add(new order.OrderItem("商品", qty, unitPrice));
        orderSummary = orderService.createOrder(orderItems);
    }

    @那么("訂單總金額應為 {int}")
    public void assert_total_amount(int expectedTotal) {
        assertNotNull(orderSummary);
        assertEquals(expectedTotal, orderSummary.getTotalAmount());
    }
}
