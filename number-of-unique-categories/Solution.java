import java.util.ArrayList;
import java.util.List;

// Definition for a category handler.
abstract class CategoryHandler {
  CategoryHandler(int[] categories) {}

  public abstract boolean haveSameCategory(int a, int b);
}

class Solution {
  public int numberOfCategories(int n, CategoryHandler categoryHandler) {
    List<Integer> uniqueCategories = new ArrayList<>();
    for (int i = 0; i < n; ++i) {
      int i_ = i;
      if (uniqueCategories.stream()
          .allMatch(category -> !categoryHandler.haveSameCategory(category, i_))) {
        uniqueCategories.add(i);
      }
    }

    return uniqueCategories.size();
  }
}
