import java.util.List;

class ProductOfNumbers {
    private List<Integer> prefixNonZeroProducts = new ArrayList<>();
    private List<Integer> prefixZeroNums = new ArrayList<>();

    ProductOfNumbers() {
        prefixNonZeroProducts.add(1);
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
        if (!getLast(prefixZeroNums, 1).equals(getLast(prefixZeroNums, k + 1).intValue())) {
            return 0;
        }

        return getLast(prefixNonZeroProducts, 1) / getLast(prefixNonZeroProducts, k + 1);
    }

    private <T> T getLast(List<T> list, int k) {
        return list.get(list.size() - k);
    }
}

// Your ProductOfNumbers object will be instantiated and called as such:
// ProductOfNumbers obj = new ProductOfNumbers();
// obj.add(num);
// int param_2 = obj.getProduct(k);