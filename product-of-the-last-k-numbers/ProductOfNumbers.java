import java.util.ArrayList;
import java.util.List;

class ProductOfNumbers {
  private List<Integer> prefixNonZeroProducts;
  private List<Integer> prefixZeroNums;

  ProductOfNumbers() {
    prefixNonZeroProducts = new ArrayList<>();
    prefixNonZeroProducts.add(1);

    prefixZeroNums = new ArrayList<>();
    prefixZeroNums.add(0);
  }

  public void add(int num) {
    if (num == 0) {
      prefixNonZeroProducts.add(1);
      prefixZeroNums.add(getLast(prefixZeroNums, 1) + 1);
    } else {
      prefixNonZeroProducts.add(getLast(prefixNonZeroProducts, 1) * num);
      prefixZeroNums.add(getLast(prefixZeroNums, 1));
    }
  }

  public int getProduct(int k) {
    return getLast(prefixZeroNums, 1) == getLast(prefixZeroNums, k + 1)
        ? (getLast(prefixNonZeroProducts, 1) / getLast(prefixNonZeroProducts, k + 1))
        : 0;
  }

  private int getLast(List<Integer> list, int d) {
    return list.get(list.size() - d);
  }
}

// Your ProductOfNumbers object will be instantiated and called as such:
// ProductOfNumbers obj = new ProductOfNumbers();
// obj.add(num);
// int param_2 = obj.getProduct(k);
