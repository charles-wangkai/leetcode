import java.util.OptionalInt;
import java.util.stream.IntStream;

class Allocator {
  int[] units;

  public Allocator(int n) {
    units = new int[n];
  }

  public int allocate(int size, int mID) {
    int[] allocated =
        IntStream.rangeClosed(-1, units.length)
            .filter(i -> i == -1 || i == units.length || units[i] != 0)
            .toArray();
    OptionalInt first =
        IntStream.range(0, allocated.length - 1)
            .filter(i -> allocated[i + 1] - allocated[i] - 1 >= size)
            .findFirst();
    if (first.isEmpty()) {
      return -1;
    }

    for (int i = 0; i < size; ++i) {
      units[allocated[first.getAsInt()] + i + 1] = mID;
    }

    return allocated[first.getAsInt()] + 1;
  }

  public int free(int mID) {
    int result = 0;
    for (int i = 0; i < units.length; ++i) {
      if (units[i] == mID) {
        units[i] = 0;
        ++result;
      }
    }

    return result;
  }
}

// Your Allocator object will be instantiated and called as such:
// Allocator obj = new Allocator(n);
// int param_1 = obj.allocate(size,mID);
// int param_2 = obj.free(mID);
