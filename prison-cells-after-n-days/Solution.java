import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

class Solution {
	public int[] prisonAfterNDays(int[] cells, int N) {
		Map<Integer, Integer> codeToIndex = new HashMap<>();
		List<Integer> codes = new ArrayList<>();
		for (int i = 0;; ++i) {
			int code = encode(cells);

			if (codeToIndex.containsKey(code)) {
				int beginIndex = codeToIndex.get(code);
				int cycleLength = i - beginIndex;

				return decode(codes.get(findIndex(beginIndex, cycleLength, N)));
			}

			codeToIndex.put(code, i);
			codes.add(code);

			cells = change(cells);
		}
	}

	int[] change(int[] cells) {
		return IntStream.range(0, cells.length)
				.map(i -> (i != 0 && i != cells.length - 1 && cells[i - 1] == cells[i + 1]) ? 1 : 0).toArray();
	}

	int findIndex(int beginIndex, int cycleLength, int N) {
		if (N < beginIndex) {
			return N;
		} else {
			return beginIndex + (N - beginIndex) % cycleLength;
		}
	}

	int encode(int[] cells) {
		return Arrays.stream(cells).reduce(0, (result, element) -> result * 2 + element);
	}

	int[] decode(int code) {
		int[] result = new int[8];
		for (int i = result.length - 1; i >= 0; --i) {
			result[i] = code % 2;
			code /= 2;
		}

		return result;
	}
}
