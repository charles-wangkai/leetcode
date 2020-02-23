import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class Cashier {
    int n;
    int discount;
    Map<Integer, Integer> pidToPrice = new HashMap<>();
    int count = 0;

    public Cashier(int n, int discount, int[] products, int[] prices) {
        this.n = n;
        this.discount = discount;

        for (int i = 0; i < products.length; ++i) {
            pidToPrice.put(products[i], prices[i]);
        }
    }

    public double getBill(int[] product, int[] amount) {
        ++count;

        return IntStream.range(0, product.length).map(i -> pidToPrice.get(product[i]) * amount[i]).sum() / 100.0
                * (100 - ((count % n == 0) ? discount : 0));
    }
}

// Your Cashier object will be instantiated and called as such:
// Cashier obj = new Cashier(n, discount, products, prices);
// double param_1 = obj.getBill(product,amount);