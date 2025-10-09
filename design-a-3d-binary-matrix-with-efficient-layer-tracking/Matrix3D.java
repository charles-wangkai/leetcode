import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

class Matrix3D {
  boolean[][][] matrix;
  int[] oneNums;
  SortedMap<Integer, SortedSet<Integer>> oneNumToXs;

  public Matrix3D(int n) {
    matrix = new boolean[n][n][n];

    oneNums = new int[n];

    oneNumToXs = new TreeMap<>();
    oneNumToXs.put(0, new TreeSet<>());
    for (int x = 0; x < n; ++x) {
      oneNumToXs.get(0).add(x);
    }
  }

  public void setCell(int x, int y, int z) {
    if (!matrix[x][y][z]) {
      matrix[x][y][z] = true;

      removeFromOneNumToXs(x, oneNums[x]);
      ++oneNums[x];
      addToOneNumToXs(x, oneNums[x]);
    }
  }

  public void unsetCell(int x, int y, int z) {
    if (matrix[x][y][z]) {
      matrix[x][y][z] = false;

      removeFromOneNumToXs(x, oneNums[x]);
      --oneNums[x];
      addToOneNumToXs(x, oneNums[x]);
    }
  }

  public int largestMatrix() {
    return oneNumToXs.lastEntry().getValue().last();
  }

  void addToOneNumToXs(int x, int oneNum) {
    oneNumToXs.putIfAbsent(oneNum, new TreeSet<>());
    oneNumToXs.get(oneNum).add(x);
  }

  void removeFromOneNumToXs(int x, int oneNum) {
    oneNumToXs.get(oneNum).remove(x);

    if (oneNumToXs.get(oneNum).isEmpty()) {
      oneNumToXs.remove(oneNum);
    }
  }
}

// Your Matrix3D object will be instantiated and called as such:
// Matrix3D obj = new Matrix3D(n);
// obj.setCell(x,y,z);
// obj.unsetCell(x,y,z);
// int param_3 = obj.largestMatrix();
