class Solution {
  public int wateringPlants(int[] plants, int capacity) {
    int result = 0;
    int rest = capacity;
    for (int i = 0; i < plants.length; ++i) {
      if (plants[i] <= rest) {
        ++result;
        rest -= plants[i];
      } else {
        result += i * 2 + 1;
        rest = capacity - plants[i];
      }
    }

    return result;
  }
}
