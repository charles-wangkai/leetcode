import java.util.SortedSet;
import java.util.TreeSet;

class SmallestInfiniteSet {
  int minConsecutive = 1;
  SortedSet<Integer> rest = new TreeSet<>();

  public int popSmallest() {
    int result;
    if (!rest.isEmpty()) {
      result = rest.first();
      rest.remove(result);
    } else {
      result = minConsecutive;
      ++minConsecutive;
    }

    return result;
  }

  public void addBack(int num) {
    if (num < minConsecutive) {
      rest.add(num);
    }
  }
}

// Your SmallestInfiniteSet object will be instantiated and called as such:
// SmallestInfiniteSet obj = new SmallestInfiniteSet();
// int param_1 = obj.popSmallest();
// obj.addBack(num);
