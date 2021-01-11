class Solution {
  public void merge(int nums1[], int m, int nums2[], int n) {
    int indexA = m - 1;
    int indexB = n - 1;
    for (int i = m + n - 1; i >= 0; --i) {
      if (indexA >= 0 && (indexB < 0 || nums1[indexA] >= nums2[indexB])) {
        nums1[i] = nums1[indexA];
        --indexA;
      } else {
        nums1[i] = nums2[indexB];
        --indexB;
      }
    }
  }
}
