import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
  public List<Integer> toggleLightBulbs(List<Integer> bulbs) {
    Set<Integer> ons = new HashSet<>();
    for (int bulb : bulbs) {
      if (ons.contains(bulb)) {
        ons.remove(bulb);
      } else {
        ons.add(bulb);
      }
    }

    return ons.stream().sorted(Comparator.naturalOrder()).toList();
  }
}