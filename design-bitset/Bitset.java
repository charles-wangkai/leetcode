import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Bitset {
  int[] bits;
  boolean flipped;
  int oneCount;

  public Bitset(int size) {
    bits = new int[size];
  }

  public void fix(int idx) {
    int target = flipped ? 0 : 1;
    if (bits[idx] != target) {
      bits[idx] = target;

      if (target == 1) {
        ++oneCount;
      } else {
        --oneCount;
      }
    }
  }

  public void unfix(int idx) {
    int target = flipped ? 1 : 0;
    if (bits[idx] != target) {
      bits[idx] = target;

      if (target == 1) {
        ++oneCount;
      } else {
        --oneCount;
      }
    }
  }

  public void flip() {
    flipped = !flipped;
  }

  public boolean all() {
    return (flipped && oneCount == 0) || (!flipped && oneCount == bits.length);
  }

  public boolean one() {
    return (flipped && oneCount != bits.length) || (!flipped && oneCount != 0);
  }

  public int count() {
    return flipped ? (bits.length - oneCount) : oneCount;
  }

  public String toString() {
    return IntStream.range(0, bits.length)
        .mapToObj(i -> String.valueOf(flipped ? (1 - bits[i]) : bits[i]))
        .collect(Collectors.joining());
  }
}

// Your Bitset object will be instantiated and called as such:
// Bitset obj = new Bitset(size);
// obj.fix(idx);
// obj.unfix(idx);
// obj.flip();
// boolean param_4 = obj.all();
// boolean param_5 = obj.one();
// int param_6 = obj.count();
// String param_7 = obj.toString();
