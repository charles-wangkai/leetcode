// This is MountainArray's API interface.
// You should not implement it, or speculate about its implementation
interface MountainArray {
  public int get(int index);

  public int length();
}

class Solution {
  public int findInMountainArray(int target, MountainArray mountainArr) {
    int topIndex = findTop(mountainArr);

    int leftIndex = findInAscending(target, mountainArr, 0, topIndex);
    if (leftIndex >= 0) {
      return leftIndex;
    }

    return findInDescending(target, mountainArr, topIndex + 1, mountainArr.length() - 1);
  }

  int findTop(MountainArray mountainArr) {
    int topIndex = -1;
    int lowerIndex = 1;
    int upperIndex = mountainArr.length() - 2;
    while (lowerIndex <= upperIndex) {
      int middleIndex = (lowerIndex + upperIndex) / 2;
      if (mountainArr.get(middleIndex) > mountainArr.get(middleIndex - 1)) {
        topIndex = middleIndex;
        lowerIndex = middleIndex + 1;
      } else {
        upperIndex = middleIndex - 1;
      }
    }

    return topIndex;
  }

  int findInAscending(int target, MountainArray mountainArr, int beginIndex, int endIndex) {
    if (beginIndex > endIndex) {
      return -1;
    }

    int middleIndex = (beginIndex + endIndex) / 2;
    int middleValue = mountainArr.get(middleIndex);

    if (middleValue < target) {
      return findInAscending(target, mountainArr, middleIndex + 1, endIndex);
    }
    if (middleValue > target) {
      return findInAscending(target, mountainArr, beginIndex, middleIndex - 1);
    }

    return middleIndex;
  }

  int findInDescending(int target, MountainArray mountainArr, int beginIndex, int endIndex) {
    if (beginIndex > endIndex) {
      return -1;
    }

    int middleIndex = (beginIndex + endIndex) / 2;
    int middleValue = mountainArr.get(middleIndex);

    if (middleValue < target) {
      return findInDescending(target, mountainArr, beginIndex, middleIndex - 1);
    }
    if (middleValue > target) {
      return findInDescending(target, mountainArr, middleIndex + 1, endIndex);
    }

    return middleIndex;
  }
}
