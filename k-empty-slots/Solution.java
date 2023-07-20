import java.util.NavigableSet;
import java.util.TreeSet;

public class Solution {
  public int kEmptySlots(int[] flowers, int k) {
    NavigableSet<Integer> slots = new TreeSet<>();
    for (int i = 0; i < flowers.length; i++) {
      Integer lowerSlot = slots.lower(flowers[i]);
      if (lowerSlot != null && flowers[i] - lowerSlot - 1 == k) {
        return i + 1;
      }

      Integer higherSlot = slots.higher(flowers[i]);
      if (higherSlot != null && higherSlot - flowers[i] - 1 == k) {
        return i + 1;
      }

      slots.add(flowers[i]);
    }
    return -1;
  }
}
