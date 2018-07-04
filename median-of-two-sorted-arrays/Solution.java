import java.util.Arrays;

public class Solution {
	public double findMedianSortedArrays(int A[], int B[]) {
		int totalLength = A.length + B.length;
		if (totalLength % 2 == 0) {
			return (findKthElement(A, 0, A.length - 1, B, 0, B.length - 1,
					totalLength / 2) + findKthElement(A, 0, A.length - 1, B, 0,
					B.length - 1, totalLength / 2 + 1)) / 2.0;
		} else {
			return findKthElement(A, 0, A.length - 1, B, 0, B.length - 1,
					totalLength / 2 + 1);
		}
	}

	int findKthElement(int[] A, int beginIndexA, int endIndexA, int[] B,
			int beginIndexB, int endIndexB, int k) {
		if (beginIndexA > endIndexA) {
			return B[beginIndexB + (k - 1)];
		}
		if (beginIndexB > endIndexB) {
			return A[beginIndexA + (k - 1)];
		}

		int middleIndexA = (beginIndexA + endIndexA) / 2;
		int foundIndexB = Arrays.binarySearch(B, beginIndexB, endIndexB + 1,
				A[middleIndexA]);
		if (foundIndexB < 0) {
			foundIndexB = -1 - foundIndexB - 1;
		}

		if (middleIndexA == endIndexA && foundIndexB == endIndexB) {
			if (k == 1 + (endIndexB - beginIndexB + 1)) {
				return A[middleIndexA];
			} else {
				return B[beginIndexB + (k - 1)];
			}
		}

		if (k <= (middleIndexA - beginIndexA + 1)
				+ (foundIndexB - beginIndexB + 1)) {
			return findKthElement(A, beginIndexA, middleIndexA, B, beginIndexB,
					foundIndexB, k);
		} else {
			return findKthElement(A, middleIndexA + 1, endIndexA, B,
					foundIndexB + 1, endIndexB, k
							- (middleIndexA - beginIndexA + 1)
							- (foundIndexB - beginIndexB + 1));
		}
	}
}
