import java.util.HashMap;
import java.util.Map;

class OrderManagementSystem {
  Map<Integer, String> orderIdToType = new HashMap<>();
  Map<Integer, Integer> orderIdToPrice = new HashMap<>();

  public void addOrder(int orderId, String orderType, int price) {
    orderIdToType.put(orderId, orderType);
    orderIdToPrice.put(orderId, price);
  }

  public void modifyOrder(int orderId, int newPrice) {
    orderIdToPrice.put(orderId, newPrice);
  }

  public void cancelOrder(int orderId) {
    orderIdToType.remove(orderId);
    orderIdToPrice.remove(orderId);
  }

  public int[] getOrdersAtPrice(String orderType, int price) {
    return orderIdToType.keySet().stream()
        .filter(
            orderId ->
                orderIdToType.get(orderId).equals(orderType)
                    && orderIdToPrice.get(orderId) == price)
        .mapToInt(Integer::intValue)
        .toArray();
  }
}

// Your OrderManagementSystem object will be instantiated and called as such:
// OrderManagementSystem obj = new OrderManagementSystem();
// obj.addOrder(orderId,orderType,price);
// obj.modifyOrder(orderId,newPrice);
// obj.cancelOrder(orderId);
// int[] param_4 = obj.getOrdersAtPrice(orderType,price);
