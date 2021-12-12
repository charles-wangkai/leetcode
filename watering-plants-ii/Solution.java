class Solution {
  public int minimumRefill(int[] plants, int capacityA, int capacityB) {
    int result = 0;
    int restA = capacityA;
    int restB = capacityB;
    for (int i = 0, j = plants.length - 1; i <= j; ++i, --j) {
      if (i != j) {
        if (restA < plants[i]) {
          restA = capacityA;
          ++result;
        }
        restA -= plants[i];

        if (restB < plants[j]) {
          restB = capacityB;
          ++result;
        }
        restB -= plants[j];
      } else if (restA >= restB) {
        if (restA < plants[i]) {
          restA = capacityA;
          ++result;
        }
        restA -= plants[i];
      } else {
        if (restB < plants[j]) {
          restB = capacityB;
          ++result;
        }
        restB -= plants[j];
      }
    }

    return result;
  }
}